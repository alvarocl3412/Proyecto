package org.carkier.carkierapi.modelos.Empresa;

import jakarta.persistence.*;

@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idempresa", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "ofrece_coches")
    private Integer ofreceCoches;

    public Empresa() {
    }

    public Empresa(Integer id, String nombre, String descripcion, String direccion, String telefono, String correoElectronico, Integer ofreceCoches) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.ofreceCoches = ofreceCoches;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Integer getOfreceCoches() {
        return ofreceCoches;
    }

    public void setOfreceCoches(Integer ofreceCoches) {
        this.ofreceCoches = ofreceCoches;
    }

}