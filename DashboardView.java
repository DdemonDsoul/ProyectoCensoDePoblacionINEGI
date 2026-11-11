package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardView extends JPanel {

    private JComboBox<String> cbReporte;

    private JButton btnGenerar;
    private JButton btnLimpiar;

    private JTable tablaResultados;

    public DashboardView() {

        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // ==========================
        // PANEL SUPERIOR
        // ==========================

        JPanel panelSuperior = new JPanel(
                new FlowLayout(FlowLayout.LEFT));

        JLabel lblTitulo =
                new JLabel("Dashboard Estadístico");

        lblTitulo.setFont(
                new Font("Arial",
                        Font.BOLD,
                        20));

        panelSuperior.add(lblTitulo);

        // ==========================
        // PANEL FILTROS
        // ==========================

        JPanel panelFiltros = new JPanel();

        panelFiltros.setBorder(
                BorderFactory.createTitledBorder(
                        "Generar Reporte"));

        cbReporte = new JComboBox<>();

        cbReporte.addItem(
                "Población por Municipio");

        cbReporte.addItem(
                "Población por Localidad");

        cbReporte.addItem(
                "Población por Sexo");

        cbReporte.addItem(
                "Población por Tipo Vivienda");

        btnGenerar =
                new JButton("Generar");

        btnLimpiar =
                new JButton("Limpiar");

        panelFiltros.add(
                new JLabel("Reporte"));

        panelFiltros.add(cbReporte);

        panelFiltros.add(btnGenerar);

        panelFiltros.add(btnLimpiar);

        // ==========================
        // TABLA
        // ==========================

        String[] columnas = {
                "Categoría",
                "Cantidad"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(
                        columnas,
                        0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        tablaResultados =
                new JTable(modelo);

        tablaResultados.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll =
                new JScrollPane(tablaResultados);

        // ==========================
        // PANEL CENTRAL
        // ==========================

        JPanel panelCentro =
                new JPanel(
                        new BorderLayout());

        panelCentro.add(
                panelFiltros,
                BorderLayout.NORTH);

        panelCentro.add(
                scroll,
                BorderLayout.CENTER);

        add(panelSuperior,
                BorderLayout.NORTH);

        add(panelCentro,
                BorderLayout.CENTER);
    }

    // ==================================
    // GETTERS
    // ==================================

    public JComboBox<String> getCbReporte() {
        return cbReporte;
    }

    public JButton getBtnGenerar() {
        return btnGenerar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTable getTablaResultados() {
        return tablaResultados;
    }

    // ==================================
    // MÉTODOS AUXILIARES
    // ==================================

    public void mostrarMensaje(
            String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje
        );
    }

    public DefaultTableModel getModeloTabla() {

        return (DefaultTableModel)
                tablaResultados.getModel();
    }

    public void limpiarTabla() {

        getModeloTabla().setRowCount(0);
    }

    public void agregarFila(
            Object[] fila) {

        getModeloTabla()
                .addRow(fila);
    }

    public void limpiarVista() {

        cbReporte.setSelectedIndex(0);

        limpiarTabla();
    }
}