package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelActividades extends JPanel {

    private JTextField txtNombre;

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEliminar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelActividades() {
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // ==========================
        // FORMULARIO
        // ==========================

        JPanel formulario = new JPanel(
                new GridLayout(1, 2, 10, 10));

        formulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Actividad Econ�mica"));

        txtNombre = new JTextField();

        formulario.add(new JLabel("Nombre"));
        formulario.add(txtNombre);

        // ==========================
        // BOTONES
        // ==========================

        JPanel botones = new JPanel();

        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");

        botones.add(btnNuevo);
        botones.add(btnGuardar);
        botones.add(btnEliminar);

        // ==========================
        // TABLA
        // ==========================

        modelo = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Actividad"
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

        // ==========================
        // AGREGAR COMPONENTES
        // ==========================

        add(formulario, BorderLayout.NORTH);

        add(new JScrollPane(tabla),
                BorderLayout.CENTER);

        add(botones, BorderLayout.SOUTH);
    }

    // ==========================
    // GETTERS
    // ==========================

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    // ==========================
    // SETTERS
    // ==========================

    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    // ==========================
    // M�TODOS AUXILIARES
    // ==========================

    public int getFilaSeleccionada() {

        return tabla.getSelectedRow();
    }

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

    public void info(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Informaci�n",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean confirmar(
            String mensaje) {

        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmaci�n",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION;
    }
}
