package es.ua.eps.carkier


import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import es.ua.eps.carkier.ApiService.ApiService
import es.ua.eps.carkier.Carnets.MostrarCarnets
import es.ua.eps.carkier.Contratos.VerContratos
import es.ua.eps.carkier.Filtro.FilterActivity
import es.ua.eps.carkier.Modelos.Comentario
import es.ua.eps.carkier.Modelos.Contrato
import es.ua.eps.carkier.Modelos.DatosUsuarios
import es.ua.eps.carkier.Modelos.FechaOcupada
import es.ua.eps.carkier.Modelos.Seguros
import es.ua.eps.carkier.Modelos.Vehiculos
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.adapter.ComentarioAdapter
import es.ua.eps.carkier.databinding.ActivityContratarVehiculoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
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
        val idVehiculo = intent.getLongExtra("idvehiculo", -1).toInt()
        CoroutineScope(Dispatchers.Main).launch {
            val vehiculo = obtenerVehiculoPorId(idVehiculo!!.toLong())
            if (vehiculo != null) {
                val bitmap =
                    binding.imgContratoVehiculo.setBase64Image(vehiculo!!.imagen.toString())
                if (bitmap != null) {
                    binding.imgContratoVehiculo.setScaledImage(bitmap)
                } else {
                    Log.e("MostrarVehiculo", "El bitmap es nulo")
                }
            } else {

            }
        }

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

        binding.btnContratoAceptar.setOnClickListener {
            val fechaInicio = binding.etxtContratoFechaInicio.text.toString()
            val fechaFinal = binding.etxtContratoFechaFinal.text.toString()
            val precioDiaStr = binding.etxtContratoPrecioDia.text.toString()
            val puntos = binding.etxtContratoPuntos.text.toString().toIntOrNull() ?: 0
            val precioString = binding.etxtContratoPrecioFinal.text.toString()
            val cleanedString = precioString.replace(",", ".")
            val precioFinal = cleanedString.toDoubleOrNull() ?: 0.0

            val puntosPersonal = sharedPreferences.getString("puntos", "0")?.toIntOrNull() ?: 0

            if (puntos < 0) {
                Toast.makeText(this, "Los puntos no pueden ser negativos", Toast.LENGTH_SHORT)
                    .show()
            }

            if (puntos > puntosPersonal) {
                Toast.makeText(this, "No tienes tantos puntos", Toast.LENGTH_SHORT).show()
            }

            // Verificar que el campo de precio no esté vacío y contenga un número
            if (precioDiaStr.isNotEmpty()) {
                try {
                    val precioDia = precioDiaStr.toDouble()

                    // Aquí calculamos la diferencia de días
                    val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale("es", "ES"))
                    val fechaInicioDate = formatoFecha.parse(fechaInicio)
                    val fechaFinalDate = formatoFecha.parse(fechaFinal)

                    if (fechaInicioDate != null && fechaFinalDate != null) {
                        val diferenciaDias =
                            ((fechaFinalDate.time - fechaInicioDate.time) / (1000 * 60 * 60 * 24)).toInt()

                        if (diferenciaDias < 1) {
                            Toast.makeText(
                                this,
                                "La fecha final debe ser posterior a la fecha de inicio",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                        // Mostramos el dialogo de confirmación
                        mostrarConfirmacionContratacion(precioFinal, puntos)
                    } else {
                        Toast.makeText(
                            this,
                            "Por favor, introduce fechas válidas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        "Por favor, introduce un precio válido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this, "El campo de precio está vacío", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imgContratoCalculadora.setOnClickListener {
            val fechaInicio = binding.etxtContratoFechaInicio.text.toString()
            val fechaFinal = binding.etxtContratoFechaFinal.text.toString()
            val puntos = (binding.etxtContratoPuntos.text.toString().ifEmpty { "0" }).toInt()
            var puntosPersonal: Int = sharedPreferences.getString("puntos", "0")?.toIntOrNull() ?: 0

            if (puntos < 0) {
                Toast.makeText(this, "Los puntos no pueden ser negativos", Toast.LENGTH_SHORT)
                    .show()
                binding.etxtContratoPuntos.setText("0")
                return@setOnClickListener
            }

            if (puntos > puntosPersonal) {
                Toast.makeText(this, "No tienes tantos puntos", Toast.LENGTH_SHORT).show()
                binding.etxtContratoPuntos.setText("0")
                return@setOnClickListener
            }

            var descuento: Double = puntos.toInt().toDouble() / 100.0
            println(descuento)
            calcularPrecioTotal(fechaInicio, fechaFinal, precioVehiculo, descuento)
        }

        binding.btnContratoCancelar.setOnClickListener() {
            val intent2 = Intent(this, Principal::class.java)
            startActivity(intent2)
        }


        cargarDatos()

        // Inicializa el DrawerLayout
        drawerLayout = findViewById<DrawerLayout>(R.id.layoutContratoDrawer)


        // Comprobar el modo previamente guardado
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        setTheme(isDarkMode)

        // Para la funcionalidad de los botones del menu de abajo
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navContratoInferior)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.principal -> {
                    startActivity(Intent(this, Principal::class.java))
                    true
                }

                R.id.filtrarBusqueda -> {
                    startActivity(Intent(this, FilterActivity::class.java))
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
        binding.navContratoLateral.setNavigationItemSelectedListener { item ->
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

                R.id.contactoTelefono -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:637 65 02 50")
                    startActivity(intent)
                    true
                }

                R.id.contactoCorreo -> {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:carkier@email.com")
                    }
                    startActivity(Intent.createChooser(intent, "Enviar correo con..."))
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

    fun mostrarConfirmacionContratacion(precioFinal: Double, puntos: Int) {
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
            crearContrato(precioFinal, puntos)
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

    fun crearContrato(precioFinal: Double, puntos: Int) {
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
            fechaFin = fechaFinal,
            pagado = false
        )

        // Aquí puedes realizar la llamada a la API para crear el contrato o guardarlo localmente
        // Ejemplo de llamada a la API:
        // RetrofitClient.instance.crearContrato(contrato).enqueue(....)
        crearContrato(contrato) { exito ->
            if (exito) {
                Log.d("CrearContrato", "Contrato registrado con éxito")
                Log.d("Contrato", "Contrato creado: $contrato")
            } else {
                Log.e("CrearContrato", "Error al registrar el contrato")
            }
            update(puntos, fechaInicio, fechaFinal)
            val intent = Intent(this, VerContratos::class.java)
            startActivity(intent)

        }
    }

    private fun update(puntos: Int, fechaInicioStr: String, fechaFinalStr: String) {

        // Convertir las fechas de String a Date
        val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale("es", "ES"))
        val fechaInicio = formatoFecha.parse(fechaInicioStr)
        val fechaFinal = formatoFecha.parse(fechaFinalStr)


        // Usamos Calendar para contar los días de forma más precisa
        val calendarInicio = Calendar.getInstance()
        val calendarFinal = Calendar.getInstance()

        // Establecemos las fechas en los calendarios
        calendarInicio.time = fechaInicio
        calendarFinal.time = fechaFinal

        // Calculamos la diferencia en días
        val diferenciaDias =
            ((calendarFinal.timeInMillis - calendarInicio.timeInMillis) / (1000 * 60 * 60 * 24)).toInt()
        // Calcular el precio total
        val puntosAumentado = diferenciaDias * 15
        var puntosPersonal: Int = sharedPreferences.getString("puntos", "0")?.toIntOrNull() ?: 0
        var puntosGastados: Int = puntos;
        // Lo que haremos es restar los puntos gastados y aumentamso 15 puntos por dias
        puntosPersonal = puntosPersonal - puntosGastados + puntosAumentado

        val datosUsuarios = DatosUsuarios(
            id = sharedPreferences.getLong("id", -1),
            puntos = puntosPersonal
        )
        val call = RetrofitClient.instance.updateUsuario(datosUsuarios)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    // Handle successful response (updated data, HTTP 200)
                    val result = response.body()
                    Log.d("API", "User updated: $result")
                } else {
                    // Handle failed response (e.g., not found, HTTP 404)
                    Log.e("API", "Failed to update user: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // Handle failure to connect to the server
                Log.e("API", "Error: ${t.message}")
            }
        })

        val editor = sharedPreferences.edit()
        editor.putString("puntos", puntosPersonal.toString())  // Clave: "puntos", Valor: "100"
        editor.apply()  // Aplica los cambios de manera asíncrona
        cargarDatos()

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
    fun calcularPrecioTotal(
        fechaInicioStr: String,
        fechaFinalStr: String,
        precioDia: Double,
        descuento: Double
    ) {
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
                        (diferenciaDias * binding.etxtContratoPrecioDia.text.toString()
                            .toDouble()) - descuento

                    // Mostrar el precio total
                    val precioTotalFormateado = String.format("%.2f ", precioTotal)
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

    // Cambia la función crearComentario para aceptar un callback
    fun crearContrato(contrato: Contrato, onComplete: (Boolean) -> Unit) {
        RetrofitClient.instance.contratoCrear(contrato).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    onComplete(true) // Éxito en la creación del contrato
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("Crear Contrato error", "Error: $errorBody")
                    onComplete(false) // Fallo en la creación del contrato
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("Crear Contrato error", "Fallo en la red: ${t.message}")
                onComplete(false) // Fallo en la solicitud (problema de conexión, timeout, etc.)
            }
        })
    }

    fun cargarDatos() {
        val nombreUsuario = sharedPreferences.getString("nombre", "")
        val correoUsuario = sharedPreferences.getString("correo", "correo@ejemplo.com")
        var puntosUsario = sharedPreferences.getString("puntos", "0 CarPoints")
        puntosUsario = puntosUsario + " CarPoints"
        val headerView =
            binding.navContratoLateral.getHeaderView(0) // Obtener el header del NavigationView
        val nombreTextView: TextView = headerView.findViewById(R.id.nombre)
        val correoTextView: TextView = headerView.findViewById(R.id.correo)
        val puntosTextView: TextView = headerView.findViewById(R.id.puntos)

        // Asignar los valores recuperados a los TextViews del header
        nombreTextView.text = nombreUsuario
        correoTextView.text = correoUsuario
        puntosTextView.text = puntosUsario

    }

    suspend fun obtenerVehiculoPorId(vehiculoId: Long): Vehiculos? {
        return try {
            val response = RetrofitClient.instance.VehiculoId(vehiculoId).awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("VehiculoAPI", "Error en la petición: ${e.message}")
            null
        }
    }

    fun ImageView.setBase64Image(base64Image: String): Bitmap? {
        return try {
            // Eliminar el prefijo "data:image/png;base64,"
            val imageData = base64Image.substringAfter(",")
            val imageBytes = Base64.decode(imageData, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            if (bitmap != null) {
                this.setImageBitmap(bitmap)
                bitmap // Devuelve el bitmap
            } else {
                Log.e("ImageView", "El bitmap es nulo")
                null
            }
        } catch (e: Exception) {
            Log.e("ImageView", "Error al establecer la imagen", e)
            null
        }
    }

    fun ImageView.setScaledImage(bitmap: Bitmap) {
        // Obtener el ancho y alto del ImageView
        val width = this.width
        val height = this.height

        // Escalar el bitmap al tamaño del ImageView
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

        // Establecer el bitmap escalado en la ImageView
        this.setImageBitmap(scaledBitmap)
    }

}
