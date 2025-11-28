package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

public class CrearAsistenteUseCase {

    private final AsistenteRepository asistenteRepository;

    public CrearAsistenteUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }
// validaciones: 
    //1. Que no sea nulo

    public Asistente ejecutar(Asistente asistente) {
        if (asistente == null) {
            throw new IllegalArgumentException("No se puede crear un asistente nulo.");
        }

        if (asistente.getId() != null) {
            throw new IllegalArgumentException(
                    "Para crear un asistente nuevo, el id debe ser null. "
                    + "El id lo asigna la base de datos."
            );
        }

        return asistenteRepository.guardar(asistente);
    }
}
