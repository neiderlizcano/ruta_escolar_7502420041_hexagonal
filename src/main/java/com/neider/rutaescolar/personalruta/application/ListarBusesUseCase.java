package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

import java.util.List;

public class ListarBusesUseCase {

    private final BusRepository busRepository;

    public ListarBusesUseCase(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> ejecutar() {
        return busRepository.listarTodos();
    }
}
