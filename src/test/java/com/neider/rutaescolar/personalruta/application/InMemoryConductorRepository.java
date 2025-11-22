package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryConductorRepository implements ConductorRepository {

    private final Map<Integer, Conductor> almacen = new HashMap<>();
    private final AtomicInteger secuenciaId = new AtomicInteger(1);

    @Override
    public Conductor guardar(Conductor conductor) {
        if (conductor.getId() == null) {
            int nuevoId = secuenciaId.getAndIncrement();
            conductor.setId(nuevoId);
            almacen.put(nuevoId, conductor);
        } else {
            almacen.put(conductor.getId(), conductor);
        }
        return conductor;
    }

    @Override
    public Optional<Conductor> buscarPorId(Integer id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public List<Conductor> listarTodos() {
        return new ArrayList<>(almacen.values());
    }

    @Override
    public void eliminarPorId(Integer id) {
        almacen.remove(id);
    }
}