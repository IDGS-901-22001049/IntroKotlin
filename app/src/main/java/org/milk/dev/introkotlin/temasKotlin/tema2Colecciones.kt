package org.joel.bonedead.introkotlin.temasKotlin

fun main() {
    // list, mutableList
    // set, mutableset
    // map, mutablemap

    var lista = listOf("lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo")
    println(lista)
    println("el primer dia de la semana es ${lista[0]}") // podemos acceder al elemento por su indice

    println("el primer dia de la semana es ${lista.get(0)} con get")
    println("el primer dia de la semana es ${lista.first()} con first")
    println("el largo de la lista es ${lista.count()}")
    println("el largo de la lista es ${lista.size} con size")
    println("martes" in lista)
    println("martes" !in lista)

    var figuras:MutableList<String> = mutableListOf("Circulo", "Cuadrado", "Triangulo")
    println(figuras)
    figuras.add("Pentagono") // para agregar algo con add
    println(figuras)
    figuras.removeAt(0) // basado en el indice con At
    println(figuras)
    figuras.remove("Cuadrado") // basado en el valor si el at

    // ---------------------------------------------------------------------------------------------
    println("maps")
    var mapFrutas = mapOf("manzana" to 100, "naranja" to 200, "fresa" to 300)
    println(mapFrutas)
    println(mapFrutas["manzana"])
    println(mapFrutas.get("naranja"))
    println(mapFrutas.keys) // los puros key del map
    println(mapFrutas.values) // los puros values del map


}