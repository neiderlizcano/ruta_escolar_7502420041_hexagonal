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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstado(EstadoTrabajador estado) {
        this.estado = estado;
    }
    
     @Override
    public String toString() {
        return String.format(
                "%d - %s %s | Lic: %s (%s) | Tel: %s | Estado: %s",
                id != null ? id : 0,
                nombre,
                apellido,
                nroLicencia,
                categoriaLicencia,
                telefono != null ? telefono : "N/D",
                estado != null ? estado.name() : "N/D"
        );
    }
}
