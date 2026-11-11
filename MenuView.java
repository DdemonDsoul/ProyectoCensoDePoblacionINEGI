package vista;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    // ==========================
    // BOTONES MENÚ
    // ==========================

    private JButton btnMunicipios;
    private JButton btnLocalidades;
    private JButton btnTipoVivienda;
    private JButton btnActividades;
    private JButton btnViviendas;
    private JButton btnHabitantes;
    private JButton btnHabitanteActividad;
    private JButton btnDashboard;
    private JButton btnSalir;

    // ==========================
    // CARDLAYOUT
    // ==========================

    private CardLayout cardLayout;
    private JPanel panelContenido;

    public MenuView() {

        setTitle("Sistema Censo INEGI");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // ==========================
        // MENÚ LATERAL
        // ==========================

        JPanel menuLateral = new JPanel();

        menuLateral.setLayout(
                new GridLayout(9, 1, 5, 5));

        menuLateral.setPreferredSize(
                new Dimension(220, 0));

        btnMunicipios = new JButton("Municipios");
        btnLocalidades = new JButton("Localidades");
        btnTipoVivienda = new JButton("Tipos Vivienda");
        btnActividades = new JButton("Actividades");
        btnViviendas = new JButton("Viviendas");
        btnHabitantes = new JButton("Habitantes");
        btnHabitanteActividad =
                new JButton("Habitante Actividad");
        btnDashboard = new JButton("Dashboard");
        btnSalir = new JButton("Salir");

        menuLateral.add(btnMunicipios);
        menuLateral.add(btnLocalidades);
        menuLateral.add(btnTipoVivienda);
        menuLateral.add(btnActividades);
        menuLateral.add(btnViviendas);
        menuLateral.add(btnHabitantes);
        menuLateral.add(btnHabitanteActividad);
        menuLateral.add(btnDashboard);
        menuLateral.add(btnSalir);

        // ==========================
        // PANEL CONTENIDO
        // ==========================

        cardLayout = new CardLayout();

        panelContenido = new JPanel(cardLayout);

        // Pantalla por defecto

        JPanel inicio = new JPanel(
                new BorderLayout());

        JLabel lblBienvenida =
                new JLabel(
                        "Bienvenido al Sistema Censo INEGI",
                        SwingConstants.CENTER);

        lblBienvenida.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24));

        inicio.add(
                lblBienvenida,
                BorderLayout.CENTER);

        // ==========================
        // REGISTRO DE VISTAS
        // ==========================

        panelContenido.add(inicio, "INICIO");

        panelContenido.add(
                new MunicipioView(),
                "MUNICIPIOS");

        panelContenido.add(
                new LocalidadView(),
                "LOCALIDADES");

        panelContenido.add(
                new TipoViviendaView(),
                "TIPOS_VIVIENDA");

        panelContenido.add(
                new ActividadEconomicaView(),
                "ACTIVIDADES");

        panelContenido.add(
                new ViviendaView(),
                "VIVIENDAS");

        panelContenido.add(
                new HabitanteView(),
                "HABITANTES");

        panelContenido.add(
                new HabitanteActividadView(),
                "HABITANTE_ACTIVIDAD");

        panelContenido.add(
                new DashboardView(),
                "DASHBOARD");

        // ==========================
        // EVENTOS TEMPORALES
        // ==========================

        btnMunicipios.addActionListener(e ->
                cardLayout.show(
                        panelContenido,
                        "MUNICIPIOS"));

        btnLocalidades.addActionListener(e ->
                cardLayout.show(
                        panelContenido,
                        "LOCALIDADES"));

        btnTipoVivienda.addActionListener(e ->
                cardLayout.show(
                        panelContenido,
                        "TIPOS_VIVIENDA"));

        btnActividades.addActionListener(e ->
                cardLayout.show(
                        panelContenido,
                        "ACTIVIDADES"));

        btnViviendas.addActionListener(e ->
                cardLayout.show(
                        panelContenido,
                        "VIVIENDAS"));

        btnHabitantes.addActionListener(e ->
                cardLayout.show(
                        panelContenido,
                        "HABITANTES"));

        btnHabitanteActividad.addActionListener(e ->
                cardLayout.show(
                        panelContenido,
                        "HABITANTE_ACTIVIDAD"));

        btnDashboard.addActionListener(e ->
                cardLayout.show(
                        panelContenido,
                        "DASHBOARD"));

        btnSalir.addActionListener(e -> {

            if (confirmarSalida()) {
                System.exit(0);
            }

        });

        // ==========================
        // FRAME
        // ==========================

        add(menuLateral, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);
    }

    // ==========================
    // MÉTODOS AUXILIARES
    // ==========================

    public void mostrarVista(
            String nombreVista) {

        cardLayout.show(
                panelContenido,
                nombreVista);
    }

    public void mostrarMensaje(
            String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje);
    }

    public boolean confirmarSalida() {

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea salir del sistema?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION);

        return opcion ==
                JOptionPane.YES_OPTION;
    }

    // ==========================
    // GETTERS
    // ==========================

    public JButton getBtnMunicipios() {
        return btnMunicipios;
    }

    public JButton getBtnLocalidades() {
        return btnLocalidades;
    }

    public JButton getBtnTipoVivienda() {
        return btnTipoVivienda;
    }

    public JButton getBtnActividades() {
        return btnActividades;
    }

    public JButton getBtnViviendas() {
        return btnViviendas;
    }

    public JButton getBtnHabitantes() {
        return btnHabitantes;
    }

    public JButton getBtnHabitanteActividad() {
        return btnHabitanteActividad;
    }

    public JButton getBtnDashboard() {
        return btnDashboard;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}