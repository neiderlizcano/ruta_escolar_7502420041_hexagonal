/*package com.neider.rutaescolar.personalruta.infrastructure_;

import com.neider.rutaescolar.personalruta.application.ActualizarConductorUseCase;
import com.neider.rutaescolar.personalruta.application.BuscarConductorPorIdUseCase;
import com.neider.rutaescolar.personalruta.application.CrearConductorUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarConductorUseCase;
import com.neider.rutaescolar.personalruta.application.ListarConductoresUseCase;
import com.neider.rutaescolar.personalruta.domain.ports.ConductorRepository;
import com.neider.rutaescolar.personalruta.infrastructure.cli.ConductorCli;
import com.neider.rutaescolar.personalruta.infrastructure.db.MySqlConductorRepository;

public class Main {

    public static void main(String[] args) {

        ConductorRepository conductorRepository = new MySqlConductorRepository();

        CrearConductorUseCase crearConductor = new CrearConductorUseCase(conductorRepository);
        ActualizarConductorUseCase actualizarConductor = new ActualizarConductorUseCase(conductorRepository);
        EliminarConductorUseCase eliminarConductor = new EliminarConductorUseCase(conductorRepository);
        ListarConductoresUseCase listarConductores = new ListarConductoresUseCase(conductorRepository);
        BuscarConductorPorIdUseCase buscarConductorPorId = new BuscarConductorPorIdUseCase(conductorRepository);

        ConductorCli cli = new ConductorCli(
                crearConductor,
                actualizarConductor,
                eliminarConductor,
                listarConductores,
                buscarConductorPorId
        );

        cli.mostrarMenu();
    }
}
*/