package org.carkier.carkierapi.Service.Vehiculos;

import jakarta.transaction.Transactional;
import org.carkier.carkierapi.Repositorio.Empresa.EmpresaRepository;
import org.carkier.carkierapi.Repositorio.Usuarios.UsuarioRepository;
import org.carkier.carkierapi.Repositorio.Vehiculos.VehiculoRepository;
import org.carkier.carkierapi.modelos.Empresa.Empresa;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {
    private final VehiculoRepository repositorio;
    private final UsuarioRepository repositorioUsuario;
    private final EmpresaRepository repositorioEpresa;

    public VehiculoServiceImpl(VehiculoRepository repositorio, UsuarioRepository repositorioUsuario, EmpresaRepository repositorioEpresa) {
        this.repositorio = repositorio;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioEpresa = repositorioEpresa;
    }

        @Override
        public List<Vehiculo> findAll() {
            return repositorio.findAll();
        }

    @Override
    public Optional<Vehiculo> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public Optional<Vehiculo> findByMatricula(String matricula) {
        return repositorio.findByMatricula(matricula);
    }


    @Override
    public Optional<Vehiculo> updateVehiuclo(Vehiculo vehiculo) {
        Optional<Vehiculo> existeVehiuclo = repositorio.findById(vehiculo.getId());
        if (existeVehiuclo.isPresent()) {
            //Como exite creamos un Vehiculo
            Vehiculo vehiculoActualizado = existeVehiuclo.get();
            //Modifcamos todos los datos
            vehiculoActualizado.setIdEmpresa(vehiculo.getIdEmpresa());
            vehiculoActualizado.setIdUsuariosPropietario(vehiculo.getIdUsuariosPropietario());
            vehiculoActualizado.setIdEstado(vehiculo.getIdEstado());
            vehiculoActualizado.setMatricula(vehiculo.getMatricula());
            vehiculoActualizado.setMarca(vehiculo.getMarca());
            vehiculoActualizado.setModelo(vehiculo.getModelo());
            vehiculoActualizado.setAnio(vehiculo.getAnio());
            vehiculoActualizado.setKm(vehiculo.getKm());
            vehiculoActualizado.setPrecioventa(vehiculo.getPrecioventa());
            vehiculoActualizado.setPreciodia(vehiculo.getPreciodia());

            repositorio.save(vehiculoActualizado);
            return Optional.of(vehiculoActualizado);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        if (vehiculo.getIdEmpresa() != null) {
            Optional<Empresa> exiseEmpresa = repositorioEpresa.findById(vehiculo.getIdEmpresa());
            if (exiseEmpresa.isPresent()) {
                Empresa em = exiseEmpresa.get();
                vehiculo.setIdEmpresa(em.getId());

            }
        } else if (vehiculo.getIdUsuariosPropietario() != null) {
            Optional<Usuario> exiseUsuario = repositorioUsuario.findById(vehiculo.getIdUsuariosPropietario());
            if (exiseUsuario.isPresent()) {
                Usuario usu = exiseUsuario.get();
                vehiculo.setIdUsuariosPropietario(usu.getId());
            }
        }
        return repositorio.save(vehiculo);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Vehiculo vehiculo = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El vehiculo no se ha  encontrado con el ID: " + id));
        repositorio.delete(vehiculo);
    }
}