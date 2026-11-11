package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TipoViviendaView extends BaseCrudView {

    public JTextField txtNombre;

    public JButton btnGuardar;
    public JButton btnEditar;
    public JButton btnEliminar;

    public JTable tabla;

    public TipoViviendaView() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(
                new GridLayout(1,2,10,10));

        txtNombre = new JTextField();

        panel.add(new JLabel("Tipo Vivienda"));
        panel.add(txtNombre);

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
                                "Tipo Vivienda"
                        },0));

        add(panel,BorderLayout.NORTH);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }
}