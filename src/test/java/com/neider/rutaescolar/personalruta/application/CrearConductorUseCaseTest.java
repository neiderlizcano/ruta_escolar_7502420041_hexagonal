package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrearConductorUseCaseTest {

    @Test
    void deberiaCrearConductorConIdGenerado() {
        InMemoryConductorRepository repo = new InMemoryConductorRepository();
        CrearConductorUseCase useCase = new CrearConductorUseCase(repo);

        Conductor nuevo = new Conductor(
                "Neider",
                "Lizcano",
                "LIC123",         
                "C2",             
                "3001234567",     
                EstadoTrabajador.ACTIVO
        );

        Conductor resultado = useCase.ejecutar(nuevo);

        assertNotNull(resultado.getId(), "El id del conductor debe generarse");
        assertEquals("NEIDER", resultado.getNombre());
        assertEquals("LIZCANO", resultado.getApellido());
        assertEquals(1, repo.listarTodos().size());
    }
}
