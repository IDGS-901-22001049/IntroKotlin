package org.milk.dev.introkotlin.ejemplo1

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import org.milk.dev.introkotlin.R
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View

class Suma2 : AppCompatActivity() {

    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var tv1: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suma2)
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        tv1 = findViewById(R.id.tv1)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcular(view: View) {
        val num1 = et1.text.toString().toDoubleOrNull()
        val num2 = et2.text.toString().toDoubleOrNull()
        val rgOperacion = findViewById<android.widget.RadioGroup>(R.id.rgOperacion)

        if (num1 == null || num2 == null) {
            tv1.text = "Ingrese números válidos"
            return
        }

        val resultado = when (rgOperacion.checkedRadioButtonId) {
            R.id.rbSumar -> num1 + num2
            R.id.rbRestar -> num1 - num2
            R.id.rbMultiplicar -> num1 * num2
            R.id.rbDividir -> {
                if (num2 == 0.0) {
                    tv1.text = "No se puede dividir por 0"
                    return
                } else {
                    num1 / num2
                }
            }
            else -> {
                tv1.text = "Seleccione una operación"
                return
            }
        }

        tv1.text = "Resultado: $resultado"
    }

}