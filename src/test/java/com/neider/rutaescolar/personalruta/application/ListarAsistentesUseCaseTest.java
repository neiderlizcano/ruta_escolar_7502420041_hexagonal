package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListarAsistentesUseCaseTest {

    @Test
    void listarAsistentesDebeRetornarTodosLosGuardados() {
        InMemoryAsistenteRepository repo = new InMemoryAsistenteRepository();

        repo.guardar(new Asistente("Laura", "Perez", "4001", "3001111111", EstadoTrabajador.ACTIVO));
        repo.guardar(new Asistente("Miguel", "Diaz", "4002", "3002222222", EstadoTrabajador.SUSPENDIDO));

        ListarAsistentesUseCase useCase = new ListarAsistentesUseCase(repo);

        List<Asistente> lista = useCase.ejecutar();

        assertEquals(2, lista.size(), "Deben listarse los dos asistentes insertados");
    }
}
