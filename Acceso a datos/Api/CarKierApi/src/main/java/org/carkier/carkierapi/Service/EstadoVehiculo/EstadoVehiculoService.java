package org.carkier.carkierapi.Service.EstadoVehiculo;

import org.carkier.carkierapi.modelos.EstadoVehiculo.EstadoVehiculo;

import java.util.List;
import java.util.Optional;


public interface EstadoVehiculoService {

    List<EstadoVehiculo> findAll();

    Optional<EstadoVehiculo> findById(Integer id);

    EstadoVehiculo findByEstado(String estado);

    EstadoVehiculo save(String estado);

    void deleteById(Integer id);
}
