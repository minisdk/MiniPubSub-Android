package com.pj.core

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

        val none = Tag("None")
        val game = Tag("Game")
        val native = Tag("Native")

        fun create(name: String) : Tag{
            val tag = namedTagMap.getOrPut(name){
                val tag = Tag(name)
                tagMap[tag.id] = tag
                tag
            }
            return tag
        }

        fun join(tag1: Tag, tag2: Tag): Tag{
            val joined = tag1.id or tag2.id
            val tag = tagMap.getOrPut(joined){
                Tag(joined)
            }
            return tag
        }

        fun join(vararg tags: Tag): Tag{
            var joined = 0b0uL
            for(tag in tags){
                joined = joined or tag.id
            }
            val tag = tagMap.getOrPut(joined){
                Tag(joined)
            }
            return tag
        }
    }
    private val id: ULong
    private val name: String

    init {
        this.id = id
        this.name = name
    }
    private constructor(id: ULong) : this(id.toString(), id)
    private constructor(name: String) : this(name, Tag.generate())

    fun contains(tag: Tag):Boolean{
        return this.id and tag.id == tag.id
    }
    fun except(tag: Tag): Boolean{
        return this.id and tag.id == 0b0uL
    }
}
