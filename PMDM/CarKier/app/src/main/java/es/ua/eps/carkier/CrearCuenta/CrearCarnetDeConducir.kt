package es.ua.eps.carkier.CrearCuenta

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.databinding.ActivityCrearCarnetDeConducirBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.app.DatePickerDialog
import java.security.Principal

class CrearCarnetDeConducir : AppCompatActivity() {

    private lateinit var editTextDate: EditText
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCrearCarnetDeConducirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa editTextDate usando el binding
        editTextDate = binding.calendarFechaExpedicion as EditText

        // Establece la fecha inicial
        updateDateLabel()

        // Configura el clic en el EditText para mostrar el DatePickerDialog
        editTextDate.setOnClickListener {
            mostrarCalendario()
        }

        binding.btnCrearCarnet.setOnClickListener {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarCalendario() {
        val calendario = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Establece la fecha seleccionada en el calendario
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                // Actualiza el EditText con la fecha seleccionada
                updateDateLabel()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        calendario.show()
    }

    private fun updateDateLabel() {
        // Define el formato deseado para la fecha
        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        editTextDate.setText(sdf.format(calendar.time))
    }
}
