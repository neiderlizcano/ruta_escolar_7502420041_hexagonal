package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

import java.util.Optional;

public class BuscarAsistentePorIdUseCase {

    private final AsistenteRepository asistenteRepository;

    public BuscarAsistentePorIdUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    public Optional<Asistente> ejecutar(Integer id) {
        return asistenteRepository.buscarPorId(id);
    }
}
