package org.carkier.carkierapi.Service.Usuario;

import org.carkier.carkierapi.modelos.Usuarios.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
    Optional<Usuario>findByDni(String dni);
    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
    Usuario save(Usuario usuario);
}
