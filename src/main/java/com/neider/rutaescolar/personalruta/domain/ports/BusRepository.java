package com.neider.rutaescolar.personalruta.domain.ports;

import com.neider.rutaescolar.personalruta.domain.model.Bus;

import java.util.List;
import java.util.Optional;

public interface BusRepository {

    Bus guardar(Bus bus);

    Optional<Bus> buscarPorId(Integer id);

    List<Bus> listarTodos();

    void eliminarPorId(Integer id);
}
