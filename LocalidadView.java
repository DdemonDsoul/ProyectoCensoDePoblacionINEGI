package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LocalidadView extends BaseCrudView {

    public JComboBox<String> cbMunicipio;
    public JComboBox<String> cbAmbito;

    public JTextField txtClave;
    public JTextField txtNombre;

    public JButton btnGuardar;
    public JButton btnEditar;
    public JButton btnEliminar;

    public JTable tabla;

    public LocalidadView() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(
                new GridLayout(4,2,10,10));

        panel.setBorder(
                BorderFactory.createTitledBorder("Localidad"));

        cbMunicipio = new JComboBox<>();

        txtClave = new JTextField();

        txtNombre = new JTextField();

        cbAmbito = new JComboBox<>();

        cbAmbito.addItem("URBANO");
        cbAmbito.addItem("RURAL");

        panel.add(new JLabel("Municipio"));
        panel.add(cbMunicipio);

        panel.add(new JLabel("Clave Localidad"));
        panel.add(txtClave);

        panel.add(new JLabel("Nombre"));
        panel.add(txtNombre);

        panel.add(new JLabel("Ámbito"));
        panel.add(cbAmbito);

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
                                "Municipio",
                                "Clave",
                                "Nombre",
                                "Ámbito"
                        },0));

        add(panel,BorderLayout.NORTH);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }
}