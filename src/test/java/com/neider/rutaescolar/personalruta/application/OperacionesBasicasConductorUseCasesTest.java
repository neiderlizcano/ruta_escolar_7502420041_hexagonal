package com.neider.rutaescolar.personalruta.application;

import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OperacionesBasicasConductorUseCasesTest {

    @Test
    void deberiaListarBuscarYEliminarConductoresCorrectamente() {
        InMemoryConductorRepository repo = new InMemoryConductorRepository();

        CrearConductorUseCase crear = new CrearConductorUseCase(repo);
        ListarConductoresUseCase listar = new ListarConductoresUseCase(repo);
        BuscarConductorPorIdUseCase buscar = new BuscarConductorPorIdUseCase(repo);
        EliminarConductorUseCase eliminar = new EliminarConductorUseCase(repo);

        Conductor c1 = new Conductor("Juan", "Lopez", "111", "LIC111", "3001", EstadoTrabajador.ACTIVO);
        Conductor c2 = new Conductor("Maria", "Perez", "222", "LIC222", "3002", EstadoTrabajador.INACTIVO);

        Conductor creado1 = crear.ejecutar(c1);
        Conductor creado2 = crear.ejecutar(c2);

        List<Conductor> todos = listar.ejecutar();
        assertEquals(2, todos.size(), "Debe listar los dos conductores creados");

        Optional<Conductor> buscado = buscar.ejecutar(creado1.getId());
        assertTrue(buscado.isPresent(), "Debe encontrar el primer conductor por id");

        assertEquals("JUAN", buscado.get().getNombre());

        eliminar.ejecutar(creado1.getId());

        List<Conductor> despuesDeEliminar = listar.ejecutar();
        assertEquals(1, despuesDeEliminar.size(), "Debe quedar solo un conductor después de eliminar");
        assertEquals("MARIA", despuesDeEliminar.get(0).getNombre());
    }
}
