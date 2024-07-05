package org.carkier.carkierapi;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.carkier.carkierapi.modelos.Usuario;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "datos_del_usuario")
public class DatosDelUsuario {


    @Id
    @Column(name = "idusuario")
    private Integer idusuario;

    @Column(name = "baneado")
    private LocalDate baneado;

    @Column(name = "puntos")
    private Integer puntos;

    @Column(name = "administrador")
    private boolean administrador;

    public DatosDelUsuario() {
    }

    public LocalDate getBaneado() {
        return baneado;
    }

    public void setBaneado(LocalDate baneado) {
        this.baneado = baneado;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "DatosDelUsuario{" +
                "idUsuario=" + idusuario +
                ", baneado=" + baneado +
                ", puntos=" + puntos +
                ", administrador=" + administrador +
                '}';
    }
}