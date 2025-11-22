package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.model.EstadoBus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrearBusUseCaseTest {

    @Test
    void crearBusDebeAsignarId() {
        InMemoryBusRepository repo = new InMemoryBusRepository();
        CrearBusUseCase useCase = new CrearBusUseCase(repo);

        Bus nuevo = new Bus("AAA123", 40, EstadoBus.ACTIVO);

        Bus guardado = useCase.ejecutar(nuevo);

        assertNotNull(guardado.getId());
        assertEquals("AAA123", guardado.getPlaca());
        assertEquals(1, repo.listarTodos().size());
    }
}
