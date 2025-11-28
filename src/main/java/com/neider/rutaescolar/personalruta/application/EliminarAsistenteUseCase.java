package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

public class EliminarAsistenteUseCase {

    private final AsistenteRepository asistenteRepository;

    public EliminarAsistenteUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }
    // validaciones: 
    //1. Que no sea nulo y numero positivo

    public void ejecutar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del asistente no puede ser null.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El id del asistente debe ser un número positivo.");
        }

        asistenteRepository.eliminarPorId(id);
    }
}
