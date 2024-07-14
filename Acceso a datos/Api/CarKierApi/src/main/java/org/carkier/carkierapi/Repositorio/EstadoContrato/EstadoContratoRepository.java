package org.carkier.carkierapi.Repositorio.EstadoContrato;

import org.carkier.carkierapi.modelos.EstadoContratos.EstadoContrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoContratoRepository extends JpaRepository<EstadoContrato, Integer> {
    EstadoContrato findByEstado(String estado);
}
