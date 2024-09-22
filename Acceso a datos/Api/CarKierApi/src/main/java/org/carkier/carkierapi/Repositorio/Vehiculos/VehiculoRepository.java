package org.carkier.carkierapi.Repositorio.Vehiculos;

import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo,Integer> {
    Optional<Vehiculo> findByMatricula(String matricula);
    List<Vehiculo> findByIdEstado(Long idEstado);

}
