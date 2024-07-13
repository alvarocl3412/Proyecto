package org.carkier.carkierapi.Service.Contratos;

import org.carkier.carkierapi.modelos.Contratos.Contrato;

import java.util.List;
import java.util.Optional;

public interface ContratoService {
    List<Contrato> findAll();
    Optional<Contrato> findById(Integer id);
    Optional<Contrato> updateContrato(Contrato contrato);
    Contrato save(Contrato vehiculo);
    void deleteById(Integer id);
}
