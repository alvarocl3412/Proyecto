package org.carkier.carkierapi.modelos.Vehiculos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvehiculo", nullable = false)
    private Integer id;

    @Column(name = "idempresa")
    private Integer idEmpresa;

    @Column(name = "idusuariospropietario")
    private Integer idUsuariosPropietario;

    @Column(name = "idestado", nullable = false)
    private Integer idEstado;

    @Size(max = 20)
    @Column(name = "matricula", nullable = false, length = 20)
    private String matricula;

    @Size(max = 100)
    @Column(name = "marca", nullable = false, length = 100)
    private String marca;

    @Size(max = 100)
    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "km")
    private Integer km;

    @Column(name = "precioventa", nullable = false)
    private Double precioventa;

    @Column(name = "preciodia", nullable = false)
    private Double preciodia;

    public Vehiculo() {
    }

    public Vehiculo(Integer id, Integer idEmpresa, Integer idUsuariosPropietario, Integer idEstado, String matricula, String marca, String modelo, Integer anio, Integer km, Double precioventa, Double preciodia) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.idUsuariosPropietario = idUsuariosPropietario;
        this.idEstado = idEstado;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.km = km;
        this.precioventa = precioventa;
        this.preciodia = preciodia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdUsuariosPropietario() {
        return idUsuariosPropietario;
    }

    public void setIdUsuariosPropietario(Integer idUsuariosPropietario) {
        this.idUsuariosPropietario = idUsuariosPropietario;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(Double precioventa) {
        this.precioventa = precioventa;
    }

    public Double getPreciodia() {
        return preciodia;
    }

    public void setPreciodia(Double preciodia) {
        this.preciodia = preciodia;
    }

}