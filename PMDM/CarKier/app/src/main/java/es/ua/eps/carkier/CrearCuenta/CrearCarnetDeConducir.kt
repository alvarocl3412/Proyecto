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
    import android.util.Log
    import android.view.View
    import android.widget.AdapterView
    import android.widget.ArrayAdapter
    import android.widget.Toast
    import es.ua.eps.carkier.Carnets.MostrarCarnets
    import es.ua.eps.carkier.Modelos.CarnetConducir
    import es.ua.eps.carkier.Modelos.TipoCarnet
    import es.ua.eps.carkier.Retrofit.RetrofitClient
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response

    class CrearCarnetDeConducir : AppCompatActivity() {

        private lateinit var sharedPreferences: SharedPreferences
        private lateinit var binding: ActivityCrearCarnetDeConducirBinding
        private lateinit var editTextDate: EditText
        private val calendar = Calendar.getInstance()
        private lateinit var listaTiposCarnet: List<TipoCarnet>
        private var idTipoCarnetSeleccionado: Long? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityCrearCarnetDeConducirBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Recuperar nombre y correo del SharedPreferences
            sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
            val id: Long = sharedPreferences.getLong("id", 0)

            // Cargar el Spinner y configurar el Listener
            CargarSpinner()

            binding.spnTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
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
                    CrearCarnet(id)
                    startActivity(Intent(this, MostrarCarnets::class.java))
                } else {
                    Toast.makeText(this, "Selecciona un tipo de carnet y una fecha válida", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun CargarSpinner() {
            RetrofitClient.instance.TiposCarnet().enqueue(object : Callback<List<TipoCarnet>> {
                override fun onResponse(call: Call<List<TipoCarnet>>, response: Response<List<TipoCarnet>>) {
                    if (response.isSuccessful) {
                        listaTiposCarnet = response.body() ?: emptyList()
                        val nombresTiposCarnet = listaTiposCarnet.map { it.nombre }

                        val spinnerAdapter = ArrayAdapter(this@CrearCarnetDeConducir, android.R.layout.simple_spinner_item, nombresTiposCarnet)
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.spnTipo.adapter = spinnerAdapter
                    } else {
                        Toast.makeText(this@CrearCarnetDeConducir, "Error al cargar los tipos de carnet", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<TipoCarnet>>, t: Throwable) {
                    Toast.makeText(this@CrearCarnetDeConducir, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
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

        fun CrearCarnet(id2: Long) {
            val carnet = CarnetConducir(
                id = null,
                idusuario = id2,
                idTipocarnet = idTipoCarnetSeleccionado ?: return,
                fechaExpedicion = editTextDate.text.toString()
            )

            RetrofitClient.instance.CrearCarnetUsuario(carnet).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@CrearCarnetDeConducir, "Creado correctamente", Toast.LENGTH_SHORT).show()
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Toast.makeText(this@CrearCarnetDeConducir, "No se ha podido crear correctamente: $errorBody", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@CrearCarnetDeConducir, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    Log.e("CrearCarnetError", "Error: ${t.message}", t)
                }
            })
        }
    }