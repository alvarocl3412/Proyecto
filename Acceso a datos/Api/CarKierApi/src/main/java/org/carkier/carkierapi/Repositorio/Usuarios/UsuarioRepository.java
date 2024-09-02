package org.carkier.carkierapi.Repositorio.Usuarios;

import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional <Usuario> findByDni(String dni);
    Optional<Usuario> findByCorreo(String corre);
    Optional<Usuario> findByNombreAndApellidos(String nombre, String apellidos);

}
