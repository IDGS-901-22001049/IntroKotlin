package org.joel.bonedead.introkotlin.temasKotlin

// hacer la formula general con lo que ingrese el usuario
fun main() {
    var correcto: Boolean

    do {
        correcto = true

        println("Ingresa el valor de a:")
        val a = readln().toDouble()
        if (a == 0.0) {
            println("El valor de 'a' no puede ser 0. No es una ecuación de segundo grado.")
            correcto = false
            continue
        }

        println("Ingresa el valor de b:")
        val b = readln().toDouble()

        println("Ingresa el valor de c:")
        val c = readln().toDouble()

        val discriminante = (b * b) - (4 * a * c)

        if (discriminante < 0) {
            println("La ecuación no tiene soluciones reales (el discriminante es negativo).")
            correcto = false
        } else {
            val resultado1 = (-b + Math.sqrt(discriminante)) / (2 * a)
            val resultado2 = (-b - Math.sqrt(discriminante)) / (2 * a)

            println("El resultado x1 es: $resultado1")
            println("El resultado x2 es: $resultado2")
        }

    } while (!correcto)
}



