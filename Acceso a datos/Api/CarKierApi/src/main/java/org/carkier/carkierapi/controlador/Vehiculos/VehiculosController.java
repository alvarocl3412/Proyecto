package org.carkier.carkierapi.controlador.Vehiculos;

import org.carkier.carkierapi.Service.Vehiculos.VehiculoService;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class VehiculosController {
    private final VehiculoService servicio;

    public VehiculosController(VehiculoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/VehiculosfindAll")
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> usuarios = servicio.findAll();
        if (usuarios.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/VehiuculosId/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Integer id) {
        Optional<Vehiculo> vehiculo = servicio.findById(id);
        if (!vehiculo.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehiculo.get());
        }
    }   

    @GetMapping("/VehiuculosMatricula/{matricula}")
    public ResponseEntity<Vehiculo> getVehiculoByMatricula(@PathVariable String matricula) {
        Optional<Vehiculo> vehiculo = servicio.findByMatricula(matricula);
        if (!vehiculo.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehiculo.get());
        }
    }

    @GetMapping("/VehiculosMarca/{marca}")
    public ResponseEntity<List<Vehiculo>> getVehiculoByMarca(@PathVariable String marca) {
        List<Vehiculo> vehiculo = servicio.findByMarca(marca);
        return ResponseEntity.ok(vehiculo);

    }

    @GetMapping("/estado/1")
    public ResponseEntity<List<Vehiculo>> obtenerVehiculosConEstado1() {
        List<Vehiculo> vehiculos = servicio .obtenerVehiculosPorEstado(1L);
        return ResponseEntity.ok(vehiculos);
    }

    @PutMapping("/updateVehiculo")
    public ResponseEntity<Vehiculo> updateVehiculo(@RequestBody Vehiculo vehiculo) {
        Optional<Vehiculo> updatedVehiculo = servicio.updateVehiuclo(vehiculo);
        return updatedVehiculo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/CrearVehiculo")
    public ResponseEntity<String> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoNuevo = servicio.save(vehiculo);
        if (vehiculoNuevo != null) {
            return ResponseEntity.ok("Vehiculo registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el vehiculo");
        }
    }

    @DeleteMapping("deleteVehiculo/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/busquedaCoche")
    public ResponseEntity<List<Vehiculo>> busquedaCoche(@RequestBody Vehiculo filtro) {
        List<Vehiculo> vehiculosEncontrados = servicio.busquedaCoche(filtro);
        if (vehiculosEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehiculosEncontrados);
        }
    }
}
