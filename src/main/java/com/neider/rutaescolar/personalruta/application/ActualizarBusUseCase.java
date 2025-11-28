package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

import java.util.NoSuchElementException;

public class ActualizarBusUseCase {

    private final BusRepository busRepository;

    public ActualizarBusUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
    // validaciones: 
    //1. Que no sea nulo

    public Bus ejecutar(Bus bus) {
        if (bus == null) {
            throw new IllegalArgumentException("No se puede actualizar un bus nulo.");
        }

        if (bus.getId() == null) {
            throw new IllegalArgumentException(
                    "El id del bus es obligatorio para actualizar. "
                    + "Primero debe existir en la base de datos."
            );
        }

        if (bus.getId() <= 0) {
            throw new IllegalArgumentException("El id del bus debe ser un número positivo.");
        }
//2. Validamos que el asistente exista antes de actualizarlo
        busRepository.buscarPorId(bus.getId())
                .orElseThrow(() -> new NoSuchElementException(
                "No existe un bus registrado con el id " + bus.getId()
        ));

        return busRepository.guardar(bus);
    }
}
