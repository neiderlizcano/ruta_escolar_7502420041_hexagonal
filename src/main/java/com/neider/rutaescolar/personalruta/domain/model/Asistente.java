package com.neider.rutaescolar.personalruta.domain.model;

public class Asistente {

    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private EstadoTrabajador estado;

    // Constructor de asistente ya registrado
    public Asistente(Integer id,
            String nombre,
            String apellido,
            String telefono,
            EstadoTrabajador estado) {
        this.id = id;
        this.nombre = nombreApellidoValido(nombre, "nombre");
        this.apellido = nombreApellidoValido(apellido, "apellido");
        this.telefono = telefonoValido(telefono);
        this.estado = validarEstado(estado);
    }

    // Constructor para crear nuevo asistente
    public Asistente(String nombre,
            String apellido,
            String telefono,
            EstadoTrabajador estado) {
        this(null, nombre, apellido, telefono, estado);
    }

    //Validaciones para campos validos
    private String nombreApellidoValido(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    "El " + campo + " del asistente es obligatorio"
            );
        }
        String limpio = valor.trim().toUpperCase();

        if (!limpio.matches("[A-ZÁÉÍÓÚÜÑ ]+")) {
            throw new IllegalArgumentException(
                    "El " + campo + " del asistente solo debe contener letras y espacios"
            );
        }

        return limpio;
    }

    private String telefonoValido(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("El teléfono del asistente es obligatorio");
        }
        String limpio = telefono.trim();
        if (!limpio.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "El teléfono del asistente solo debe contener números"
            );
        }
        return limpio;
    }

    private EstadoTrabajador validarEstado(EstadoTrabajador estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado del asistente es obligatorio");
        }
        return estado;
    }

    // Get y sets
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public EstadoTrabajador getEstado() {
        return estado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //Metodos para actualizar datos del asistente
    public void actualizarDatosBasicos(String nuevoNombre,
            String nuevoApellido,
            String nuevoTelefono) {
        this.nombre = nombreApellidoValido(nuevoNombre, "nombre");
        this.apellido = nombreApellidoValido(nuevoApellido, "apellido");
        this.telefono = telefonoValido(nuevoTelefono);
    }

    public void actualizarTelefono(String nuevoTelefono) {
        this.telefono = telefonoValido(nuevoTelefono);
    }

    public void cambiarEstado(EstadoTrabajador nuevoEstado) {
        this.estado = validarEstado(nuevoEstado);
    }

    @Override
    public String toString() {
        String idTexto = (id != null) ? id.toString() : "NUEVO";
        String estadoTexto = (estado != null) ? estado.name() : "N/D";
        return String.format(
                "%s - %s %s | Tel: %s | Estado: %s",
                idTexto,
                nombre,
                apellido,
                telefono,
                estadoTexto
        );
    }
}
