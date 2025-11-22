package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

public class CrearAsistenteUseCase {

    private final AsistenteRepository asistenteRepository;

    public CrearAsistenteUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    public Asistente ejecutar(Asistente asistente) {
        return asistenteRepository.guardar(asistente);
    }
}
