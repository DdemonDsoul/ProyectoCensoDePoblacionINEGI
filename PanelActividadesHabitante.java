package vista;

import modelo.entidades.ActividadEconomica;
import modelo.entidades.Habitante;
import modelo.entidades.HabitanteActividad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelActividadesHabitante extends JPanel {

    private JComboBox<Habitante> cbHabitante;
    private JComboBox<ActividadEconomica> cbActividad;

    private JTextField txtIngreso;

    private JButton btnBuscar;
    private JButton btnAgregar;
    private JButton btnQuitar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelActividadesHabitante() {
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(3, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Actividad Econ�mica del Habitante"));

        cbHabitante = new JComboBox<>();
        cbActividad = new JComboBox<>();
        txtIngreso = new JTextField();

        formulario.add(new JLabel("Habitante"));
        formulario.add(cbHabitante);

        formulario.add(new JLabel("Actividad"));
        formulario.add(cbActividad);

        formulario.add(new JLabel("Ingreso Mensual"));
        formulario.add(txtIngreso);

        JPanel botones = new JPanel();

        // El controlador busca getBtnBuscar(), getBtnAgregar(), getBtnQuitar()
        btnBuscar = new JButton("Buscar");
        btnAgregar = new JButton("Agregar");
        btnQuitar = new JButton("Quitar");

        botones.add(btnBuscar);
        botones.add(btnAgregar);
        botones.add(btnQuitar);

        modelo = new DefaultTableModel(
                new String[]{"ID", "Actividad", "Ingreso"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    // ==========================
    // BOTONES
    // ==========================
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnQuitar() { return btnQuitar; }

    // ==========================
    // TABLA
    // ==========================
    public JTable getTabla() { return tabla; }
    public DefaultTableModel getModelo() { return modelo; }
    public int getFilaSeleccionada() { return tabla.getSelectedRow(); }

    // ==========================
    // COMBOS
    // ==========================
    public void setHabitantes(List<Habitante> habitantes) {
        cbHabitante.removeAllItems();
        for (Habitante h : habitantes) {
            cbHabitante.addItem(h);
        }
    }

    public void setActividades(List<ActividadEconomica> actividades) {
        cbActividad.removeAllItems();
        for (ActividadEconomica a : actividades) {
            cbActividad.addItem(a);
        }
    }

    public Habitante getHabitante() {
        return (Habitante) cbHabitante.getSelectedItem();
    }
    
    // GETTER CORREGIDO PARA EL CONTROLADOR
    public JComboBox<Habitante> getCmbHabitante() {
        return cbHabitante;
    }

    public ActividadEconomica getActividad() {
        return (ActividadEconomica) cbActividad.getSelectedItem();
    }

    // ==========================
    // DATOS Y B�SQUEDA
    // ==========================
    public String getIngreso() {
        return txtIngreso.getText().trim();
    }

    public void limpiarIngreso() {
        txtIngreso.setText("");
    }
    
    public String getTextoBusqueda() {
        Habitante h = getHabitante();
        return h != null ? h.getNombre() : "";
    }

    // ==========================
    // LIMPIAR
    // ==========================
    public void limpiar() {
        cbHabitante.setSelectedIndex(-1);
        cbActividad.setSelectedIndex(-1);
        txtIngreso.setText("");
        tabla.clearSelection();
        txtIngreso.requestFocus();
    }

    public void limpiarTabla() {
        modelo.setRowCount(0);
    }

    // ==========================
    // MENSAJES
    // ==========================
    public void error(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(this, mensaje, "Confirmaci�n", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
