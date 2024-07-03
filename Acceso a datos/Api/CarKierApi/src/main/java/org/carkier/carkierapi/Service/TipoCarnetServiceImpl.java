package org.carkier.carkierapi.Service;


import jakarta.transaction.Transactional;
import org.carkier.carkierapi.Repositorio.TipoCarnetRepository;
import org.carkier.carkierapi.modelos.TipoCarnet;
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
            // Si ya existe un tipo de carné con el mismo nombre, devolvemos null o lanzamos una excepción, según tu lógica de negocio
            return null; // O puedes lanzar una excepción aquí
        }

        TipoCarnet nuevoTipoCarnet = new TipoCarnet();
        nuevoTipoCarnet.setNombre(nombre.toUpperCase());

        return repositorio.save(nuevoTipoCarnet);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        TipoCarnet tipoCarnet = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de carnet no encontrado con ID: " + id));

        repositorio.delete(tipoCarnet);
    }
}
