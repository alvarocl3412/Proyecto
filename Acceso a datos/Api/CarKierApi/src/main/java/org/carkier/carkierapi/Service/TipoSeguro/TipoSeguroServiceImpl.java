package org.carkier.carkierapi.Service.TipoSeguro;

import jakarta.transaction.Transactional;
import org.carkier.carkierapi.Repositorio.TipoSeguroRepository;
import org.carkier.carkierapi.modelos.TipoSeguro.TipoSeguro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoSeguroServiceImpl implements TipoSeguroService{
    private final TipoSeguroRepository repositorio;

    public TipoSeguroServiceImpl(TipoSeguroRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<TipoSeguro> findAll() {
        return repositorio.findAll();
    }
    @Override
    public Optional<TipoSeguro> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public TipoSeguro findByNombre(String nombre) {
        return repositorio.findByNombre(nombre.toLowerCase());
    }

    @Transactional
    @Override
    public TipoSeguro updatePrecio(Integer id, double precio) {
        TipoSeguro tipoSeguro = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TipoSeguro no encontrado con el id: " + id));
        tipoSeguro.setCoste(precio);
        return repositorio.save(tipoSeguro);
    }

    @Override
    public TipoSeguro save(TipoSeguro seguro) {
        return repositorio.save(seguro);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        TipoSeguro estado = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de segurp no encontrado con ID: " + id));
        repositorio.delete(estado);
    }

}
