package org.carkier.carkierapi.Service;

import org.carkier.carkierapi.modelos.EstadoContrato;
import org.carkier.carkierapi.modelos.EstadoVehiculo;
import org.carkier.carkierapi.modelos.TipoSeguro;

import java.util.List;
import java.util.Optional;

public interface TipoSeguroService {
    List<TipoSeguro> findAll();
    Optional<TipoSeguro> findById(Integer id);
    TipoSeguro findByNombre(String nombre);

    TipoSeguro updatePrecio(Integer id, double precio);
    TipoSeguro save(String nombre,String descripcion ,double precio);

    void deleteById(Integer id);
}
