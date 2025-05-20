package org.joel.bonedead.introkotlin.temasKotlin

fun main() {
    for (number in 1 .. 5){
        println(number)
    }

    val nombre = listOf("joel", "ezequiel", "rodriguez", "briones")
    for (nombre in nombre){
        println(nombre)
    }

    // while
    var numerox = 0
    while (numerox < 5){
        println(numerox)
        numerox++
    }
    do {
        println(numerox)
        numerox++
    }while (numerox < 5)

}