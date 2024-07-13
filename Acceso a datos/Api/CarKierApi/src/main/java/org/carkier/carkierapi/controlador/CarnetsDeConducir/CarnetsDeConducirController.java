package org.carkier.carkierapi.controlador.CarnetsDeConducir;

import org.carkier.carkierapi.Service.CarnetsDeConducir.CarnetsDeConducirService;
import org.carkier.carkierapi.modelos.CarnetsDeConducir.CarnetsDeConducir;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class CarnetsDeConducirController {

    private final CarnetsDeConducirService servicio;

    public CarnetsDeConducirController(CarnetsDeConducirService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/mostrarCarnets")
    public ResponseEntity<List<CarnetsDeConducir>> getAllCarnets() {
        List<CarnetsDeConducir> carnets = servicio.findAll();
        if (carnets.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(carnets);
    }
    @GetMapping("/CarnetsId/{id}")
    public ResponseEntity<CarnetsDeConducir> getCarnetById(@PathVariable Integer id) {
        Optional<CarnetsDeConducir> carnets = servicio.findById(id);
        if (!carnets.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            CarnetsDeConducir carnet = carnets.get();
            return ResponseEntity.ok(carnet);
        }
    }

    @PutMapping("/updateCarnets")
    public ResponseEntity<CarnetsDeConducir> updateCarnet(@RequestBody CarnetsDeConducir vehiculo) {
        Optional<CarnetsDeConducir> updatedCarnet = servicio.updateCarnet(vehiculo);
        return updatedCarnet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/registrarCarnets")
    public ResponseEntity<String> crearCarnet(@RequestBody CarnetsDeConducir carnet) {
        CarnetsDeConducir nuevoCarnet = servicio.save(carnet);
        if (nuevoCarnet != null) {
            return ResponseEntity.ok("Carnet de conducir ha sido registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el carnet de conducir");
        }
    }

    @DeleteMapping("deleteCarnets/{id}")
    public ResponseEntity<Void> deleteCarnet(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}