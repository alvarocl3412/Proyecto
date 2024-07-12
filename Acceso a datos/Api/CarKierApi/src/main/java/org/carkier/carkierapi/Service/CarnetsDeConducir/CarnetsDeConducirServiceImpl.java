package org.carkier.carkierapi.Service.CarnetsDeConducir;

import jakarta.transaction.Transactional;
import org.carkier.carkierapi.Repositorio.CarnetsDeConducirRepository;
import org.carkier.carkierapi.Repositorio.TipoCarnetRepository;
import org.carkier.carkierapi.Repositorio.UsuarioRepository;
import org.carkier.carkierapi.modelos.CarnetsDeConducir.CarnetsDeConducir;
import org.carkier.carkierapi.modelos.TipoSeguro.TipoSeguro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarnetsDeConducirServiceImpl  implements CarnetsDeConducirService {
    private final CarnetsDeConducirRepository repositorio;

    @Autowired
    private  UsuarioRepository usuarioRepositorio;
    @Autowired
    private  TipoCarnetRepository tipoRepositorio;

    public CarnetsDeConducirServiceImpl(CarnetsDeConducirRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<CarnetsDeConducir> findAll() {
       return  repositorio.findAll();
    }
    @Override
    public Optional<CarnetsDeConducir> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public CarnetsDeConducir save(CarnetsDeConducir carnet) {
        return repositorio.save(carnet);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        CarnetsDeConducir canet = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de segurp no encontrado con ID: " + id));
        repositorio.delete(canet);
    }

}
