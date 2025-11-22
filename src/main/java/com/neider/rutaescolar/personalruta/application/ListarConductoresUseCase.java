package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

import java.util.List;

public class ListarConductoresUseCase {

    private final ConductorRepository conductorRepository;

    public ListarConductoresUseCase(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    public List<Conductor> ejecutar() {
        return conductorRepository.listarTodos();
    }
}
