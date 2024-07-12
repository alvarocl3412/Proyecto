package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional <Usuario> findByDni(String dni);
    Optional<Usuario> findByCorreo(String corre);
}
