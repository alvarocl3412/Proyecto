package org.carkier.carkierapi.Repositorio.Comentarios;

import org.carkier.carkierapi.modelos.Comentarios.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
}
