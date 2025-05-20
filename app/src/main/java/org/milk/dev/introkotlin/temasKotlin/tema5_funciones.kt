package org.joel.bonedead.introkotlin.temasKotlin

fun saludo(){
    return println("hola desde saludo")
}

fun suma(x:Int, y:Int):Int{
    return  x + y
}

fun suma2(x:Int, y:Int):Int = x + y

fun main() {
    saludo()
    println("/////////////////////////////////////////////////////////////")

    println(suma(2,4))
    println(suma2(2,4))

}
