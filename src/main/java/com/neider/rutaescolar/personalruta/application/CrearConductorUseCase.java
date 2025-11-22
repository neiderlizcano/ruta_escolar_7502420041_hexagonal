package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

public class CrearConductorUseCase {

    private final ConductorRepository conductorRepository;

    public CrearConductorUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    public Conductor ejecutar(Conductor conductor) {
       
        return conductorRepository.guardar(conductor);
    }
}
