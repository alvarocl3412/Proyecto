package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.EstadoVehiculo.EstadoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;



//Hay que poner bien los nombres del metodo poner igual que los atrbutos de la clase
public interface EstadoVehiculoRepository extends JpaRepository<EstadoVehiculo, Integer> {
    EstadoVehiculo findByEstado(String estado);
}
