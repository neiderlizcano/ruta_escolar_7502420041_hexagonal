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

        // id = null (primer parámetro)
        Conductor sinId = new Conductor(
                null,
                "Carlos",
                "Perez",
                "LIC999",
                "C1",
                "3010000000",
                EstadoTrabajador.ACTIVO
        );

        assertThrows(IllegalArgumentException.class,
                () -> useCase.ejecutar(sinId),
                "Debe lanzar IllegalArgumentException si el id es null");
    }

    @Test
    void deberiaLanzarExcepcionSiConductorNoExiste() {
        InMemoryConductorRepository repo = new InMemoryConductorRepository();
        ActualizarConductorUseCase useCase = new ActualizarConductorUseCase(repo);

        // id inexistente en el repositorio
        Conductor conIdInexistente = new Conductor(
                99,                     // id que NO existe en el repo
                "Ana",
                "Lopez",
                "LIC888",
                "B1",
                "3020000000",
                EstadoTrabajador.ACTIVO
        );

        assertThrows(NoSuchElementException.class,
                () -> useCase.ejecutar(conIdInexistente),
                "Debe lanzar NoSuchElementException si el conductor no existe");
    }

    @Test
    void deberiaActualizarTelefonoYEstadoCuandoConductorExiste() {
        InMemoryConductorRepository repo = new InMemoryConductorRepository();
        CrearConductorUseCase crear = new CrearConductorUseCase(repo);
        ActualizarConductorUseCase actualizar = new ActualizarConductorUseCase(repo);

        // Crear conductor SIN id (lo asigna el repo)
        Conductor original = new Conductor(
                "Luis",
                "Gomez",
                "LIC777",
                "C3",
                "3000000000",
                EstadoTrabajador.ACTIVO
        );

        Conductor creado = crear.ejecutar(original);

        // Usamos los métodos de dominio
        creado.actualizarTelefono("3111111111");
        creado.cambiarEstado(EstadoTrabajador.SUSPENDIDO);

        Conductor actualizado = actualizar.ejecutar(creado);

        assertEquals("3111111111", actualizado.getTelefono());
        assertEquals(EstadoTrabajador.SUSPENDIDO, actualizado.getEstado());
    }
}
