
package com.neider.rutaescolar.personalruta.infraestructure.gui.reportes;

import com.neider.rutaescolar.personalruta.application.ListarBusesUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Bus;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MinibusesYEstadoActual extends javax.swing.JPanel {

    private final ListarBusesUseCase listarBusesUseCase;

    public MinibusesYEstadoActual(ListarBusesUseCase listarBusesUseCase) {
        this.listarBusesUseCase = listarBusesUseCase;
        initComponents();
        cargarTabla();
    }

    private void cargarTabla() {
        if (listarBusesUseCase == null) {
            return;
        }

        List<Bus> buses = listarBusesUseCase.ejecutar();

        DefaultTableModel modelo = (DefaultTableModel) MinibusesYestadoActual.getModel();
        modelo.setRowCount(0);

        for (Bus b : buses) {
            modelo.addRow(new Object[]{
                    b.getId(),
                    b.getPlaca(),
                    b.getEstado() != null ? b.getEstado().name() : ""
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        MinibusesYestadoActual = new javax.swing.JTable();

        MinibusesYestadoActual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "PLACA", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        MinibusesYestadoActual.setShowGrid(true);
        jScrollPane1.setViewportView(MinibusesYestadoActual);
        MinibusesYestadoActual.getAccessibleContext().setAccessibleName("LISTA DE MINIBUSES Y ESTADO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MinibusesYestadoActual;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
