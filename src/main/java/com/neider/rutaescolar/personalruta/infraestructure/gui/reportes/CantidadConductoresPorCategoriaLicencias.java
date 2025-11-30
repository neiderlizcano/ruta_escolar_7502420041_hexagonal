package com.neider.rutaescolar.personalruta.infraestructure.gui.reportes;

import com.neider.rutaescolar.personalruta.application.ListarConductoresUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.Window;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CantidadConductoresPorCategoriaLicencias extends javax.swing.JPanel {

    private final ListarConductoresUseCase listarConductoresUseCase;

    public CantidadConductoresPorCategoriaLicencias() {
        this.listarConductoresUseCase = null;
        initComponents();
        configurarEventos();
    }

    public CantidadConductoresPorCategoriaLicencias(ListarConductoresUseCase listarConductoresUseCase) {
        this.listarConductoresUseCase = listarConductoresUseCase;
        initComponents();
        configurarEventos();
        cargarDatos();
    }

    private void configurarEventos() {
        Salir.addActionListener(e -> onSalir());
    }

    private void onSalir() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }

        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);
    }

    private void cargarDatos() {
        if (listarConductoresUseCase == null) {
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) TablaCategoria.getModel();
        modelo.setRowCount(0);

        try {
            List<Conductor> conductores = listarConductoresUseCase.ejecutar();

            Map<String, Integer> conteos = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

            for (Conductor c : conductores) {
                String estado = (c.getEstado() != null)
                        ? c.getEstado().name()
                        : "(SIN ESTADO)";
                conteos.put(estado, conteos.getOrDefault(estado, 0) + 1);
            }

            int total = 0;
            for (Map.Entry<String, Integer> entry : conteos.entrySet()) {
                modelo.addRow(new Object[] {
                    entry.getKey(),
                    entry.getValue()
                });
                total += entry.getValue();
            }

            Total.setText(String.valueOf(total));

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar la cantidad de conductores por estado: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCategoria = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        Bienvenida = new javax.swing.JLabel();
        Salir = new javax.swing.JButton();

        TablaCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "CATEGORIA", "CANTIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCategoria.setShowGrid(true);
        jScrollPane1.setViewportView(TablaCategoria);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TOTAL:");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Total.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Bienvenida.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Bienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenida.setText("CONDUCTORES POR CATEGORIA LICENCIA");

        Salir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Salir.setText("SALIR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(Bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bienvenida;
    private javax.swing.JButton Salir;
    private javax.swing.JTable TablaCategoria;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
