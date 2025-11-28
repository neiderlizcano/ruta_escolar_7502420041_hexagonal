package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

public class EliminarConductorUseCase {

    private final ConductorRepository conductorRepository;

    public EliminarConductorUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    // validaciones: 
    //1. Que no sea nulo y sea numero positivo
    public void ejecutar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del conductor no puede ser null.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El id del conductor debe ser un número positivo.");
        }

        conductorRepository.eliminarPorId(id);
    }
}
