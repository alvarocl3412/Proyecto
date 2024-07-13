package org.carkier.carkierapi.Service.DatosDelUsuario;

import org.carkier.carkierapi.modelos.DatosDelUsuario.DatosDelUsuario;
import java.util.List;
import java.util.Optional;

public interface DatosDelUsuarioService {
    List<DatosDelUsuario> findAll();
    Optional<DatosDelUsuario> findById(Integer id);
    Optional<DatosDelUsuario> updateDatos(DatosDelUsuario datos);
    DatosDelUsuario banearUsuario(Integer id);
    DatosDelUsuario marcarEliminarUsuario(Integer id);
}
