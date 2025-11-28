package com.neider.rutaescolar.personalruta.domain.model;

public class Conductor {

    private Integer id;
    private String nombre;
    private String apellido;
    private String nroLicencia;
    private String categoriaLicencia;
    private String telefono;
    private EstadoTrabajador estado;

    public Conductor(Integer id,
            String nombre,
            String apellido,
            String nroLicencia,
            String categoriaLicencia,
            String telefono,
            EstadoTrabajador estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroLicencia = nroLicencia;
        this.categoriaLicencia = categoriaLicencia;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Conductor(String nombre,
            String apellido,
            String nroLicencia,
            String categoriaLicencia,
            String telefono,
            EstadoTrabajador estado) {
        this(null, nombre, apellido, nroLicencia, categoriaLicencia, telefono, estado);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNroLicencia() {
        return nroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public EstadoTrabajador getEstado() {
        return estado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNroLicencia(String nroLicencia) {
        this.nroLicencia = nroLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstado(EstadoTrabajador estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Conductor{" +
                "id=" + id +
                ", nombre='" + nombre + ' ' + apellido + '\'' +
                ", nroLicencia='" + nroLicencia + '\'' +
                ", categoriaLicencia='" + categoriaLicencia + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estado=" + estado +
                '}';
    }
}
