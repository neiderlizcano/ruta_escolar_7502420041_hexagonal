package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

import java.util.NoSuchElementException;

public class ActualizarAsistenteUseCase {

    private final AsistenteRepository asistenteRepository;

    public ActualizarAsistenteUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    public Asistente ejecutar(Asistente asistente) {
        if (asistente.getId() == null) {
            throw new IllegalArgumentException("El id del asistente es obligatorio para actualizar");
        }

        asistenteRepository.buscarPorId(asistente.getId())
                .orElseThrow(() -> new NoSuchElementException("Asistente no encontrado"));

        return asistenteRepository.guardar(asistente);
    }
}
