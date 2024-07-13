package org.carkier.carkierapi.controlador.Comentarios;

import org.carkier.carkierapi.Service.Comentarios.ComentarioService;
import org.carkier.carkierapi.modelos.Comentarios.Comentario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class ComentarioController {
    private final ComentarioService servicio;

    public ComentarioController(ComentarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/Comentarios/findAll")
    public ResponseEntity<List<Comentario>> getAllComentario() {
        List<Comentario> comentarios = servicio.findAll();
        if (comentarios.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(comentarios);
    }
    @GetMapping("/ComentariosId/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id) {
        Optional<Comentario> comentario = servicio.findById(id);
        if (!comentario.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(comentario.get());
        }
    }

    @PutMapping("/updateComentario")
    public ResponseEntity<Comentario> updateComentario(@RequestBody Comentario comentario) {
        Optional<Comentario> updatedComentario = servicio.updateComentario(comentario);
        return updatedComentario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/CrearComentario")
    public ResponseEntity<String> crearComentario(@RequestBody Comentario Comentario) {
        Comentario comentarioNuevo = servicio.save(Comentario);
        if (comentarioNuevo != null) {
            return ResponseEntity.ok("Comentario registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el comentario");
        }
    }

    @DeleteMapping("deleteComentario/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable  Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
