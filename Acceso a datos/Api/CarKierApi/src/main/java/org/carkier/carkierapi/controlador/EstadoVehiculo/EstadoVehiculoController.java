package org.carkier.carkierapi.controlador.EstadoVehiculo;

import org.carkier.carkierapi.Service.EstadoVehiculo.EstadoVehiculoService;
import org.carkier.carkierapi.modelos.EstadoVehiculo.EstadoVehiculo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class EstadoVehiculoController {

    private final EstadoVehiculoService servicio;

    public EstadoVehiculoController(EstadoVehiculoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/EstadoVehiculo/findAll")
    public ResponseEntity<List<EstadoVehiculo>> getAllEstadoVehiculos() {
        List<EstadoVehiculo> estado = servicio.findAll();
        if (estado.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(estado);
    }
    @GetMapping("/EstadoVehiculoId/{id}")
    public ResponseEntity<EstadoVehiculo> getEstadoVehiculotById(@PathVariable  Integer id) {
        Optional<EstadoVehiculo> estados = servicio.findById(id);
        if (!estados.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            EstadoVehiculo estado = estados.get();
            return ResponseEntity.ok(estado);
        }
    }
    @GetMapping("/EstadoVehiculoEstado/{estado}")
    public ResponseEntity<EstadoVehiculo> getEstadoVehiculoByEstado(@PathVariable  String estado) {
        EstadoVehiculo estados = servicio.findByEstado(estado.toUpperCase());
        if (estados == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(estados);
        }
    }
    @PostMapping("/EstadoVehiculoSave")
    public ResponseEntity<?> crearTipoCarnet(@RequestParam String estado) {
        EstadoVehiculo estadoCreado = servicio.save(estado.toUpperCase());
        if (estadoCreado == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un estado con ese nombre");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoCreado);
        }
    }

    @DeleteMapping("EstadoVehiculoDelete/{id}")
    public ResponseEntity<Void> deleteEstadoVehiculo(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}