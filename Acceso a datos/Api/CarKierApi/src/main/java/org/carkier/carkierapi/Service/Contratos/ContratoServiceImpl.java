package org.carkier.carkierapi.Service.Contratos;

import org.carkier.carkierapi.Dto.FechasOcupadas;
import org.carkier.carkierapi.Repositorio.Contratos.ContratoRepository;
import org.carkier.carkierapi.Repositorio.Usuarios.UsuarioRepository;
import org.carkier.carkierapi.Repositorio.Vehiculos.VehiculoRepository;
import org.carkier.carkierapi.modelos.Contratos.Contrato;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ContratoServiceImpl implements ContratoService {
    private final ContratoRepository repositorio;
    private final VehiculoRepository repositorioVehiculo;
    private final UsuarioRepository repositorioUsuario;

    public ContratoServiceImpl(ContratoRepository repositorio, VehiculoRepository repositorioVehiuclo, UsuarioRepository repositorioUsuario) {
        this.repositorio = repositorio;
        this.repositorioVehiculo = repositorioVehiuclo;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Contrato> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Contrato> findById(Integer id) {
        return repositorio.findById(id);
    }

    // Obtener contratos por estado
    public List<Contrato> obtenerContratosPorEstado(Integer idEstado) {
        return repositorio.findByIdEstado(idEstado);
    }

    // Obtener contratos por cliente
    public List<Contrato> obtenerContratosPorCliente(Integer idCliente) {
        return repositorio.findByIdCliente(idCliente);
    }

    // Obtener contratos por estado y cliente
    public List<Contrato> obtenerContratosPorEstadoYCliente(Integer idEstado, Integer idCliente) {
        return repositorio.findByIdEstadoAndIdCliente(idEstado, idCliente);
    }

    @Override
    public Optional<Contrato> updateContrato(Contrato contrato) {
        Optional<Contrato> exiseContrato = repositorio.findById(contrato.getId());
        if (exiseContrato.isPresent()) {
            Contrato contratoActualizado = exiseContrato.get();
            Vehiculo existeVehiuclo = repositorioVehiculo.findById(contrato.getIdvehiculo()).get();
            if (existeVehiuclo != null) {
                contratoActualizado.setIdvehiculo(existeVehiuclo.getId());
                Usuario existeUsuario = repositorioUsuario.findById(contrato.getIdCliente()).get();
                if (existeUsuario != null) {
                    contratoActualizado.setIdCliente(existeUsuario.getId());
                    contratoActualizado.setIdEstado(contrato.getIdEstado());
                    contratoActualizado.setIdSeguro(contrato.getIdSeguro());
                    contratoActualizado.setPrecioDia(contrato.getPrecioDia());
                    contratoActualizado.setPrecioTotal(contrato.getPrecioTotal());
                    contratoActualizado.setFechaInicio(contrato.getFechaInicio());
                    contratoActualizado.setFechaFin(contrato.getFechaFin());
                    contratoActualizado.setPagado(contrato.getPagado());
                    repositorio.save(contratoActualizado);
                    return Optional.of(contratoActualizado);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Contrato save(Contrato contrato) {
        return repositorio.save(contrato);
    }

    @Override
    public void deleteById(Integer id) {
        Contrato contrato = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El contrato no se ha encontrado con el ID: " + id));
        repositorio.delete(contrato);
    }

    @Override
    public FechasOcupadas getFechasOcupadasDesdeHoyPorVehiculo(int idVehiculo) {
        LocalDate fechaActual = LocalDate.now(); // Día actual
        Set<String> diasOcupados = new HashSet<>(); // Usamos un Set para evitar duplicados
        List<Contrato> contratos = repositorio.findByIdVehiculoAndFechaFinAfter(idVehiculo, fechaActual);

        for (Contrato contrato : contratos) {
            // Filtrar solo contratos del vehículo específico
            if (contrato.getIdvehiculo() == idVehiculo) {
                // Solo consideramos contratos que terminan después de hoy
                if (contrato.getFechaFin().isAfter(fechaActual) || contrato.getFechaFin().isEqual(fechaActual)) {
                    // Ajustamos la fecha de inicio al día actual si está dentro del rango
                    LocalDate inicio = contrato.getFechaInicio().isBefore(fechaActual) ? fechaActual : contrato.getFechaInicio();
                    LocalDate fin = contrato.getFechaFin();

                    // Agregamos cada día del rango a la lista
                    while (!inicio.isAfter(fin)) {
                        diasOcupados.add(inicio.toString());
                        inicio = inicio.plusDays(1); // Pasamos al día siguiente
                    }
                }
            }
        }

        // Convertimos el Set a una lista ordenada
        List<String> listaDiasOcupados = new ArrayList<>(diasOcupados);
        listaDiasOcupados.sort(String::compareTo); // Ordenamos las fechas

        // Devolvemos un objeto FechaOcupada que contiene la lista de fechas ocupadas
        return new FechasOcupadas(listaDiasOcupados);
    }
}
