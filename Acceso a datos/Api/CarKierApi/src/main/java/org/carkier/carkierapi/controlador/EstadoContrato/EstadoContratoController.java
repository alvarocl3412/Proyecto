package org.carkier.carkierapi.controlador.EstadoContrato;

import org.carkier.carkierapi.Service.EstadoContrato.EstadoContratoService;
import org.carkier.carkierapi.modelos.EstadoContratos.EstadoContrato;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class EstadoContratoController {
    private  final EstadoContratoService servicio;
    public EstadoContratoController(EstadoContratoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/EstadoContratofindAll")
    public ResponseEntity<List<EstadoContrato>> getAllEstadoContrato() {
        List<EstadoContrato> estado = servicio.findAll();
        if (estado.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(estado);
    }

    @GetMapping("/EstadoContratoId/{id}")
    public ResponseEntity<EstadoContrato> getEstadoContratoById(@PathVariable Integer id) {
        Optional<EstadoContrato> estados = servicio.findById(id);
        if (!estados.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            EstadoContrato estado = estados.get();
            return ResponseEntity.ok(estado);
        }
    }
    @GetMapping("/EstadoContratoEstado/{estado}")
    public ResponseEntity<EstadoContrato> getEstadoContratoByEstado(@PathVariable  String estado) {
        EstadoContrato estados = servicio.findByEstado(estado.toUpperCase());
        if (estados == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(estados);
        }
    }
    @PostMapping("/crearEstadoContrato")
    public ResponseEntity<?> crearEstadoContrato(@RequestParam String estado) {
        EstadoContrato estadoCreado = servicio.save(estado.toUpperCase());
        if (estadoCreado == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un estado  del contrato con ese nombre");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoCreado);
        }
    }

    @DeleteMapping("/deleteEstadoContrato/{id}")
    public ResponseEntity<Void> deleteEstadoContrato(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
