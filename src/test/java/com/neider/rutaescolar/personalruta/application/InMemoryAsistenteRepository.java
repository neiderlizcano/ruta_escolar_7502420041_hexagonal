package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryAsistenteRepository implements AsistenteRepository {

    private final Map<Integer, Asistente> data = new HashMap<>();
    private final AtomicInteger sequence = new AtomicInteger(0);

    @Override
    public Asistente guardar(Asistente asistente) {
        if (asistente.getId() == null) {
            int newId = sequence.incrementAndGet();
            asistente.setId(newId);
        }
        data.put(asistente.getId(), asistente);
        return asistente;
    }

    @Override
    public Optional<Asistente> buscarPorId(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Asistente> listarTodos() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void eliminarPorId(Integer id) {
        data.remove(id);
    }
}
