package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBusRepository implements BusRepository {

    private final Map<Integer, Bus> data = new HashMap<>();
    private final AtomicInteger sequence = new AtomicInteger(0);

    @Override
    public Bus guardar(Bus bus) {
        if (bus.getId() == null) {
            int newId = sequence.incrementAndGet();
            bus.setId(newId);
        }
        data.put(bus.getId(), bus);
        return bus;
    }

    @Override
    public Optional<Bus> buscarPorId(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Bus> listarTodos() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void eliminarPorId(Integer id) {
        data.remove(id);
    }
}
