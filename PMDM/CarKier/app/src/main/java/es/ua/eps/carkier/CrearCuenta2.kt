package es.ua.eps.carkier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.databinding.ActivityCrearCuenta2Binding

class CrearCuenta2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCrearCuenta2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCrear.setOnClickListener(){
            alertaDialogo()
        }
    }

    private fun alertaDialogo() {
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

    private fun agregarCarnetConducir() {
        val intent = Intent(this, CrearCarnetDeConducir::class.java)
        startActivity(intent)
    }

    private fun noAgregarCarnet() {

    }
}