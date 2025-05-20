package org.milk.dev.introkotlin.ejemplo1

class Circulo(val radio: Double) {
    fun calcularArea(): Double {
        return Math.PI * radio * radio
    }
}

class Cuadrado(val lado: Double) {
    fun calcularArea(): Double {
        return lado * lado
    }
}

class Triangulo(val base: Double, val altura: Double) {
    fun calcularArea(): Double {
        return (base * altura) / 2
    }
}

class Pentagono(val perimetro: Double, val apotema: Double) {
    fun calcularArea(): Double {
        return (perimetro * apotema) / 2
    }

}