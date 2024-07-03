package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.EstadoVehiculo;
import org.carkier.carkierapi.modelos.TipoCarnet;
import org.carkier.carkierapi.modelos.TipoSeguro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSeguroRepository extends JpaRepository<TipoSeguro, Integer> {
    TipoSeguro findByNombre(String nombre);
}
