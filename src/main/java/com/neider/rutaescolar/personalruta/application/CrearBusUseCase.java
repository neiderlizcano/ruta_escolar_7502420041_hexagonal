package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

public class CrearBusUseCase {

    private final BusRepository busRepository;

    public CrearBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
// validaciones: 
    //1. Que no sea nulo

    public Bus ejecutar(Bus bus) {
        if (bus == null) {
            throw new IllegalArgumentException("No se puede crear un bus nulo.");
        }

        if (bus.getId() != null) {
            throw new IllegalArgumentException(
                    "Para crear un bus nuevo, el id debe ser null. "
                    + "El id lo genera la base de datos."
            );
        }

        return busRepository.guardar(bus);
    }
}
