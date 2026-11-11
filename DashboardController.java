package controlador;

import modelo.estadisticas.*;
import vista.PanelDashboard;

import java.sql.SQLException;
import java.util.Arrays;

public class DashboardController implements Recargable {
    private final PanelDashboard vista;
    private final ServicioEstadisticas servicio = new ServicioEstadisticas();

    public DashboardController(PanelDashboard vista) {
        this.vista = vista;
        vista.setNiveles(Arrays.asList(
                new PoblacionPorEntidad(),
                new PoblacionPorMunicipio(),
                new PoblacionPorLocalidad(),
                new PoblacionPorTipoVivienda()));
        vista.getBtnActualizar().addActionListener(e -> cargar());
        vista.getCmbNivel().addActionListener(e -> cargar());
    }

    @Override
    public void cargar() {
        try {
            servicio.setEstrategia(new ResumenGeneral());
            vista.setTarjetas(servicio.ejecutar());

            if (vista.getNivel() != null) {
                servicio.setEstrategia(vista.getNivel());
                vista.setGrafica(servicio.getTitulo(), servicio.ejecutar());
            }
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
