package com.neider.rutaescolar.personalruta.infraestructure.gui.Conductor;

import com.neider.rutaescolar.personalruta.application.ListarConductoresUseCase;
import com.neider.rutaescolar.personalruta.application.BuscarConductorPorIdUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListarConductores extends javax.swing.JPanel {


    private final ListarConductoresUseCase listarConductoresUseCase;
    private final BuscarConductorPorIdUseCase buscarConductorPorIdUseCase;

    public ListarConductores(ListarConductoresUseCase listarConductoresUseCase,
                             BuscarConductorPorIdUseCase buscarConductorPorIdUseCase) {
        this.listarConductoresUseCase = listarConductoresUseCase;
        this.buscarConductorPorIdUseCase = buscarConductorPorIdUseCase;
        initComponents();
        configurarEventos();
    }

    private void configurarEventos() {
        Aceptar.addActionListener(e -> onAceptar());
        Limpiar.addActionListener(e -> onLimpiar());
        Cancelar.addActionListener(e -> onCancelar());
    }

    private void onAceptar() {
        if (listarConductoresUseCase == null || buscarConductorPorIdUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String textoId = CampoPlaca.getText() != null ? CampoPlaca.getText().trim() : "";
        DefaultTableModel modelo = (DefaultTableModel) ListaConductores1.getModel();
        modelo.setRowCount(0);

        try {
            if (textoId.isBlank()) {
                List<Conductor> conductores = listarConductoresUseCase.ejecutar();

                if (conductores.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No hay conductores registrados.",
                            "Sin datos",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                cargarTabla(conductores);

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
                var resultado = buscarConductorPorIdUseCase.ejecutar(id);

                if (resultado.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No se encontró un conductor con ID: " + id,
                            "Sin resultados",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                Conductor c = resultado.get();
                modelo.addRow(new Object[]{
                        c.getId(),
                        c.getNombre(),
                        c.getApellido(),
                        c.getNroLicencia(),
                        c.getCategoriaLicencia(),
                        c.getTelefono(),
                        c.getEstado() != null ? c.getEstado().name() : ""
                });
            }

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al listar los conductores: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cargarTabla(List<Conductor> conductores) {
        DefaultTableModel modelo = (DefaultTableModel) ListaConductores1.getModel();
        modelo.setRowCount(0);

        for (Conductor c : conductores) {
            modelo.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getApellido(),
                    c.getNroLicencia(),
                    c.getCategoriaLicencia(),
                    c.getTelefono(),
                    c.getEstado() != null ? c.getEstado().name() : ""
            });
        }
    }

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) ListaConductores1.getModel();
        modelo.setRowCount(0);
    }

    private void onLimpiar() {
        CampoPlaca.setText("");
        limpiarTabla();
        CampoPlaca.requestFocus();
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
        ListarConductor = new javax.swing.JLabel();
        Id = new javax.swing.JLabel();
        CampoPlaca = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        Icono = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaConductores1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Limpiar.setText("LIMPIAR");

        Cancelar.setText("CANCELAR");

        ListarConductor.setFont(new java.awt.Font("Segoe UI", 0, 29)); // NOI18N
        ListarConductor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ListarConductor.setText("Listar Conductor por id:");

        Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Id.setText("ID:");
        Id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Aceptar.setText("ACEPTAR");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Checklist-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        ListaConductores1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        ListaConductores1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "No. LICENCIA", "CATEGORIA LICENCIA", "TELEFONO", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ListaConductores1.setShowGrid(true);
        jScrollPane1.setViewportView(ListaConductores1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                                    .addComponent(CampoPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListarConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Aceptar)
                        .addGap(185, 185, 185))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ListarConductor)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JTextField CampoPlaca;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Icono;
    private javax.swing.JLabel Id;
    private javax.swing.JButton Limpiar;
    private javax.swing.JTable ListaConductores1;
    private javax.swing.JLabel ListarConductor;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

