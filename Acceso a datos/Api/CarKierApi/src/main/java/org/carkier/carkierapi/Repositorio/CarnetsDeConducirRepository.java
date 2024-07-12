package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.CarnetsDeConducir.CarnetsDeConducir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarnetsDeConducirRepository extends JpaRepository<CarnetsDeConducir, Integer> {
}
