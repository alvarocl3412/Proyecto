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

    @GetMapping("/DatosDelUsuario/findAll")
    public ResponseEntity<List<DatosDelUsuario>> getAllDatos() {
        List<DatosDelUsuario> datosusu = servicio.findAll();
        if (datosusu.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(datosusu);
    }

    @GetMapping("/DatosDelUsuarioId/{id}")
    public ResponseEntity<DatosDelUsuario> getDatosById(@PathVariable Integer id) {
        Optional<DatosDelUsuario> datos = servicio.findById(id);
        if (!datos.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            DatosDelUsuario usur = datos.get();
            return ResponseEntity.ok(usur);
        }
    }

    @PutMapping("/DatosDelUsuarioModificarPrecio/{id}/puntos")
    public ResponseEntity<DatosDelUsuario> updatePrecio(@PathVariable Integer id, @RequestParam Integer puntos) {
        DatosDelUsuario actualizado = servicio.updatePrecio(id, puntos);
        return ResponseEntity.ok(actualizado);
    }

    @PutMapping("/DatosDelUsuarioAdmin/{id}/admin")
    public ResponseEntity<DatosDelUsuario> updateAdmin(@PathVariable Integer id, @RequestParam Boolean admin) {
        DatosDelUsuario actualizado = servicio.updateAdmin(id, admin);
        return ResponseEntity.ok(actualizado);
    }

    @PutMapping("/DatosDelUsuarioMBanear/{id}")
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
