package com.neider.rutaescolar.personalruta.domain.ports;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;

import java.util.List;
import java.util.Optional;

public interface ConductorRepository {

    Conductor guardar(Conductor conductor);

    Optional<Conductor> buscarPorId(Integer id);

    List<Conductor> listarTodos();

    void eliminarPorId(Integer id);
}
