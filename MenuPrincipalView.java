package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView extends JFrame {

    private JMenuBar menuBar;

    private JLabel lblUsuario;

    private CardLayout cardLayout;
    private JPanel panelContenido;

    public MenuPrincipalView() {

        setTitle("Sistema Censo INEGI");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // ==========================
        // MENU BAR
        // ==========================

        menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        // ==========================
        // PANEL SUPERIOR
        // ==========================

        JPanel panelSuperior =
                new JPanel(new BorderLayout());

        lblUsuario =
                new JLabel("Usuario: ");

        lblUsuario.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 10, 5, 10));

        panelSuperior.add(
                lblUsuario,
                BorderLayout.EAST);

        // ==========================
        // CARD LAYOUT
        // ==========================

        cardLayout = new CardLayout();

        panelContenido =
                new JPanel(cardLayout);

        JLabel inicio =
                new JLabel(
                        "Bienvenido al Sistema Censo INEGI",
                        SwingConstants.CENTER);

        inicio.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24));

        panelContenido.add(
                inicio,
                "INICIO");

        // ==========================
        // FRAME
        // ==========================

        add(panelSuperior,
                BorderLayout.NORTH);

        add(panelContenido,
                BorderLayout.CENTER);
    }

    // ==================================
    // MEN�S
    // ==================================

    public JMenu agregarMenu(
            String nombre) {

        JMenu menu =
                new JMenu(nombre);

        menuBar.add(menu);

        return menu;
    }

    // ==================================
    // OPCIONES DE MEN�
    // ==================================

    public JMenuItem agregarOpcion(
            JMenu menu,
            String id,
            String texto) {

        JMenuItem item =
                new JMenuItem(texto);

        menu.add(item);

        return item;
    }

    // ==================================
    // PANTALLAS
    // ==================================

    public void agregarPantalla(
            String id,
            JComponent panel) {

        panelContenido.add(panel, id);
    }

    public void mostrarPantalla(
            String id) {

        cardLayout.show(
                panelContenido,
                id);
    }

    // ==================================
    // USUARIO
    // ==================================

    public void setUsuario(
            String nombreUsuario) {

        lblUsuario.setText(
                "Usuario: " + nombreUsuario);
    }

    // ==================================
    // SALIDA
    // ==================================

    public boolean confirmarSalida() {

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "�Desea salir del sistema?",
                        "Confirmaci�n",
                        JOptionPane.YES_NO_OPTION);

        return opcion ==
                JOptionPane.YES_OPTION;
    }

    // ==================================
    // GETTERS
    // ==================================

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
