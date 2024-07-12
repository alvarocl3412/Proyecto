package org.carkier.carkierapi.controlador;

import jakarta.validation.constraints.NotBlank;
import org.carkier.carkierapi.Service.TipoCarnet.TipoCarnetService;
import org.carkier.carkierapi.modelos.TipoCarnet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class TipoCarnetController {
    private final TipoCarnetService servicio;
    public TipoCarnetController(TipoCarnetService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/TipoCarnet/findAll")
    public ResponseEntity<List<TipoCarnet>> getAllJugadores() {
        List<TipoCarnet> jugadores = servicio.findAll();
        if (jugadores.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(jugadores);
    }
    @GetMapping("/TipoCarnetNombre/{nombre}")
    public ResponseEntity<TipoCarnet> getTipoCarnetByNombre(@PathVariable  String nombre) {
        TipoCarnet idTipo = servicio.findByNombre(nombre.toUpperCase());
        if (idTipo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(idTipo);
        }
    }
    @GetMapping("/TipoCarnetId/{id}")
    public ResponseEntity<TipoCarnet> getTipoCarnetById(@PathVariable Integer id) {
        Optional<TipoCarnet> tipoCarnetOptional = servicio.findById(id);
        if (!tipoCarnetOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            TipoCarnet tipoCarnet = tipoCarnetOptional.get();
            return ResponseEntity.ok(tipoCarnet);
        }
    }

    @PostMapping("/TipoCarnetsave")
    public ResponseEntity<?> crearTipoCarnet(@RequestParam String nombre) {
        TipoCarnet tipoCarnetCreado = servicio.save(nombre.toUpperCase());

        if (tipoCarnetCreado == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un tipo de carnet con ese nombre");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoCarnetCreado);
        }
    }
    @DeleteMapping("/TipoCarnetDelete/{id}")
    public ResponseEntity<Void> deleteTipoCarnet(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}