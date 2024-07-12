package org.carkier.carkierapi.Service.TipoCarnet;

import org.carkier.carkierapi.modelos.TipoCarnet.TipoCarnet;
import java.util.List;
import java.util.Optional;


public interface TipoCarnetService {
    List<TipoCarnet> findAll();
    Optional<TipoCarnet> findById(Integer id);
    TipoCarnet findByNombre(String nombre);

    TipoCarnet save(String nombre);

    void deleteById(Integer id);

}
