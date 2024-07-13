package org.carkier.carkierapi.controlador.DatosDelUsuario;

import org.carkier.carkierapi.Service.DatosDelUsuario.DatosDelUsuarioService;
import org.carkier.carkierapi.modelos.DatosDelUsuario.DatosDelUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class DatosDelUsuarioController {

    private final DatosDelUsuarioService servicio;

    public DatosDelUsuarioController(DatosDelUsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/DatosUsuarios/findAll")
    public ResponseEntity<List<DatosDelUsuario>> getAllDatos() {
        List<DatosDelUsuario> datosusu = servicio.findAll();
        if (datosusu.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(datosusu);
    }

    @GetMapping("/DatosUsuariosId/{id}")
    public ResponseEntity<DatosDelUsuario> getDatosById(@PathVariable Integer id) {
        Optional<DatosDelUsuario> datos = servicio.findById(id);
        if (!datos.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            DatosDelUsuario usur = datos.get();
            return ResponseEntity.ok(usur);
        }
    }

    @PutMapping("/updateDatosUsuarios")
    public ResponseEntity<DatosDelUsuario> updateDatos(@RequestBody DatosDelUsuario datos) {
        Optional<DatosDelUsuario> datosActualizado = servicio.updateDatos(datos);
        return datosActualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/DatosDelUsuarioBanear/{id}")
    public ResponseEntity<DatosDelUsuario> BanearUsuario(@PathVariable Integer id) {
        DatosDelUsuario actualizado = servicio.banearUsuario(id);
        return ResponseEntity.ok(actualizado);
    }

    @PutMapping("/DatosDelUsuarioMarcarEliminar/{id}")
    public ResponseEntity<DatosDelUsuario> EliminarUsuario(@PathVariable Integer id) {
        DatosDelUsuario actualizado = servicio.marcarEliminarUsuario(id);
        return ResponseEntity.ok(actualizado);
    }

}
