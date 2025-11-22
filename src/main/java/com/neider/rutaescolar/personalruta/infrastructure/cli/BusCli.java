package com.neider.rutaescolar.personalruta.infrastructure.cli;

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
            System.out.println("=== GESTION DE BUSES ===");
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
                opcion = Integer.parseInt(linea);
            }

            switch (opcion) {
                case 1 -> crear(sc);
                case 2 -> listar();
                case 3 -> buscar(sc);
                case 4 -> actualizar(sc);
                case 5 -> eliminar(sc);
                case 0 -> System.out.println("Saliendo del menú de buses...");
                default -> System.out.println("Opción inválida");
            }

            System.out.println();

        } while (opcion != 0);
    }

    private void crear(Scanner sc) {
        System.out.println("=== Crear bus ===");
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Capacidad: ");
        Integer capacidad = Integer.parseInt(sc.nextLine());

        Bus nuevo = new Bus(
                placa,
                capacidad,
                EstadoBus.ACTIVO
        );

        Bus guardado = crearBus.ejecutar(nuevo);
        System.out.println("Bus creado con ID: " + guardado.getId());
    }

    private void listar() {
        System.out.println("=== Listar buses ===");
        List<Bus> buses = listarBuses.ejecutar();
        if (buses.isEmpty()) {
            System.out.println("No hay buses registrados.");
            return;
        }

        for (Bus b : buses) {
            System.out.printf("%d - Placa: %s | Capacidad: %d | Estado: %s%n",
                    b.getId(),
                    b.getPlaca(),
                    b.getCapacidad(),
                    b.getEstado().name());
        }
    }

    private void buscar(Scanner sc) {
        System.out.println("=== Buscar bus ===");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());
        Optional<Bus> opt = buscarBusPorId.ejecutar(id);
        opt.ifPresentOrElse(
                b -> System.out.printf("Encontrado: %d - Placa: %s | Capacidad: %d | Estado: %s%n",
                        b.getId(),
                        b.getPlaca(),
                        b.getCapacidad(),
                        b.getEstado().name()),
                () -> System.out.println("Bus no encontrado")
        );
    }

    private void actualizar(Scanner sc) {
        System.out.println("=== Actualizar bus ===");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());

        Optional<Bus> opt = buscarBusPorId.ejecutar(id);
        if (opt.isEmpty()) {
            System.out.println("Bus no encontrado");
            return;
        }

        Bus existente = opt.get();

        System.out.println("Dejar vacío un campo si no quieres cambiarlo.");
        System.out.print("Nueva capacidad (actual: " + existente.getCapacidad() + "): ");
        String capStr = sc.nextLine();
        if (!capStr.isBlank()) {
            existente.setCapacidad(Integer.parseInt(capStr));
        }

        System.out.print("Nuevo estado (ACTIVO/INACTIVO/SUSPENDIDO, actual: " + existente.getEstado() + "): ");
        String estadoStr = sc.nextLine();
        if (!estadoStr.isBlank()) {
            existente.setEstado(EstadoBus.valueOf(estadoStr.toUpperCase()));
        }

        Bus actualizado = actualizarBus.ejecutar(existente);
        System.out.println("Bus actualizado. Capacidad: " + actualizado.getCapacidad() +
                ", Estado: " + actualizado.getEstado());
    }

    private void eliminar(Scanner sc) {
        System.out.println("=== Eliminar bus ===");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());
        eliminarBus.ejecutar(id);
        System.out.println("Bus eliminado (si existía).");
    }
}
