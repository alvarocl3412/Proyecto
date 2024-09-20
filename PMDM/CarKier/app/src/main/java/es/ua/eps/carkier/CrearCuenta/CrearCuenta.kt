package es.ua.eps.carkier.CrearCuenta

import android.app.DatePickerDialog
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.databinding.ActivityCrearCuentaBinding
import java.util.Calendar

class CrearCuenta : AppCompatActivity() {
        private lateinit var binding:ActivityCrearCuentaBinding
        private lateinit var sharedPreferences: SharedPreferences
        private lateinit var editTextDate: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
            setContentView(binding.root)

        editTextDate = binding.etxtFechaNac

        // Establecer un listener para mostrar el DatePicker
        editTextDate.setOnClickListener {
            showDatePickerDialog()
        }

            binding.btnSiguiente.setOnClickListener(){
                recogerDatos()
                val intent = Intent(this, CrearCuenta2::class.java)
                startActivity(intent)
            }

        }

    private fun showDatePickerDialog() {
        // Obtener la fecha actual
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Crear el DatePickerDialog
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            // Formatear la fecha como yyyy/MM/dd
            val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
            editTextDate.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    fun recogerDatos() {
        // Inicializa SharedPreferences para guardar datos persistentes
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("dni", binding.etxtDni.text.toString())
        editor.putString("nombre", binding.etxtNombre.text.toString())
        editor.putString("apellidos", binding.etxtApellidos.text.toString()) // Asegúrate de que esto sea correcto
        editor.putString("fechaNacimiento", editTextDate.text.toString())
        editor.apply()
    }

    }