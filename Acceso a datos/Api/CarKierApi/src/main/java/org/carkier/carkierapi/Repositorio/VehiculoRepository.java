package org.carkier.carkierapi.Repositorio;

import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo,Integer> {
}
