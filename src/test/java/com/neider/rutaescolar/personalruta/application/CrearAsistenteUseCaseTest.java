package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrearAsistenteUseCaseTest {

    @Test
    void crearAsistenteDebeAsignarId() {
        InMemoryAsistenteRepository repo = new InMemoryAsistenteRepository();
        CrearAsistenteUseCase useCase = new CrearAsistenteUseCase(repo);

        Asistente nuevo = new Asistente(
                "Maria",
                "Lopez",
                "3001",
                "3005555555",
                EstadoTrabajador.ACTIVO
        );

        Asistente guardado = useCase.ejecutar(nuevo);

        assertNotNull(guardado.getId(), "El id no debería ser nulo después de guardar");
        assertEquals("Maria", guardado.getNombres());
        assertEquals(1, repo.listarTodos().size(), "Debe haber exactamente un asistente en el repositorio");
    }
}
