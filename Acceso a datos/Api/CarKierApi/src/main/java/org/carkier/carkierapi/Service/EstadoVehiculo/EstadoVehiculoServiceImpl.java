package org.carkier.carkierapi.Service.EstadoVehiculo;


import org.carkier.carkierapi.Repositorio.EstadoVehiculoRepository;
import org.carkier.carkierapi.modelos.EstadoVehiculo.EstadoVehiculo;
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
            return null;
        }
        EstadoVehiculo nuevoEstado = new EstadoVehiculo();
        nuevoEstado.setEstado(estado.toUpperCase());
        return repositorio.save(nuevoEstado);
    }

    @Override
    public void deleteById(Integer id) {
            EstadoVehiculo estado = repositorio.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Estado del vehiuclo no se ha  encontrado con el ID: " + id));
            repositorio.delete(estado);
    }
}