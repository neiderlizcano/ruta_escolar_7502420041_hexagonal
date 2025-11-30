package com.neider.rutaescolar.personalruta.infraestructure.gui.reportes;

import com.neider.rutaescolar.personalruta.application.ListarBusesUseCase;
import com.neider.rutaescolar.personalruta.application.ListarAsistentesUseCase;
import com.neider.rutaescolar.personalruta.application.ListarConductoresUseCase;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Listas extends javax.swing.JPanel {

    private final ListarBusesUseCase listarBusesUseCase;
    private final ListarAsistentesUseCase listarAsistentesUseCase;
    private final ListarConductoresUseCase listarConductoresUseCase;

    // Constructor para diseñador (NetBeans)
    public Listas() {
        this.listarBusesUseCase = null;
        this.listarAsistentesUseCase = null;
        this.listarConductoresUseCase = null;
        initComponents();
        configurarEventos();
    }

    // Constructor REAL
    public Listas(ListarBusesUseCase listarBusesUseCase,
                  ListarAsistentesUseCase listarAsistentesUseCase,
                  ListarConductoresUseCase listarConductoresUseCase) {

        this.listarBusesUseCase = Objects.requireNonNull(listarBusesUseCase);
        this.listarAsistentesUseCase = Objects.requireNonNull(listarAsistentesUseCase);
        this.listarConductoresUseCase = Objects.requireNonNull(listarConductoresUseCase);

        initComponents();
        configurarEventos();
    }

    private void configurarEventos() {
        Aceptar.addActionListener(e -> onAceptar());
        Cancelar.addActionListener(e -> onCancelar());
    }

    private void onAceptar() {
        int opcion = Listas.getSelectedIndex();

        switch (opcion) {
            case 0 -> mostrarReporteMinibuses();
            case 1 -> mostrarReporteConductores();
            case 2 -> mostrarReporteAsistentes();
            default -> JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una opción válida.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void mostrarReporteMinibuses() {
        if (listarBusesUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        MinibusesYEstadoActual panel = new MinibusesYEstadoActual(listarBusesUseCase);

        JFrame frame = new JFrame("Minibuses y estado actual");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    private void mostrarReporteConductores() {
        if (listarConductoresUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        ConductoresYEstadoActual panel = new ConductoresYEstadoActual(listarConductoresUseCase);

        JFrame frame = new JFrame("Conductores y estado actual");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    private void mostrarReporteAsistentes() {
        if (listarAsistentesUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        AsistentesYEstadoActual panel = new AsistentesYEstadoActual(listarAsistentesUseCase);

        JFrame frame = new JFrame("Asistentes y estado actual");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    private void onCancelar() {
        Window window = SwingUtilities.getWindowAncestor(this);
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

        Cancelar = new javax.swing.JButton();
        ReporteListas = new javax.swing.JLabel();
        Aceptar = new javax.swing.JButton();
        Icono = new javax.swing.JLabel();
        Listas = new javax.swing.JComboBox<>();
        EscogerOpcion = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Cancelar.setText("CANCELAR");

        ReporteListas.setFont(new java.awt.Font("Segoe UI", 0, 29)); // NOI18N
        ReporteListas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ReporteListas.setText("REPORTE DE LISTAS");

        Aceptar.setText("ACEPTAR");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Checklist-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        Listas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Listar todo los minibuses y su estado actual", "Listar todos los conductores y su estado actual", "Listar todos los asistentes y su estado actual" }));

        EscogerOpcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EscogerOpcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EscogerOpcion.setText("Escoja una opción");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(ReporteListas, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(EscogerOpcion))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(Cancelar)
                                .addGap(97, 97, 97)
                                .addComponent(Aceptar)))
                        .addGap(0, 108, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(493, 493, 493)
                        .addComponent(Listas, 0, 1, Short.MAX_VALUE)))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ReporteListas)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Listas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EscogerOpcion))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel EscogerOpcion;
    private javax.swing.JLabel Icono;
    private javax.swing.JComboBox<String> Listas;
    private javax.swing.JLabel ReporteListas;
    // End of variables declaration//GEN-END:variables
}

