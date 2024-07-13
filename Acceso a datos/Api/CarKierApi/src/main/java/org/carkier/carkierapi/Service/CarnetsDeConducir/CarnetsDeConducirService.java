package org.carkier.carkierapi.Service.CarnetsDeConducir;


import org.carkier.carkierapi.modelos.CarnetsDeConducir.CarnetsDeConducir;
import java.util.List;
import java.util.Optional;

public interface CarnetsDeConducirService {
    List<CarnetsDeConducir> findAll();
    Optional<CarnetsDeConducir> findById(Integer id);
    Optional<CarnetsDeConducir> updateCarnet(CarnetsDeConducir carnet);
    CarnetsDeConducir save(CarnetsDeConducir carnet);
    void deleteById(Integer id);
}
