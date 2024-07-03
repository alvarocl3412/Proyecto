package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.EstadoContrato;
import org.carkier.carkierapi.modelos.EstadoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoContratoRepository extends JpaRepository<EstadoContrato, Integer> {
    EstadoContrato findByEstado(String estado);
}
