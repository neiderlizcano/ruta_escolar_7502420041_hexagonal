package com.neider.rutaescolar.personalruta.infraestructure.gui.Conductor;

import com.neider.rutaescolar.personalruta.application.BuscarConductorPorIdUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarConductorUseCase;
import com.neider.rutaescolar.personalruta.application.ActualizarConductorUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Conductor;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.Optional;

public class EliminarYBuscarConductor extends javax.swing.JPanel {

    private final BuscarConductorPorIdUseCase buscarConductorPorIdUseCase;
    private final EliminarConductorUseCase eliminarConductorUseCase;
    private final ActualizarConductorUseCase actualizarConductorUseCase;

    private Conductor conductorActual;

    public EliminarYBuscarConductor(BuscarConductorPorIdUseCase buscarConductorPorIdUseCase,
                                    EliminarConductorUseCase eliminarConductorUseCase,
                                    ActualizarConductorUseCase actualizarConductorUseCase) {
        this.buscarConductorPorIdUseCase = buscarConductorPorIdUseCase;
        this.eliminarConductorUseCase = eliminarConductorUseCase;
        this.actualizarConductorUseCase = actualizarConductorUseCase;
        initComponents();
        configurarEventos();
    }

    private void configurarEventos() {
        Buscar.addActionListener(e -> onBuscar());
        Eliminar.addActionListener(e -> onEliminar());
        Limpiar1.addActionListener(e -> limpiarFormulario());
        Cancelar.addActionListener(e -> onCancelar());
        Actualizar.addActionListener(e -> onActualizar());
    }

    private void onBuscar() {
        if (buscarConductorPorIdUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String textoId = CampoId.getText() != null ? CampoId.getText().trim() : "";

        if (textoId.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un ID de conductor.",
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
            Optional<Conductor> resultado = buscarConductorPorIdUseCase.ejecutar(id);

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró ningún conductor con ID " + id + ".",
                        "Sin resultados",
                        JOptionPane.INFORMATION_MESSAGE
                );
                limpiarTabla();
                conductorActual = null;
                return;
            }

            Conductor encontrado = resultado.get();
            conductorActual = encontrado;
            mostrarConductorEnTabla(encontrado);

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al buscar el conductor: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void onEliminar() {
        if (eliminarConductorUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (conductorActual == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Primero debe buscar un conductor antes de eliminar.",
                    "Sin conductor seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar el conductor con ID " + conductorActual.getId()
                        + " y nombre " + conductorActual.getNombre() + " " + conductorActual.getApellido() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            eliminarConductorUseCase.ejecutar(conductorActual.getId());

            JOptionPane.showMessageDialog(
                    this,
                    "Conductor eliminado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limpiarFormulario();

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al eliminar el conductor: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void onActualizar() {
        if (actualizarConductorUseCase == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Casos de uso no inicializados (solo vista de diseño).",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (conductorActual == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Primero debe buscar un conductor por ID para poder actualizarlo.",
                    "Sin conductor seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        ActualizarConductor panelActualizar =
                new ActualizarConductor(actualizarConductorUseCase);
        panelActualizar.cargarConductor(conductorActual);

        java.awt.Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }

        javax.swing.JFrame frame = new javax.swing.JFrame("Actualizar Conductor");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(panelActualizar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

    private void mostrarConductorEnTabla(Conductor c) {
        DefaultTableModel modelo = (DefaultTableModel) ListaConductores.getModel();
        modelo.setRowCount(0);
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

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) ListaConductores.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarFormulario() {
        CampoId.setText("");
        limpiarTabla();
        conductorActual = null;
        CampoId.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Actualizar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        BuscarConductor = new javax.swing.JLabel();
        Id = new javax.swing.JLabel();
        CampoId = new javax.swing.JTextField();
        Eliminar = new javax.swing.JButton();
        Icono = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaConductores = new javax.swing.JTable();
        Buscar = new javax.swing.JButton();
        Limpiar1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Actualizar.setText("ACTUALIZAR");

        Cancelar.setText("CANCELAR");

        BuscarConductor.setFont(new java.awt.Font("Segoe UI", 0, 29)); // NOI18N
        BuscarConductor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BuscarConductor.setText("Buscar Conductor por id:");

        Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Id.setText("ID:");
        Id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Eliminar.setText("ELIMINAR");

        Icono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        ListaConductores.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        ListaConductores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
        ListaConductores.setShowGrid(true);
        jScrollPane1.setViewportView(ListaConductores);

        Buscar.setText("BUSCAR");

        Limpiar1.setText("LIMPIAR");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Limpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Eliminar)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscarConductor)
                        .addGap(21, 21, 21))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BuscarConductor)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Limpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Buscar;
    private javax.swing.JLabel BuscarConductor;
    private javax.swing.JTextField CampoId;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JLabel Icono;
    private javax.swing.JLabel Id;
    private javax.swing.JButton Limpiar1;
    private javax.swing.JTable ListaConductores;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

