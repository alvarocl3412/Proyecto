package org.carkier.carkierapi.controlador.Empresa;

import org.carkier.carkierapi.Service.Empresa.EmpresaService;
import org.carkier.carkierapi.modelos.Empresa.Empresa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class EmpresaController {

    private  final EmpresaService servicio;

    public EmpresaController(EmpresaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/Empresas/findAll")
    public ResponseEntity<List<Empresa>> getAllEmpresa() {
        List<Empresa> empresas = servicio.findAll();
        if (empresas.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(empresas);
    }
    @GetMapping("/EmpresasId/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Integer id) {
        Optional<Empresa> empresa = servicio.findById(id);
        if (!empresa.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(empresa.get());
        }
    }

    @PutMapping("/updateEmpresa")
    public ResponseEntity<Empresa> updateEmpresa(@RequestBody Empresa empresa) {
        Optional<Empresa> updatedEmpresa = servicio.updateEmpresa(empresa);
        return updatedEmpresa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/CrearEmpresa")
    public ResponseEntity<String> crearEmpresa(@RequestBody Empresa empresa) {
        Empresa empresaNueva = servicio.save(empresa);
        if (empresaNueva != null) {
            return ResponseEntity.ok("Empresa registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la empresa");
        }
    }

    @DeleteMapping("CarnetsDelete/{id}")
    public ResponseEntity<Void> deleteCarnets(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
