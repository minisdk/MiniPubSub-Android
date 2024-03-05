package com.pj.pubsub.extensions
import com.google.protobuf.ByteString
import com.pj.pubsub.proto.NativePubSub

typealias Container = NativePubSub.Container

fun ContainerBuilder() : NativePubSub.Container.Builder {
    return Container.newBuilder()
}

fun NativePubSub.Container.Builder.add(key: String, value: Boolean){
    this.putBooleans(key, value)
}
fun NativePubSub.Container.Builder.add(key: String, value: Int){
    this.putIntegers(key, value)
}
fun NativePubSub.Container.Builder.add(key: String, value: Float){
    this.putFloats(key, value)
}
fun NativePubSub.Container.Builder.add(key: String, value: String){
    this.putStrings(key, value)
}
fun NativePubSub.Container.Builder.add(key: String, value: ByteArray){
    this.putBytes(key, ByteString.copyFrom(value))
}
fun NativePubSub.Container.Builder.add(key: String, value: NativePubSub.Container){
    this.putContainers(key, value)
}

fun NativePubSub.Container.getBoolean(key: String) : Boolean?{
    return this.booleansMap[key]
}
fun NativePubSub.Container.getInt(key: String) : Int?{
    return this.integersMap[key]
}
fun NativePubSub.Container.getFloat(key: String) : Float?{
    return this.floatsMap[key]
}
fun NativePubSub.Container.getString(key: String) : String?{
    return this.stringsMap[key]
}
fun NativePubSub.Container.getBytes(key: String) : ByteArray?{
    return this.bytesMap[key]?.toByteArray()
}
fun NativePubSub.Container.getContainer(key: String) : NativePubSub.Container?{
    return this.containersMap[key]
}