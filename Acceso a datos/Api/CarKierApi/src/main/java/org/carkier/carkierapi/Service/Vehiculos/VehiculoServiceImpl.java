package org.carkier.carkierapi.Service.Vehiculos;

import jakarta.transaction.Transactional;
import org.carkier.carkierapi.Repositorio.Empresa.EmpresaRepository;
import org.carkier.carkierapi.Repositorio.Usuarios.UsuarioRepository;
import org.carkier.carkierapi.Repositorio.Vehiculos.VehiculoRepository;
import org.carkier.carkierapi.modelos.Empresa.Empresa;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Vehiculo> findByMarca(String marca) {
        return repositorio.findByMarca(marca);
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
            vehiculoActualizado.setImagen(vehiculo.getImagen());

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
    @Override
    public List<Vehiculo> obtenerVehiculosPorEstado(Long idEstado) {
        return repositorio.findByIdEstado(idEstado);
    }

    @Override
    public List<Vehiculo> busquedaCoche(Vehiculo filtro) {
        List<Vehiculo> todosVehiculos = repositorio.findAll();

        return todosVehiculos.stream()
            .filter(vehiculo -> {
                // Filtrar por id si no es nulo
                if (filtro.getId() != null && !filtro.getId().equals(vehiculo.getId())) {
                    return false;
                }

                // Filtrar por idEmpresa si no es nulo
                if (filtro.getIdEmpresa() != null && !filtro.getIdEmpresa().equals(vehiculo.getIdEmpresa())) {
                    return false;
                }

                // Filtrar por idUsuariosPropietario si no es nulo
                if (filtro.getIdUsuariosPropietario() != null && !filtro.getIdUsuariosPropietario().equals(vehiculo.getIdUsuariosPropietario())) {
                    return false;
                }

                // Filtrar por idEstado si no es nulo
                if (filtro.getIdEstado() != null && !filtro.getIdEstado().equals(vehiculo.getIdEstado())) {
                    return false;
                }

                // Filtrar por matricula si no es nula
                if (filtro.getMatricula() != null && !filtro.getMatricula().isEmpty() && 
                    !vehiculo.getMatricula().toLowerCase().contains(filtro.getMatricula().toLowerCase())) {
                    return false;
                }

                // Filtrar por marca si no es nula
                if (filtro.getMarca() != null && !filtro.getMarca().isEmpty() && 
                    !vehiculo.getMarca().toLowerCase().contains(filtro.getMarca().toLowerCase())) {
                    return false;
                }

                // Filtrar por modelo si no es nulo
                if (filtro.getModelo() != null && !filtro.getModelo().isEmpty() && 
                    !vehiculo.getModelo().toLowerCase().contains(filtro.getModelo().toLowerCase())) {
                    return false;
                }

                // Filtrar por año si no es nulo
                if (filtro.getAnio() != null && !filtro.getAnio().equals(vehiculo.getAnio())) {
                    return false;
                }

                // Filtrar por km si no es nulo
                if (filtro.getKm() != null && !filtro.getKm().equals(vehiculo.getKm())) {
                    return false;
                }

                // Filtrar por precioventa si no es nulo
                if (filtro.getPrecioventa() != null && !filtro.getPrecioventa().equals(vehiculo.getPrecioventa())) {
                    return false;
                }

                // Filtrar por preciodia si no es nulo
                if (filtro.getPreciodia() != null && !filtro.getPreciodia().equals(vehiculo.getPreciodia())) {
                    return false;
                }

                // Si pasa todos los filtros, incluir en el resultado
                return true;
            })
            .collect(Collectors.toList());
    }
}
