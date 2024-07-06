package org.carkier.carkierapi;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.carkier.carkierapi.modelos.TipoCarnet;
import org.carkier.carkierapi.modelos.Usuario;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "carnets_de_conducir")
public class CarnetsDeConducir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarnet", nullable = false)
    private Integer id;


    @Column(name = "idusuario", nullable = false)
    private Integer idusuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "id_tipocarnet", nullable = false)
    private TipoCarnet idTipocarnet;

    @NotNull
    @Column(name = "fecha_expedicion", nullable = false)
    private LocalDate fechaExpedicion;

    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad;

    public CarnetsDeConducir() {
    }

    public CarnetsDeConducir(Integer id, Integer idusuario, TipoCarnet idTipocarnet, LocalDate fechaExpedicion, LocalDate fechaCaducidad) {
        this.id = id;
        this.idusuario = idusuario;
        this.idTipocarnet = idTipocarnet;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public TipoCarnet getIdTipocarnet() {
        return idTipocarnet;
    }

    public void setIdTipocarnet(TipoCarnet idTipocarnet) {
        this.idTipocarnet = idTipocarnet;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String toString() {
        return "CarnetsDeConducir{" +
                "id=" + id +
                ", idusuario=" + idusuario +
                ", idTipocarnet=" + idTipocarnet +
                ", fechaExpedicion=" + fechaExpedicion +
                ", fechaCaducidad=" + fechaCaducidad +
                '}';
    }
}