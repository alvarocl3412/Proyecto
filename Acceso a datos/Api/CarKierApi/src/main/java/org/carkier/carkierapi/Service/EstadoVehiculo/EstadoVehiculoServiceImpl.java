package org.carkier.carkierapi.Service;


import jakarta.transaction.Transactional;
import org.carkier.carkierapi.Repositorio.EstadoVehiculoRepository;
import org.carkier.carkierapi.modelos.EstadoVehiculo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoVehiculoServiceImpl implements EstadoVehiculoService {

    private final EstadoVehiculoRepository repositorio;


    public EstadoVehiculoServiceImpl(EstadoVehiculoRepository equpo) {
        this.repositorio = equpo;
    }
    @Override
    public List<EstadoVehiculo> findAll() {
        return repositorio.findAll();
    }
    @Override
    public Optional<EstadoVehiculo> findById(Integer id) {
        return repositorio.findById(id);
    }
    @Override
    public EstadoVehiculo findByEstado(String estado) {
        return repositorio.findByEstado(estado.toUpperCase());
    }
    @Override
    public EstadoVehiculo save(String estado) {
        EstadoVehiculo existeEstado = repositorio.findByEstado(estado.toUpperCase());
        if (existeEstado != null) {
            // Si ya existe un tipo de carné con el mismo nombre, devolvemos null o lanzamos una excepción, según tu lógica de negocio
            return null; // O puedes lanzar una excepción aquí
        }
        EstadoVehiculo nuevoEstado = new EstadoVehiculo();
        nuevoEstado.setEstado(estado.toUpperCase());
        return repositorio.save(nuevoEstado);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
            EstadoVehiculo estado = repositorio.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de carnet no encontrado con ID: " + id));
            repositorio.delete(estado);
    }
}