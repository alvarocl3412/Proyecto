package org.carkier.carkierapi.Repositorio.TipoCarnet;

import org.carkier.carkierapi.modelos.TipoCarnet.TipoCarnet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoCarnetRepository extends JpaRepository<TipoCarnet, Integer> {
    TipoCarnet findByNombre(String nombre);
}
