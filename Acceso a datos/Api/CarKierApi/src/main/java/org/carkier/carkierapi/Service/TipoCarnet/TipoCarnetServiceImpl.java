package org.carkier.carkierapi.Service.TipoCarnet;


import org.carkier.carkierapi.Repositorio.TipoCarnetRepository;
import org.carkier.carkierapi.modelos.TipoCarnet.TipoCarnet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCarnetServiceImpl implements TipoCarnetService {
    private final TipoCarnetRepository repositorio;

    public TipoCarnetServiceImpl(TipoCarnetRepository equpo) {
        this.repositorio = equpo;
    }

    @Override
    public List<TipoCarnet> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Optional<TipoCarnet> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public TipoCarnet findByNombre(String nombre) {
        return repositorio.findByNombre(nombre.toUpperCase());
    }

    @Override
    public TipoCarnet save(String nombre) {
        TipoCarnet tipoCarnetExistente = repositorio.findByNombre(nombre.toUpperCase());
        if (tipoCarnetExistente != null) {
            return null;
        }

        TipoCarnet nuevoTipoCarnet = new TipoCarnet();
        nuevoTipoCarnet.setNombre(nombre.toUpperCase());

        return repositorio.save(nuevoTipoCarnet);
    }

    @Override
    public void deleteById(Integer id) {
        TipoCarnet tipoCarnet = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de carnet no se ha  encontrado con el ID: " + id));

        repositorio.delete(tipoCarnet);
    }
}
