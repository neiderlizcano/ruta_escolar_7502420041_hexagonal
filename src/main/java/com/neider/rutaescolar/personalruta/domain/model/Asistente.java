package com.neider.rutaescolar.personalruta.domain.model;

public class Asistente {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String documento;
    private String telefono;
    private EstadoTrabajador estado;

    public Asistente(Integer id,
                     String nombres,
                     String apellidos,
                     String documento,
                     String telefono,
                     EstadoTrabajador estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Asistente(String nombres,
                     String apellidos,
                     String documento,
                     String telefono,
                     EstadoTrabajador estado) {
        this(null, nombres, apellidos, documento, telefono, estado);
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
