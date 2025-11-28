package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

import java.util.Optional;

public class BuscarConductorPorIdUseCase {

    private final ConductorRepository conductorRepository;

    public BuscarConductorPorIdUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    // validaciones: 
    //1. Que no sea nulo y sea numero positivo
    public Optional<Conductor> ejecutar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del conductor no puede ser null.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El id del conductor debe ser un número positivo.");
        }

        return conductorRepository.buscarPorId(id);
    }
}
