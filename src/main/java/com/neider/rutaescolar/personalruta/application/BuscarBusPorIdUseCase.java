package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

import java.util.Optional;

public class BuscarBusPorIdUseCase {

    private final BusRepository busRepository;

    public BuscarBusPorIdUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Optional<Bus> ejecutar(Integer id) {
        return busRepository.buscarPorId(id);
    }
}
