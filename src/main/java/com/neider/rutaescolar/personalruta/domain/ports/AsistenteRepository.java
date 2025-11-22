package com.neider.rutaescolar.personalruta.domain.ports;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;

import java.util.List;
import java.util.Optional;

public interface AsistenteRepository {

    Asistente guardar(Asistente asistente);

    Optional<Asistente> buscarPorId(Integer id);

    List<Asistente> listarTodos();

    void eliminarPorId(Integer id);
}
