package org.carkier.carkierapi.Service.Empresa;

import org.carkier.carkierapi.modelos.Empresa.Empresa;
import java.util.List;
import java.util.Optional;

public interface EmpresaService {

    List<Empresa> findAll();

    Optional<Empresa> findById(Integer id);

    Optional<Empresa> updateEmpresa(Empresa empresa);
    Empresa save(Empresa empresa);
    void deleteById(Integer id);

}
