package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.model.EstadoBus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActualizarBusUseCaseTest {

    @Test
    void actualizarBusDebeCambiarCapacidadYEstado() {
        InMemoryBusRepository repo = new InMemoryBusRepository();

        Bus original = new Bus("DDD333", 40, EstadoBus.ACTIVO);
        original = repo.guardar(original);

        ActualizarBusUseCase useCase = new ActualizarBusUseCase(repo);

        original.setCapacidad(60);
        original.setEstado(EstadoBus.SUSPENDIDO);

        Bus actualizado = useCase.ejecutar(original);

        assertEquals(60, actualizado.getCapacidad());
        assertEquals(EstadoBus.SUSPENDIDO, actualizado.getEstado());
    }
}
