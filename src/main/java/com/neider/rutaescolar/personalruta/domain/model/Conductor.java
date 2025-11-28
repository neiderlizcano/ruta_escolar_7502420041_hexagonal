package com.neider.rutaescolar.personalruta.domain.model;

public class Conductor {

    private Integer id;
    private String nombre;
    private String apellido;
    private String nroLicencia;
    private String categoriaLicencia;
    private String telefono;
    private EstadoTrabajador estado;

    // Constructor para conductor registrado
    public Conductor(Integer id,
            String nombre,
            String apellido,
            String nroLicencia,
            String categoriaLicencia,
            String telefono,
            EstadoTrabajador estado) {

        this.id = id;
        this.nombre = nombreApellidoValido(nombre, "nombre");
        this.apellido = nombreApellidoValido(apellido, "apellido");
        this.nroLicencia = licenciaValida(nroLicencia);
        this.categoriaLicencia = categoriaValida(categoriaLicencia);
        this.telefono = telefonoValido(telefono);
        this.estado = validarEstado(estado);
    }

    // Constructor para conductor ya creado
    public Conductor(String nombre,
            String apellido,
            String nroLicencia,
            String categoriaLicencia,
            String telefono,
            EstadoTrabajador estado) {
        this(null, nombre, apellido, nroLicencia, categoriaLicencia, telefono, estado);
    }

    // validaciones de campos.
    private String nombreApellidoValido(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    "El " + campo + " del conductor es obligatorio, no lo dejes vacío."
            );
        }

        String limpio = valor.trim().toUpperCase();

        if (!limpio.matches("[A-ZÁÉÍÓÚÜÑ ]+")) {
            throw new IllegalArgumentException(
                    "El " + campo + " del conductor solo debe tener letras y espacios. Revisa lo que escribiste."
            );
        }

        return limpio;
    }

    private String licenciaValida(String licencia) {
        if (licencia == null || licencia.isBlank()) {
            throw new IllegalArgumentException(
                    "El número de licencia del conductor es obligatorio."
            );
        }
        String limpio = licencia.trim().toUpperCase();
        if (!limpio.matches("[A-Z0-9\\-]+")) {
            throw new IllegalArgumentException(
                    "El número de licencia solo debe tener letras, números y guiones. Nada de espacios ni símbolos raros."
            );
        }
        return limpio;
    }

    private String categoriaValida(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException(
                    "La categoría de la licencia es obligatoria."
            );
        }
        String limpio = categoria.trim().toUpperCase();
        if (!limpio.matches("[A-Z0-9]+")) {
            throw new IllegalArgumentException(
                    "La categoría de licencia solo debe tener letras y números. Ejemplos válidos: A2, C1."
            );
        }
        return limpio;
    }

    private String telefonoValido(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException(
                    "El teléfono del conductor es obligatorio. Escribe un número de contacto."
            );
        }
        String limpio = telefono.trim();
        if (!limpio.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "El teléfono del conductor solo debe tener números, sin espacios ni símbolos."
            );
        }
        return limpio;
    }

    private EstadoTrabajador validarEstado(EstadoTrabajador estado) {
        if (estado == null) {
            throw new IllegalArgumentException(
                    "Debes seleccionar un estado para el conductor."
            );
        }
        return estado;
    }

    // Get y set
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNroLicencia() {
        return nroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
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

    /**
     * Actualiza todos los datos básicos del conductor aplicando las mismas
     * reglas de validación y mayúsculas.
     *
     * Útil cuando en la GUI se permite editar nombre, apellido, número de
     * licencia, categoría y teléfono al mismo tiempo.
     */
    public void actualizarDatosBasicos(String nuevoNombre,
            String nuevoApellido,
            String nuevoNroLicencia,
            String nuevaCategoriaLicencia,
            String nuevoTelefono) {
        this.nombre = nombreApellidoValido(nuevoNombre, "nombre");
        this.apellido = nombreApellidoValido(nuevoApellido, "apellido");
        this.nroLicencia = licenciaValida(nuevoNroLicencia);
        this.categoriaLicencia = categoriaValida(nuevaCategoriaLicencia);
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
        return "Conductor{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", nroLicencia='" + nroLicencia + '\''
                + ", categoriaLicencia='" + categoriaLicencia + '\''
                + ", telefono='" + telefono + '\''
                + ", estado=" + estado
                + '}';
    }

}
