package es.ua.eps.carkier

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.ua.eps.carkier.databinding.ActivityInicioSesionBinding
import es.ua.eps.carkier.databinding.ActivityPrincipalBinding

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // RecyclerView configuration
        binding.recyclerViewVehiculos?.layoutManager = GridLayoutManager(this, 2) // 2 columnas
        // Datos de ejemplo para los vehículos
        val listaDeVehiculos = listOf(
            Vehiuclo("Coche 1"),
            Vehiuclo("Coche 2"),
            Vehiuclo("Coche 3"),
            Vehiuclo("Coche 4"),
            Vehiuclo("Coche 5")
        )

        // Asignar el adaptador
        val adapter = VehiculoAdapter(listaDeVehiculos)
        binding.recyclerViewVehiculos?.adapter = adapter


        //Para la funcionalidad de los botones del menu de abajo
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                   startActivity(Intent(this, Principal::class.java))
                    true
                }
                R.id.nav_search -> {
                  //  startActivity(Intent(this, SearchActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    //startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}