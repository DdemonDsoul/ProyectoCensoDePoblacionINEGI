package vista;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    private JButton btnEntrar;
    private JButton btnSalir;

    public LoginView() {

        setTitle("Censo INEGI");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        JPanel panelPrincipal = new JPanel(
                new BorderLayout());

        // ==========================
        // TITULO
        // ==========================

        JLabel lblTitulo = new JLabel(
                "CENSO INEGI",
                SwingConstants.CENTER);

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24));

        panelPrincipal.add(
                lblTitulo,
                BorderLayout.NORTH);

        // ==========================
        // FORMULARIO
        // ==========================

        JPanel formulario =
                new JPanel(
                        new GridLayout(2, 2, 10, 10));

        formulario.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30));

        txtUsuario = new JTextField();

        txtPassword = new JPasswordField();

        formulario.add(
                new JLabel("Usuario"));

        formulario.add(txtUsuario);

        formulario.add(
                new JLabel("Contrase�a"));

        formulario.add(txtPassword);

        panelPrincipal.add(
                formulario,
                BorderLayout.CENTER);

        // ==========================
        // BOTONES
        // ==========================

        JPanel panelBotones =
                new JPanel();

        btnEntrar =
                new JButton("Entrar");

        btnSalir =
                new JButton("Salir");

        panelBotones.add(btnEntrar);
        panelBotones.add(btnSalir);

        panelPrincipal.add(
                panelBotones,
                BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    // ==================================
    // GETTERS
    // ==================================

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public String getUsuario() {

        return txtUsuario
                .getText()
                .trim();
    }

    public String getPassword() {

        return String.valueOf(
                txtPassword.getPassword()
        );
    }

    // ==================================
    // UTILIDADES
    // ==================================

    public void limpiarPassword() {

        txtPassword.setText("");

        txtPassword.requestFocus();
    }

    public void limpiar() {

        txtUsuario.setText("");
        txtPassword.setText("");

        txtUsuario.requestFocus();
    }

    public void error(
            String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void mensaje(
            String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Informaci�n",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
