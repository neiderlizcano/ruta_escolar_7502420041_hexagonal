package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

public class CrearBusUseCase {

    private final BusRepository busRepository;

    public CrearBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus ejecutar(Bus bus) {
        return busRepository.guardar(bus);
    }
}
