package org.carkier.carkierapi.Repositorio.Contratos;

import org.carkier.carkierapi.modelos.Contratos.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContratoRepository extends JpaRepository<Contrato,Integer> {
    // Método para obtener contratos por estado
    List<Contrato> findByIdEstado(Integer idEstado);

    // Método para obtener contratos por cliente
    List<Contrato> findByIdCliente(Integer idCliente);

    // Método para obtener contratos por estado y cliente
    List<Contrato> findByIdEstadoAndIdCliente(Integer idEstado, Integer idCliente);

    @Query("SELECT c FROM Contrato c WHERE c.idvehiculo = :idVehiculo AND c.fechaFin >= :fechaActual")
    List<Contrato> findByIdVehiculoAndFechaFinAfter(int idVehiculo, LocalDate fechaActual);
}
