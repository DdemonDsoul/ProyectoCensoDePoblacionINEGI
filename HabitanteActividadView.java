package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class HabitanteActividadView extends BaseCrudView {

    private JComboBox<String> cbHabitante;
    private JComboBox<String> cbActividad;

    private JTextField txtIngreso;

    private JButton btnGuardar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public HabitanteActividadView() {

        setLayout(new BorderLayout());

        JPanel panelFormulario =
                new JPanel(new GridLayout(3, 2, 10, 10));

        panelFormulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Actividad Económica del Habitante"));

        cbHabitante = new JComboBox<>();
        cbActividad = new JComboBox<>();
        txtIngreso = new JTextField();

        panelFormulario.add(new JLabel("Habitante"));
        panelFormulario.add(cbHabitante);

        panelFormulario.add(new JLabel("Actividad Económica"));
        panelFormulario.add(cbActividad);

        panelFormulario.add(new JLabel("Ingreso Mensual"));
        panelFormulario.add(txtIngreso);

        JPanel panelBotones = new JPanel();

        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        String[] columnas = {
                "ID",
                "Habitante",
                "Actividad",
                "Ingreso Mensual"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(columnas, 0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {
                        return false;
                    }
                };

        tabla = new JTable(modelo);

        add(panelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // ==========================
    // GETTERS
    // ==========================

    public JComboBox<String> getCbHabitante() {
        return cbHabitante;
    }

    public JComboBox<String> getCbActividad() {
        return cbActividad;
    }

    public JTextField getTxtIngreso() {
        return txtIngreso;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    // ==========================
    // MÉTODOS AUXILIARES
    // ==========================

    public void limpiarFormulario() {

        cbHabitante.setSelectedIndex(-1);
        cbActividad.setSelectedIndex(-1);

        txtIngreso.setText("");

        cbHabitante.requestFocus();
    }

    public boolean validarCampos() {

        if (cbHabitante.getSelectedItem() == null) {

            mostrarMensaje(
                    "Seleccione un habitante");

            return false;
        }

        if (cbActividad.getSelectedItem() == null) {

            mostrarMensaje(
                    "Seleccione una actividad económica");

            return false;
        }

        if (txtIngreso.getText().trim().isEmpty()) {

            mostrarMensaje(
                    "Ingrese el ingreso mensual");

            txtIngreso.requestFocus();

            return false;
        }

        try {

            Double.parseDouble(
                    txtIngreso.getText());

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Ingrese un ingreso válido");

            txtIngreso.requestFocus();

            return false;
        }

        return true;
    }

}