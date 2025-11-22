package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

public class EliminarConductorUseCase {

    private final ConductorRepository conductorRepository;

    public EliminarConductorUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    public void ejecutar(Integer id) {
        conductorRepository.eliminarPorId(id);
    }
}
