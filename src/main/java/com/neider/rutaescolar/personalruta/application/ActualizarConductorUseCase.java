package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

import java.util.NoSuchElementException;

public class ActualizarConductorUseCase {

    private final ConductorRepository conductorRepository;

    public ActualizarConductorUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    public Conductor ejecutar(Conductor conductor) {
        if (conductor.getId() == null) {
            throw new IllegalArgumentException("El id del conductor es obligatorio para actualizar");
        }

        conductorRepository.buscarPorId(conductor.getId())
                .orElseThrow(() -> new NoSuchElementException("Conductor no encontrado"));

        return conductorRepository.guardar(conductor);
    }
}
