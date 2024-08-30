package org.carkier.carkierapi.Repositorio.CarnetDeConducir;

import org.carkier.carkierapi.modelos.CarnetsDeConducir.CarnetsDeConducir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarnetsDeConducirRepository extends JpaRepository<CarnetsDeConducir, Integer> {
    List<CarnetsDeConducir> findByIdusuario(Integer idusuario);

}
