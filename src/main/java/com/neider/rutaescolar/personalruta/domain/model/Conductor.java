package com.neider.rutaescolar.personalruta.domain.model;

public class Conductor {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String documento;
    private String nroLicencia;
    private String categoriaLicencia;
    private String telefono;
    private EstadoTrabajador estado;

    public Conductor(Integer id,
            String nombres,
            String apellidos,
            String documento,
            String nroLicencia,
            String categoriaLicencia,
            String telefono,
            EstadoTrabajador estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.nroLicencia = nroLicencia;
        this.categoriaLicencia = categoriaLicencia;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Conductor(String nombres,
            String apellidos,
            String documento,
            String nroLicencia,
            String categoriaLicencia,
            String telefono,
            EstadoTrabajador estado) {
        this(null, nombres, apellidos, documento, nroLicencia, categoriaLicencia, telefono, estado);
    }

    public Integer getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDocumento() {
        return documento;
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstado(EstadoTrabajador estado) {
        this.estado = estado;
    }
}
