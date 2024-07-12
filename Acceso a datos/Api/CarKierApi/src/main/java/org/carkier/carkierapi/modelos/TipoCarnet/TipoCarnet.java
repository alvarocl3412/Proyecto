package org.carkier.carkierapi.modelos.TipoCarnet;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tipo_carnet")
public class TipoCarnet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nombre", nullable = false, length = 10)
    private String nombre;

    public TipoCarnet() {
    }

    public TipoCarnet(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoCarnet{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}