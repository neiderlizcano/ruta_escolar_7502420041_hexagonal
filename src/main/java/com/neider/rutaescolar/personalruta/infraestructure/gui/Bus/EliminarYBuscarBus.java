package com.neider.rutaescolar.personalruta.infraestructure.gui.Bus;

import com.neider.rutaescolar.personalruta.application.BuscarBusPorIdUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarBusUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Bus;
import com.neider.rutaescolar.personalruta.application.ActualizarBusUseCase;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.Optional;

public class EliminarYBuscarBus extends javax.swing.JPanel {

    private final BuscarBusPorIdUseCase buscarBusPorIdUseCase;
    private final EliminarBusUseCase eliminarBusUseCase;
    private final ActualizarBusUseCase ActualizarBusUseCase;

    private Bus busActual;

    public EliminarYBuscarBus(BuscarBusPorIdUseCase buscarBusPorIdUseCase,
            EliminarBusUseCase eliminarBusUseCase,
            ActualizarBusUseCase actualizarBusUseCase) {
        this.buscarBusPorIdUseCase = buscarBusPorIdUseCase;
        this.eliminarBusUseCase = eliminarBusUseCase;
        this.ActualizarBusUseCase = actualizarBusUseCase;
        initComponents();
        configurarEventos();
    }

    private void configurarEventos() {
        Buscar.addActionListener(e -> onBuscar());
        Eliminar.addActionListener(e -> onEliminar());
        Limpiar.addActionListener(e -> limpiarFormulario());
        Cancelar.addActionListener(e -> onCancelar());
    }

    private void onBuscar() {
        String textoId = CampoId.getText() != null ? CampoId.getText().trim() : "";

        if (textoId.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un ID de bus.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int id;
        try {
            id = Integer.parseInt(textoId);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número entero válido.",
                    "Formato incorrecto",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (id <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser mayor que cero.",
                    "Valor inválido",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            Optional<Bus> resultado = buscarBusPorIdUseCase.ejecutar(id);

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró ningún bus con ID " + id + ".",
                        "Sin resultados",
                        JOptionPane.INFORMATION_MESSAGE
                );
                limpiarTabla();
                busActual = null;
                return;
            }

            Bus encontrado = resultado.get();
            busActual = encontrado;
            mostrarBusEnTabla(encontrado);

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al buscar el bus: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void onEliminar() {
        if (busActual == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Primero debe buscar un bus antes de eliminar.",
                    "Sin bus seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar el bus con ID " + busActual.getId()
                + " y placa " + busActual.getPlaca() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            eliminarBusUseCase.ejecutar(busActual.getId());

            JOptionPane.showMessageDialog(
                    this,
                    "Bus eliminado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limpiarFormulario();

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al eliminar el bus: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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
    
    private void onActualizar() {
    if (busActual == null) {
        JOptionPane.showMessageDialog(
                this,
                "Primero debe buscar un bus por ID para poder actualizarlo.",
                "Sin bus seleccionado",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

      ActualizarBus panelActualizar = new ActualizarBus(ActualizarBusUseCase);
    panelActualizar.cargarBus(busActual);

    java.awt.Window window = SwingUtilities.getWindowAncestor(this);
    if (window != null) {
        window.dispose();
    }

    javax.swing.JFrame frame = new javax.swing.JFrame("Actualizar Bus");
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    frame.setContentPane(panelActualizar);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}


    private void mostrarBusEnTabla(Bus bus) {
        DefaultTableModel modelo = (DefaultTableModel) ListaBuses.getModel();
        modelo.setRowCount(0);
        modelo.addRow(new Object[]{
            bus.getId(),
            bus.getPlaca(),
            bus.getCapacidad(),
            bus.getEstado() != null ? bus.getEstado().name() : ""
        });
    }

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) ListaBuses.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarFormulario() {
        CampoId.setText("");
        limpiarTabla();
        busActual = null;
        CampoId.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Limpiar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        BuscarBus = new javax.swing.JLabel();
        Id = new javax.swing.JLabel();
        CampoId = new javax.swing.JTextField();
        Eliminar = new javax.swing.JButton();
        Icono = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaBuses = new javax.swing.JTable();
        Buscar = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Limpiar.setText("LIMPIAR");

        Cancelar.setText("CANCELAR");

        BuscarBus.setFont(new java.awt.Font("Segoe UI", 0, 29)); // NOI18N
        BuscarBus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BuscarBus.setText("Buscar bus por id:");

        Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Id.setText("ID:");
        Id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Eliminar.setText("ELIMINAR");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        ListaBuses.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        ListaBuses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "PLACA", "CAPACIDAD", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ListaBuses.setShowGrid(true);
        jScrollPane1.setViewportView(ListaBuses);

        Buscar.setText("BUSCAR");

        Actualizar.setText("ACTUALIZAR");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(BuscarBus, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Cancelar))))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Eliminar)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Actualizar)
                                        .addGap(18, 18, 18)
                                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26))))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BuscarBus)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        onActualizar();
    }//GEN-LAST:event_ActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Buscar;
    private javax.swing.JLabel BuscarBus;
    private javax.swing.JTextField CampoId;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JLabel Icono;
    private javax.swing.JLabel Id;
    private javax.swing.JButton Limpiar;
    private javax.swing.JTable ListaBuses;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
