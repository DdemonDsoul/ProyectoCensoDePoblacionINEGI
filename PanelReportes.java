package vista;

import modelo.estadisticas.EstrategiaEstadistica;
import modelo.estadisticas.ResultadoConteo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelReportes extends JPanel {

    private JComboBox<EstrategiaEstadistica> cmbEstrategia;
    private JButton btnGenerar;
    private JLabel lblTitulo;
    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelReportes() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Reportes Estadísticos"));

        cmbEstrategia = new JComboBox<>();
        btnGenerar = new JButton("Generar");

        panelSuperior.add(new JLabel("Reporte"));
        panelSuperior.add(cmbEstrategia);
        panelSuperior.add(btnGenerar);

        add(panelSuperior, BorderLayout.NORTH);

        lblTitulo = new JLabel("Seleccione un reporte", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.SOUTH);

        modelo = new DefaultTableModel(new String[]{"Categoría", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public JButton getBtnGenerar() { return btnGenerar; }

    public EstrategiaEstadistica getEstrategia() {
        return (EstrategiaEstadistica) cmbEstrategia.getSelectedItem();
    }

    public void setEstrategias(List<? extends EstrategiaEstadistica> estrategias){
        cmbEstrategia.removeAllItems();
        for(EstrategiaEstadistica e : estrategias){
            cmbEstrategia.addItem(e);
        }
    }

    public void mostrar(String titulo, List<ResultadoConteo> datos){
        lblTitulo.setText(titulo);
        modelo.setRowCount(0);
        for(ResultadoConteo r : datos){
            // CORRECCIÓN: getEtiqueta() y getTotal()
            modelo.addRow(new Object[]{ r.getEtiqueta(), r.getTotal() });
        }
    }

    public void error(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}