package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelTiposVivienda extends JPanel {

    private JTextField txtNombre;

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEliminar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelTiposVivienda() {

        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(
                new GridLayout(1, 2, 10, 10));

        formulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Tipo de Vivienda"));

        txtNombre = new JTextField();

        formulario.add(
                new JLabel("Nombre"));

        formulario.add(txtNombre);

        JPanel botones = new JPanel();

        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");

        botones.add(btnNuevo);
        botones.add(btnGuardar);
        botones.add(btnEliminar);

        modelo = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Tipo de Vivienda"
                }, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        tabla = new JTable(modelo);

        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        add(formulario,
                BorderLayout.NORTH);

        add(new JScrollPane(tabla),
                BorderLayout.CENTER);

        add(botones,
                BorderLayout.SOUTH);
    }

    // =====================
    // BOTONES
    // =====================

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    // =====================
    // TABLA
    // =====================

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public int getFilaSeleccionada() {
        return tabla.getSelectedRow();
    }

    // =====================
    // DATOS
    // =====================

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    // =====================
    // UTILIDADES
    // =====================

    public void limpiar() {

        txtNombre.setText("");

        tabla.clearSelection();

        txtNombre.requestFocus();
    }

    public void limpiarTabla() {

        modelo.setRowCount(0);
    }

    public void error(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmar(String mensaje) {

        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmaci�n",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION;
    }
}
