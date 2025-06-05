package org.milk.dev.introkotlin.diccionario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.milk.dev.introkotlin.R
import java.io.File
import java.io.FileInputStream

class BuscarPalabraActivity : AppCompatActivity() {
    private val FILE_NAME = "diccionario.txt"
    private var buscarEnEspanol = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buscar_palabra)
        val españolRadio = findViewById<RadioButton>(R.id.españolRadio)
        val inglesRadio = findViewById<RadioButton>(R.id.inglesRadio)
        val palabraBuscar = findViewById<EditText>(R.id.palabraBuscar)
        val palabraResult = findViewById<TextView>(R.id.palabraResult)
        val regresarInput = findViewById<Button>(R.id.regresarInput)

        españolRadio.setOnClickListener {
            buscarEnEspanol = true
            palabraBuscar.hint = "Ingresa palabra en inglés"
            palabraResult.text = ""
        }

        inglesRadio.setOnClickListener {
            buscarEnEspanol = false
            palabraBuscar.hint = "Ingresa palabra en español"
            palabraResult.text = ""
        }

        palabraBuscar.setOnEditorActionListener { _, _, _ ->
            buscarPalabra(palabraBuscar.text.toString().trim(), palabraResult)
            true
        }

        regresarInput.setOnClickListener {
            val intent = Intent(this, DiccionarioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun buscarPalabra(palabra: String, resultView: TextView) {
        if (palabra.isEmpty()) {
            Toast.makeText(this, "Ingresa una palabra para buscar", Toast.LENGTH_SHORT).show()
            return
        }

        val file = File(filesDir, FILE_NAME)
        if (!file.exists()) {
            resultView.text = "Diccionario vacío"
            return
        }

        try {
            FileInputStream(file).use { fis ->
                fis.bufferedReader().useLines { lines ->
                    var encontrada = false

                    lines.forEach { line ->
                        val partes = line.split(":")
                        if (partes.size == 2) {
                            val espanol = partes[0].trim()
                            val ingles = partes[1].trim()

                            if (buscarEnEspanol && ingles.equals(palabra, ignoreCase = true)) {
                                resultView.text = espanol
                                encontrada = true
                                return@useLines
                            } else if (!buscarEnEspanol && espanol.equals(palabra, ignoreCase = true)) {
                                resultView.text = ingles
                                encontrada = true
                                return@useLines
                            }
                        }
                    }

                    if (!encontrada) {
                        resultView.text = "Palabra no encontrada"
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al buscar la palabra", Toast.LENGTH_SHORT).show()
            resultView.text = "Error en la búsqueda"
        }
    }
}