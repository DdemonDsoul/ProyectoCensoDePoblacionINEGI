package vista;

import modelo.estadisticas.EstrategiaEstadistica;
import modelo.estadisticas.ResultadoConteo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelDashboard extends JPanel {

    private JComboBox<EstrategiaEstadistica> cmbNivel;
    private JButton btnActualizar;
    private JTable tablaResumen;
    private JTable tablaGrafica;
    private DefaultTableModel modeloResumen;
    private DefaultTableModel modeloGrafica;
    private JLabel lblTituloGrafica;

    public PanelDashboard() {
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        superior.setBorder(BorderFactory.createTitledBorder("Dashboard Estad�stico"));

        cmbNivel = new JComboBox<>();
        btnActualizar = new JButton("Actualizar");

        superior.add(new JLabel("Nivel:"));
        superior.add(cmbNivel);
        superior.add(btnActualizar);

        modeloResumen = new DefaultTableModel(new String[]{"Indicador", "Valor"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaResumen = new JTable(modeloResumen);

        JPanel panelResumen = new JPanel(new BorderLayout());
        panelResumen.setBorder(BorderFactory.createTitledBorder("Resumen General"));
        panelResumen.add(new JScrollPane(tablaResumen), BorderLayout.CENTER);

        lblTituloGrafica = new JLabel("Resultados", SwingConstants.CENTER);
        lblTituloGrafica.setFont(new Font("Arial", Font.BOLD, 18));

        modeloGrafica = new DefaultTableModel(new String[]{"Categor�a", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaGrafica = new JTable(modeloGrafica);

        JPanel panelGrafica = new JPanel(new BorderLayout());
        panelGrafica.setBorder(BorderFactory.createTitledBorder("Estad�sticas"));
        panelGrafica.add(lblTituloGrafica, BorderLayout.NORTH);
        panelGrafica.add(new JScrollPane(tablaGrafica), BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelResumen, panelGrafica);
        split.setDividerLocation(250);

        add(superior, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);
    }

    public JComboBox<EstrategiaEstadistica> getCmbNivel() { return cmbNivel; }
    public JButton getBtnActualizar() { return btnActualizar; }
    
    public EstrategiaEstadistica getNivel() {
        return (EstrategiaEstadistica) cmbNivel.getSelectedItem();
    }

    public void setNiveles(List<? extends EstrategiaEstadistica> niveles) {
        cmbNivel.removeAllItems();
        for(EstrategiaEstadistica e : niveles){
            cmbNivel.addItem(e);
        }
    }

    public void setTarjetas(List<ResultadoConteo> resultados){
        modeloResumen.setRowCount(0);
        for(ResultadoConteo r : resultados){
            // CORRECCI�N: getEtiqueta() y getTotal()
            modeloResumen.addRow(new Object[]{ r.getEtiqueta(), r.getTotal() });
        }
    }

    public void setGrafica(String titulo, List<ResultadoConteo> resultados){
        lblTituloGrafica.setText(titulo);
        modeloGrafica.setRowCount(0);
        for(ResultadoConteo r : resultados){
            // CORRECCI�N: getEtiqueta() y getTotal()
            modeloGrafica.addRow(new Object[]{ r.getEtiqueta(), r.getTotal() });
        }
    }

    public void error(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
