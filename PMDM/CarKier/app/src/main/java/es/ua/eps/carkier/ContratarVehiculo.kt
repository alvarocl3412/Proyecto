package es.ua.eps.carkier

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import es.ua.eps.carkier.ApiService.ApiService
import es.ua.eps.carkier.Modelos.CarnetConducir
import es.ua.eps.carkier.Modelos.Seguros
import es.ua.eps.carkier.Modelos.TipoCarnet
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.databinding.ActivityContratarVehiculoBinding
import es.ua.eps.carkier.databinding.ActivityMostrarVehiculoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ContratarVehiculo : AppCompatActivity() {
    private lateinit var binding: ActivityContratarVehiculoBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listaTiposCarnet: List<Seguros>
    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var editTextDate: EditText

    private lateinit var editTextDate2: EditText

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var calendar: Calendar
    private var idSeguro: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContratarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        CargarSpinner()

        binding.spinnerContratoSeguro.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    idSeguro = listaTiposCarnet[position].id?.toLong()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    idSeguro = null
                }
            }

        calendar = Calendar.getInstance() // Inicializa el calendario

        // Inicializa editTextDate
        editTextDate = binding.etxtContratoFechaInicio
        editTextDate.setOnClickListener { mostrarCalendario(editTextDate) }

        editTextDate2 = binding.etxtContratoFechaFinal
        editTextDate2.setOnClickListener { mostrarCalendario(editTextDate2) }
    }

    fun CargarSpinner() {
        RetrofitClient.instance.Seguros().enqueue(object : Callback<List<Seguros>> {
            override fun onResponse(call: Call<List<Seguros>>, response: Response<List<Seguros>>) {
                if (response.isSuccessful) {
                    listaTiposCarnet = response.body() ?: emptyList()
                    // Crear la lista de nombres y costes
                    val nombresTiposCarnet = listaTiposCarnet.map { "${it.nombre} - ${it.coste} €" }

                    // Configurar el adaptador directamente
                    val spinnerAdapter = ArrayAdapter(
                        this@ContratarVehiculo,
                        android.R.layout.simple_spinner_item,
                        nombresTiposCarnet
                    )
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerContratoSeguro.adapter = spinnerAdapter
                } else {
                    Toast.makeText(
                        this@ContratarVehiculo,
                        "Error al cargar los tipos de carnet",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Seguros>>, t: Throwable) {
                Toast.makeText(
                    this@ContratarVehiculo,
                    "Error de conexión: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun updateDateLabel(editText: EditText) {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        editText.setText(sdf.format(calendar.time))
    }

    private fun mostrarCalendario(editText: EditText) {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateLabel(editText)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

}
