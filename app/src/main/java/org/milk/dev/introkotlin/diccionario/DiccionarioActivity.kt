package org.milk.dev.introkotlin.diccionario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.milk.dev.introkotlin.R

class DiccionarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_diccionario)
        val capturarPalabra = findViewById<Button>(R.id.capturarPalabra)
        val buscarPalabra = findViewById<Button>(R.id.buscarPalabra)

        capturarPalabra.setOnClickListener {
            val intent = Intent(this, GuardarPalabraActivity::class.java)
            startActivity(intent)
        }
        buscarPalabra.setOnClickListener {
            val intent = Intent(this, BuscarPalabraActivity::class.java)
            startActivity(intent)
        }
        }
    }