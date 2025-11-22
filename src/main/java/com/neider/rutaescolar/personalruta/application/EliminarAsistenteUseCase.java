package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

public class EliminarAsistenteUseCase {

    private final AsistenteRepository asistenteRepository;

    public EliminarAsistenteUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    public void ejecutar(Integer id) {
        asistenteRepository.eliminarPorId(id);
    }
}
