package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.model.EstadoBus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActualizarBusUseCaseTest {

    @Test
    void actualizarBusDebeCambiarEstado() {
        InMemoryBusRepository repo = new InMemoryBusRepository();

        Bus original = new Bus("DDD333", EstadoBus.ACTIVO);
        original = repo.guardar(original);

        ActualizarBusUseCase useCase = new ActualizarBusUseCase(repo);

        Bus modificado = new Bus(
                original.getId(), // mismo id
                original.getPlaca(), // misma placa
                EstadoBus.SUSPENDIDO // nuevo estado
        );

        Bus actualizado = useCase.ejecutar(modificado);

        assertEquals(EstadoBus.SUSPENDIDO, actualizado.getEstado());
        assertEquals(original.getId(), actualizado.getId());
        assertEquals(original.getPlaca(), actualizado.getPlaca());
    }
}
