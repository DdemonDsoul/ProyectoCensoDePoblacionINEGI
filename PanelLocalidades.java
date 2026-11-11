package vista;

import modelo.entidades.Localidad;
import modelo.entidades.Municipio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelLocalidades extends JPanel {

    private JComboBox<Municipio> cbMunicipio;
    private JComboBox<String> cbAmbito;

    private JTextField txtClave;
    private JTextField txtNombre;

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEliminar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelLocalidades() {

        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // ==========================
        // FORMULARIO
        // ==========================

        JPanel formulario =
                new JPanel(
                        new GridLayout(4,2,10,10));

        formulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Localidades"));

        cbMunicipio = new JComboBox<>();

        txtClave = new JTextField();

        txtNombre = new JTextField();

        cbAmbito = new JComboBox<>();

        cbAmbito.addItem("URBANO");
        cbAmbito.addItem("RURAL");

        formulario.add(
                new JLabel("Municipio"));

        formulario.add(cbMunicipio);

        formulario.add(
                new JLabel("Clave Localidad"));

        formulario.add(txtClave);

        formulario.add(
                new JLabel("Nombre"));

        formulario.add(txtNombre);

        formulario.add(
                new JLabel("�mbito"));

        formulario.add(cbAmbito);

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

        modelo =
                new DefaultTableModel(
                        new String[]{
                                "ID",
                                "Municipio",
                                "Clave",
                                "Nombre",
                                "�mbito"
                        }, 0){

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column){

                        return false;
                    }
                };

        tabla = new JTable(modelo);

        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        // ==========================
        // AGREGAR COMPONENTES
        // ==========================

        add(formulario,
                BorderLayout.NORTH);

        add(new JScrollPane(tabla),
                BorderLayout.CENTER);

        add(botones,
                BorderLayout.SOUTH);
    }

    // ==========================
    // GETTERS BOTONES
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

    // ==========================
    // TABLA
    // ==========================

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public int getFilaSeleccionada() {

        return tabla.getSelectedRow();
    }

    // ==========================
    // CAMPOS
    // ==========================

    public Municipio getMunicipio() {

        return (Municipio)
                cbMunicipio.getSelectedItem();
    }

    public String getClave() {

        return txtClave.getText().trim();
    }

    public String getNombre() {

        return txtNombre.getText().trim();
    }

    public String getAmbito() {

        return (String)
                cbAmbito.getSelectedItem();
    }

    // ==========================
    // CARGA DE COMBOBOX
    // ==========================

    public void setMunicipios(
            List<Municipio> municipios) {

        cbMunicipio.removeAllItems();

        for(Municipio m : municipios){

            cbMunicipio.addItem(m);
        }
    }

    // ==========================
    // CARGAR DATOS
    // ==========================

    public void setDatos(
            Localidad localidad){

        cbMunicipio.setSelectedItem(
                localidad.getMunicipio());

        txtClave.setText(
                localidad.getClaveLocalidad());

        txtNombre.setText(
                localidad.getNombre());

        cbAmbito.setSelectedItem(
                localidad.getAmbito());
    }

    // ==========================
    // LIMPIAR
    // ==========================

    public void limpiar() {

        txtClave.setText("");
        txtNombre.setText("");

        cbMunicipio.setSelectedIndex(-1);

        cbAmbito.setSelectedIndex(0);

        tabla.clearSelection();

        txtClave.requestFocus();
    }

    public void limpiarTabla() {

        modelo.setRowCount(0);
    }

    // ==========================
    // MENSAJES
    // ==========================

    public void error(
            String mensaje){

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
