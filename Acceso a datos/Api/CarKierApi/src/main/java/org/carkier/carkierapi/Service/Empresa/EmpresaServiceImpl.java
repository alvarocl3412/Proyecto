package org.carkier.carkierapi.Service.Empresa;

import org.carkier.carkierapi.Repositorio.EmpresaRepository;
import org.carkier.carkierapi.modelos.Empresa.Empresa;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl  implements  EmpresaService{
    private final EmpresaRepository repositorio;

    public EmpresaServiceImpl(EmpresaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Empresa> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Empresa> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public Optional<Empresa> updateEmpresa(Empresa empresa) {
        Optional<Empresa> exiseEmpresa = repositorio.findById(empresa.getId());
        if (exiseEmpresa.isPresent()) {
            //Como exite creamos una empresa
            Empresa EmpresaActualizada = exiseEmpresa.get();
            //Modifcamos todos los datos
            EmpresaActualizada.setNombre(empresa.getNombre());
            EmpresaActualizada.setDireccion(empresa.getDireccion());
            EmpresaActualizada.setTelefono(empresa.getTelefono());
            EmpresaActualizada.setDescripcion(empresa.getDescripcion());
            EmpresaActualizada.setCorreoElectronico(empresa.getCorreoElectronico());
            EmpresaActualizada.setOfreceCoches(empresa.getOfreceCoches());

            repositorio.save(EmpresaActualizada);
            return Optional.of(EmpresaActualizada);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Empresa save(Empresa empresa) {
        return repositorio.save(empresa);
    }

    @Override
    public void deleteById(Integer id) {
        Empresa empresa = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No encontrado  la empresa con el ID: " + id));
        repositorio.delete(empresa);
    }
}
