package es.ua.eps.carkier


import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import es.ua.eps.carkier.Carnets.MostrarCarnets
import es.ua.eps.carkier.Contratos.VerContratos
import es.ua.eps.carkier.Modelos.Contrato
import es.ua.eps.carkier.Modelos.FechaOcupada
import es.ua.eps.carkier.Modelos.Seguros
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.databinding.ActivityContratarVehiculoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ContratarVehiculo : AppCompatActivity() {

    private lateinit var binding: ActivityContratarVehiculoBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listaTiposSeguros: List<Seguros>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var editTextDate: EditText
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var editTextDate2: EditText
    private lateinit var calendar: Calendar
    private var idSeguro: Long? = null

    private var fechasOcupadas: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContratarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        CargarSpinner()
        // Recupera el precio del Intent
        val precioVehiculo = intent.getDoubleExtra("preciovehiculo", 0.0)

        // Metodo para saber las fechas ocupadas del vehiculo
        obtenerFechasOcupadas(intent.getLongExtra("idvehiculo", -1).toInt())

        binding.spinnerContratoSeguro.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    idSeguro = listaTiposSeguros[position].id?.toLong()
                    val precio = ObtenerSeguro(id + 1, precioVehiculo)
                    binding.etxtContratoPrecioDia.setText(precio.toString())

                    // cargarPreciovehiculo(precioVehiculo,precio)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    idSeguro = null
                }
            }

        calendar = Calendar.getInstance(Locale("es", "ES")) // Establecer el idioma a español

        // Inicializa editTextDate
        editTextDate = binding.etxtContratoFechaInicio
        editTextDate.setOnClickListener { mostrarCalendario(editTextDate) }

        editTextDate2 = binding.etxtContratoFechaFinal
        editTextDate2.setOnClickListener { mostrarCalendario(editTextDate2) }

        binding.btnContratar.setOnClickListener {
            val fechaInicio = binding.etxtContratoFechaInicio.text.toString()
            val fechaFinal = binding.etxtContratoFechaFinal.text.toString()
            val precioDiaStr = binding.etxtContratoPrecioDia.text.toString()

            // Verificar que el campo de precio no esté vacío y contenga un número
            if (precioDiaStr.isNotEmpty()) {
                try {
                    val precioDia = precioDiaStr.toDouble()

                    // Aquí calculamos la diferencia de días
                    val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale("es", "ES"))
                    val fechaInicioDate = formatoFecha.parse(fechaInicio)
                    val fechaFinalDate = formatoFecha.parse(fechaFinal)

                    if (fechaInicioDate != null && fechaFinalDate != null) {
                        val diferenciaDias = ((fechaFinalDate.time - fechaInicioDate.time) / (1000 * 60 * 60 * 24)).toInt()

                        if (diferenciaDias < 1) {
                            Toast.makeText(this, "La fecha final debe ser posterior a la fecha de inicio", Toast.LENGTH_SHORT).show()

                        }

                        // Calculamos el precio final
                        val precioFinal = diferenciaDias * precioDia

                        // Mostramos el dialogo de confirmación
                        mostrarConfirmacionContratacion(precioFinal)
                    } else {
                        Toast.makeText(this, "Por favor, introduce fechas válidas", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Por favor, introduce un precio válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "El campo de precio está vacío", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imgCalculator.setOnClickListener {
            val fechaInicio = binding.etxtContratoFechaInicio.text.toString()
            val fechaFinal = binding.etxtContratoFechaFinal.text.toString()
            calcularPrecioTotal(fechaInicio, fechaFinal, precioVehiculo)
        }

        binding.btnContratoCancelar.setOnClickListener() {
            val intent = Intent(this, MostrarVehiculo::class.java)
            startActivity(intent)
        }


        // Inicializa el DrawerLayout
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)


        // Comprobar el modo previamente guardado
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        setTheme(isDarkMode)

        // Para la funcionalidad de los botones del menu de abajo
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.principal -> {
                    startActivity(Intent(this, Principal::class.java))
                    true
                }

                R.id.filtrarBusqueda -> {
                    true
                }

                R.id.perfil -> {
                    // Abre el Navigation Drawer cuando se pulsa en nav_search
                    drawerLayout.openDrawer(GravityCompat.START)
                    true
                }

                else -> false
            }
        }

        // Configuración del NavigationView usando binding
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.carnet -> {
                    val intent = Intent(this, MostrarCarnets::class.java)
                    startActivity(intent)
                    true
                }

                R.id.contrato -> {
                    val intent = Intent(this, VerContratos::class.java)
                    startActivity(intent)
                    true
                }

                R.id.CerrarSesion -> {
                    cerrarSesion()
                    true
                }

                R.id.modoNocturno -> {
                    // Cambiar entre modo oscuro y modo claro
                    val isCurrentlyDarkMode = sharedPreferences.getBoolean("dark_mode", false)
                    if (isCurrentlyDarkMode) {
                        setTheme(false)  // Cambiar a modo claro
                    } else {
                        setTheme(true)  // Cambiar a modo oscuro
                    }
                    true
                }
                // Otros ítems pueden ser manejados aquí
                else -> false
            }
        }


    }

    // Cargar el spinner de seguros
    fun CargarSpinner() {
        RetrofitClient.instance.Seguros().enqueue(object : Callback<List<Seguros>> {
            override fun onResponse(call: Call<List<Seguros>>, response: Response<List<Seguros>>) {
                if (response.isSuccessful) {
                    listaTiposSeguros = response.body() ?: emptyList()
                    val nombresTiposCarnet =
                        listaTiposSeguros.map { "${it.nombre} - ${it.coste} €" }

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

    // Obtener seguro por ID
    fun ObtenerSeguro(id: Long, precioVehiculo: Double): Double {
        for (seguro in listaTiposSeguros) {
            if (seguro.id == id) {
                return seguro.coste + precioVehiculo
            }
        }
        return 0.0
    }

    // Mostrar calendario y marcar fechas ocupadas en rojo
    fun mostrarCalendario(editText: EditText) {
        // Lista de fechas ocupadas (ajusta según tus datos)
        val fechasOcupadas = fechasOcupadas // Ajusta según tus datos

        // Convertir las fechas ocupadas a milisegundos
        val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fechasOcupadasMillis = fechasOcupadas.mapNotNull { formatoFecha.parse(it)?.time }

        // Obtener la fecha actual en milisegundos
        val fechaActualMillis = Calendar.getInstance().timeInMillis

        // Configurar restricciones del calendario
        val constraintsBuilder = CalendarConstraints.Builder()
            .setValidator(object : CalendarConstraints.DateValidator {
                override fun isValid(date: Long): Boolean {
                    // Permitir fechas mayores o iguales a hoy y que no estén ocupadas
                    return date >= fechaActualMillis && !fechasOcupadasMillis.contains(date)
                }

                override fun describeContents(): Int = 0

                override fun writeToParcel(dest: Parcel, flags: Int) {}
            })

        // Crear el MaterialDatePicker y establecer el idioma a español
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Selecciona una fecha")
            .setCalendarConstraints(constraintsBuilder.build())
            .build()

        // Establecer el idioma del calendario en español
        Locale.setDefault(Locale("es", "ES"))
        val configuration = resources.configuration
        configuration.setLocale(Locale("es", "ES"))
        resources.updateConfiguration(configuration, resources.displayMetrics)

        // Mostrar el MaterialDatePicker
        datePicker.show(
            (editText.context as AppCompatActivity).supportFragmentManager,
            "MATERIAL_DATE_PICKER"
        )

        // Manejar la selección de fecha
        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val fechaSeleccionada = formatoFecha.format(Date(selectedDate))
            editText.setText(fechaSeleccionada)
        }
    }

    // Obtener fechas ocupadas desde el servidor
    fun obtenerFechasOcupadas(idVehiculo: Int) {
        RetrofitClient.instance.getFechasOcupadas(idVehiculo)
            .enqueue(object : Callback<FechaOcupada> {
                override fun onResponse(
                    call: Call<FechaOcupada>,
                    response: Response<FechaOcupada>
                ) {
                    if (response.isSuccessful) {
                        val fechasOcupadasResponse = response.body()
                        fechasOcupadas = fechasOcupadasResponse?.fechas ?: emptyList()

                        fechasOcupadas.forEach {
                            Log.d("FechasOcupadas", "Fecha ocupada: $it")
                        }
                    } else {
                        Toast.makeText(
                            this@ContratarVehiculo,
                            "Error al obtener fechas ocupadas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<FechaOcupada>, t: Throwable) {
                    Toast.makeText(
                        this@ContratarVehiculo,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun mostrarConfirmacionContratacion(precioFinal: Double) {
        // Crear el mensaje con el precio final
        val mensaje = "¿Estás seguro de que deseas contratar el servicio?\n\n" +
                "Precio Total: ${String.format("%.2f", precioFinal)} €"

        // Crear el AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmación de Contratación")
        builder.setMessage(mensaje)

        // Botón "Sí" para confirmar
        builder.setPositiveButton("Sí") { dialog, which ->
            // Aquí se crea el contrato si el usuario confirma
            crearContrato(precioFinal)
            Toast.makeText(this, "Contrato creado exitosamente.", Toast.LENGTH_SHORT).show()
        }

        // Botón "No" para cancelar
        builder.setNegativeButton("No") { dialog, which ->
            // El usuario decidió no contratar, cerramos el dialogo
            dialog.dismiss()
        }

        // Mostrar el diálogo
        builder.create().show()
    }

    fun crearContrato(precioFinal: Double) {
        val fechaInicio = binding.etxtContratoFechaInicio.text.toString()
        val fechaFinal = binding.etxtContratoFechaFinal.text.toString()
        val idVehiculo = intent.getLongExtra("idvehiculo", -1)
        val idUsuario = sharedPreferences.getLong("id", -1)

        val contrato = Contrato(
            idvehiculo = idVehiculo,
            idCliente = idUsuario,
            idSeguro = idSeguro.toString(),
            precioDia = 30.0, // Este valor lo puedes obtener de tu UI como ya lo hacías
            precioTotal = precioFinal,
            fechaInicio = fechaInicio,
            fechaFin = fechaFinal
        )

        // Aquí puedes realizar la llamada a la API para crear el contrato o guardarlo localmente
        // Ejemplo de llamada a la API:
        // RetrofitClient.instance.crearContrato(contrato).enqueue(....)

        Log.d("Contrato", "Contrato creado: $contrato")
    }


    fun cerrarSesion() {
        // Elimina las preferencias compartidas
        val sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Borra todas las preferencias
        editor.apply()

        // Redirige al usuario a la actividad de inicio de sesión
        val intent = Intent(this, InicioSesion::class.java)
        startActivity(intent)
        finish() // Termina la actividad actual para que el usuario no pueda regresar a ella
    }

    fun setTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            // Establecer modo oscuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Establecer modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Guardar la preferencia del usuario
        val editor = sharedPreferences.edit()
        editor.putBoolean("dark_mode", isDarkMode)
        editor.apply()
    }

    // Función para calcular el precio total
    fun calcularPrecioTotal(fechaInicioStr: String, fechaFinalStr: String, precioDia: Double) {
        if (fechaInicioStr.isNotBlank() && fechaFinalStr.isNotBlank()) {
            try {
                // Convertir las fechas de String a Date
                val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale("es", "ES"))
                val fechaInicio = formatoFecha.parse(fechaInicioStr)
                val fechaFinal = formatoFecha.parse(fechaFinalStr)

                if (fechaInicio != null && fechaFinal != null) {
                    // Validar que la fecha final no sea anterior a la fecha de inicio
                    if (fechaInicio.after(fechaFinal)) {
                        Toast.makeText(
                            this,
                            "La fecha final no puede ser anterior a la fecha de inicio",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    // Usamos Calendar para contar los días de forma más precisa
                    val calendarInicio = Calendar.getInstance()
                    val calendarFinal = Calendar.getInstance()

                    // Establecemos las fechas en los calendarios
                    calendarInicio.time = fechaInicio
                    calendarFinal.time = fechaFinal

                    // Si la fecha de inicio es el mismo día que la fecha final, contamos ese día también
                    //  calendarFinal.add(Calendar.DAY_OF_MONTH, 1)  // Sumamos un día a la fecha final para incluirla en el cálculo

                    // Calculamos la diferencia en días
                    val diferenciaDias =
                        ((calendarFinal.timeInMillis - calendarInicio.timeInMillis) / (1000 * 60 * 60 * 24)).toInt()

                    // Validar que la diferencia de días sea al menos 1
                    if (diferenciaDias < 1) {
                        Toast.makeText(
                            this,
                            "La duración del alquiler debe ser de al menos 1 día",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    // Calcular el precio total
                    val precioTotal =
                        diferenciaDias * binding.etxtContratoPrecioDia.text.toString().toDouble()

                    // Mostrar el precio total
                    val precioTotalFormateado = String.format("%.2f €", precioTotal)
                    binding.etxtContratoPrecioFinal.setText(precioTotalFormateado)

                }

            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Error al calcular las fechas: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "Por favor, ingresa todas las fechas y el precio por día",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}

