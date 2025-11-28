package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

import java.util.NoSuchElementException;

public class ActualizarAsistenteUseCase {

    private final AsistenteRepository asistenteRepository;

    public ActualizarAsistenteUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    // validaciones: 
    //1. Que no sea nulo
    public Asistente ejecutar(Asistente asistente) {
        if (asistente == null) {
            throw new IllegalArgumentException("No se puede actualizar un asistente nulo.");
        }

        if (asistente.getId() == null) {
            throw new IllegalArgumentException(
                    "El id del asistente es obligatorio para actualizar. "
                    + "Primero debe existir en la base de datos."
            );
        }

        //2. Validamos que el asistente exista antes de actualizarlo
        asistenteRepository.buscarPorId(asistente.getId())
                .orElseThrow(() -> new NoSuchElementException(
                "No existe un asistente registrado con el id " + asistente.getId()
        ));

        return asistenteRepository.guardar(asistente);
    }
}
