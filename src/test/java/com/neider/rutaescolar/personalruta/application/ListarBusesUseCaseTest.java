package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.model.EstadoBus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListarBusesUseCaseTest {

    @Test
    void listarBusesDebeRetornarTodosLosGuardados() {
        InMemoryBusRepository repo = new InMemoryBusRepository();

        repo.guardar(new Bus("BBB111", EstadoBus.ACTIVO));
        repo.guardar(new Bus("CCC222", EstadoBus.INACTIVO));

        ListarBusesUseCase useCase = new ListarBusesUseCase(repo);

        List<Bus> lista = useCase.ejecutar();

        assertEquals(2, lista.size(), "Deben listarse los dos buses insertados");

        assertTrue(
                lista.stream().anyMatch(b -> b.getPlaca().equals("BBB111")),
                "Debe existir el bus BBB111"
        );
        assertTrue(
                lista.stream().anyMatch(b -> b.getPlaca().equals("CCC222")),
                "Debe existir el bus CCC222"
        );
    }
}
