package org.carkier.carkierapi.controlador;

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

    @GetMapping("/Carnets/findAll")
    public ResponseEntity<List<CarnetsDeConducir>> getAllCarnets() {
        List<CarnetsDeConducir> carnets = servicio.findAll();
        if (carnets.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(carnets);
    }
    @GetMapping("/CarnetsId/{id}")
    public ResponseEntity<CarnetsDeConducir> getCarnetById(@PathVariable Integer id) {
        Optional<CarnetsDeConducir> usu = servicio.findById(id);
        if (!usu.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            CarnetsDeConducir usur = usu.get();
            return ResponseEntity.ok(usur);
        }
    }

    @PostMapping("/CarnetsRegistrar")
    public ResponseEntity<CarnetsDeConducir> crearCarnet(@RequestBody CarnetsDeConducir carnetDTO) {
        CarnetsDeConducir nuevoCarnet = servicio.save(carnetDTO);
        return new ResponseEntity<>(nuevoCarnet, HttpStatus.CREATED);
    }

}
