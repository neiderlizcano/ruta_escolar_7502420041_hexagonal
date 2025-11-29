package com.neider.rutaescolar.personalruta.infrastructure.gui.Asistente;

import com.neider.rutaescolar.personalruta.application.CrearAsistenteUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import com.neider.rutaescolar.personalruta.infrastructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CrearAsistente extends javax.swing.JPanel {

    private final CrearAsistenteUseCase crearAsistenteUseCase;

    public CrearAsistente() {
        this.crearAsistenteUseCase = null;
        initComponents();
        inicializarEstado();
    }

    public CrearAsistente(CrearAsistenteUseCase crearAsistenteUseCase) {
        this.crearAsistenteUseCase = crearAsistenteUseCase;
        initComponents();
        inicializarEstado();
    }

    private void inicializarEstado() {
        Estados1.removeAllItems();
        Estados1.addItem("ACTIVO");
        Estados1.addItem("INACTIVO");
        Estados1.addItem("SUSPENDIDO");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Icono = new javax.swing.JLabel();
        Limpiar1 = new javax.swing.JButton();
        Cancelar1 = new javax.swing.JButton();
        CrearAsistente = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        Estado1 = new javax.swing.JLabel();
        CampoNombre = new javax.swing.JTextField();
        Estados1 = new javax.swing.JComboBox<>();
        Guardar1 = new javax.swing.JButton();
        Apellido = new javax.swing.JLabel();
        Telefono = new javax.swing.JLabel();
        CampoTelefono = new javax.swing.JTextField();
        CampoApellido2 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/neider/rutaescolar/personalruta/infrastructure/gui/icons/create-new-page-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        Limpiar1.setText("LIMPIAR");
        Limpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpiar1ActionPerformed(evt);
            }
        });

        Cancelar1.setText("CANCELAR");
        Cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar1ActionPerformed(evt);
            }
        });

        CrearAsistente.setFont(new java.awt.Font("Segoe UI", 0, 33)); // NOI18N
        CrearAsistente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CrearAsistente.setText("Crear Asistente");

        Nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Nombre.setText("NOMBRE:");
        Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Estado1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estado1.setText("ESTADO:");
        Estado1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Estados1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO", "SUSPENDIDO" }));

        Guardar1.setText("GUARDAR");
        Guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar1ActionPerformed(evt);
            }
        });

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(Estado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Estados1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 72, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Guardar1)
                                .addGap(150, 150, 150)
                                .addComponent(Limpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Cancelar1)
                                .addGap(164, 164, 164))))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(156, 156, 156)
                    .addComponent(CrearAsistente)
                    .addContainerGap(68, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Estados1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Limpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CrearAsistente)
                    .addGap(415, 415, 415)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar1ActionPerformed
        if (crearAsistenteUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            Asistente asistente = crearAsistenteDesdeFormulario();
            Asistente creado = crearAsistenteUseCase.ejecutar(asistente);

            JOptionPane.showMessageDialog(
                    this,
                    "Asistente creado con ID: " + creado.getId(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limpiarFormulario();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al crear el asistente: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }//GEN-LAST:event_Guardar1ActionPerformed

    private void Limpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpiar1ActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_Limpiar1ActionPerformed

    private void Cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar1ActionPerformed
        limpiarFormulario();
        java.awt.Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }

        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);

    }//GEN-LAST:event_Cancelar1ActionPerformed

    private Asistente crearAsistenteDesdeFormulario() {
        String nombre = CampoNombre.getText();
        String apellido = CampoApellido2.getText();
        String telefono = CampoTelefono.getText();
        Object seleccionado = Estados1.getSelectedItem();

        if (seleccionado == null) {
            throw new IllegalArgumentException("Debe seleccionar un estado para el asistente.");
        }

        String nombreMayus = nombre != null ? nombre.trim().toUpperCase() : "";
        String apellidoMayus = apellido != null ? apellido.trim().toUpperCase() : "";
        String telefonoLimpio = telefono != null ? telefono.trim() : "";

        if (nombreMayus.isBlank()) {
            throw new IllegalArgumentException("El nombre del asistente es obligatorio.");
        }
        if (apellidoMayus.isBlank()) {
            throw new IllegalArgumentException("El apellido del asistente es obligatorio.");
        }
        if (telefonoLimpio.isBlank()) {
            throw new IllegalArgumentException("El teléfono del asistente es obligatorio.");
        }
        if (!telefonoLimpio.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono solo debe contener números.");
        }

        EstadoTrabajador estado = EstadoTrabajador.valueOf(seleccionado.toString().toUpperCase());

        return new Asistente(
                nombreMayus,
                apellidoMayus,
                telefonoLimpio,
                estado
        );
    }

    private void limpiarFormulario() {
        CampoNombre.setText("");
        CampoApellido2.setText("");
        CampoTelefono.setText("");
        if (Estados1.getItemCount() > 0) {
            Estados1.setSelectedIndex(0);
        }
        CampoNombre.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Apellido;
    private javax.swing.JTextField CampoApellido2;
    private javax.swing.JTextField CampoNombre;
    private javax.swing.JTextField CampoTelefono;
    private javax.swing.JButton Cancelar1;
    private javax.swing.JLabel CrearAsistente;
    private javax.swing.JLabel Estado1;
    private javax.swing.JComboBox<String> Estados1;
    private javax.swing.JButton Guardar1;
    private javax.swing.JLabel Icono;
    private javax.swing.JButton Limpiar1;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Telefono;
    // End of variables declaration//GEN-END:variables
}
