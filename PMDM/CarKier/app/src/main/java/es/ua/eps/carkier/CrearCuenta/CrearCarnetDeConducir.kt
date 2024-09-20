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
import android.content.SharedPreferences
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import es.ua.eps.carkier.Modelos.TipoCarnet
import es.ua.eps.carkier.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.Principal

class CrearCarnetDeConducir : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityCrearCarnetDeConducirBinding
    private lateinit var editTextDate: EditText
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearCarnetDeConducirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar nombre y correo del SharedPreferences
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
       CargarSpinner()

        // Inicializa editTextDate usando el binding
        editTextDate = binding.calendarFechaExpedicion as EditText

        // Establece la fecha inicial
        updateDateLabel()

        // Configura el clic en el EditText para mostrar el DatePickerDialog
        editTextDate.setOnClickListener {
            mostrarCalendario()
        }

        binding.btnCrearCarnet.setOnClickListener {
            CrearCarnet()
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    fun CargarSpinner() {
        // Llamada a la API
        RetrofitClient.instance.TiposCarnet().enqueue(object : Callback<List<TipoCarnet>> {
            override fun onResponse(call: Call<List<TipoCarnet>>, response: Response<List<TipoCarnet>>) {
                if (response.isSuccessful) {
                    // Obtener la lista de tipos de carnet
                    val listaTiposCarnet = response.body() ?: emptyList()

                    // Crear una lista de solo los nombres para mostrar en el Spinner
                    val nombresTiposCarnet = listaTiposCarnet.map { it.nombre }

                    // Cargar los nombres en el Spinner
                    val spinnerAdapter = ArrayAdapter(
                        this@CrearCarnetDeConducir,  // Contexto válido
                        android.R.layout.simple_spinner_item,
                        nombresTiposCarnet
                    )
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    // Asigna el adaptador al Spinner
                    binding.spnTipo.adapter = spinnerAdapter

                } else {
                    Toast.makeText(this@CrearCarnetDeConducir, "Error al cargar los tipos de carnet", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<TipoCarnet>>, t: Throwable) {
                Toast.makeText(this@CrearCarnetDeConducir, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        /*
                 // Guarda el id correspondiente al nombre seleccionado, si es necesario
                    binding.spnTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            var idSeleccionado = listaTiposCarnet[position].id                            // Haz lo que necesites con el ID
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Nada seleccionado
                        }
                    }
         */
    }

    fun mostrarCalendario() {
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

    fun updateDateLabel() {
        // Define el formato deseado para la fecha
        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        editTextDate.setText(sdf.format(calendar.time))
    }

    fun CrearCarnet(){
        var spinner :String = binding.spnTipo.toString()
    }

}