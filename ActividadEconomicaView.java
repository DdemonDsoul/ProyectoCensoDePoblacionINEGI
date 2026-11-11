package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ActividadEconomicaView extends BaseCrudView {

    public JTextField txtActividad;

    public JButton btnGuardar;
    public JButton btnEditar;
    public JButton btnEliminar;

    public JTable tabla;

    public ActividadEconomicaView() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(
                new GridLayout(1,2,10,10));

        txtActividad = new JTextField();

        panel.add(new JLabel("Actividad Económica"));
        panel.add(txtActividad);

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
                                "Actividad"
                        },0));

        add(panel,BorderLayout.NORTH);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }
}