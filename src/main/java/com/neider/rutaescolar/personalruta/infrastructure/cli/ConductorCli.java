package com.neider.rutaescolar.personalruta.infrastructure.cli;

import com.neider.rutaescolar.personalruta.application.ActualizarConductorUseCase;
import com.neider.rutaescolar.personalruta.application.BuscarConductorPorIdUseCase;
import com.neider.rutaescolar.personalruta.application.CrearConductorUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarConductorUseCase;
import com.neider.rutaescolar.personalruta.application.ListarConductoresUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConductorCli {

    private final CrearConductorUseCase crearConductor;
    private final ActualizarConductorUseCase actualizarConductor;
    private final EliminarConductorUseCase eliminarConductor;
    private final ListarConductoresUseCase listarConductores;
    private final BuscarConductorPorIdUseCase buscarConductorPorId;

    public ConductorCli(CrearConductorUseCase crearConductor,
            ActualizarConductorUseCase actualizarConductor,
            EliminarConductorUseCase eliminarConductor,
            ListarConductoresUseCase listarConductores,
            BuscarConductorPorIdUseCase buscarConductorPorId) {
        this.crearConductor = crearConductor;
        this.actualizarConductor = actualizarConductor;
        this.eliminarConductor = eliminarConductor;
        this.listarConductores = listarConductores;
        this.buscarConductorPorId = buscarConductorPorId;
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("=== GESTION DE CONDUCTORES ===");
            System.out.println("1. Crear conductor");
            System.out.println("2. Listar conductores");
            System.out.println("3. Buscar conductor por ID");
            System.out.println("4. Actualizar conductor");
            System.out.println("5. Eliminar conductor");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String linea = sc.nextLine();
            if (linea.isBlank()) {
                opcion = -1;
            } else {
                opcion = Integer.parseInt(linea);
            }

            switch (opcion) {
                case 1 ->
                    crear(sc);
                case 2 ->
                    listar();
                case 3 ->
                    buscar(sc);
                case 4 ->
                    actualizar(sc);
                case 5 ->
                    eliminar(sc);
                case 0 ->
                    System.out.println("Saliendo del menú...");
                default ->
                    System.out.println("Opción inválida");
            }

            System.out.println();

        } while (opcion != 0);
    }

    private void crear(Scanner sc) {
        System.out.println("=== Crear conductor ===");
        System.out.print("Nombres: ");
        String nombres = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        System.out.print("Número de licencia: ");
        String nroLicencia = sc.nextLine();
        System.out.print("Categoría de licencia: ");
        String categoriaLic = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        Conductor nuevo = new Conductor(
                nombres,
                apellidos,
                documento,
                nroLicencia,
                categoriaLic,
                telefono,
                EstadoTrabajador.ACTIVO
        );

        Conductor guardado = crearConductor.ejecutar(nuevo);
        System.out.println("Conductor creado con ID: " + guardado.getId());
    }

    private void listar() {
        System.out.println("=== Listar conductores ===");
        List<Conductor> conductores = listarConductores.ejecutar();
        if (conductores.isEmpty()) {
            System.out.println("No hay conductores registrados.");
            return;
        }

        for (Conductor c : conductores) {
            System.out.printf("%d - %s %s | Doc: %s | Lic: %s (%s) | Estado: %s%n",
                    c.getId(),
                    c.getNombres(),
                    c.getApellidos(),
                    c.getDocumento(),
                    c.getNroLicencia(),
                    c.getCategoriaLicencia(),
                    c.getEstado().name());
        }
    }

    private void buscar(Scanner sc) {
        System.out.println("=== Buscar conductor ===");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());
        Optional<Conductor> opt = buscarConductorPorId.ejecutar(id);
        opt.ifPresentOrElse(
                c -> System.out.printf("Encontrado: %d - %s %s | Doc: %s | Lic: %s | Estado: %s%n",
                        c.getId(),
                        c.getNombres(),
                        c.getApellidos(),
                        c.getDocumento(),
                        c.getNroLicencia(),
                        c.getEstado().name()),
                () -> System.out.println("Conductor no encontrado")
        );
    }

    private void actualizar(Scanner sc) {
        System.out.println("=== Actualizar conductor ===");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());

        Optional<Conductor> opt = buscarConductorPorId.ejecutar(id);
        if (opt.isEmpty()) {
            System.out.println("Conductor no encontrado");
            return;
        }

        Conductor existente = opt.get();

        System.out.println("Dejar vacío un campo si no quieres cambiarlo.");
        System.out.print("Nuevo teléfono (actual: " + existente.getTelefono() + "): ");
        String telefono = sc.nextLine();
        if (!telefono.isBlank()) {
            existente.setTelefono(telefono);
        }

        System.out.print("Nuevo estado (ACTIVO/INACTIVO/SUSPENDIDO, actual: " + existente.getEstado() + "): ");
        String estadoStr = sc.nextLine();
        if (!estadoStr.isBlank()) {
            existente.setEstado(EstadoTrabajador.valueOf(estadoStr.toUpperCase()));
        }

        Conductor actualizado = actualizarConductor.ejecutar(existente);
        System.out.println("Conductor actualizado. Teléfono: " + actualizado.getTelefono()
                + ", Estado: " + actualizado.getEstado());
    }

    private void eliminar(Scanner sc) {
        System.out.println("=== Eliminar conductor ===");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());
        eliminarConductor.ejecutar(id);
        System.out.println("Conductor eliminado (si existía).");
    }
}
