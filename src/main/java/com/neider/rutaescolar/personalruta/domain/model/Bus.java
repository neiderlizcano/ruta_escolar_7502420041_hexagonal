package com.neider.rutaescolar.personalruta.domain.model;

public class Bus {

    private Integer id;
    private String placa;
    private Integer capacidad;
    private EstadoBus estado;

    public Bus(Integer id,
               String placa,
               Integer capacidad,
               EstadoBus estado) {
        this.id = id;
        this.placa = placa;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Bus(String placa,
               Integer capacidad,
               EstadoBus estado) {
        this(null, placa, capacidad, estado);
    }

    public Integer getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public EstadoBus getEstado() {
        return estado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setEstado(EstadoBus estado) {
        this.estado = estado;
    }
}
