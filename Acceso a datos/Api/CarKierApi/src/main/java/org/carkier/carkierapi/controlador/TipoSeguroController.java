package org.carkier.carkierapi.controlador;

import jakarta.validation.constraints.NotBlank;
import org.carkier.carkierapi.Service.TipoCarnetService;
import org.carkier.carkierapi.Service.TipoSeguroService;
import org.carkier.carkierapi.modelos.EstadoVehiculo;
import org.carkier.carkierapi.modelos.TipoCarnet;
import org.carkier.carkierapi.modelos.TipoSeguro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TipoSeguroController {

    private final TipoSeguroService servicio;
    public TipoSeguroController(TipoSeguroService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/TipoSeguro/findAll")
    public ResponseEntity<List<TipoSeguro>> getAllSeguros() {
        List<TipoSeguro> seguros = servicio.findAll();
        if (seguros.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(seguros);
    }

    @GetMapping("/TipoSeguroId/{id}")
    public ResponseEntity<TipoSeguro> getSegurosById(@PathVariable  Integer id) {
        Optional<TipoSeguro> seguro = servicio.findById(id);
        if (!seguro.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            TipoSeguro tipo = seguro.get();
            return ResponseEntity.ok(tipo);
        }
    }

    @GetMapping("/TipoSeguroNombre/{nombre}")
    public ResponseEntity<TipoSeguro> getEstadoVehiculoByEstado(@PathVariable  String nombre) {
        TipoSeguro estados = servicio.findByNombre(nombre.toLowerCase());
        if (estados == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(estados);
        }
    }

    @PutMapping("/TipoSeguroModificar/{id}/precio")
    public ResponseEntity<TipoSeguro> updatePrecio(@PathVariable Integer id, @RequestParam Double precio) {
        TipoSeguro actualizado = servicio.updatePrecio(id, precio);
        return ResponseEntity.ok(actualizado);
    }


    @PostMapping("/TipoSeguroSave/{id}/precio")
    public ResponseEntity<TipoSeguro> saveTipoSeguro(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam Double coste) {
        TipoSeguro nuevoTipoSeguro = servicio.save(nombre, descripcion, coste);
        return ResponseEntity.ok(nuevoTipoSeguro);
    }

    @DeleteMapping("TipoSeguroDelete/{id}")
    public ResponseEntity<Void> deleteTipoCarnet(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
