/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.neider.rutaescolar.personalruta.infrastructure.gui.Asistente;

import com.neider.rutaescolar.personalruta.application.BuscarAsistentePorIdUseCase;
import com.neider.rutaescolar.personalruta.application.ListarAsistentesUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.infrastructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ListarAsistente extends javax.swing.JPanel {

   private final ListarAsistentesUseCase listarAsistentesUseCase;
    private final BuscarAsistentePorIdUseCase buscarAsistentePorIdUseCase;

 
    public ListarAsistente() {
        this.listarAsistentesUseCase = null;
        this.buscarAsistentePorIdUseCase = null;
        initComponents();
        configurarTabla();
        configurarEventos();
    }

  
    public ListarAsistente(ListarAsistentesUseCase listarAsistentesUseCase,
                           BuscarAsistentePorIdUseCase buscarAsistentePorIdUseCase) {
        this.listarAsistentesUseCase = listarAsistentesUseCase;
        this.buscarAsistentePorIdUseCase = buscarAsistentePorIdUseCase;
        initComponents();
        configurarTabla();
        configurarEventos();
    }


    private void configurarTabla() {
        DefaultTableModel model = (DefaultTableModel) ListaAsistentes.getModel();
        model.setRowCount(0);
    }

    private void configurarEventos() {
        Aceptar.addActionListener(e -> onListar());
        Limpiar.addActionListener(e -> onLimpiar());
        Cancelar.addActionListener(e -> onCancelar());
    }


    private void onListar() {
        if (listarAsistentesUseCase == null || buscarAsistentePorIdUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String textoId = CampoId.getText().trim();
        DefaultTableModel model = (DefaultTableModel) ListaAsistentes.getModel();
        model.setRowCount(0); 

        try {
            if (textoId.isBlank()) {
                var asistentes = listarAsistentesUseCase.ejecutar();
                if (asistentes.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No hay asistentes registrados.",
                            "Sin datos",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                for (Asistente a : asistentes) {
                    model.addRow(new Object[]{
                            a.getId(),
                            a.getNombre(),
                            a.getApellido(),
                            a.getTelefono(),
                            a.getEstado() != null ? a.getEstado().name() : ""
                    });
                }
            } else {
                if (!textoId.matches("\\d+")) {
                    JOptionPane.showMessageDialog(
                            this,
                            "El ID solo debe contener números.",
                            "Formato inválido",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                Integer id = Integer.valueOf(textoId);
                var resultado = buscarAsistentePorIdUseCase.ejecutar(id);

                if (resultado.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No se encontró un asistente con ID: " + id,
                            "Sin resultados",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                Asistente a = resultado.get();
                model.addRow(new Object[]{
                        a.getId(),
                        a.getNombre(),
                        a.getApellido(),
                        a.getTelefono(),
                        a.getEstado() != null ? a.getEstado().name() : ""
                });
            }

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al listar asistentes: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void onLimpiar() {
        CampoId.setText("");
        DefaultTableModel model = (DefaultTableModel) ListaAsistentes.getModel();
        model.setRowCount(0);
        CampoId.requestFocus();
    }

    private void onCancelar() {
        onLimpiar();

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

        Limpiar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        ListarAsistente = new javax.swing.JLabel();
        Id = new javax.swing.JLabel();
        CampoId = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        Icono = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaAsistentes = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Limpiar.setText("LIMPIAR");

        Cancelar.setText("CANCELAR");

        ListarAsistente.setFont(new java.awt.Font("Segoe UI", 0, 29)); // NOI18N
        ListarAsistente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ListarAsistente.setText("Listar Asistente por id:");

        Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Id.setText("ID:");
        Id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Aceptar.setText("ACEPTAR");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/neider/rutaescolar/personalruta/infrastructure/gui/icons/Checklist-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        ListaAsistentes.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        ListaAsistentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "TELEFONO", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ListaAsistentes.setShowGrid(true);
        jScrollPane1.setViewportView(ListaAsistentes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Aceptar)
                        .addGap(141, 141, 141))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Cancelar))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListarAsistente, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ListarAsistente)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JTextField CampoId;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Icono;
    private javax.swing.JLabel Id;
    private javax.swing.JButton Limpiar;
    private javax.swing.JTable ListaAsistentes;
    private javax.swing.JLabel ListarAsistente;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

