package org.joel.bonedead.introkotlin.temasKotlin

fun main() {
    val d: Int
    val e = true

    if(e){
        d = 10
    }else{
        d = 20
    }
    println(d)
    val numero = if(e) 10 else 20
    println(numero)
    println("Ingrese el sueldo del empleado")
    val sueldo = readln().toDouble()
    if (sueldo > 3000 ){
        println("Pasa contacto")
    }else{
        println("LERO LERO JAJJAJAJAJAJAJAJA")
    }

    val objeto:Any = "hola"
    when(objeto){
        "1"-> println("es uno")
        "hola"-> println("es hola")
        is String-> println("es un string")
        is Long-> println("es un long")
        else-> println("no es nada")
    }
    // range
    1..4// 1234

    4 downTo 1// 4321
    1 .. 10 step 2// 13579
    'z' downTo 'a' step 2

}