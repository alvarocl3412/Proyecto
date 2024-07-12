package org.carkier.carkierapi.Service;

import org.carkier.carkierapi.Repositorio.EstadoContratoRepository;
import org.carkier.carkierapi.modelos.EstadoContrato;
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
            // Si ya existe un tipo de carné con el mismo nombre, devolvemos null o lanzamos una excepción, según tu lógica de negocio
            return null; // O puedes lanzar una excepción aquí
        }
        EstadoContrato nuevoEstado = new EstadoContrato();
        nuevoEstado.setEstado(estado.toUpperCase());
        return repositorio.save(nuevoEstado);
    }

    @Override
    public void deleteById(Integer id) {
        EstadoContrato estado = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de carnet no encontrado con ID: " + id));
        repositorio.delete(estado);
    }
}
