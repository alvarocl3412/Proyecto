package org.carkier.carkierapi.Repositorio.EstadoVehiculo;

import org.carkier.carkierapi.modelos.EstadoVehiculo.EstadoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoVehiculoRepository extends JpaRepository<EstadoVehiculo, Integer> {
    EstadoVehiculo findByEstado(String estado);
}
