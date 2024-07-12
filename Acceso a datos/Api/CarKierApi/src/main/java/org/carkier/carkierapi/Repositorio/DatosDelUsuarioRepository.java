package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.DatosDelUsuario.DatosDelUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosDelUsuarioRepository extends JpaRepository<DatosDelUsuario, Integer> {
}
