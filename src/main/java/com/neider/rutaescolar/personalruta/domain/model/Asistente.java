package com.neider.rutaescolar.personalruta.domain.model;

public class Asistente {

    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private EstadoTrabajador estado;

    public Asistente(Integer id,
                     String nombre,
                     String apellido,
                     String telefono,
                     EstadoTrabajador estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Asistente(String nombres,
                     String apellidos,
                     String telefono,
                     EstadoTrabajador estado) {
        this(null, nombres, apellidos, telefono, estado);
    }

    public Integer getId() {
        return id;
    }

    public String getNombres() {
        return nombre;
    }

    public String getApellidos() {
        return apellido;
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
                "%d - %s %s | Tel: %s | Estado: %s",
                id != null ? id : 0,
                nombre,
                apellido,
                telefono != null ? telefono : "N/D",
                estado != null ? estado.name() : "N/D"
        );
    }
}
