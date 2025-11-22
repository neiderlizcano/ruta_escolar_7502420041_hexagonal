package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

import java.util.List;

public class ListarAsistentesUseCase {

    private final AsistenteRepository asistenteRepository;

    public ListarAsistentesUseCase(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    public List<Asistente> ejecutar() {
        return asistenteRepository.listarTodos();
    }
}
