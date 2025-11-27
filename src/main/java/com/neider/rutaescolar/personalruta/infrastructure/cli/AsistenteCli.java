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
            System.out.println("GESTION DE ASISTENTES");
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
                opcion = Integer.parseInt(linea);
            }

            switch (opcion) {
                case 1 -> crear(sc);
                case 2 -> listar();
                case 3 -> buscar(sc);
                case 4 -> actualizar(sc);
                case 5 -> eliminar(sc);
                case 0 -> System.out.println("Saliendo del menú de asistentes...");
                default -> System.out.println("Opción inválida");
            }

            System.out.println();

        } while (opcion != 0);
    }

    private void crear(Scanner sc) {
        System.out.println("Crear asistente");
        System.out.print("Nombres: ");
        String nombres = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        Asistente nuevo = new Asistente(
                nombres,
                apellidos,
                documento,
                telefono,
                EstadoTrabajador.ACTIVO
        );

        Asistente guardado = crearAsistente.ejecutar(nuevo);
        System.out.println("Asistente creado con ID: " + guardado.getId());
    }

    private void listar() {
        System.out.println("Listar asistentes");
        List<Asistente> asistentes = listarAsistentes.ejecutar();
        if (asistentes.isEmpty()) {
            System.out.println("No hay asistentes registrados.");
            return;
        }

        for (Asistente a : asistentes) {
            System.out.printf("%d - %s %s | Doc: %s | Tel: %s | Estado: %s%n",
                    a.getId(),
                    a.getNombres(),
                    a.getApellidos(),
                    a.getDocumento(),
                    a.getTelefono(),
                    a.getEstado().name());
        }
    }

    private void buscar(Scanner sc) {
        System.out.println("Buscar asistente");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());
        Optional<Asistente> opt = buscarAsistentePorId.ejecutar(id);
        opt.ifPresentOrElse(
                a -> System.out.printf("Encontrado: %d - %s %s | Doc: %s | Tel: %s | Estado: %s%n",
                        a.getId(),
                        a.getNombres(),
                        a.getApellidos(),
                        a.getDocumento(),
                        a.getTelefono(),
                        a.getEstado().name()),
                () -> System.out.println("Asistente no encontrado")
        );
    }

    private void actualizar(Scanner sc) {
        System.out.println("Actualizar asistente");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());

        Optional<Asistente> opt = buscarAsistentePorId.ejecutar(id);
        if (opt.isEmpty()) {
            System.out.println("Asistente no encontrado");
            return;
        }

        Asistente existente = opt.get();

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

        Asistente actualizado = actualizarAsistente.ejecutar(existente);
        System.out.println("Asistente actualizado. Teléfono: " + actualizado.getTelefono() +
                ", Estado: " + actualizado.getEstado());
    }

    private void eliminar(Scanner sc) {
        System.out.println("=== Eliminar asistente ===");
        System.out.print("ID: ");
        Integer id = Integer.parseInt(sc.nextLine());
        eliminarAsistente.ejecutar(id);
        System.out.println("Asistente eliminado (si existía).");
    }
}