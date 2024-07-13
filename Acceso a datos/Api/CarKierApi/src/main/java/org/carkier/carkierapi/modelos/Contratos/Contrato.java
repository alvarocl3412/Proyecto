package org.carkier.carkierapi.modelos.Contratos;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contratos")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontrato", nullable = false)
    private Integer id;

    @Column(name = "idvehiculo", nullable = false)
    private Integer idvehiculo;

    @Column(name = "idcliente", nullable = false)
    private Integer idCliente;

    @Column(name = "idestado", nullable = false)
    private Integer idEstado;

    @Column(name = "id_seguro", nullable = false)
    private Integer idSeguro;

    @Column(name = "precio_dia")
    private Double precioDia;

    @Column(name = "precio_total")
    private Double precioTotal;

    @Column(name = "pagado", nullable = false)
    private Boolean pagado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    public Contrato() {
    }

    public Contrato(Integer id, Integer idvehiculo, Integer idCliente, Integer idEstado, Integer idSeguro, Double precioDia, Double precioTotal, Boolean pagado, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.idvehiculo = idvehiculo;
        this.idCliente = idCliente;
        this.idEstado = idEstado;
        this.idSeguro = idSeguro;
        this.precioDia = precioDia;
        this.precioTotal = precioTotal;
        this.pagado = pagado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Integer idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(Integer idSeguro) {
        this.idSeguro = idSeguro;
    }

    public Double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Double precioDia) {
        this.precioDia = precioDia;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

}