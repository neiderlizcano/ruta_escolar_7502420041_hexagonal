package com.neider.rutaescolar.personalruta.infraestructure.cli;

import com.neider.rutaescolar.personalruta.application.ActualizarBusUseCase;
import com.neider.rutaescolar.personalruta.application.BuscarBusPorIdUseCase;
import com.neider.rutaescolar.personalruta.application.CrearBusUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarBusUseCase;
import com.neider.rutaescolar.personalruta.application.ListarBusesUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.domain.model.EstadoBus;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BusCli {

    private final CrearBusUseCase crearBus;
    private final ActualizarBusUseCase actualizarBus;
    private final EliminarBusUseCase eliminarBus;
    private final ListarBusesUseCase listarBuses;
    private final BuscarBusPorIdUseCase buscarBusPorId;

    public BusCli(CrearBusUseCase crearBus,
                  ActualizarBusUseCase actualizarBus,
                  EliminarBusUseCase eliminarBus,
                  ListarBusesUseCase listarBuses,
                  BuscarBusPorIdUseCase buscarBusPorId) {
        this.crearBus = crearBus;
        this.actualizarBus = actualizarBus;
        this.eliminarBus = eliminarBus;
        this.listarBuses = listarBuses;
        this.buscarBusPorId = buscarBusPorId;
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("=== GESTIÓN DE BUSES ===");
            System.out.println("1. Crear bus");
            System.out.println("2. Listar buses");
            System.out.println("3. Buscar bus por ID");
            System.out.println("4. Actualizar bus");
            System.out.println("5. Eliminar bus");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String linea = sc.nextLine();
            if (linea.isBlank()) {
                opcion = -1;
            } else {
                try {
                    opcion = Integer.parseInt(linea.trim());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }
            }

            switch (opcion) {
                case 1 -> crear(sc);
                case 2 -> listar();
                case 3 -> buscar(sc);
                case 4 -> actualizar(sc);
                case 5 -> eliminar(sc);
                case 0 -> System.out.println("Saliendo del menú de buses...");
                default -> System.out.println("Opción inválida, intenta de nuevo.");
            }

            System.out.println();

        } while (opcion != 0);
    }

    private void crear(Scanner sc) {
        System.out.println("=== Crear bus ===");
        System.out.print("Placa: ");
        String placa = sc.nextLine();

        System.out.print("Estado (ACTIVO/INACTIVO/SUSPENDIDO, por defecto ACTIVO): ");
        String estadoStr = sc.nextLine();

        try {
            EstadoBus estado = estadoStr.isBlank()
                    ? EstadoBus.ACTIVO
                    : EstadoBus.valueOf(estadoStr.trim().toUpperCase());

            
            Bus nuevo = new Bus(placa, estado);

            Bus guardado = crearBus.ejecutar(nuevo);
            System.out.println("Bus creado: " + guardado); 

        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear bus: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("=== Listar buses ===");
        List<Bus> buses = listarBuses.ejecutar();
        if (buses.isEmpty()) {
            System.out.println("No hay buses registrados.");
            return;
        }

        for (Bus b : buses) {
            System.out.println(b);
        }
    }

    private void buscar(Scanner sc) {
        System.out.println("=== Buscar bus ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Optional<Bus> opt = buscarBusPorId.ejecutar(id);
        opt.ifPresentOrElse(
                b -> System.out.println("Bus encontrado: " + b),
                () -> System.out.println("Bus no encontrado.")
        );
    }

    private void actualizar(Scanner sc) {
        System.out.println("=== Actualizar bus ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Optional<Bus> opt = buscarBusPorId.ejecutar(id);
        if (opt.isEmpty()) {
            System.out.println("Bus no encontrado.");
            return;
        }

        Bus existente = opt.get();
        System.out.println("Bus actual: " + existente);
        System.out.println("Dejar vacío un campo si no quieres cambiarlo.");

        System.out.print("Nueva placa (actual: " + existente.getPlaca() + "): ");
        String nuevaPlaca = sc.nextLine();

        System.out.print("Nuevo estado (ACTIVO/INACTIVO/SUSPENDIDO, actual: " + existente.getEstado() + "): ");
        String estadoStr = sc.nextLine();

        try {
            if (!nuevaPlaca.isBlank()) {
                existente.actualizarPlaca(nuevaPlaca);
            }

            if (!estadoStr.isBlank()) {
                EstadoBus nuevoEstado = EstadoBus.valueOf(estadoStr.trim().toUpperCase());
                existente.cambiarEstado(nuevoEstado);
            }

            Bus actualizado = actualizarBus.ejecutar(existente);
            System.out.println("Bus actualizado: " + actualizado);

        } catch (IllegalArgumentException e) {
            System.out.println("Error al actualizar bus: " + e.getMessage());
        }
    }

    private void eliminar(Scanner sc) {
        System.out.println("=== Eliminar bus ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        eliminarBus.ejecutar(id);
        System.out.println("Bus eliminado (si existía).");
    }
}
