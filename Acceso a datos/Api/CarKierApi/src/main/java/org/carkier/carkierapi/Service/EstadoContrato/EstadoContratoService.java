package org.carkier.carkierapi.Service;

import org.carkier.carkierapi.modelos.EstadoContrato;
import java.util.List;
import java.util.Optional;

public interface EstadoContratoService {
    List<EstadoContrato> findAll();
    Optional<EstadoContrato> findById(Integer id);
    EstadoContrato findByEstado(String estado);
    EstadoContrato save(String estado);
    void deleteById(Integer id);
}
