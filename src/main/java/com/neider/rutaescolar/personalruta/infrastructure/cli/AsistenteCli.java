package com.neider.rutaescolar.personalruta.infrastructure.cli;

import com.neider.rutaescolar.personalruta.application.ActualizarAsistenteUseCase;
import com.neider.rutaescolar.personalruta.application.BuscarAsistentePorIdUseCase;
import com.neider.rutaescolar.personalruta.application.CrearAsistenteUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarAsistenteUseCase;
import com.neider.rutaescolar.personalruta.application.ListarAsistentesUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AsistenteCli {

    private final CrearAsistenteUseCase crearAsistente;
    private final ActualizarAsistenteUseCase actualizarAsistente;
    private final EliminarAsistenteUseCase eliminarAsistente;
    private final ListarAsistentesUseCase listarAsistentes;
    private final BuscarAsistentePorIdUseCase buscarAsistentePorId;

    public AsistenteCli(CrearAsistenteUseCase crearAsistente,
                        ActualizarAsistenteUseCase actualizarAsistente,
                        EliminarAsistenteUseCase eliminarAsistente,
                        ListarAsistentesUseCase listarAsistentes,
                        BuscarAsistentePorIdUseCase buscarAsistentePorId) {
        this.crearAsistente = crearAsistente;
        this.actualizarAsistente = actualizarAsistente;
        this.eliminarAsistente = eliminarAsistente;
        this.listarAsistentes = listarAsistentes;
        this.buscarAsistentePorId = buscarAsistentePorId;
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("=== GESTIÓN DE ASISTENTES ===");
            System.out.println("1. Crear asistente");
            System.out.println("2. Listar asistentes");
            System.out.println("3. Buscar asistente por ID");
            System.out.println("4. Actualizar asistente");
            System.out.println("5. Eliminar asistente");
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
                case 0 -> System.out.println("Saliendo del menú de asistentes...");
                default -> System.out.println("Opción inválida, intenta de nuevo.");
            }

            System.out.println();

        } while (opcion != 0);
    }

    private void crear(Scanner sc) {
        System.out.println("=== Crear asistente ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        try {
            Asistente nuevo = new Asistente(
                    nombre,
                    apellido,
                    telefono,
                    EstadoTrabajador.ACTIVO
            );

            Asistente guardado = crearAsistente.ejecutar(nuevo);
            System.out.println("Asistente creado: " + guardado);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear asistente: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("=== Listar asistentes ===");
        List<Asistente> asistentes = listarAsistentes.ejecutar();
        if (asistentes.isEmpty()) {
            System.out.println("No hay asistentes registrados.");
            return;
        }

        for (Asistente a : asistentes) {
            System.out.println(a); 
        }
    }

    private void buscar(Scanner sc) {
        System.out.println("=== Buscar asistente ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Optional<Asistente> opt = buscarAsistentePorId.ejecutar(id);
        opt.ifPresentOrElse(
                a -> System.out.println("Asistente encontrado: " + a),
                () -> System.out.println("Asistente no encontrado.")
        );
    }

    private void actualizar(Scanner sc) {
        System.out.println("=== Actualizar asistente ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Optional<Asistente> opt = buscarAsistentePorId.ejecutar(id);
        if (opt.isEmpty()) {
            System.out.println("Asistente no encontrado.");
            return;
        }

        Asistente existente = opt.get();
        System.out.println("Dejar vacío el campo si no quieres cambiarlo.");

        System.out.print("Nuevo teléfono (actual: " + existente.getTelefono() + "): ");
        String nuevoTelefono = sc.nextLine();

        System.out.print("Nuevo estado (ACTIVO/INACTIVO/SUSPENDIDO, actual: " + existente.getEstado() + "): ");
        String estadoStr = sc.nextLine();

        try {
            if (!nuevoTelefono.isBlank()) {
                existente.actualizarTelefono(nuevoTelefono);
            }

            if (!estadoStr.isBlank()) {
                EstadoTrabajador nuevoEstado = EstadoTrabajador.valueOf(estadoStr.trim().toUpperCase());
                existente.cambiarEstado(nuevoEstado);
            }

            Asistente actualizado = actualizarAsistente.ejecutar(existente);
            System.out.println("Asistente actualizado: " + actualizado);

        } catch (IllegalArgumentException e) {
            System.out.println("Error al actualizar asistente: " + e.getMessage());
        }
    }

    private void eliminar(Scanner sc) {
        System.out.println("=== Eliminar asistente ===");
        System.out.print("ID: ");
        String linea = sc.nextLine();
        Integer id;
        try {
            id = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        eliminarAsistente.ejecutar(id);
        System.out.println("Asistente eliminado (si existía).");
    }
}
