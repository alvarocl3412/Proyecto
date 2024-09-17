package es.ua.eps.carkier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.databinding.ActivityCrearCarnetDeConducirBinding

class CrearCarnetDeConducir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCrearCarnetDeConducirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCrearCarnet.setOnClickListener(){
            val intent = Intent(this, CrearCuenta2::class.java)
            startActivity(intent)
        }
    }
}