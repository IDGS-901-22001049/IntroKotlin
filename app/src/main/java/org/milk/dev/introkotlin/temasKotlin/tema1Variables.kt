package org.joel.bonedead.introkotlin.temasKotlin

fun main(){
    println("Hola mundo")

    val nombre = "Joel" // variables de solo lectura
    var apellido:String = "Briones" // permite tener una variable mutable

    println(nombre + " " + apellido)

    println("Hola $nombre $apellido") // forma de concatenar variables

    var num1 = 10
    println("la suma de $num1 + 2 en igual a ${num1 + 2}" ) // los corchetes sirven para concatenar operaciones
    num1 = num1 + 3
    num1+=4
    num1++
    println(num1)

    var sueldo:Float=12.25f
    println(sueldo)
    var sueldo2:Double=12.25
    println(sueldo2)
    var mayorEdad:Boolean=true
    var estadoCivil:Char='S'

}