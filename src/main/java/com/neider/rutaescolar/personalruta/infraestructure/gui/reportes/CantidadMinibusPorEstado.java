package com.neider.rutaescolar.personalruta.infraestructure.gui.reportes;

import com.neider.rutaescolar.personalruta.application.ListarBusesUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CantidadMinibusPorEstado extends javax.swing.JPanel {

    private final ListarBusesUseCase listarBusesUseCase;

    public CantidadMinibusPorEstado() {
        this.listarBusesUseCase = null;
        initComponents();
        configurarEventos();
    }

    public CantidadMinibusPorEstado(ListarBusesUseCase listarBusesUseCase) {
        this.listarBusesUseCase = listarBusesUseCase;
        initComponents();
        configurarEventos();
        cargarDatos();
    }

    private void configurarEventos() {
        Salir.addActionListener(e -> onSalir());
    }

    private void onSalir() {
        java.awt.Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }

        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);
    }

    private void cargarDatos() {
        if (listarBusesUseCase == null) {
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) TablaEstado.getModel();
        modelo.setRowCount(0);

        try {
            List<Bus> buses = listarBusesUseCase.ejecutar();

            Map<String, Integer> conteos = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

            for (Bus b : buses) {
                String estado = b.getEstado() != null ? b.getEstado().name() : "(SIN ESTADO)";
                conteos.put(estado, conteos.getOrDefault(estado, 0) + 1);
            }

            int total = 0;
            for (Map.Entry<String, Integer> entry : conteos.entrySet()) {
                modelo.addRow(new Object[]{
                        entry.getKey(),
                        entry.getValue()
                });
                total += entry.getValue();
            }

            Total.setText(String.valueOf(total));

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar la cantidad de minibuses por estado: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bienvenida = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaEstado = new javax.swing.JTable();
        Salir = new javax.swing.JButton();

        Bienvenida.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Bienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenida.setText("MINIBUS POR ESTADO");

        Total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Total.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TOTAL:");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TablaEstado.setModel(new javax.swing.table.DefaultTableModel(
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
                "ESTADO", "CANTIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaEstado.setShowGrid(true);
        jScrollPane1.setViewportView(TablaEstado);

        Salir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Salir.setText("SALIR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(Bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                        .addComponent(Total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bienvenida;
    private javax.swing.JButton Salir;
    private javax.swing.JTable TablaEstado;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
