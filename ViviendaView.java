package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViviendaView extends BaseCrudView {

    public JComboBox<String> cbLocalidad;
    public JComboBox<String> cbTipo;

    public JTextField txtCalle;
    public JTextField txtNumero;
    public JTextField txtColonia;
    public JTextField txtHabitaciones;

    public JCheckBox chkAgua;
    public JCheckBox chkLuz;
    public JCheckBox chkDrenaje;
    public JCheckBox chkInternet;

    public JTable tabla;

    public JButton btnGuardar;
    public JButton btnEditar;
    public JButton btnEliminar;

    public ViviendaView() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(
                new GridLayout(8,2,10,10));

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

        panel.add(new JLabel("Localidad"));
        panel.add(cbLocalidad);

        panel.add(new JLabel("Tipo Vivienda"));
        panel.add(cbTipo);

        panel.add(new JLabel("Calle"));
        panel.add(txtCalle);

        panel.add(new JLabel("Número"));
        panel.add(txtNumero);

        panel.add(new JLabel("Colonia"));
        panel.add(txtColonia);

        panel.add(new JLabel("Habitaciones"));
        panel.add(txtHabitaciones);

        panel.add(chkAgua);
        panel.add(chkLuz);

        panel.add(chkDrenaje);
        panel.add(chkInternet);

        JPanel botones = new JPanel();

        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        botones.add(btnGuardar);
        botones.add(btnEditar);
        botones.add(btnEliminar);

        tabla = new JTable(
                new DefaultTableModel(
                        new String[]{
                                "ID",
                                "Localidad",
                                "Tipo",
                                "Calle",
                                "Número"
                        },0));

        add(panel,BorderLayout.NORTH);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }
}