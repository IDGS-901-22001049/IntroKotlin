package org.milk.dev.introkotlin.ejemplo1

fun main() {
    var opcion: Int

    do {
        println("\n--- Calculo de areas ---")
        println("1. Area de un circulo")
        println("2. Area de un cuadrado")
        println("3. Area de un triangulo")
        println("4. Area de un pentagono")
        println("5. Salir")
        print("Elige una opcion: ")

        // si el null por defecto sera -1
        // el simbolo ? declara que el valor puede ser nulo
        opcion = readLine()?.toIntOrNull() ?: -1

        when (opcion) {
            1 -> {
                print("Ingresa el radio del circulo: ")
                val radio = readLine()?.toDoubleOrNull()
                if (radio != null && radio > 0) {
                    val area = Circulo(radio).calcularArea()
                    println("Area del circulo: %.2f".format(area))
                } else {
                    println("Dato invalido. Debe ser mayor 0.")
                }
            }

            2 -> {
                print("Ingresa el lado del cuadrado: ")
                val lado = readLine()?.toDoubleOrNull()
                if (lado != null && lado > 0) {
                    val area = Cuadrado(lado).calcularArea()
                    println("Area del cuadrado: %.2f".format(area))
                } else {
                    println("Dato invalido. Debe ser mayor 0.")
                }
            }

            3 -> {
                print("Ingresa la base del triangulo: ")
                val base = readLine()?.toDoubleOrNull()
                print("Ingresa la altura del triangulo: ")
                val altura = readLine()?.toDoubleOrNull()
                if (base != null && altura != null && base > 0 && altura > 0) {
                    val area = Triangulo(base, altura).calcularArea()
                    println("Area del triangulo: %.2f".format(area))
                } else {
                    println("Datos invalidos. Ambos deben mayores  0.")
                }
            }

            4 -> {
                print("Ingresa el perimetro del pentagono: ")
                val perimetro = readLine()?.toDoubleOrNull()
                print("Ingresa el apotema del pentagono: ")
                val apotema = readLine()?.toDoubleOrNull()
                if (perimetro != null && apotema != null && perimetro > 0 && apotema > 0) {
                    val area = Pentagono(perimetro, apotema).calcularArea()
                    println("Area del pentagono: %.2f".format(area))
                } else {
                    println("Datos invalidos. Ambos deben  mayores  0.")
                }
            }

            5 -> println("Saliendo del programa")

            else -> println("Opcion invalida. Intenta de nuevo.")
        }

    } while (opcion != 5)
}