package com.pj.pubsub

class Tag private constructor(name: String, id: ULong) {
    companion object {
        private val tagMap:MutableMap<ULong, Tag> = mutableMapOf()
        private val namedTagMap:MutableMap<String, Tag> = mutableMapOf()
        private var idBase = 0b1uL
        private fun generate() : ULong{
            var result = idBase
            idBase = idBase shl 1
            return result
        }

        val none = namedWithID("None", 0b0uL)
        val game = named("Game")
        val native = named("Native")

        fun named(name: String): Tag{
            val tag = namedTagMap.getOrPut(name){
                val tag = Tag(name)
                tagMap[tag.id] = tag
                tag
            }
            return tag
        }

        fun named(names: Array<String>): Tag{
            var joined = 0b0uL
            names.forEach {name ->
                val named = Tag.named(name)
                joined = joined or named.id
            }
            return cached(joined)
        }

        private fun cached(id: ULong): Tag{
            val tag = tagMap.getOrPut(id){
                Tag(id)
            }
            return tag
        }

        private fun namedWithID(name: String, id: ULong): Tag{
            val tag = tagMap.getOrPut(id){
                val tag = Tag(name, id)
                namedTagMap[name] = tag
                tag
            }
            return tag
        }

        fun join(tag1: Tag, tag2: Tag): Tag{
            val joined = tag1.id or tag2.id
            return cached(joined)
        }

        fun join(vararg tags: Tag): Tag{
            var joined = 0b0uL
            for(tag in tags){
                joined = joined or tag.id
            }
            return cached(joined)
        }
    }

    private val id: ULong
    val name: String

    init {
        this.id = id
        this.name = name
    }

    val names: Array<String> by lazy {
        val joinedNames = mutableListOf<String>()
        var findID = 0b1uL
        while(findID != 0b0uL){
            if(contains(findID)){
                val cached = tagMap[findID]
                if(cached != null)
                    joinedNames.add(cached.name)
            }
            findID = findID shl 1
        }
        joinedNames.toTypedArray()
    }

    private constructor(id: ULong) : this(id.toString(), id)
    private constructor(name: String) : this(name, Tag.generate())

    private fun contains(id: ULong): Boolean{
        return this.id and id == id
    }

    fun join(tag: Tag): Tag{
        return Tag.join(this, tag)
    }

    fun contains(tag: Tag):Boolean{
        return contains(tag.id)
    }
    fun except(tag: Tag): Boolean{
        return this.id and tag.id == 0b0uL
    }
}
