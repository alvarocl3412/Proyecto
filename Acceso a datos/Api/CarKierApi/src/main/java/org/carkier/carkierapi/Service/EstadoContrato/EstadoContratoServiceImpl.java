package org.carkier.carkierapi.Service.EstadoContrato;

import org.carkier.carkierapi.Repositorio.EstadoContratoRepository;
import org.carkier.carkierapi.modelos.EstadoContratos.EstadoContrato;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoContratoServiceImpl implements EstadoContratoService {
    private final EstadoContratoRepository repositorio;

    public EstadoContratoServiceImpl(EstadoContratoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<EstadoContrato> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Optional<EstadoContrato> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public EstadoContrato findByEstado(String estado) {
        return repositorio.findByEstado(estado.toUpperCase());
    }

    @Override
    public EstadoContrato save(String estado) {
        EstadoContrato existeEstado = repositorio.findByEstado(estado.toUpperCase());
        if (existeEstado != null) {
            return null;
        }
        EstadoContrato nuevoEstado = new EstadoContrato();
        nuevoEstado.setEstado(estado.toUpperCase());
        return repositorio.save(nuevoEstado);
    }

    @Override
    public void deleteById(Integer id) {
        EstadoContrato estado = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El estado del contrato  no se ha  encontrado con el ID: " + id));
        repositorio.delete(estado);
    }
}
