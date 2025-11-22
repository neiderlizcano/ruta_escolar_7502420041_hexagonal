package com.neider.rutaescolar.personalruta.infrastructure_;

import com.neider.rutaescolar.personalruta.application.ActualizarAsistenteUseCase;
import com.neider.rutaescolar.personalruta.application.BuscarAsistentePorIdUseCase;
import com.neider.rutaescolar.personalruta.application.CrearAsistenteUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarAsistenteUseCase;
import com.neider.rutaescolar.personalruta.application.ListarAsistentesUseCase;
import com.neider.rutaescolar.personalruta.domain.ports.AsistenteRepository;
import com.neider.rutaescolar.personalruta.infrastructure.cli.AsistenteCli;
import com.neider.rutaescolar.personalruta.infrastructure.db.MySqlAsistenteRepository;

public class MainAsistente {

    public static void main(String[] args) {

        AsistenteRepository asistenteRepository = new MySqlAsistenteRepository();

        CrearAsistenteUseCase crearAsistente = new CrearAsistenteUseCase(asistenteRepository);
        ActualizarAsistenteUseCase actualizarAsistente = new ActualizarAsistenteUseCase(asistenteRepository);
        EliminarAsistenteUseCase eliminarAsistente = new EliminarAsistenteUseCase(asistenteRepository);
        ListarAsistentesUseCase listarAsistentes = new ListarAsistentesUseCase(asistenteRepository);
        BuscarAsistentePorIdUseCase buscarAsistentePorId = new BuscarAsistentePorIdUseCase(asistenteRepository);

        AsistenteCli cli = new AsistenteCli(
                crearAsistente,
                actualizarAsistente,
                eliminarAsistente,
                listarAsistentes,
                buscarAsistentePorId
        );

        cli.mostrarMenu();
    }
}
