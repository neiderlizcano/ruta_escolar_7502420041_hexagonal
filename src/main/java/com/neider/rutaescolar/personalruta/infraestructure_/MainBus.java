package com.neider.rutaescolar.personalruta.infraestructure_;

/*package com.neider.rutaescolar.personalruta.infrastructure_;

import com.neider.rutaescolar.personalruta.application.ActualizarBusUseCase;
import com.neider.rutaescolar.personalruta.application.BuscarBusPorIdUseCase;
import com.neider.rutaescolar.personalruta.application.CrearBusUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarBusUseCase;
import com.neider.rutaescolar.personalruta.application.ListarBusesUseCase;
import com.neider.rutaescolar.personalruta.domain.ports.BusRepository;
import com.neider.rutaescolar.personalruta.infrastructure.cli.BusCli;
import com.neider.rutaescolar.personalruta.infrastructure.db.MySqlBusRepository;

public class MainBus {

    public static void main(String[] args) {

        BusRepository busRepository = new MySqlBusRepository();

        CrearBusUseCase crearBus = new CrearBusUseCase(busRepository);
        ActualizarBusUseCase actualizarBus = new ActualizarBusUseCase(busRepository);
        EliminarBusUseCase eliminarBus = new EliminarBusUseCase(busRepository);
        ListarBusesUseCase listarBuses = new ListarBusesUseCase(busRepository);
        BuscarBusPorIdUseCase buscarBusPorId = new BuscarBusPorIdUseCase(busRepository);

        BusCli cli = new BusCli(
                crearBus,
                actualizarBus,
                eliminarBus,
                listarBuses,
                buscarBusPorId
        );

        cli.mostrarMenu();
    }
}*/