package com.neider.rutaescolar.personalruta.domain.model;

public class Bus {

    public static final int capacidadMaximaNinos = 10;
    private Integer id;
    private String placa;
    private int capacidad;
    private EstadoBus estado;

    public Bus(Integer id,
               String placa,
               int capacidad,
               EstadoBus estado) {
        this.id = id;
        this.placa = placa;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Bus(String placa,
               EstadoBus estado) {
        this(null, placa, capacidadMaximaNinos, estado);
    }

  public Integer getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public EstadoBus getEstado() {
        return estado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setEstado(EstadoBus estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", capacidad=" + capacidad +
                ", estado=" + estado +
                '}';
    }
}
