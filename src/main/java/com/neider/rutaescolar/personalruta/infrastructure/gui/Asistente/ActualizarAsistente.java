package com.neider.rutaescolar.personalruta.infrastructure.gui.Asistente;

import com.neider.rutaescolar.personalruta.application.ActualizarAsistenteUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import com.neider.rutaescolar.personalruta.infrastructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ActualizarAsistente extends javax.swing.JPanel {

    private final ActualizarAsistenteUseCase actualizarAsistenteUseCase;
    private Asistente asistenteActual;

  public ActualizarAsistente(ActualizarAsistenteUseCase actualizarAsistenteUseCase) {
        this.actualizarAsistenteUseCase = actualizarAsistenteUseCase;
        initComponents();
        inicializarEstados();
        configurarEventos();
    }

    public ActualizarAsistente() {
        this.actualizarAsistenteUseCase = null;
        initComponents();
        inicializarEstados();
        configurarEventos();
    }
    
       private void inicializarEstados() {
 
        Estados.removeAllItems();
        for (EstadoTrabajador estado : EstadoTrabajador.values()) {
            Estados.addItem(estado.name());
        }
    }

    public void cargarAsistente(Asistente asistente) {
        this.asistenteActual = asistente;
        if (asistente == null) {
            limpiarCampos();
            return;
        }
        CampoNombre.setText(asistente.getNombre());
        CampoApellido2.setText(asistente.getApellido());
        CampoTelefono.setText(asistente.getTelefono());
        if (asistente.getEstado() != null) {
            Estados.setSelectedItem(asistente.getEstado().name());
        } else if (Estados.getItemCount() > 0) {
            Estados.setSelectedIndex(0);
        }
    }
    
    private void configurarEventos() {
        Guardar.addActionListener(e -> onGuardar());
        Limpiar.addActionListener(e -> limpiarCampos());
        Cancelar.addActionListener(e -> onCancelar());
    }
    
   private void onGuardar() {
        if (actualizarAsistenteUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "El panel no está configurado con el caso de uso ActualizarAsistenteUseCase.",
                    "Error de configuración",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (asistenteActual == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay asistente cargado para actualizar. Selecciona uno desde la lista primero.",
                    "Sin asistente",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String nuevoNombre = CampoNombre.getText();
        String nuevoApellido = CampoApellido2.getText();
        String nuevoTelefono = CampoTelefono.getText();
        String estadoStr = (String) Estados.getSelectedItem();

        try {
            asistenteActual.actualizarDatosBasicos(
                    nuevoNombre,
                    nuevoApellido,
                    nuevoTelefono
            );

            if (estadoStr != null && !estadoStr.isBlank()) {
                EstadoTrabajador nuevoEstado =
                        EstadoTrabajador.valueOf(estadoStr.trim().toUpperCase());
                asistenteActual.cambiarEstado(nuevoEstado);
            }

            Asistente actualizado = actualizarAsistenteUseCase.ejecutar(asistenteActual);

            JOptionPane.showMessageDialog(
                    this,
                    "Asistente actualizado:\n" + actualizado,
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
            // Errores al hablar con la BD
            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar el asistente en la base de datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void onCancelar() {
        limpiarCampos();

        java.awt.Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }

        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);
    }

    private void limpiarCampos() {
        CampoNombre.setText("");
        CampoApellido2.setText("");
        CampoTelefono.setText("");
        if (Estados.getItemCount() > 0) {
            Estados.setSelectedIndex(0);
        }
        CampoNombre.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Apellido2 = new javax.swing.JLabel();
        Limpiar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        ActualizarAsistente = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        Estado = new javax.swing.JLabel();
        CampoNombre = new javax.swing.JTextField();
        Estados = new javax.swing.JComboBox<>();
        Guardar = new javax.swing.JButton();
        Icono = new javax.swing.JLabel();
        Apellido = new javax.swing.JLabel();
        Telefono = new javax.swing.JLabel();
        CampoTelefono = new javax.swing.JTextField();
        CampoApellido2 = new javax.swing.JTextField();

        Apellido2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Apellido2.setText("No. LICENCIA");
        Apellido2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Limpiar.setText("LIMPIAR");

        Cancelar.setText("CANCELAR");

        ActualizarAsistente.setFont(new java.awt.Font("Segoe UI", 0, 33)); // NOI18N
        ActualizarAsistente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ActualizarAsistente.setText("Actualizar Asistente");

        Nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Nombre.setText("NOMBRE:");
        Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Estado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estado.setText("ESTADO:");
        Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Estados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO", "SUSPENDIDO" }));

        Guardar.setText("GUARDAR");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/neider/rutaescolar/personalruta/infrastructure/gui/icons/note-edit-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        Apellido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Apellido.setText("APELLIDO:");
        Apellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                        .addGap(21, 21, 21)
                        .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Estados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CampoNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoApellido2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Guardar)
                                    .addGap(150, 150, 150)
                                    .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(118, 118, 118)
                                    .addComponent(Cancelar)
                                    .addGap(113, 113, 113)))
                            .addComponent(ActualizarAsistente))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ActualizarAsistente)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Estados, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActualizarAsistente;
    private javax.swing.JLabel Apellido;
    private javax.swing.JLabel Apellido2;
    private javax.swing.JTextField CampoApellido2;
    private javax.swing.JTextField CampoNombre;
    private javax.swing.JTextField CampoTelefono;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Estado;
    private javax.swing.JComboBox<String> Estados;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel Icono;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Telefono;
    // End of variables declaration//GEN-END:variables
}

