package org.carkier.carkierapi.Service.Vehiculos;

import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    List<Vehiculo> findAll();

    Optional<Vehiculo> findById(Integer id);

    Optional<Vehiculo> findByMatricula(String matricula);
    Optional<Vehiculo> updateVehiuclo(Vehiculo vehiculo);

    List<Vehiculo> findByMarca(String marca);
    List<Vehiculo> obtenerVehiculosPorEstado(Long idEstado);

    Vehiculo save(Vehiculo vehiculo);
    void deleteById(Integer id);

    List<Vehiculo> busquedaCoche(Vehiculo filtro);

}
