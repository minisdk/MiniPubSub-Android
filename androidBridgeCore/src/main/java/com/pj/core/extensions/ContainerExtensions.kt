package com.pj.core.extensions
import com.google.protobuf.ByteString
import com.pj.core.proto.NativeBridge

typealias Container = NativeBridge.Container

fun ContainerBuilder() : NativeBridge.Container.Builder {
    return Container.newBuilder()
}

fun NativeBridge.Container.Builder.add(key: String, value: Boolean){
    this.putBooleans(key, value)
}
fun NativeBridge.Container.Builder.add(key: String, value: Int){
    this.putIntegers(key, value)
}
fun NativeBridge.Container.Builder.add(key: String, value: Float){
    this.putFloats(key, value)
}
fun NativeBridge.Container.Builder.add(key: String, value: String){
    this.putStrings(key, value)
}
fun NativeBridge.Container.Builder.add(key: String, value: ByteArray){
    this.putBytes(key, ByteString.copyFrom(value))
}
fun NativeBridge.Container.Builder.add(key: String, value: NativeBridge.Container){
    this.putContainers(key, value)
}

fun NativeBridge.Container.getBoolean(key: String) : Boolean?{
    return this.booleansMap[key]
}
fun NativeBridge.Container.getInt(key: String) : Int?{
    return this.integersMap[key]
}
fun NativeBridge.Container.getFloat(key: String) : Float?{
    return this.floatsMap[key]
}
fun NativeBridge.Container.getString(key: String) : String?{
    return this.stringsMap[key]
}
fun NativeBridge.Container.getBytes(key: String) : ByteArray?{
    return this.bytesMap[key]?.toByteArray()
}
fun NativeBridge.Container.getContainer(key: String) : NativeBridge.Container?{
    return this.containersMap[key]
}