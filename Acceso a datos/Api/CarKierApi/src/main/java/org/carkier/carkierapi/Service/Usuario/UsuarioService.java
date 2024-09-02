package org.carkier.carkierapi.Service.Usuario;

import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
    Optional<Usuario>findByDni(String dni);

    Optional<Usuario>findByNombreAndApellidos(String nombre, String apellidos);
    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
    Optional<Usuario> findByCorreoAndContrasenaAdmin(String correo, String contrasena);
    Optional<Usuario> updateUsuario(Usuario usuario);
    Usuario save(Usuario usuario);
}
