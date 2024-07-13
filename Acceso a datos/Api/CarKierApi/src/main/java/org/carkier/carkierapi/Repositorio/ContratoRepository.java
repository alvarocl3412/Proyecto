package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.Contratos.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato,Integer> {
}
