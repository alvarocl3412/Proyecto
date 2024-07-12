package org.carkier.carkierapi.controlador.TipoSeguro;

import org.carkier.carkierapi.Service.TipoSeguro.TipoSeguroService;
import org.carkier.carkierapi.modelos.TipoSeguro.TipoSeguro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
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
    public ResponseEntity<TipoSeguro> getSeguroByNombre(@PathVariable  String nombre) {
        TipoSeguro estados = servicio.findByNombre(nombre.toLowerCase());
        if (estados == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(estados);
        }
    }

    @PutMapping("/TipoSeguroModificar/{id}/precio")
    public ResponseEntity<TipoSeguro> updatePrecioSeguro(@PathVariable Integer id, @RequestParam Double precio) {
        TipoSeguro actualizado = servicio.updatePrecio(id, precio);
        return ResponseEntity.ok(actualizado);
    }


    @PostMapping("/TipoSeguroSave")
    public ResponseEntity<String> crearTipoSeguro(@RequestBody TipoSeguro seguro) {
        TipoSeguro seguroNueva = servicio.save(seguro);
        if (seguroNueva != null) {
            return ResponseEntity.ok("Seguro registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el segurp");
        }
    }

    @DeleteMapping("TipoSeguroDelete/{id}")
    public ResponseEntity<Void> deleteTipoSeguro(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
