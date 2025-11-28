package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

import java.util.Optional;

public class BuscarAsistentePorIdUseCase {

    private final AsistenteRepository asistenteRepository;

    public BuscarAsistentePorIdUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }
    // validaciones: 
    //1. Que no sea nulo y sea numero positivo

    public Optional<Asistente> ejecutar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del asistente no puede ser null.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El id del asistente debe ser un número positivo.");
        }

        return asistenteRepository.buscarPorId(id);
    }
}
