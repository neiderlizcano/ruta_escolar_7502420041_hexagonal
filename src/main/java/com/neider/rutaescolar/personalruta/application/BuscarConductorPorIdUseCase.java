package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

import java.util.Optional;

public class BuscarConductorPorIdUseCase {

    private final ConductorRepository conductorRepository;

    public BuscarConductorPorIdUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    public Optional<Conductor> ejecutar(Integer id) {
        return conductorRepository.buscarPorId(id);
    }
}
