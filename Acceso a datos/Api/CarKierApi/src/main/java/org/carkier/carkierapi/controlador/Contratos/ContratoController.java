package org.carkier.carkierapi.controlador.Contratos;

import org.carkier.carkierapi.Dto.FechasOcupadas;
import org.carkier.carkierapi.Service.Contratos.ContratoService;
import org.carkier.carkierapi.modelos.Contratos.Contrato;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CarKier")
public class ContratoController {
    private final ContratoService servicio;

    public ContratoController(ContratoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/ContratofindAll")
    public ResponseEntity<List<Contrato>> getAllContrato() {
        List<Contrato> contratos = servicio.findAll();
        if (contratos.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(contratos);
    }

    @GetMapping("/ContratosId/{id}")
    public ResponseEntity<Contrato> getContratoById(@PathVariable Integer id) {
        Optional<Contrato> contrato = servicio.findById(id);
        if (!contrato.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(contrato.get());
        }
    }

    // Obtener contratos por estado
    @GetMapping("/estado/{idEstado}")
    public List<Contrato> obtenerContratosPorEstado(@PathVariable Integer idEstado) {
        return servicio.obtenerContratosPorEstado(idEstado);
    }

    // Obtener contratos por cliente
    @GetMapping("/contratocliente/{idCliente}")
    public List<Contrato> obtenerContratosPorCliente(@PathVariable Integer idCliente) {
        return servicio.obtenerContratosPorCliente(idCliente);
    }

    // Obtener contratos por estado y cliente
    @GetMapping("/contratoEstadocliente/{idEstado}/{idCliente}")
    public List<Contrato> obtenerContratosPorEstadoYCliente(@PathVariable Integer idEstado, @PathVariable Integer idCliente) {
        return servicio.obtenerContratosPorEstadoYCliente(idEstado, idCliente);
    }

    // Metodo para conseguir las fechas de los contratos
    @GetMapping("/contratofechasOcupada/{idVehiculo}")
    public FechasOcupadas obtenerFechasOcupadas(@PathVariable int idVehiculo) {
        return servicio.getFechasOcupadasDesdeHoyPorVehiculo(idVehiculo);
    }

    @PutMapping("/updateContrato")
    public ResponseEntity<Contrato> updateContrato(@RequestBody Contrato contrato) {
        Optional<Contrato> updatedContrato = servicio.updateContrato(contrato);
        return updatedContrato.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/CrearContrato")
    public ResponseEntity<String> crearContrato(@RequestBody Contrato contrato) {
        Contrato contratoNuevo = servicio.save(contrato);
        if (contratoNuevo != null) {
            return ResponseEntity.ok("Contrato registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el contrato");
        }
    }

    @DeleteMapping("deleteContrato/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable Integer id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
