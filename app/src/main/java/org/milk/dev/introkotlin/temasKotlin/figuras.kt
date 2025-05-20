package org.joel.bonedead.introkotlin.temasKotlin

// cuadrado circulo pentagono triangulo
// formula general
// usando el dowhile hacer una piramide de * depende el numero que ingrese el usuario

fun main() {
    println("ingresa una opcion")
    println("1. cuadrado")
    println("2. circulo")
    println("3. pentagono")
    println("4. triangulo")
    println("5. salir")
var opcion = readln().toInt()


    while (opcion != 5){
    if (opcion == 1){
        println("ingresa el lado del cuadrado")
        var lado = readln().toDouble()
            if (lado == 0.0){
                println("el lado no puede ser 0")
                continue
            }
        var area = lado * lado
        println("el area del cuadrado es $area")
    } // cuadrado
        if (opcion == 2){
        println("ingresa el radio del circulo")
        var radio = readln().toDouble()
            if (radio == 0.0){
                println("el radio no puede ser 0")
                continue

            }
        var area = 3.14 * radio * radio
        println("el area del circulo es $area")
    }
        if (opcion == 3){
            println("ingresa el perimetro del pentagono")
        var perimetro = readln().toDouble()
            println("Ingresa el apotema")
            var apotema = readln().toDouble()
            if (apotema == 0.0){
                println("el apotema no puede ser 0")
                continue
            }
            if (perimetro == 0.0){
                println("el perimetro no puede ser 0")
                continue
            }
             var resultado = (perimetro * apotema) / 2
            println("el area del pentagono es $resultado")
        }
        if (opcion == 4){
            println("ingresa la base del triangulo")
            var base = readln().toDouble()
            println("ingresa la altura del triangulo")
            var altura = readln().toDouble()
            if (base == 0.0){
                println("la base no puede ser 0")
                continue
            }
            if (altura == 0.0){
                println("la altura no puede ser 0")
                continue
            }

            var area = (base * altura) / 2
            println("el area del triangulo es $area")
        }
        if (opcion > 5){
            println("opcion invalida")
        }
        println("ingresa una opcion")
        println("1. cuadrado")
        println("2. circulo")
        println("3. pentagono")
        println("4. triangulo")
        println("5. salir")
        opcion = readln().toInt()
        }



}


