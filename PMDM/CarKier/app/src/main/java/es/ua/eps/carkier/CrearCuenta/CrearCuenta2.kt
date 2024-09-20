package es.ua.eps.carkier.CrearCuenta

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.Principal
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.databinding.ActivityCrearCuenta2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class CrearCuenta2 : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityCrearCuenta2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearCuenta2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCrear.setOnClickListener(){
            recogerDatos()
            alertaDialogo()
        }
    }
    fun recogerDatos() {
        // Recuperar datos del SharedPreferences
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        val dni = sharedPreferences.getString("dni", "sin dni") ?: "sin dni"
        val nombre = sharedPreferences.getString("nombre", "d") ?: "d"
        val apellidos = sharedPreferences.getString("apellidos", "d") ?: "d"

        // Obtener la fecha de nacimiento como String
        val fechaNac = sharedPreferences.getString("fechaNacimiento", "0000-00-00") ?: "0000-00-00"

        val telefono = binding.etxtTelefono.text.toString()
        val correo = binding.etxtCorreo.text.toString()
        val contra = binding.etxtContra.text.toString()

        // Creamos el usuario, dejando la fecha como String
        val usuarios = Usuarios(
            dni = dni,
            nombre = nombre,
            apellidos = apellidos,
            telefono = telefono,
            correo = correo,
            contrasena = contra,
            fechaNacimiento = fechaNac // Ya no convertimos la fecha a Date
        )

        crearCliente(usuarios)
    }


    fun crearCliente(usuarios: Usuarios) {
        RetrofitClient.instance.crearUsuario(usuarios).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@CrearCuenta2, "Creado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    // Mensaje de error más detallado
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(this@CrearCuenta2, "No se ha podido crear correctamente: $errorBody", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@CrearCuenta2, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("CrearClienteError", "Error: ${t.message}", t)
            }
        })
    }

    fun alertaDialogo() {
        // Crear el AlertDialog
        val builder = AlertDialog.Builder(this)

        // Mensaje que saldra
        builder.setMessage("¿Quieres agregar un carnet de conducir?")
            .setTitle("Confirmación")

        builder.setPositiveButton("Sí") { dialog, id ->
        //Si le dal al si
            agregarCarnetConducir()
        }

        builder.setNegativeButton("No") { dialog, id ->
        //Si le da  al no
            noAgregarCarnet()
        }

        // Crear y mostrar el diálogo
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

     fun agregarCarnetConducir() {
        val intent = Intent(this, CrearCarnetDeConducir::class.java)
        startActivity(intent)
    }

     fun noAgregarCarnet() {
        val intent = Intent(this, Principal::class.java)
        startActivity(intent)
    }
}