package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelMunicipios extends JPanel {

    private JTextField txtClave;
    private JTextField txtNombre;

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEliminar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelMunicipios() {

        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(
                new GridLayout(2,2,10,10));

        formulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Municipio"));

        txtClave = new JTextField();
        txtNombre = new JTextField();

        formulario.add(
                new JLabel("Clave Municipio"));

        formulario.add(txtClave);

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
                        "Clave",
                        "Nombre"
                },0){

            @Override
            public boolean isCellEditable(
                    int row,
                    int column){

                return false;
            }
        };

        tabla = new JTable(modelo);

        add(formulario,BorderLayout.NORTH);

        add(new JScrollPane(tabla),
                BorderLayout.CENTER);

        add(botones,BorderLayout.SOUTH);
    }

    // =====================
    // GETTERS
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

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public String getClave() {
        return txtClave.getText().trim();
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    // =====================
    // UTILIDADES
    // =====================

    public int getFilaSeleccionada() {

        return tabla.getSelectedRow();
    }

    public void setDatos(
            String clave,
            String nombre){

        txtClave.setText(clave);

        txtNombre.setText(nombre);
    }

    public void limpiar(){

        txtClave.setText("");
        txtNombre.setText("");

        tabla.clearSelection();
    }

    public void limpiarTabla(){

        modelo.setRowCount(0);
    }

    public void error(String mensaje){

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmar(
            String mensaje){

        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmaci�n",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION;
    }
}
