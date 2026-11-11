package vista;

import modelo.entidades.Localidad;
import modelo.entidades.TipoVivienda;
import modelo.entidades.Vivienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelViviendas extends JPanel {

    private JComboBox<Localidad> cbLocalidad;
    private JComboBox<TipoVivienda> cbTipo;

    private JTextField txtCalle;
    private JTextField txtNumero;
    private JTextField txtColonia;
    private JTextField txtHabitaciones;

    private JCheckBox chkAgua;
    private JCheckBox chkLuz;
    private JCheckBox chkDrenaje;
    private JCheckBox chkInternet;

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEliminar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelViviendas() {
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // ==========================
        // FORMULARIO
        // ==========================

        JPanel formulario =
                new JPanel(new GridLayout(8, 2, 10, 10));

        formulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Vivienda"));

        cbLocalidad = new JComboBox<>();
        cbTipo = new JComboBox<>();

        txtCalle = new JTextField();
        txtNumero = new JTextField();
        txtColonia = new JTextField();
        txtHabitaciones = new JTextField();

        chkAgua = new JCheckBox("Agua");
        chkLuz = new JCheckBox("Luz");
        chkDrenaje = new JCheckBox("Drenaje");
        chkInternet = new JCheckBox("Internet");

        formulario.add(new JLabel("Localidad"));
        formulario.add(cbLocalidad);

        formulario.add(new JLabel("Tipo Vivienda"));
        formulario.add(cbTipo);

        formulario.add(new JLabel("Calle"));
        formulario.add(txtCalle);

        formulario.add(new JLabel("N�mero"));
        formulario.add(txtNumero);

        formulario.add(new JLabel("Colonia"));
        formulario.add(txtColonia);

        formulario.add(new JLabel("Habitaciones"));
        formulario.add(txtHabitaciones);

        formulario.add(chkAgua);
        formulario.add(chkLuz);

        formulario.add(chkDrenaje);
        formulario.add(chkInternet);

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
                        "Localidad",
                        "Municipio",
                        "Tipo",
                        "Direcci�n",
                        "Habitaciones",
                        "Servicios"
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
        // COMPONENTES
        // ==========================

        add(formulario, BorderLayout.NORTH);

        add(new JScrollPane(tabla),
                BorderLayout.CENTER);

        add(botones, BorderLayout.SOUTH);
    }

    // ==================================
    // BOTONES
    // ==================================

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    // ==================================
    // TABLA
    // ==================================

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public int getFilaSeleccionada() {
        return tabla.getSelectedRow();
    }

    // ==================================
    // COMBOS
    // ==================================

    public Localidad getLocalidad() {
        return (Localidad) cbLocalidad.getSelectedItem();
    }

    public TipoVivienda getTipo() {
        return (TipoVivienda) cbTipo.getSelectedItem();
    }

    public void setLocalidades(
            List<Localidad> localidades) {

        cbLocalidad.removeAllItems();

        for (Localidad l : localidades) {
            cbLocalidad.addItem(l);
        }
    }

    public void setTipos(
            List<TipoVivienda> tipos) {

        cbTipo.removeAllItems();

        for (TipoVivienda t : tipos) {
            cbTipo.addItem(t);
        }
    }

    // ==================================
    // DATOS
    // ==================================

    public String getCalle() {
        return txtCalle.getText().trim();
    }

    public String getNumero() {
        return txtNumero.getText().trim();
    }

    public String getColonia() {
        return txtColonia.getText().trim();
    }

    public int getHabitaciones() {

        try {
            return Integer.parseInt(
                    txtHabitaciones.getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================================
    // SERVICIOS
    // ==================================

    public boolean isAgua() {
        return chkAgua.isSelected();
    }

    public boolean isLuz() {
        return chkLuz.isSelected();
    }

    public boolean isDrenaje() {
        return chkDrenaje.isSelected();
    }

    public boolean isInternet() {
        return chkInternet.isSelected();
    }

    // ==================================
    // CARGAR DATOS
    // ==================================

    public void setDatos(
            Vivienda vivienda) {

        cbLocalidad.setSelectedItem(
                vivienda.getLocalidad());

        cbTipo.setSelectedItem(
                vivienda.getTipoVivienda());

        txtCalle.setText(
                vivienda.getCalle());

        txtNumero.setText(
                vivienda.getNumero());

        txtColonia.setText(
                vivienda.getColonia());

        txtHabitaciones.setText(
                String.valueOf(
                        vivienda.getNumHabitaciones()));

        chkAgua.setSelected(
                vivienda.isAgua());

        chkLuz.setSelected(
                vivienda.isLuz());

        chkDrenaje.setSelected(
                vivienda.isDrenaje());

        chkInternet.setSelected(
                vivienda.isInternet());
    }

    // ==================================
    // LIMPIAR
    // ==================================

    public void limpiar() {

        txtCalle.setText("");
        txtNumero.setText("");
        txtColonia.setText("");
        txtHabitaciones.setText("");

        chkAgua.setSelected(false);
        chkLuz.setSelected(false);
        chkDrenaje.setSelected(false);
        chkInternet.setSelected(false);

        cbLocalidad.setSelectedIndex(-1);
        cbTipo.setSelectedIndex(-1);

        tabla.clearSelection();

        txtCalle.requestFocus();
    }

    public void limpiarTabla() {
        modelo.setRowCount(0);
    }

    // ==================================
    // MENSAJES
    // ==================================

    public void error(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
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
