package org.carkier.carkierapi.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "estado_contrato")
public class EstadoContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "estado", nullable = false, length = 100)
    private String estado;

    public EstadoContrato() {
    }

    public EstadoContrato(Integer id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EstadoContrato{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                '}';
    }
}
