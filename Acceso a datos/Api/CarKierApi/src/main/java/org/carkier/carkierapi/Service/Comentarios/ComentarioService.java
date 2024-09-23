package org.carkier.carkierapi.Service.Comentarios;

import org.carkier.carkierapi.modelos.Comentarios.Comentario;
import java.util.List;
import java.util.Optional;

public interface ComentarioService {
    List<Comentario> findAll();
    List<Comentario> findComentariosByVehiculoId(int idVehiculo);
    Optional<Comentario> findById(Integer id);
    Optional<Comentario> findByIdComentarioRespuesta(Integer idComentarioRespuesta);
    Optional<Comentario> updateComentario(Comentario comentario);
    Comentario save(Comentario comentario);
    void deleteById(Integer id);
}
