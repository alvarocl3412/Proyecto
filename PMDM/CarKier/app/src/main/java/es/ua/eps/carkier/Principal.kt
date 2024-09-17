package es.ua.eps.carkier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.ua.eps.carkier.Modelos.Vehiculos
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
            Vehiculos("Coche 1"),
            Vehiculos("Coche 2"),
            Vehiculos("Coche 3"),
            Vehiculos("Coche 4"),
            Vehiculos("Coche 5")
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
                    cerrarSesion()
                    true
                }
                else -> false
            }
        }
    }


         fun cerrarSesion() {
        // Elimina las preferencias compartidas
        val sharedPreferences = getSharedPreferences("MiPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Borra todas las preferencias
        editor.apply()

        // Redirige al usuario a la actividad de inicio de sesión
        val intent = Intent(this, InicioSesion::class.java)
        startActivity(intent)
        finish() // Termina la actividad actual para que el usuario no pueda regresar a ella
    }
}