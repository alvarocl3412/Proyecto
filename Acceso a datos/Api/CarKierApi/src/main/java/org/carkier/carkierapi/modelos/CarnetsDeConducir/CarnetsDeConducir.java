package org.carkier.carkierapi.modelos.CarnetsDeConducir;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

    @Entity
    @Table(name = "carnets_de_conducir")
    public class CarnetsDeConducir {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idcarnet", nullable = false)
        private Integer id;

        @Column(name = "idusuario")
        private Integer idusuario;

        @Column(name = "id_tipocarnet")
        private Integer idTipocarnet;

        @Column(name = "fecha_expedicion", nullable = false)
        private LocalDate fechaExpedicion;

        @Column(name = "fecha_caducidad")
        private LocalDate fechaCaducidad;

        public CarnetsDeConducir() {
        }

        public CarnetsDeConducir(Integer id, Integer idusuario, Integer idTipocarnet, LocalDate fechaExpedicion, LocalDate fechaCaducidad) {
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

        public Integer getIdTipocarnet() {
            return idTipocarnet;
        }

        public void setIdTipocarnet(Integer idTipocarnet) {
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

    }