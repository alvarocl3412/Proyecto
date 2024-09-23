package org.carkier.carkierapi.Repositorio.Comentarios;

import org.carkier.carkierapi.modelos.Comentarios.Comentario;
import org.carkier.carkierapi.modelos.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
    Optional<Comentario> findByIdComentarioRespuesta(Integer idComentarioRespuesta);

    List<Comentario> findByIdVehiculo(int idVehiculo);
}
