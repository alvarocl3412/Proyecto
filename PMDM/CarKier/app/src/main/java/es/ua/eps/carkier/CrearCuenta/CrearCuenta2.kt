package es.ua.eps.carkier.CrearCuenta

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import es.ua.eps.carkier.InicioSesion
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.Principal
import es.ua.eps.carkier.R
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

        val telefono = (findViewById<TextInputEditText>(R.id.etxtTelefono)).text?.toString()
        val correo = (findViewById<TextInputEditText>(R.id.etxtCorreo)).text?.toString()
        val contra = (findViewById<TextInputEditText>(R.id.etxtContra)).text?.toString()

        // Creamos el usuario, dejando la fecha como String
        val usuarios = Usuarios(
            dni = dni,
            nombre = nombre,
            apellidos = apellidos,
            telefono = telefono.toString(),
            correo = correo.toString(),
            contrasena = contra.toString(),
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
         // Redirige a la actividad principal
         val correo = (findViewById<TextInputEditText>(R.id.etxtCorreo)).text?.toString()
         val contra = (findViewById<TextInputEditText>(R.id.etxtContra)).text?.toString()

         comprobarExistente(correo.toString(), contra.toString(),1)
     }

     fun noAgregarCarnet() {
         val correo = (findViewById<TextInputEditText>(R.id.etxtCorreo)).text?.toString()
         val contra = (findViewById<TextInputEditText>(R.id.etxtContra)).text?.toString()
         comprobarExistente(correo.toString(), contra.toString(),2)

    }

    private fun comprobarExistente(email: String, contra: String, opcionNum: Int) {
        RetrofitClient.instance.loginUsuario(email, contra).enqueue(object : Callback<Usuarios> {
            override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, obtener el usuario y guardar datos
                    val usuario = response.body()
                    Toast.makeText(this@CrearCuenta2, "Bienvenido ${usuario?.nombre}", Toast.LENGTH_SHORT).show()
                    guardarDatosUsuario(usuario)

                    if (opcionNum == 1) {
                        val intent = Intent(this@CrearCuenta2, CrearCarnetDeConducir::class.java)
                        startActivity(intent)
                    } else {
                        val intent2 = Intent(this@CrearCuenta2, Principal::class.java)
                        startActivity(intent2)
                    }

                    //Terminamos la actividad para que no regresa a ella
                    finish()
                } else {
                    // Maneja la respuesta no exitosa
                    Toast.makeText(this@CrearCuenta2, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                // Maneja errores de conexión
                Toast.makeText(this@CrearCuenta2, "Fallo: ${t.message}", Toast.LENGTH_SHORT).show()
            }

            // Método para guardar los detalles del usuario en SharedPreferences
            private fun guardarDatosUsuario(usuario: Usuarios?) {
                val editor = sharedPreferences.edit()
                // Guardar detalles del usuario
                editor.putBoolean("recordarme", true) // Ya no depende de un checkbox, siempre guardamos
                editor.putLong("id", usuario?.id ?: 0)
                editor.putString("dni", usuario?.dni)
                editor.putString("nombre", usuario?.nombre)
                editor.putString("apellido", usuario?.apellidos)
                editor.putString("telefono", usuario?.telefono)
                editor.putString("correo", usuario?.correo)
                editor.putString("contrasena", usuario?.contrasena)
                editor.putString("fechaNac", usuario?.fechaNacimiento.toString())
                editor.apply() // Aplica los cambios
            }
        })

    }
}