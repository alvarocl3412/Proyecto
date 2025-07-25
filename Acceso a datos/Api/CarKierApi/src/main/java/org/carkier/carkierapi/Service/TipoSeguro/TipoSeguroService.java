package org.carkier.carkierapi.Service.TipoSeguro;

import org.carkier.carkierapi.modelos.TipoSeguro.TipoSeguro;
import java.util.List;
import java.util.Optional;

public interface TipoSeguroService {
    List<TipoSeguro> findAll();
    Optional<TipoSeguro> findById(Integer id);
    TipoSeguro findByNombre(String nombre);
    Optional<TipoSeguro> updateSeguro(TipoSeguro seguro);
    TipoSeguro save(TipoSeguro seguro);

    void deleteById(Integer id);
}
