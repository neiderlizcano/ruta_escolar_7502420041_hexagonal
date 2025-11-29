package com.neider.rutaescolar.personalruta.infraestructure.gui.Conductor;

import com.neider.rutaescolar.personalruta.application.CrearConductorUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.domain.model.EstadoTrabajador;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CrearConductor extends javax.swing.JPanel {

    private final CrearConductorUseCase crearConductorUseCase;

    public CrearConductor(CrearConductorUseCase crearConductorUseCase) {
        this.crearConductorUseCase = crearConductorUseCase;
        initComponents();
        inicializarEstados();
        configurarEventos();
    }

    private void inicializarEstados() {
        Estados1.removeAllItems();
        for (EstadoTrabajador estado : EstadoTrabajador.values()) {
            Estados1.addItem(estado.name());
        }
    }

    private void configurarEventos() {
        Guardar1.addActionListener(e -> onGuardar());
        Limpiar1.addActionListener(e -> limpiarFormulario());
        Cancelar1.addActionListener(e -> onCancelar());
    }

    private void onGuardar() {
        if (crearConductorUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            Conductor conductor = crearConductorDesdeFormulario();
            Conductor creado = crearConductorUseCase.ejecutar(conductor);

            JOptionPane.showMessageDialog(
                    this,
                    "Conductor creado con ID: " + creado.getId(),
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
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al crear el conductor: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private Conductor crearConductorDesdeFormulario() {
        String nombre = CampoNombre.getText();
        String apellido = CampoApellido2.getText();
        String nroLicencia = CampoNumeroLicencia.getText();
        String categoriaLic = CampoCategoriaLicencia1.getText();
        String telefono = CampoTelefono.getText();

        Object seleccionado = Estados1.getSelectedItem();
        if (seleccionado == null) {
            throw new IllegalArgumentException("Debes seleccionar un estado para el conductor.");
        }

        EstadoTrabajador estado
                = EstadoTrabajador.valueOf(seleccionado.toString().toUpperCase());

        return new Conductor(
                nombre,
                apellido,
                nroLicencia,
                categoriaLic,
                telefono,
                estado
        );
    }

    private void limpiarFormulario() {
        CampoNombre.setText("");
        CampoApellido2.setText("");
        CampoNumeroLicencia.setText("");
        CampoCategoriaLicencia1.setText("");
        CampoTelefono.setText("");

        if (Estados1.getItemCount() > 0) {
            Estados1.setSelectedIndex(0);
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

        Icono = new javax.swing.JLabel();
        Limpiar1 = new javax.swing.JButton();
        Cancelar1 = new javax.swing.JButton();
        CrearConductor = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        Estado1 = new javax.swing.JLabel();
        CampoNombre = new javax.swing.JTextField();
        Estados1 = new javax.swing.JComboBox<>();
        Guardar1 = new javax.swing.JButton();
        NumeroLicencia = new javax.swing.JLabel();
        Apellido = new javax.swing.JLabel();
        CategoriaLicencia = new javax.swing.JLabel();
        Telefono = new javax.swing.JLabel();
        CampoNumeroLicencia = new javax.swing.JTextField();
        CampoTelefono = new javax.swing.JTextField();
        CampoApellido2 = new javax.swing.JTextField();
        CampoCategoriaLicencia1 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Icono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create-new-page-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        Limpiar1.setText("LIMPIAR");

        Cancelar1.setText("CANCELAR");

        CrearConductor.setFont(new java.awt.Font("Segoe UI", 0, 33)); // NOI18N
        CrearConductor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CrearConductor.setText("Crear Conductor");

        Nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Nombre.setText("NOMBRE:");
        Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Estado1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estado1.setText("ESTADO:");
        Estado1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Estados1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO", "SUSPENDIDO" }));

        Guardar1.setText("GUARDAR");

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
                .addGap(103, 103, 103)
                .addComponent(Guardar1)
                .addGap(201, 201, 201)
                .addComponent(Limpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CrearConductor)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CategoriaLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(NumeroLicencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Estados1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CampoNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoApellido2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoNumeroLicencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoCategoriaLicencia1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(108, 122, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cancelar1)
                .addGap(205, 205, 205))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CrearConductor)
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
                            .addComponent(Estados1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(NumeroLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CampoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Limpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Guardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Apellido;
    private javax.swing.JTextField CampoApellido2;
    private javax.swing.JTextField CampoCategoriaLicencia1;
    private javax.swing.JTextField CampoNombre;
    private javax.swing.JTextField CampoNumeroLicencia;
    private javax.swing.JTextField CampoTelefono;
    private javax.swing.JButton Cancelar1;
    private javax.swing.JLabel CategoriaLicencia;
    private javax.swing.JLabel CrearConductor;
    private javax.swing.JLabel Estado1;
    private javax.swing.JComboBox<String> Estados1;
    private javax.swing.JButton Guardar1;
    private javax.swing.JLabel Icono;
    private javax.swing.JButton Limpiar1;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel NumeroLicencia;
    private javax.swing.JLabel Telefono;
    // End of variables declaration//GEN-END:variables
}


