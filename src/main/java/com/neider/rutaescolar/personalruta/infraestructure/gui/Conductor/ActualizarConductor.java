package com.neider.rutaescolar.personalruta.infraestructure.gui.Conductor;

import com.neider.rutaescolar.personalruta.application.ActualizarConductorUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ActualizarConductor extends javax.swing.JPanel {

    private final ActualizarConductorUseCase actualizarConductorUseCase;
    private Conductor conductorActual;

    public ActualizarConductor(ActualizarConductorUseCase actualizarConductorUseCase) {
        this.actualizarConductorUseCase = actualizarConductorUseCase;
        initComponents();
        inicializarEstados();
        configurarEventos();
    }

     public void cargarConductor(Conductor conductor) {
        this.conductorActual = conductor;

        if (conductor == null) {
            limpiarFormulario();
            return;
        }

        CampoNombre.setText(conductor.getNombre());
        CampoApellido2.setText(conductor.getApellido());
        CampoNumeroLicencia.setText(conductor.getNroLicencia());
        CampoCategoriaLicencia1.setText(conductor.getCategoriaLicencia());
        CampoTelefono.setText(conductor.getTelefono());

        if (conductor.getEstado() != null) {
            Estados.setSelectedItem(conductor.getEstado().name());
        } else if (Estados.getItemCount() > 0) {
            Estados.setSelectedIndex(0);
        }
    }

    private void inicializarEstados() {
        Estados.removeAllItems();
        for (EstadoTrabajador estado : EstadoTrabajador.values()) {
            Estados.addItem(estado.name());
        }
    }

    private void configurarEventos() {
        Guardar.addActionListener(e -> onGuardar());
        Limpiar.addActionListener(e -> limpiarFormulario());
        Cancelar.addActionListener(e -> onCancelar());
    }

    private void onGuardar() {
        if (actualizarConductorUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "El panel no está configurado con ActualizarConductorUseCase (solo vista de diseño).",
                    "Error de configuración",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (conductorActual == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay conductor cargado para actualizar. Selecciónalo primero desde la pantalla de búsqueda.",
                    "Sin conductor",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String nombre = CampoNombre.getText();
        String apellido = CampoApellido2.getText();
        String nroLicencia = CampoNumeroLicencia.getText();
        String categoriaLic = CampoCategoriaLicencia1.getText();
        String telefono = CampoTelefono.getText();
        Object seleccionado = Estados.getSelectedItem();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debes seleccionar un estado para el conductor.",
                    "Dato requerido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            conductorActual.actualizarDatosBasicos(
                    nombre,
                    apellido,
                    nroLicencia,
                    categoriaLic,
                    telefono
            );

            EstadoTrabajador nuevoEstado =
                    EstadoTrabajador.valueOf(seleccionado.toString().toUpperCase());
            conductorActual.cambiarEstado(nuevoEstado);

            Conductor actualizado = actualizarConductorUseCase.ejecutar(conductorActual);

            JOptionPane.showMessageDialog(
                    this,
                    "Conductor actualizado:\n" + actualizado,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error de datos",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar el conductor en la base de datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiarFormulario() {
        CampoNombre.setText("");
        CampoApellido2.setText("");
        CampoNumeroLicencia.setText("");
        CampoCategoriaLicencia1.setText("");
        CampoTelefono.setText("");
        if (Estados.getItemCount() > 0) {
            Estados.setSelectedIndex(0);
        }
        CampoNombre.requestFocus();
    }

    private void onCancelar() {
        limpiarFormulario();

        java.awt.Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }

        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);
    }
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Apellido2 = new javax.swing.JLabel();
        Limpiar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        ActualizarConductor = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        Estado = new javax.swing.JLabel();
        CampoNombre = new javax.swing.JTextField();
        Estados = new javax.swing.JComboBox<>();
        Guardar = new javax.swing.JButton();
        Icono = new javax.swing.JLabel();
        NumeroLicencia = new javax.swing.JLabel();
        Apellido = new javax.swing.JLabel();
        CategoriaLicencia = new javax.swing.JLabel();
        Telefono = new javax.swing.JLabel();
        CampoNumeroLicencia = new javax.swing.JTextField();
        CampoTelefono = new javax.swing.JTextField();
        CampoApellido2 = new javax.swing.JTextField();
        CampoCategoriaLicencia1 = new javax.swing.JTextField();

        Apellido2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Apellido2.setText("No. LICENCIA");
        Apellido2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Limpiar.setText("LIMPIAR");

        Cancelar.setText("CANCELAR");

        ActualizarConductor.setFont(new java.awt.Font("Segoe UI", 0, 33)); // NOI18N
        ActualizarConductor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ActualizarConductor.setText("Actualizar Conductor");

        Nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Nombre.setText("NOMBRE:");
        Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Estado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estado.setText("ESTADO:");
        Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Estados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO", "SUSPENDIDO" }));

        Guardar.setText("GUARDAR");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/note-edit-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        NumeroLicencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NumeroLicencia.setText("No. LICENCIA:");
        NumeroLicencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Apellido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Apellido.setText("APELLIDO:");
        Apellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        CategoriaLicencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CategoriaLicencia.setText("CATEGORIA LICENCIA:");
        CategoriaLicencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Telefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Telefono.setText("TELEFONO:");
        Telefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(ActualizarConductor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(Guardar)
                        .addGap(116, 116, 116)
                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CategoriaLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(NumeroLicencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Estados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CampoNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoApellido2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoNumeroLicencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoCategoriaLicencia1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Cancelar)
                .addGap(203, 203, 203))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ActualizarConductor)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(CampoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(CampoApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(CampoNumeroLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(CampoCategoriaLicencia1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CategoriaLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(Estados, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(NumeroLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CampoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActualizarConductor;
    private javax.swing.JLabel Apellido;
    private javax.swing.JLabel Apellido2;
    private javax.swing.JTextField CampoApellido2;
    private javax.swing.JTextField CampoCategoriaLicencia1;
    private javax.swing.JTextField CampoNombre;
    private javax.swing.JTextField CampoNumeroLicencia;
    private javax.swing.JTextField CampoTelefono;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel CategoriaLicencia;
    private javax.swing.JLabel Estado;
    private javax.swing.JComboBox<String> Estados;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel Icono;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel NumeroLicencia;
    private javax.swing.JLabel Telefono;
    // End of variables declaration//GEN-END:variables
}

