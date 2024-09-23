package org.carkier.carkierapi.modelos.EstadoVehiculo;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_vehiculo")
public class EstadoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado", nullable = false)
    private Integer id;

    @Column(name = "estado", nullable = false, length = 150)
    private String estado;

    public EstadoVehiculo() {
    }

    public EstadoVehiculo(Integer id, String estado) {
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
        return "EstadoVehiculo{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                '}';
    }
}