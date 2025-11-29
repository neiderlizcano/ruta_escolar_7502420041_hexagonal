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

        Bus nuevo = new Bus("AAA123", EstadoBus.ACTIVO);

        Bus guardado = useCase.ejecutar(nuevo);

        assertNotNull(guardado.getId(), "El id no debería ser nulo después de guardar");
        assertEquals("AAA123", guardado.getPlaca());          // si la placa se pasa a mayúscula, sigue siendo AAA123
        assertEquals(Bus.CapacidadMaxima, guardado.getCapacidad(),
                "La capacidad debe ser la capacidad máxima definida en el dominio");
        assertEquals(1, repo.listarTodos().size(),
                "Debe haber exactamente un bus en el repositorio");
    }
}
