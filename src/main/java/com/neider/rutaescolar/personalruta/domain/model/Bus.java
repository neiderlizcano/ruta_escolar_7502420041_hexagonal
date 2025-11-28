package com.neider.rutaescolar.personalruta.domain.model;

public class Bus {

    //En el CEA 10 es la capacidad de ninos x bus
    public static final int CapacidadMaxima = 10;

    private Integer id;
    private String placa;
    private int capacidad;
    private EstadoBus estado;

    //Constructor para un bus que ya existe
    public Bus(Integer id, String placa, EstadoBus estado) {
        this.id = id;
        this.placa = PlacaObligatoria(placa);
        this.capacidad = CapacidadMaxima;
        this.estado = validarEstado(estado);
    }

    //Constructor para crear un bus
    public Bus(String placa, EstadoBus estado) {
        this(null, placa, estado);
    }

    //Validacion de placa, que no este vacia
    private String PlacaObligatoria(String placa) {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("La placa del bus es obligatoria");
        }
        return placa.trim().toUpperCase();
    }

    //Validacion de estado.
    private EstadoBus validarEstado(EstadoBus estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado del bus es obligatorio");
        }
        return estado;
    }

    //Get y sets
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

    //Se actualiza la placa
    public void actualizarPlaca(String nuevaPlaca) {
        this.placa = PlacaObligatoria(nuevaPlaca);
    }

    public void cambiarEstado(EstadoBus nuevoEstado) {
        this.estado = validarEstado(nuevoEstado);
    }

    //Verificacion si hay cupos disponibles
    public boolean tieneCuposDisponibles(int ninosAsignados) {
        validarNinosAsignados(ninosAsignados);
        return ninosAsignados < capacidad;
    }

    //Nos devuelve cuantos cupos faltan
    public int cuposDisponibles(int ninosAsignados) {
        validarNinosAsignados(ninosAsignados);
        int restantes = capacidad - ninosAsignados;
        return Math.max(restantes, 0);
    }

    //Se valida que ninos no supere 10
    private void validarNinosAsignados(int ninosAsignados) {
        if (ninosAsignados < 0) {
            throw new IllegalArgumentException("La cantidad de niños asignados no puede ser negativa");
        }
        if (ninosAsignados > capacidad) {
            throw new IllegalArgumentException(
                    "Los niños asignados (" + ninosAsignados
                    + ") no pueden superar la capacidad del bus (" + capacidad + ")"
            );
        }
    }

    @Override
    public String toString() {
        String idTexto = (id != null) ? id.toString() : "NUEVO";
        String estadoTexto = (estado != null) ? estado.name() : "N/D";
        return String.format(
                "%s - %s | Capacidad niños: %d | Estado: %s",
                idTexto,
                placa,
                capacidad,
                estadoTexto
        );
    }
}
