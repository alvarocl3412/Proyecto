package org.carkier.carkierapi.Service.TipoSeguro;

import org.carkier.carkierapi.Repositorio.TipoSeguro.TipoSeguroRepository;
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

    @Override
    public Optional<TipoSeguro> updateSeguro(TipoSeguro seguro) {
        Optional<TipoSeguro> tipoSeguro = repositorio.findById(seguro.getId());
        if (tipoSeguro.isPresent()){
            TipoSeguro seguroActualizado = tipoSeguro.get();
            seguroActualizado.setNombre(seguro.getNombre());
            seguroActualizado.setDescripcion(seguro.getDescripcion());
            seguroActualizado.setCoste(seguro.getCoste());
            repositorio.save(seguroActualizado);
            return Optional.of(seguroActualizado);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public TipoSeguro save(TipoSeguro seguro) {
        return repositorio.save(seguro);
    }

    @Override
    public void deleteById(Integer id) {
        TipoSeguro estado = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El tipo de seguro no se ha encontrado con el ID: " + id));
        repositorio.delete(estado);
    }

}
