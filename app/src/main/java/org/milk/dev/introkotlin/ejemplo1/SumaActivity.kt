package org.milk.dev.introkotlin.ejemplo1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.milk.dev.introkotlin.R

class SumaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suma)


        val radioCirculo = findViewById<EditText>(R.id.RadioCircurlo)
        val ladoCuadrado = findViewById<EditText>(R.id.LadoCuadrado)
        val baseTriangulo = findViewById<EditText>(R.id.BaseTriangulo)
        val alturaTriangulo = findViewById<EditText>(R.id.AlturaTriangulo)
        val perimetroPenta = findViewById<EditText>(R.id.PerimetroPenta)
        val apotemaPenta = findViewById<EditText>(R.id.ApotemaPenta)
        val resultado = findViewById<TextView>(R.id.Resultado)
        val calcularBtn = findViewById<Button>(R.id.CalcularBtn)

        calcularBtn.setOnClickListener {
            var area = 0.0
            var contador = 0

            val radio = radioCirculo.text.toString().toDoubleOrNull()
            if (radio != null) {
                if (radio<=0) contador = 2
                area = Circulo(radio).calcularArea()
                contador++
            }
            val lado = ladoCuadrado.text.toString().toDoubleOrNull()
            if (lado != null) {
                if (lado<=0) contador = 2
                area = Cuadrado(lado).calcularArea()
                contador++
            }

            val base = baseTriangulo.text.toString().toDoubleOrNull()
            val altura = alturaTriangulo.text.toString().toDoubleOrNull()
            if (base != null && altura != null) {
                if (base<=0 || altura<=0) contador = 2
                area = Triangulo(base, altura).calcularArea()
                contador++
            }

            val perimetro = perimetroPenta.text.toString().toDoubleOrNull()
            val apotema = apotemaPenta.text.toString().toDoubleOrNull()
            if (perimetro != null && apotema != null) {
                if (perimetro<=0 || apotema<=0) contador = 2
                area = Pentagono(perimetro, apotema).calcularArea()
                contador++
            }

            when {
                contador == 0 -> {

                    Toast.makeText(this, "Llena los datos de una figura", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                }
                contador > 1 -> {
                    Toast.makeText(this, "Solo puedes llenar una figura a la vez", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                }
                else -> {
                    resultado.text = "Área: %.2f".format(area)
                    limpiarCampos()
                }
            }
        }
    } 

    fun limpiarCampos() {
        val radioCirculo = findViewById<EditText>(R.id.RadioCircurlo)
        val ladoCuadrado = findViewById<EditText>(R.id.LadoCuadrado)
        val baseTriangulo = findViewById<EditText>(R.id.BaseTriangulo)
        val alturaTriangulo = findViewById<EditText>(R.id.AlturaTriangulo)
        val perimetroPenta = findViewById<EditText>(R.id.PerimetroPenta)
        val apotemaPenta = findViewById<EditText>(R.id.ApotemaPenta)

        radioCirculo.text.clear()
        ladoCuadrado.text.clear()
        baseTriangulo.text.clear()
        alturaTriangulo.text.clear()
        perimetroPenta.text.clear()
        apotemaPenta.text.clear()
    }
}
