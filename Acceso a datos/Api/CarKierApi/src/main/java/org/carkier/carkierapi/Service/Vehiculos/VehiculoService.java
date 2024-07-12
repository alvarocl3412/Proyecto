package org.carkier.carkierapi.Service.Vehiculos;

import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    List<Vehiculo> findAll();

    Optional<Vehiculo> findById(Integer id);
    Optional<Vehiculo> updateVehiuclo(Vehiculo vehiculo);

    Vehiculo save(Vehiculo vehiculo);
    void deleteById(Integer id);
}
