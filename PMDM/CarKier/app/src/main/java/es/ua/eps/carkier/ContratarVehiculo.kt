package es.ua.eps.carkier

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import es.ua.eps.carkier.Modelos.Contrato
import es.ua.eps.carkier.Modelos.Seguros
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.databinding.ActivityContratarVehiculoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale

class ContratarVehiculo : AppCompatActivity() {

    private lateinit var binding: ActivityContratarVehiculoBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listaTiposSeguros: List<Seguros>
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
        // Recupera el precio del Intent
        val precioVehiculo = intent.getDoubleExtra("preciovehiculo", 0.0)
        cargarPreciovehiculo(precioVehiculo)

        binding.spinnerContratoSeguro.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    idSeguro = listaTiposSeguros[position].id?.toLong()
                    var precio = ObtenerSeguro(id+1,precioVehiculo)
                    cargarPreciovehiculo(precio)
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

        binding.btnContratar.setOnClickListener() {
            val fechaInicio = binding.etxtContratoFechaInicio.text.toString()
            val fechaFinal = binding.etxtContratoFechaFinal.text.toString()
            val idVehiculo = intent.getLongExtra("idVehiculo", -1)
            val idUsuario = sharedPreferences.getLong("id", -1)

            val contraro =
                Contrato(
                    idvehiculo = idVehiculo,
                    idCliente = idUsuario,
                    idSeguro = idSeguro.toString(),
                    precioDia = 100,
                    precioTotal = 200,
                    fechaInicio = fechaInicio,
                    fechaFin = fechaFinal
                )
        }

    }


    fun CargarSpinner() {
        RetrofitClient.instance.Seguros().enqueue(object : Callback<List<Seguros>> {
            override fun onResponse(call: Call<List<Seguros>>, response: Response<List<Seguros>>) {
                if (response.isSuccessful) {
                    listaTiposSeguros = response.body() ?: emptyList()
                    // Crear la lista de nombres y costes
                    val nombresTiposCarnet =
                        listaTiposSeguros.map { "${it.nombre} - ${it.coste} €" }

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

    fun cargarPreciovehiculo(precio: Double) {
        // Formatea el precio para que tenga dos decimales y añade el símbolo de moneda si es necesario
        val precioFormateado = String.format("%.2f", precio) // Formato a dos decimales
        // Asigna el precio al EditText
        binding.etxtContratoPrecioDia.setText(precioFormateado)

        // Obtén las fechas de los EditText
        val fechaIni = binding.etxtContratoFechaInicio.text.toString()
        val fechaFI = binding.etxtContratoFechaFinal.text.toString()

        // Verifica si las fechas están vacías
        if (fechaIni.isNotBlank() && fechaFI.isNotBlank()) {
            try {
                val formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val fechaInicio = LocalDate.parse(fechaIni, formatoFecha)
                val fechaFinal = LocalDate.parse(fechaFI, formatoFecha)

                // Calcula la diferencia en días
                val diferenciaDias = ChronoUnit.DAYS.between(fechaInicio, fechaFinal).toInt()

                // Verifica que la diferencia sea positiva
                if (diferenciaDias < 0) {
                    Toast.makeText(this, "La fecha final debe ser posterior a la fecha de inicio", Toast.LENGTH_SHORT).show()
                    return // Salir de la función si la diferencia es negativa
                }

                // Calcula el precio final
                val precioFinal = diferenciaDias * precio

                // Actualiza el texto del precio final en el TextView
                val precio = String.format("%.2f €", precioFinal) // Formato el resultado
                binding.etxtContratoPrecioFinal.setText(precio)
            } catch (e: Exception) {
                // Manejo de errores si la fecha no es válida
                Toast.makeText(this, "Error al parsear las fechas: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, introduce ambas fechas", Toast.LENGTH_SHORT).show()
        }
    }



    fun ObtenerSeguro(id: Long,precioVehiculo: Double): Double {
        for (seguro in listaTiposSeguros) {
            if (seguro.id == id) {
               val precio = seguro.coste+ precioVehiculo
                return precio
            }
        }
        return 0.0
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
