package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

import java.util.Optional;

public class BuscarBusPorIdUseCase {

    private final BusRepository busRepository;

    public BuscarBusPorIdUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
// validaciones: 
    //1. Que no sea nulo y numero positivo

    public Optional<Bus> ejecutar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del bus no puede ser null.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El id del bus debe ser un número positivo.");
        }

        return busRepository.buscarPorId(id);
    }
}
