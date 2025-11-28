package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

public class CrearConductorUseCase {

    private final ConductorRepository conductorRepository;

    public CrearConductorUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    // validaciones: 
    //1. Que no sea nulo
    public Conductor ejecutar(Conductor conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("No se puede crear un conductor nulo.");
        }

        if (conductor.getId() != null) {
            throw new IllegalArgumentException(
                    "Para crear un conductor nuevo, el id debe ser null. "
                    + "El id lo genera la base de datos."
            );
        }

        return conductorRepository.guardar(conductor);
    }
}
