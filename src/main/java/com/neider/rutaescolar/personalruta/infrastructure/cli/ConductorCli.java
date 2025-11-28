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
            System.out.println("=== GESTIÓN DE CONDUCTORES ===");
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
                try {
                    opcion = Integer.parseInt(linea.trim());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }
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
                    System.out.println("Saliendo del menú de conductores...");
                default ->
                    System.out.println("Opción inválida, intenta de nuevo.");
            }

            System.out.println();

        } while (opcion != 0);
    }

    private void crear(Scanner sc) {
        System.out.println("=== Crear conductor ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Número de licencia: ");
        String nroLicencia = sc.nextLine();
        System.out.print("Categoría de licencia (ej: C1, C2, A2): ");
        String categoriaLic = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        try {
            Conductor nuevo = new Conductor(
                    nombre,
                    apellido,
                    nroLicencia,
                    categoriaLic,
                    telefono,
                    EstadoTrabajador.ACTIVO
            );

            Conductor guardado = crearConductor.ejecutar(nuevo);
            System.out.println("Conductor creado: " + guardado);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear conductor: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("=== Listar conductores ===");
        List<Conductor> conductores = listarConductores.ejecutar();
        if (conductores.isEmpty()) {
            System.out.println("No hay conductores registrados.");
            return;
        }

        for (Conductor c : conductores) {
            System.out.println(c);
        }
    }

    private void buscar(Scanner sc) {
        System.out.println("=== Buscar conductor ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Optional<Conductor> opt = buscarConductorPorId.ejecutar(id);
        opt.ifPresentOrElse(
                c -> System.out.println("Conductor encontrado: " + c),
                () -> System.out.println("Conductor no encontrado.")
        );
    }

    private void actualizar(Scanner sc) {
        System.out.println("=== Actualizar conductor ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Optional<Conductor> opt = buscarConductorPorId.ejecutar(id);
        if (opt.isEmpty()) {
            System.out.println("Conductor no encontrado.");
            return;
        }

        Conductor existente = opt.get();
        System.out.println("Conductor actual: " + existente);
        System.out.println("Dejar vacío un campo si no quieres cambiarlo.");

        System.out.print("Nuevo nombre (actual: " + existente.getNombre() + "): ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Nuevo apellido (actual: " + existente.getApellido() + "): ");
        String nuevoApellido = sc.nextLine();

        System.out.print("Nuevo número de licencia (actual: " + existente.getNroLicencia() + "): ");
        String nuevoNroLicencia = sc.nextLine();

        System.out.print("Nueva categoría de licencia (actual: " + existente.getCategoriaLicencia() + "): ");
        String nuevaCategoriaLic = sc.nextLine();

        System.out.print("Nuevo teléfono (actual: " + existente.getTelefono() + "): ");
        String nuevoTelefono = sc.nextLine();

        System.out.print("Nuevo estado (ACTIVO/INACTIVO/SUSPENDIDO, actual: " + existente.getEstado() + "): ");
        String estadoStr = sc.nextLine();

        try {
            String nombreFinal = nuevoNombre.isBlank() ? existente.getNombre() : nuevoNombre;
            String apellidoFinal = nuevoApellido.isBlank() ? existente.getApellido() : nuevoApellido;
            String nroLicFinal = nuevoNroLicencia.isBlank() ? existente.getNroLicencia() : nuevoNroLicencia;
            String categoriaFinal = nuevaCategoriaLic.isBlank() ? existente.getCategoriaLicencia() : nuevaCategoriaLic;
            String telefonoFinal = nuevoTelefono.isBlank() ? existente.getTelefono() : nuevoTelefono;

            existente.actualizarDatosBasicos(
                    nombreFinal,
                    apellidoFinal,
                    nroLicFinal,
                    categoriaFinal,
                    telefonoFinal
            );

            if (!estadoStr.isBlank()) {
                EstadoTrabajador nuevoEstado = EstadoTrabajador.valueOf(estadoStr.trim().toUpperCase());
                existente.cambiarEstado(nuevoEstado);
            }

            Conductor actualizado = actualizarConductor.ejecutar(existente);
            System.out.println("Conductor actualizado: " + actualizado);

        } catch (IllegalArgumentException e) {
            System.out.println("Error al actualizar conductor: " + e.getMessage());
        }
    }

    private void eliminar(Scanner sc) {
        System.out.println("=== Eliminar conductor ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        eliminarConductor.ejecutar(id);
        System.out.println("Conductor eliminado (si existía).");
    }
}
