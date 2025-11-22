package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActualizarAsistenteUseCaseTest {

    @Test
    void actualizarAsistenteDebeCambiarTelefonoYEstado() {
        InMemoryAsistenteRepository repo = new InMemoryAsistenteRepository();

        Asistente original = new Asistente(
                "Sofia",
                "Gomez",
                "5001",
                "3003333333",
                EstadoTrabajador.ACTIVO
        );
        original = repo.guardar(original);

        ActualizarAsistenteUseCase useCase = new ActualizarAsistenteUseCase(repo);

        original.setTelefono("3009999999");
        original.setEstado(EstadoTrabajador.INACTIVO);

        Asistente actualizado = useCase.ejecutar(original);

        assertEquals("3009999999", actualizado.getTelefono());
        assertEquals(EstadoTrabajador.INACTIVO, actualizado.getEstado());
    }
}
