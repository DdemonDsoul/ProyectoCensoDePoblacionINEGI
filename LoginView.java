package vista;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    public JTextField txtUsuario;
    public JPasswordField txtPassword;
    public JButton btnIngresar;

    public LoginView() {

        setTitle("Censo INEGI");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("CENSO INEGI");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(120,20,200,30);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(50,80,100,25);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150,80,180,25);

        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setBounds(50,120,100,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150,120,180,25);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(130,180,120,35);

        panel.add(lblTitulo);
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnIngresar);

        add(panel);
    }
    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getPassword() {
        return String.valueOf(
                txtPassword.getPassword()
        );
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje
        );
    }
}