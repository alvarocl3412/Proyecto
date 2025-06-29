package org.carkier.carkierapi.Repositorio.TipoSeguro;

import org.carkier.carkierapi.modelos.TipoSeguro.TipoSeguro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSeguroRepository extends JpaRepository<TipoSeguro, Integer> {
    TipoSeguro findByNombre(String nombre);
}
