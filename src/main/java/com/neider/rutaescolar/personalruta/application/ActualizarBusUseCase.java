package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

import java.util.NoSuchElementException;

public class ActualizarBusUseCase {

    private final BusRepository busRepository;

    public ActualizarBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus ejecutar(Bus bus) {
        if (bus.getId() == null) {
            throw new IllegalArgumentException("El id del bus es obligatorio para actualizar");
        }

        busRepository.buscarPorId(bus.getId())
                .orElseThrow(() -> new NoSuchElementException("Bus no encontrado"));

        return busRepository.guardar(bus);
    }
}
