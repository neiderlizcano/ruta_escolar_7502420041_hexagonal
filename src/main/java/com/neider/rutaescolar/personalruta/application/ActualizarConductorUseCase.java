package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

import java.util.NoSuchElementException;

public class ActualizarConductorUseCase {

    private final ConductorRepository conductorRepository;

    public ActualizarConductorUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }
    // validaciones: 
    //1. Que no sea nulo

    public Conductor ejecutar(Conductor conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("No se puede actualizar un conductor nulo.");
        }

        if (conductor.getId() == null) {
            throw new IllegalArgumentException(
                    "El id del conductor es obligatorio para actualizar. "
                    + "Primero debe existir en la base de datos."
            );
        }

        if (conductor.getId() <= 0) {
            throw new IllegalArgumentException("El id del conductor debe ser un número positivo.");
        }
        //2. Validamos que el asistente exista antes de actualizarlo
        conductorRepository.buscarPorId(conductor.getId())
                .orElseThrow(() -> new NoSuchElementException(
                "No existe un conductor registrado con el id " + conductor.getId()
        ));

        return conductorRepository.guardar(conductor);
    }
}
