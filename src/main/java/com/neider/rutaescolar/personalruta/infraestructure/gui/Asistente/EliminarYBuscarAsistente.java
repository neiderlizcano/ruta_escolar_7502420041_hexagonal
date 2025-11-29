package com.neider.rutaescolar.personalruta.infraestructure.gui.Asistente;

import com.neider.rutaescolar.personalruta.application.BuscarAsistentePorIdUseCase;
import com.neider.rutaescolar.personalruta.application.EliminarAsistenteUseCase;
import com.neider.rutaescolar.personalruta.application.ActualizarAsistenteUseCase;
import com.neider.rutaescolar.personalruta.domain.model.Asistente;
import com.neider.rutaescolar.personalruta.infraestructure.gui.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class EliminarYBuscarAsistente extends javax.swing.JPanel {

    private final BuscarAsistentePorIdUseCase buscarAsistentePorIdUseCase;
    private final EliminarAsistenteUseCase eliminarAsistenteUseCase;
    private final ActualizarAsistenteUseCase actualizarAsistenteUseCase;

    private Asistente asistenteSeleccionado;


    public EliminarYBuscarAsistente(
            BuscarAsistentePorIdUseCase buscarAsistentePorIdUseCase,
            EliminarAsistenteUseCase eliminarAsistenteUseCase,
            ActualizarAsistenteUseCase actualizarAsistenteUseCase) {
        this.buscarAsistentePorIdUseCase = buscarAsistentePorIdUseCase;
        this.eliminarAsistenteUseCase = eliminarAsistenteUseCase;
        this.actualizarAsistenteUseCase = actualizarAsistenteUseCase;
        initComponents();
        configurarTabla();
        configurarEventos();
    }

    private void configurarTabla() {
        DefaultTableModel model = (DefaultTableModel) ListaAsistente.getModel();
        model.setRowCount(0);
    }

    private void configurarEventos() {
        Buscar.addActionListener(e -> onBuscar());
        Eliminar.addActionListener(e -> onEliminar());
        Limpiar.addActionListener(e -> onLimpiar());
        Cancelar.addActionListener(e -> onCancelar());
        Cancelar1.addActionListener(e -> onActualizar());
    }

    private void onBuscar() {
    String textoId = CampoId.getText().trim();
    if (textoId.isBlank()) {
        JOptionPane.showMessageDialog(
                this,
                "Debes ingresar un ID.",
                "Dato requerido",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

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

    DefaultTableModel model = (DefaultTableModel) ListaAsistente.getModel();
    model.setRowCount(0);

    if (resultado.isPresent()) {
        Asistente a = resultado.get();
        asistenteSeleccionado = a;

        model.addRow(new Object[]{
            a.getId(),
            a.getNombre(),
            a.getApellido(),
            a.getTelefono(),
            a.getEstado() != null ? a.getEstado().name() : ""
        });
    } else {
        asistenteSeleccionado = null;
        JOptionPane.showMessageDialog(
                this,
                "No se encontró un asistente con ID: " + id,
                "Sin resultados",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}


    private void onEliminar() {
    String textoId = CampoId.getText().trim();
    if (textoId.isBlank() || !textoId.matches("\\d+")) {
        JOptionPane.showMessageDialog(
                this,
                "Debes ingresar un ID numérico válido para eliminar.",
                "Dato inválido",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    Integer id = Integer.valueOf(textoId);

    var resultado = buscarAsistentePorIdUseCase.ejecutar(id);
    if (resultado.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "No existe un asistente con ID: " + id,
                "No encontrado",
                JOptionPane.INFORMATION_MESSAGE
        );
        return;
    }

    Asistente a = resultado.get();

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que deseas eliminar al asistente?\n"
            + "ID: " + a.getId() + "\n"
            + "Nombre: " + a.getNombre() + " " + a.getApellido(),
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        eliminarAsistenteUseCase.ejecutar(id);

        JOptionPane.showMessageDialog(
                this,
                "Asistente eliminado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        asistenteSeleccionado = null;
        onLimpiar();

    } catch (RuntimeException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Error al eliminar el asistente: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}


    private void onActualizar() {
    if (asistenteSeleccionado == null) {
        JOptionPane.showMessageDialog(
                this,
                "Primero busca un asistente por ID para poder actualizarlo.",
                "Sin asistente seleccionado",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    ActualizarAsistente panelActualizar
            = new ActualizarAsistente(actualizarAsistenteUseCase);
    panelActualizar.cargarAsistente(asistenteSeleccionado);

    java.awt.Window window = SwingUtilities.getWindowAncestor(this);
    if (window != null) {
        window.dispose();
    }

    javax.swing.JFrame frame = new javax.swing.JFrame("Actualizar Asistente");
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    frame.setContentPane(panelActualizar);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
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

    private void onLimpiar() {
        CampoId.setText("");
        asistenteSeleccionado = null;
        DefaultTableModel model = (DefaultTableModel) ListaAsistente.getModel();
        model.setRowCount(0);
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
        BuscarAsistente = new javax.swing.JLabel();
        Id = new javax.swing.JLabel();
        CampoId = new javax.swing.JTextField();
        Eliminar = new javax.swing.JButton();
        Icono = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaAsistente = new javax.swing.JTable();
        Buscar = new javax.swing.JButton();
        Cancelar1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        Limpiar.setText("LIMPIAR");

        Cancelar.setText("CANCELAR");

        BuscarAsistente.setFont(new java.awt.Font("Segoe UI", 0, 29)); // NOI18N
        BuscarAsistente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BuscarAsistente.setText("Buscar Asistente por id:");

        Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Id.setText("ID:");
        Id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Eliminar.setText("ELIMINAR");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search-icon.png"))); // NOI18N
        Icono.setMaximumSize(new java.awt.Dimension(653, 612));
        Icono.setMinimumSize(new java.awt.Dimension(653, 612));

        ListaAsistente.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        ListaAsistente.setModel(new javax.swing.table.DefaultTableModel(
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
        ListaAsistente.setShowGrid(true);
        jScrollPane1.setViewportView(ListaAsistente);

        Buscar.setText("BUSCAR");

        Cancelar1.setText("ACTUALIZAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(BuscarAsistente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cancelar)
                            .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Eliminar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Cancelar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(31, 31, 31)
                                .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BuscarAsistente)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Icono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JLabel BuscarAsistente;
    private javax.swing.JTextField CampoId;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Cancelar1;
    private javax.swing.JButton Eliminar;
    private javax.swing.JLabel Icono;
    private javax.swing.JLabel Id;
    private javax.swing.JButton Limpiar;
    private javax.swing.JTable ListaAsistente;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

