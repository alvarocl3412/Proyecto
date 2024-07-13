package org.carkier.carkierapi.Service.Contratos;

import org.carkier.carkierapi.Repositorio.ContratoRepository;
import org.carkier.carkierapi.Repositorio.UsuarioRepository;
import org.carkier.carkierapi.Repositorio.VehiculoRepository;
import org.carkier.carkierapi.modelos.Contratos.Contrato;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con ID: " + id));
        repositorio.delete(contrato);
    }
}
