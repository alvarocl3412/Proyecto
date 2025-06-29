package org.carkier.carkierapi.modelos.Comentarios;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomentarios", nullable = false)
    private Integer id;

    @Column(name = "idusuario")
    private Integer idUsuario;

    @Column(name = "idvehiculo", nullable = false)
    private Integer idVehiculo;

    @Column(name = "idcomentario")
    private Integer idComentarioRespuesta;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @Column(name = "fecha")
    private LocalDate fecha;

    public Comentario() {
    }

    public Comentario(Integer id, Integer idUsuario, Integer idVehiculo, Integer idComentarioRespuesta, String comentario, LocalDate fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idVehiculo = idVehiculo;
        this.idComentarioRespuesta = idComentarioRespuesta;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Integer getIdComentarioRespuesta() {
        return idComentarioRespuesta;
    }

    public void setIdComentarioRespuesta(Integer idComentarioRespuesta) {
        this.idComentarioRespuesta = idComentarioRespuesta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}