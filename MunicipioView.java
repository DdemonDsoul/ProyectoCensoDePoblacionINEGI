package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MunicipioView extends BaseCrudView {

    public JTextField txtClave;
    public JTextField txtNombre;

    public JButton btnGuardar;
    public JButton btnEditar;
    public JButton btnEliminar;
    public JButton btnLimpiar;

    public JTable tabla;

    public MunicipioView() {

        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(2,2,10,10));

        formulario.setBorder(
                BorderFactory.createTitledBorder("Municipio"));

        formulario.add(new JLabel("Clave Municipio"));
        txtClave = new JTextField();
        formulario.add(txtClave);

        formulario.add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);

        JPanel botones = new JPanel();

        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        botones.add(btnGuardar);
        botones.add(btnEditar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        String columnas[] = {
                "ID",
                "Clave",
                "Nombre"
        };

        tabla = new JTable(
                new DefaultTableModel(columnas,0));

        add(formulario,BorderLayout.NORTH);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
    }
    public JTextField getTxtClave() {
        return txtClave;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
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
    public void limpiarFormulario() {

        txtClave.setText("");
        txtNombre.setText("");

        txtClave.requestFocus();

    }
    public boolean validarCampos() {

        if(txtClave.getText().trim().isEmpty()) {

            mostrarMensaje(
                    "Ingrese la clave"
            );

            txtClave.requestFocus();

            return false;
        }

        if(txtNombre.getText().trim().isEmpty()) {

            mostrarMensaje(
                    "Ingrese el municipio"
            );

            txtNombre.requestFocus();

            return false;
        }

        return true;
    }
}