package es.ua.eps.carkier.CrearCuenta

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.Carnets.MostrarCarnets
import es.ua.eps.carkier.Modelos.CarnetConducir
import es.ua.eps.carkier.Modelos.TipoCarnet
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.databinding.ActivityModificarCarnetsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ModificarCarnets : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityModificarCarnetsBinding
    private lateinit var editTextDate: EditText
    private val calendar = Calendar.getInstance()
    private lateinit var listaTiposCarnet: List<TipoCarnet>
    private var idTipoCarnetSeleccionado: Long? = null
    private lateinit var _carnet: CarnetConducir;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModificarCarnetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CargarSpinner()
        // Recuperar nombre y correo del SharedPreferences
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        val id: Long = sharedPreferences.getLong("id", 0)

        // Cargar el Spinner y configurar el Listener

        _carnet = crearCarnet()

        // No llamar a inicializarComponentes inmediatamente. Espera hasta que la listaTiposCarnet esté cargada.
        binding.spnTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                idTipoCarnetSeleccionado = listaTiposCarnet[position].id?.toLong()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                idTipoCarnetSeleccionado = null
            }
        }

        // Inicializa editTextDate
        editTextDate = binding.calendarFechaExpedicion as EditText
        updateDateLabel()
        editTextDate.setOnClickListener { mostrarCalendario() }

        binding.btnCrearCarnet.setOnClickListener {
            if (idTipoCarnetSeleccionado != null && editTextDate.text.isNotEmpty()) {
                _carnet.fechaExpedicion = binding.calendarFechaExpedicion.text.toString()
                modificarCarnet(_carnet)
                startActivity(Intent(this, MostrarCarnets::class.java))
            } else {
                Toast.makeText(
                    this,
                    "Selecciona un tipo de carnet y una fecha válida",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun crearCarnet(): CarnetConducir {
        // Recuperamos el ID del usuario desde SharedPreferences
        val idcarnet = intent.getLongExtra("idCarnet", 0L)
        val idUsuario = intent.getLongExtra("idUsuarios", 0L)

        // Recuperamos la fecha de expedición desde el EditText
        val fechaExpedicion: String = intent.getStringExtra("fechaExpedicion").toString()

        // Verificamos si se ha seleccionado un tipo de carnet en el Spinner
        val idTipoCarnet = intent.getLongExtra("tipo", 0L)

        // Devolvemos el objeto CarnetConducir con los valores correspondientes
        return CarnetConducir(
            id = idcarnet,
            idusuario = idUsuario,
            idTipocarnet = idTipoCarnet,
            fechaExpedicion = fechaExpedicion
        )
    }

    fun CargarSpinner() {
        RetrofitClient.instance.TiposCarnet().enqueue(object : Callback<List<TipoCarnet>> {
            override fun onResponse(
                call: Call<List<TipoCarnet>>,
                response: Response<List<TipoCarnet>>
            ) {
                if (response.isSuccessful) {
                    val listaTipos = response.body() ?: emptyList()

                    // Convertimos la lista de tipos de carnet a una lista de TipoCarnetSpinner (con id y nombre)
                    listaTiposCarnet = listaTipos.map { TipoCarnet(it.id ?: 0, it.nombre) }

                    // Crear el adaptador y asignarlo al spinner
                    val spinnerAdapter = ArrayAdapter(
                        this@ModificarCarnets,
                        android.R.layout.simple_spinner_item,
                        listaTiposCarnet
                    )
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spnTipo.adapter = spinnerAdapter

                    // Llamamos a inicializarComponentes después de haber cargado el spinner
                    inicializarComponentes(_carnet)
                } else {
                    Toast.makeText(
                        this@ModificarCarnets,
                        "Error al cargar los tipos de carnet",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<TipoCarnet>>, t: Throwable) {
                Toast.makeText(
                    this@ModificarCarnets,
                    "Error de conexión: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun inicializarComponentes(_carnet: CarnetConducir) {
        if (::listaTiposCarnet.isInitialized) { // Verifica si listaTiposCarnet ya fue cargada
            // Establecer la fecha de expedición en el EditText
            binding.calendarFechaExpedicion.setText(_carnet.fechaExpedicion)

            // Buscar el índice del tipo de carnet en la lista de Spinner para seleccionarlo
            val posicion = listaTiposCarnet.indexOfFirst { it.id.toLong() == _carnet.idTipocarnet }

            // Si encontramos el tipo de carnet, lo seleccionamos en el Spinner
            if (posicion != -1) {
                binding.spnTipo.setSelection(posicion)
            }
        } else {
            // Si listaTiposCarnet aún no ha sido cargado, puedes mostrar un mensaje o hacer algo al respecto
            Toast.makeText(
                this@ModificarCarnets,
                "Esperando los datos de tipos de carnet",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun mostrarCalendario() {
        val calendario = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateLabel()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        calendario.show()
    }

    fun updateDateLabel() {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        editTextDate.setText(sdf.format(calendar.time))
    }

    fun modificarCarnet(carnet: CarnetConducir) {
        RetrofitClient.instance.updateCarnet(carnet).enqueue(object : Callback<CarnetConducir> {
            override fun onResponse(
                call: Call<CarnetConducir>,
                response: Response<CarnetConducir>
            ) {
                if (response.isSuccessful) {
                    // Verificamos que la respuesta no sea nula
                    val updatedCarnet = response.body()
                    if (updatedCarnet != null) {
                        // El carnet se ha actualizado correctamente, podemos tomar las acciones necesarias
                        Toast.makeText(
                            this@ModificarCarnets,
                            "Carnet actualizado correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Si es necesario, redirigir al usuario a otra actividad, como MostrarCarnets
                        startActivity(Intent(this@ModificarCarnets, MostrarCarnets::class.java))
                    } else {
                        // Si la respuesta es exitosa pero no devuelve datos
                        Toast.makeText(
                            this@ModificarCarnets,
                            "La respuesta del servidor no contiene datos del carnet",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // Error en la respuesta de la API (código de error 4xx, 5xx, etc.)
                    Toast.makeText(
                        this@ModificarCarnets,
                        "Error al actualizar el carnet. Código de error: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<CarnetConducir>, t: Throwable) {
                // Si hay un error de conexión o cualquier otro error no relacionado con la API
                Toast.makeText(
                    this@ModificarCarnets,
                    "Error de conexión: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}