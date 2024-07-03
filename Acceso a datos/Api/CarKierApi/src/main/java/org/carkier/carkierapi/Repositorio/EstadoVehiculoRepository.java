package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.EstadoVehiculo;
import org.carkier.carkierapi.modelos.TipoCarnet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


//Hay que poner bien los nombres del metodo poner igual que los atrbutos de la clase
public interface EstadoVehiculoRepository extends JpaRepository<EstadoVehiculo, Integer> {
    EstadoVehiculo findByEstado(String estado);
}
