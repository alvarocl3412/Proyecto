package org.carkier.carkierapi.modelos.DatosDelUsuario;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "datos_del_usuario")
public class DatosDelUsuario {

    @Id
    @Column(name = "idusuario", nullable = false)
    private Integer id;

    @Column(name = "puntos")
    private Integer puntos;

    @Column(name = "administrador")
    private boolean administrador;

    @Column(name = "fechaban_inicio")
    private LocalDate fechaBanInicio;
    @Column(name = "fechaban_final")
    private LocalDate fechaBanFinal;

    @Column(name = "cantidad_ban")
    private Integer cantidadBan;

    @Column(name = "marcadoeliminar")
    private boolean marcaEliminar;



    public DatosDelUsuario() {
    }

    public DatosDelUsuario(Integer id, Integer puntos, boolean administrador, LocalDate fechaBanInicio, LocalDate fechaBanFinal, Integer cantidadBan, boolean marcaEliminar) {
        this.id = id;
        this.puntos = puntos;
        this.administrador = administrador;
        this.fechaBanInicio = fechaBanInicio;
        this.fechaBanFinal = fechaBanFinal;
        this.cantidadBan = cantidadBan;
        this.marcaEliminar = marcaEliminar;
    }

    public Integer getCantidadBan() {
        return cantidadBan;
    }

    public void setCantidadBan(Integer cantidadBan) {
        this.cantidadBan = cantidadBan;
    }

    public LocalDate getFechaBanFinal() {
        return fechaBanFinal;
    }

    public void setFechaBanFinal(LocalDate fechaBanFinal) {
        this.fechaBanFinal = fechaBanFinal;
    }

    public boolean isMarcaEliminar() {
        return marcaEliminar;
    }

    public void setMarcaEliminar(boolean marcaEliminar) {
        this.marcaEliminar = marcaEliminar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getFechaBanInicio() {
        return fechaBanInicio;
    }

    public void setFechaBanInicio(LocalDate fechaBanInicio) {
        this.fechaBanInicio = fechaBanInicio;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "DatosDelUsuario{" +
                "id=" + id +
                ", puntos=" + puntos +
                ", administrador=" + administrador +
                ", fechaBanInicio=" + fechaBanInicio +
                ", fechaBanFinal=" + fechaBanFinal +
                ", cantidadBan=" + cantidadBan +
                ", marcaEliminar=" + marcaEliminar +
                '}';
    }

}