package org.milk.dev.introkotlin.operaciones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.milk.dev.introkotlin.R
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class OperacionesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operaciones)

        val etNumero1 = findViewById<TextInputEditText>(R.id.etNumero1)
        val etNumero2 = findViewById<TextInputEditText>(R.id.etNumero2)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        val btnSumar = findViewById<MaterialButton>(R.id.btnSumar)
        val btnRestar = findViewById<MaterialButton>(R.id.btnRestar)
        val btnMultiplicar = findViewById<MaterialButton>(R.id.btnMultiplicar)
        val btnDividir = findViewById<MaterialButton>(R.id.btnDividir)
        val btnLimpiar = findViewById<MaterialButton>(R.id.btnLimpiar)

        btnSumar.setOnClickListener { calcular('+', etNumero1, etNumero2, tvResultado) }
        btnRestar.setOnClickListener { calcular('-', etNumero1, etNumero2, tvResultado) }
        btnMultiplicar.setOnClickListener { calcular('*', etNumero1, etNumero2, tvResultado) }
        btnDividir.setOnClickListener { calcular('/', etNumero1, etNumero2, tvResultado) }
        btnLimpiar.setOnClickListener { limpiarCampos(etNumero1, etNumero2, tvResultado) }
    }

    private fun calcular(
        operacion: Char,
        etNumero1: TextInputEditText,
        etNumero2: TextInputEditText,
        tvResultado: TextView
    ) {
        try {
            val num1 = etNumero1.text.toString().toDoubleOrNull()
            val num2 = etNumero2.text.toString().toDoubleOrNull()

            when {
                num1 == null || num2 == null -> {
                    mostrarError("Ingrese ambos números válidos")
                    return
                }
                operacion == '/' && num2 == 0.0 -> {
                    mostrarError("No se puede dividir por cero")
                    return
                }
                else -> {
                    val resultado = when (operacion) {
                        '+' -> num1 + num2
                        '-' -> num1 - num2
                        '*' -> num1 * num2
                        '/' -> num1 / num2
                        else -> 0.0
                    }
                    mostrarResultado(resultado, tvResultado)
                }
            }
        } catch (e: Exception) {
            mostrarError("Error en el cálculo: ${e.message}")
        }
    }

    private fun mostrarResultado(resultado: Double, tvResultado: TextView) {
        tvResultado.text = if (resultado % 1 == 0.0) {
            resultado.toInt().toString()
        } else {
            String.format("%.4f", resultado).trimEnd('0').trimEnd('.')
        }
    }

    private fun limpiarCampos(
        etNumero1: TextInputEditText,
        etNumero2: TextInputEditText,
        tvResultado: TextView
    ) {
        etNumero1.text?.clear()
        etNumero2.text?.clear()
        tvResultado.text = "0"
        Snackbar.make(etNumero1, "Campos limpiados", Snackbar.LENGTH_SHORT).show()
    }

    private fun mostrarError(mensaje: String) {
        Snackbar.make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_LONG).show()
    }
}