package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ActualizarConductorUseCaseTest {

    @Test
    void deberiaLanzarExcepcionSiIdEsNull() {
        InMemoryConductorRepository repo = new InMemoryConductorRepository();
        ActualizarConductorUseCase useCase = new ActualizarConductorUseCase(repo);

        Conductor sinId = new Conductor(
                "Carlos",
                "Perez",
                "999",
                "LIC999",
                "C1",
                "3010000000",
                EstadoTrabajador.ACTIVO
        );

        assertThrows(IllegalArgumentException.class, () -> useCase.ejecutar(sinId),
                "Debe lanzar IllegalArgumentException si el id es null");
    }

    @Test
    void deberiaLanzarExcepcionSiConductorNoExiste() {
        InMemoryConductorRepository repo = new InMemoryConductorRepository();
        ActualizarConductorUseCase useCase = new ActualizarConductorUseCase(repo);

        Conductor conIdInexistente = new Conductor(
                "Ana",
                "Lopez",
                "888",
                "LIC888",
                "B1",
                "3020000000",
                EstadoTrabajador.ACTIVO
        );
        conIdInexistente.setId(99);

        assertThrows(NoSuchElementException.class, () -> useCase.ejecutar(conIdInexistente),
                "Debe lanzar NoSuchElementException si el conductor no existe");
    }

    @Test
    void deberiaActualizarTelefonoYEstadoCuandoConductorExiste() {
        InMemoryConductorRepository repo = new InMemoryConductorRepository();
        CrearConductorUseCase crear = new CrearConductorUseCase(repo);
        ActualizarConductorUseCase actualizar = new ActualizarConductorUseCase(repo);

        Conductor original = new Conductor(
                "Luis",
                "Gomez",
                "777",
                "LIC777",
                "C3",
                "3000000000",
                EstadoTrabajador.ACTIVO
        );

        Conductor creado = crear.ejecutar(original);

        creado.setTelefono("3111111111");
        creado.setEstado(EstadoTrabajador.SUSPENDIDO);

        Conductor actualizado = actualizar.ejecutar(creado);

        assertEquals("3111111111", actualizado.getTelefono());
        assertEquals(EstadoTrabajador.SUSPENDIDO, actualizado.getEstado());
    }
}
