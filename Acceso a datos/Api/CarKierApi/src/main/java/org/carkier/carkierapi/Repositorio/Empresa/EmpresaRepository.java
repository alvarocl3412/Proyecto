package org.carkier.carkierapi.Repositorio.Empresa;

import org.carkier.carkierapi.modelos.Empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository  extends JpaRepository<Empresa, Integer> {
    Optional<Empresa> findByNombre(String nombre);
}
