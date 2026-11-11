package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HabitanteView extends BaseCrudView {

    public JComboBox<String> cbVivienda;
    
    public JTextField txtNombre;
    public JTextField txtApellidoP;
    public JTextField txtApellidoM;
    public JTextField txtEdad;
    public JTextField txtEstadoCivil;
    public JTextField txtEscolaridad;
    public JTextField txtEstatura;

    public JComboBox<String> cbSexo;

    public JTable tabla;

    public JButton btnGuardar;
    public JButton btnEditar;
    public JButton btnEliminar;

    public HabitanteView() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(
                new GridLayout(8,2,10,10));

        cbVivienda = new JComboBox<>();

        txtNombre = new JTextField();
        txtApellidoP = new JTextField();
        txtApellidoM = new JTextField();

        cbSexo = new JComboBox<>();
        cbSexo.addItem("M");
        cbSexo.addItem("F");

        txtEdad = new JTextField();
        txtEstadoCivil = new JTextField();
        txtEscolaridad = new JTextField();
        txtEstatura = new JTextField();

        panel.add(new JLabel("Vivienda"));
        panel.add(cbVivienda);

        panel.add(new JLabel("Nombre"));
        panel.add(txtNombre);

        panel.add(new JLabel("Apellido Paterno"));
        panel.add(txtApellidoP);

        panel.add(new JLabel("Apellido Materno"));
        panel.add(txtApellidoM);

        panel.add(new JLabel("Sexo"));
        panel.add(cbSexo);

        panel.add(new JLabel("Edad"));
        panel.add(txtEdad);

        panel.add(new JLabel("Estado Civil"));
        panel.add(txtEstadoCivil);

        panel.add(new JLabel("Escolaridad"));
        panel.add(txtEscolaridad);

        panel.add(new JLabel("Estatura"));
        panel.add(txtEstatura);

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
                                "Nombre",
                                "Sexo",
                                "Edad"
                        },0));

        add(panel,BorderLayout.NORTH);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }
}