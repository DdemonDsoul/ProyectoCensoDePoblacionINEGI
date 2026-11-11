package vista;

import modelo.entidades.Habitante;
import modelo.entidades.HabitanteActividad;
import modelo.entidades.Vivienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelDetalleVivienda extends JPanel {

    private JComboBox<Vivienda> cmbVivienda;
    private JButton btnConsultar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblContador;

    public PanelDetalleVivienda() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        superior.setBorder(BorderFactory.createTitledBorder("Consulta por Vivienda"));

        cmbVivienda = new JComboBox<>();
        btnConsultar = new JButton("Consultar");
        lblContador = new JLabel("Habitantes: 0");

        superior.add(new JLabel("Vivienda"));
        superior.add(cmbVivienda);
        superior.add(btnConsultar);
        superior.add(Box.createHorizontalStrut(20));
        superior.add(lblContador);

        modelo = new DefaultTableModel(new String[]{"Habitante", "Actividad", "Ingreso"}, 0){
            @Override
            public boolean isCellEditable(int row, int column){ return false; }
        };

        tabla = new JTable(modelo);

        add(superior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public JComboBox<Vivienda> getCmbVivienda() { return cmbVivienda; }
    public JButton getBtnConsultar() { return btnConsultar; }
    public JTable getTabla() { return tabla; }
    public DefaultTableModel getModelo() { return modelo; }

    public Vivienda getVivienda() {
        return (Vivienda) cmbVivienda.getSelectedItem();
    }

    public void setViviendas(List<Vivienda> viviendas){
        // Evitar que se dispare el evento ActionListener del controlador multiples veces
        java.awt.event.ActionListener[] listeners = cmbVivienda.getActionListeners();
        for (java.awt.event.ActionListener l : listeners) cmbVivienda.removeActionListener(l);

        cmbVivienda.removeAllItems();
        for(Vivienda v : viviendas){ cmbVivienda.addItem(v); }

        for (java.awt.event.ActionListener l : listeners) cmbVivienda.addActionListener(l);
    }

    public void limpiarTabla(){ modelo.setRowCount(0); }

    public void error(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrar(List<?> habitantes, int total, List<?> actividades) {
        limpiarTabla();
        lblContador.setText("Habitantes: " + total);

        Map<Integer, String> nombres = new HashMap<>();
        for (Object o : habitantes) {
            if (o instanceof Habitante) {
                Habitante h = (Habitante) o;
                nombres.put(h.getIdHabitante(), h.getNombreCompleto());
            }
        }

        for (Object o : actividades) {
            if (o instanceof HabitanteActividad) {
                HabitanteActividad ha = (HabitanteActividad) o;
                String habitante = nombres.getOrDefault(ha.getIdHabitante(), "ID " + ha.getIdHabitante());
                String actividad = (ha.getActividad() != null) ? ha.getActividad().getNombre() : "";
                BigDecimal ingreso = ha.getIngresoMensual();
                modelo.addRow(new Object[]{habitante, actividad, ingreso});
            }
        }
    }
}