package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

public class EliminarBusUseCase {

    private final BusRepository busRepository;

    public EliminarBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public void ejecutar(Integer id) {
        busRepository.eliminarPorId(id);
    }
}
