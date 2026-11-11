package controlador;

import modelo.sesion.Sesion;
import vista.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Arma la ventana principal, crea cada pantalla con su controlador y
 * maneja la navegación y el cierre del sistema.
 */
public class MenuController {
    private final MenuPrincipalView vista = new MenuPrincipalView();
    private final Map<String, Recargable> controladores = new HashMap<>();

    public MenuController() {
        // ---- Menú Catálogos ----
        JMenu catalogos = vista.agregarMenu("Catálogos");
        registrar(catalogos, "municipios", "Municipios", new PanelMunicipios(), p -> new MunicipioController((PanelMunicipios) p));
        registrar(catalogos, "localidades", "Localidades", new PanelLocalidades(), p -> new LocalidadController((PanelLocalidades) p));
        registrar(catalogos, "tipos", "Tipos de vivienda", new PanelTiposVivienda(), p -> new TipoViviendaController((PanelTiposVivienda) p));
        registrar(catalogos, "actividades", "Actividades económicas", new PanelActividades(), p -> new ActividadController((PanelActividades) p));

        // ---- Menú Registro ----
        JMenu registro = vista.agregarMenu("Registro");
        registrar(registro, "viviendas", "Viviendas", new PanelViviendas(), p -> new ViviendaController((PanelViviendas) p));
        registrar(registro, "habitantes", "Habitantes", new PanelHabitantes(), p -> new HabitanteController((PanelHabitantes) p));
        registrar(registro, "actHab", "Actividades por habitante", new PanelActividadesHabitante(),
                p -> new ActividadHabitanteController((PanelActividadesHabitante) p));

        // ---- Menú Consultas ----
        JMenu consultas = vista.agregarMenu("Consultas");
        registrar(consultas, "detalle", "Habitantes y sustento por vivienda", new PanelDetalleVivienda(),
                p -> new DetalleViviendaController((PanelDetalleVivienda) p));

        // ---- Menú Estadísticas ----
        JMenu estadisticas = vista.agregarMenu("Estadísticas");
        registrar(estadisticas, "dashboard", "Dashboard", new PanelDashboard(), p -> new DashboardController((PanelDashboard) p));
        registrar(estadisticas, "reportes", "Reportes y gráficas", new PanelReportes(), p -> new ReporteController((PanelReportes) p));

        // ---- Menú Sistema ----
        JMenu sistema = vista.agregarMenu("Sistema");
        JMenuItem cerrarSesion = vista.agregarOpcion(sistema, "cerrarSesion", "Cerrar sesión");
        JMenuItem cerrarSistema = vista.agregarOpcion(sistema, "cerrarSistema", "Cerrar sistema");
        cerrarSesion.addActionListener(e -> cerrarSesion());
        cerrarSistema.addActionListener(e -> cerrarSistema());

        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) { cerrarSistema(); }
        });

        vista.setUsuario(Sesion.getInstancia().getUsuarioActual().getNombreCompleto());
    }

    private interface Fabrica { Recargable crear(JComponent panel); }

    private void registrar(JMenu menu, String id, String texto, JComponent panel, Fabrica fabrica) {
        vista.agregarPantalla(id, panel);
        controladores.put(id, fabrica.crear(panel));
        vista.agregarOpcion(menu, id, texto).addActionListener(e -> mostrar(id));
    }

    private void mostrar(String id) {
        controladores.get(id).cargar();   // refresca los datos antes de mostrar
        vista.mostrarPantalla(id);
    }

    public void iniciar() {
        mostrar("dashboard");
        vista.setVisible(true);
    }

    private void cerrarSesion() {
        Sesion.getInstancia().cerrarSesion();
        vista.dispose();
        new LoginController(new LoginView()).iniciar();
    }

    /** Requisito: cerrar sistema = cerrar BD + destruir variables de sesión. */
    private void cerrarSistema() {
        if (!vista.confirmarSalida()) return;
        Sesion.cerrarSistema();
        vista.dispose();
        System.exit(0);
    }
}
