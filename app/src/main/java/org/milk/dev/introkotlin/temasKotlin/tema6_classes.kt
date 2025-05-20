package org.joel.bonedead.introkotlin.temasKotlin

class usuarios(){
    val materia: String = ""
}

class usuario2(val id:Int, val nombre:String){
    val materia: String = ""
    fun hola(){
        println("hola")
    }
}

fun main() {
    val alumno = usuarios()
    val alumno2 = usuario2(1, "joel")
    println(alumno2.id)
    println(alumno2.nombre)
    alumno2.hola()
}