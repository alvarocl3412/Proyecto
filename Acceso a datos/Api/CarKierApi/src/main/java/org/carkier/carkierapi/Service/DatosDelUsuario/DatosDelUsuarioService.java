package org.carkier.carkierapi.Service;

import org.carkier.carkierapi.modelos.DatosDelUsuario;

import java.util.List;
import java.util.Optional;

public interface DatosDelUsuarioService {
    List<DatosDelUsuario> findAll();

    Optional<DatosDelUsuario> findById(Integer id);

    DatosDelUsuario updatePrecio(Integer id, Integer puntos);
    DatosDelUsuario updateAdmin(Integer id, Boolean admin);
    DatosDelUsuario banearUsuario(Integer id);
    DatosDelUsuario marcarEliminarUsuario(Integer id);
}
