package org.milk.dev.introkotlin.cinepolis

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import org.milk.dev.introkotlin.R
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.NumberFormat
import java.util.Locale

class CinepolisActivity : AppCompatActivity() {
    private val PRECIO_BOLETO = 12000.0
    private val MAX_BOLETOS = 7
    private val PREFS_NAME = "CinePolisPrefs"
    private val HISTORIAL_KEY = "historial_compras"

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var historialCompras: MutableList<Compra>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinepolis)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        cargarHistorial()

        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnCalcular).setOnClickListener {
            calcularPago()
        }

        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnVerHistorial).setOnClickListener {
            mostrarHistorial()
        }
    }

    private fun calcularPago() {
        try {
            if (!validarCampos()) return

            val nombre = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etNombre).text.toString()
            val cantidadCompradores = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etCantidadCompradores).text.toString().toInt()
            val cantidadBoletos = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etCantidadBoletos).text.toString().toInt()
            val tieneTarjeta = findViewById<RadioButton>(R.id.rbSi).isChecked

            var subtotal = cantidadBoletos * PRECIO_BOLETO

            val descuento = when {
                cantidadBoletos > 5 -> 0.15
                cantidadBoletos in 3..5 -> 0.10
                else -> 0.0
            }

            val descuentoCantidad = subtotal * descuento
            subtotal -= descuentoCantidad

            var descuentoTarjeta = 0.0
            if (tieneTarjeta) {
                descuentoTarjeta = subtotal * 0.10
                subtotal -= descuentoTarjeta
            }

            val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault())

            val resumenCompra = Compra(
                nombre = nombre,
                cantidadBoletos = cantidadBoletos,
                cantidadCompradores = cantidadCompradores,
                tieneTarjeta = tieneTarjeta,
                subtotal = cantidadBoletos * PRECIO_BOLETO,
                descuentoCantidad = descuentoCantidad,
                descuentoTarjeta = descuentoTarjeta,
                total = subtotal,
                fecha = System.currentTimeMillis()
            )

            // Guardar en historial
            historialCompras.add(resumenCompra)
            guardarHistorial()

            // Mostrar resultados
            mostrarResultado(resumenCompra, formatoMoneda)

            // Mostrar snackbar de confirmación
            Snackbar.make(
                findViewById(android.R.id.content),
                "Compra registrada correctamente",
                Snackbar.LENGTH_LONG
            ).setAction("Ver Historial") {
                mostrarHistorial()
            }.show()

        } catch (e: NumberFormatException) {
            mostrarError("Ingrese valores válidos en todos los campos")
        }
    }

    private fun validarCampos(): Boolean {
        if (findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etNombre).text.isNullOrEmpty()) {
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.nameLayout).error = "El nombre es requerido"
            return false
        } else {
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.nameLayout).error = null
        }

        val cantidadCompradores = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etCantidadCompradores).text.toString().toIntOrNull()
        if (cantidadCompradores == null || cantidadCompradores <= 0) {
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.buyersLayout).error = "Ingrese una cantidad válida de compradores"
            return false
        } else {
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.buyersLayout).error = null
        }

        val cantidadBoletos = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etCantidadBoletos).text.toString().toIntOrNull()
        if (cantidadBoletos == null || cantidadBoletos <= 0 || cantidadBoletos > MAX_BOLETOS) {
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.ticketsLayout).error = "Ingrese entre 1 y $MAX_BOLETOS boletos"
            return false
        } else {
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.ticketsLayout).error = null
        }

        return true
    }

    private fun mostrarResultado(compra: Compra, formatoMoneda: NumberFormat) {
        val resultado = """
            Nombre: ${compra.nombre}
            Compradores: ${compra.cantidadCompradores}
            Boletos: ${compra.cantidadBoletos}
            
            Subtotal: ${formatoMoneda.format(compra.subtotal)}
            Descuento por cantidad (${when {
            compra.cantidadBoletos > 5 -> "15%"
            compra.cantidadBoletos in 3..5 -> "10%"
            else -> "0%"
        }}): ${formatoMoneda.format(compra.descuentoCantidad)}
            
            ${if (compra.tieneTarjeta) "Descuento por tarjeta Cineco (10%): ${formatoMoneda.format(compra.descuentoTarjeta)}" else ""}
            
            Total a pagar: ${formatoMoneda.format(compra.total)}
        """.trimIndent()

        findViewById<android.widget.TextView>(R.id.tvResultado).text = resultado
    }

    private fun mostrarError(mensaje: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Error")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun cargarHistorial() {
        val historialJson = sharedPreferences.getString(HISTORIAL_KEY, null)
        historialCompras = if (historialJson != null) {
            Gson().fromJson(historialJson, object : TypeToken<MutableList<Compra>>() {}.type)
        } else {
            mutableListOf()
        }
    }

    private fun guardarHistorial() {
        val editor = sharedPreferences.edit()
        editor.putString(HISTORIAL_KEY, Gson().toJson(historialCompras))
        editor.apply()
    }

    private fun mostrarHistorial() {
        if (historialCompras.isEmpty()) {
            mostrarError("No hay compras registradas")
            return
        }

        val items = historialCompras.sortedByDescending { it.fecha }.map { compra ->
            "${compra.nombre} - Boletos: ${compra.cantidadBoletos} - ${NumberFormat.getCurrencyInstance(Locale.getDefault()).format(compra.total)}"
        }.toTypedArray()

        MaterialAlertDialogBuilder(this)
            .setTitle("Historial de Compras")
            .setItems(items) { _, which ->
                mostrarDetalleCompra(historialCompras.sortedByDescending { it.fecha }[which])
            }
            .setNegativeButton("Cerrar", null)
            .setNeutralButton("Limpiar historial") { _, _ ->
                confirmarLimpiarHistorial()
            }
            .show()
    }

    private fun mostrarDetalleCompra(compra: Compra) {
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val fechaFormateada = android.text.format.DateFormat.getDateFormat(this).format(compra.fecha)

        val mensaje = """
            Fecha: $fechaFormateada
            Nombre: ${compra.nombre}
            Compradores: ${compra.cantidadCompradores}
            Boletos: ${compra.cantidadBoletos}
            Tarjeta Cineco: ${if (compra.tieneTarjeta) "Sí" else "No"}
            
            Subtotal: ${formatoMoneda.format(compra.subtotal)}
            Descuento por cantidad: ${formatoMoneda.format(compra.descuentoCantidad)}
            ${if (compra.tieneTarjeta) "Descuento por tarjeta: ${formatoMoneda.format(compra.descuentoTarjeta)}" else ""}
            
            Total a pagar: ${formatoMoneda.format(compra.total)}
        """.trimIndent()

        MaterialAlertDialogBuilder(this)
            .setTitle("Detalle de Compra")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun confirmarLimpiarHistorial() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Confirmación")
            .setMessage("¿Estás seguro de que deseas limpiar todo el historial de compras?")
            .setPositiveButton("Limpiar") { _, _ ->
                historialCompras.clear()
                guardarHistorial()
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Historial limpiado correctamente",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    data class Compra(
        val nombre: String,
        val cantidadBoletos: Int,
        val cantidadCompradores: Int,
        val tieneTarjeta: Boolean,
        val subtotal: Double,
        val descuentoCantidad: Double,
        val descuentoTarjeta: Double,
        val total: Double,
        val fecha: Long
    )
}