package org.carkier.carkierapi.Service.Comentarios;

import org.carkier.carkierapi.Repositorio.Comentarios.ComentarioRepository;
import org.carkier.carkierapi.Repositorio.Usuarios.UsuarioRepository;
import org.carkier.carkierapi.Repositorio.Vehiculos.VehiculoRepository;
import org.carkier.carkierapi.modelos.Comentarios.Comentario;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.carkier.carkierapi.modelos.Vehiculos.Vehiculo;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository repositorio;
    private final VehiculoRepository repositorioVehiculo;
    private final UsuarioRepository repositorioUsuario;

    public ComentarioServiceImpl(ComentarioRepository repositorio, VehiculoRepository repositorioVehiculo, UsuarioRepository repositorioUsuario) {
        this.repositorio = repositorio;
        this.repositorioVehiculo = repositorioVehiculo;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Comentario> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Comentario> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public Optional<Comentario> findByIdComentarioRespuesta(Integer idComentarioRespuesta) {
        return repositorio.findByIdComentarioRespuesta(idComentarioRespuesta);
    }
    public List<Comentario> findComentariosByVehiculoId(int idVehiculo) {
        return repositorio.findByIdVehiculo(idVehiculo);
    }

    @Override
    public Optional<Comentario> updateComentario(Comentario comentario) {
        Optional<Comentario> existecomentario = repositorio.findById(comentario.getId());
        if (existecomentario.isPresent()) {
            //Como exite creamos un Comentario
            Comentario comentarioActualizado = existecomentario.get();
            //Modifcamos todos los datos
            comentarioActualizado.setIdUsuario(comentario.getIdUsuario());
            comentarioActualizado.setIdVehiculo(comentario.getIdVehiculo());
            comentarioActualizado.setComentario(comentario.getComentario());
            comentarioActualizado.setIdComentarioRespuesta(comentario.getIdComentarioRespuesta());
            comentarioActualizado.setFecha(LocalDate.now());

            repositorio.save(comentarioActualizado);
            return Optional.of(comentarioActualizado);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public Comentario save(Comentario comentario) {
        Optional<Vehiculo> exiseVehiculo = repositorioVehiculo.findById(comentario.getIdVehiculo());
        if (exiseVehiculo.isPresent()) {
            Vehiculo em = exiseVehiculo.get();
            comentario.setIdVehiculo(em.getId());
        }
        Optional<Usuario> exiseUsuario = repositorioUsuario.findById(comentario.getIdUsuario());
        if (exiseUsuario.isPresent()) {
            Usuario usu = exiseUsuario.get();
            comentario.setIdUsuario(usu.getId());
        }
        return repositorio.save(comentario);
    }
    @Override
    public void deleteById(Integer id) {
        Comentario comentario = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El comentario no se ha encontrado con el ID: " + id));
        repositorio.delete(comentario);
    }
}
