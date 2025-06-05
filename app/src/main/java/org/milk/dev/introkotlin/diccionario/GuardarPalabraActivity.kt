package org.milk.dev.introkotlin.diccionario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.milk.dev.introkotlin.R
import java.io.File
import java.io.FileOutputStream

class GuardarPalabraActivity : AppCompatActivity() {
    private val FILE_NAME = "diccionario.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guardar_palabra)
        val guardarButton = findViewById<Button>(R.id.guardarButton)
        val regresarInput = findViewById<Button>(R.id.regresarInput)
        val españolnput = findViewById<EditText>(R.id.españolnput)
        val InglesInput = findViewById<EditText>(R.id.InglesInput)

        guardarButton.setOnClickListener {
            val palabraEspanol = españolnput.text.toString().trim()
            val palabraIngles = InglesInput.text.toString().trim()

            if (palabraEspanol.isEmpty() || palabraIngles.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa ambas palabras", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            guardarPalabras(palabraEspanol, palabraIngles)

            mostrarAlertaExito()

            españolnput.text.clear()
            InglesInput.text.clear()
        }

        regresarInput.setOnClickListener {
            intent = Intent(this, DiccionarioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun guardarPalabras(espanol: String, ingles: String) {
        val file = File(filesDir, FILE_NAME)
        val palabraPair = "$espanol:$ingles\n"

        try {
            if (!file.exists()) {
                file.createNewFile()
            }

            FileOutputStream(file, true).use { fos ->
                fos.write(palabraPair.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al guardar las palabras", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarAlertaExito() {
        AlertDialog.Builder(this)
            .setTitle("Palabras guardadas")
            .setMessage("Las palabras se han almacenado correctamente en el diccionario.")
            .setPositiveButton("Aceptar", null)
            .show()
    }
}