package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

public class EliminarBusUseCase {

    private final BusRepository busRepository;

    public EliminarBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
// validaciones: 
    //1. Que no sea nulo y numero positivo

    public void ejecutar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del bus no puede ser null.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El id del bus debe ser un número positivo.");
        }

        busRepository.eliminarPorId(id);
    }
}
