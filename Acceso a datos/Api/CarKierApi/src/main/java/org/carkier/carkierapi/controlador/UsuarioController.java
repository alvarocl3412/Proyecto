package org.carkier.carkierapi.controlador;

import org.carkier.carkierapi.Service.UsuarioService;
import org.carkier.carkierapi.modelos.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class UsuarioController {
    private final UsuarioService servicio;

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/Usuario/findAll")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = servicio.findAll();
        if (usuarios.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/UsuariosBaneados/findAll")
    public ResponseEntity<List<Usuario>> getAllUsuariosBaneados() {
        List<Usuario> usuarios = servicio.findAllUsuariosBaneados();
        if (usuarios.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/UsuarioId/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usu = servicio.findById(id);
        if (!usu.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Usuario usur = usu.get();
            return ResponseEntity.ok(usur);
        }
    }

    @GetMapping("/UsuarioDni/{dni}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String dni) {
        Optional<Usuario> usu = servicio.findByDni(dni);
        if (!usu.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Usuario usur = usu.get();
            return ResponseEntity.ok(usur);
        }
    }

    @PostMapping("/UsuarioInicioSesion")
    public ResponseEntity<?> loginUsuario(@RequestParam String correo, @RequestParam String contrasena) {
        Optional<Usuario> usuario = servicio.findByCorreoAndContrasena(correo, contrasena);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid correo or contrasena");
        }
    }

    @PostMapping("/UsuarioRegistrar")
    public ResponseEntity<String> registerUsuario(@RequestBody Usuario usuario) {
        usuario.setDatosDelUsuario(null);
        Usuario savedUsuario = servicio.save(usuario);
        if (savedUsuario != null) {
            return ResponseEntity.ok("Usuario registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el usuario");
        }
    }
}
