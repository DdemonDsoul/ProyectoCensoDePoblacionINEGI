package controlador;

import modelo.estadisticas.*;
import vista.PanelReportes;

import java.sql.SQLException;
import java.util.Arrays;

/** Aquí se usa el PATRÓN STRATEGY: el controlador le da una estrategia al servicio y lo ejecuta. */
public class ReporteController implements Recargable {
    private final PanelReportes vista;
    private final ServicioEstadisticas servicio = new ServicioEstadisticas();

    public ReporteController(PanelReportes vista) {
        this.vista = vista;
        vista.setEstrategias(Arrays.asList(
                new PoblacionPorMunicipio(),
                new PoblacionPorLocalidad(),
                new PoblacionPorTipoVivienda(),
                new PoblacionPorSexo(),
                new PoblacionPorEntidad()));
        vista.getBtnGenerar().addActionListener(e -> generar());
    }

    @Override
    public void cargar() { generar(); }

    private void generar() {
        if (vista.getEstrategia() == null) return;
        try {
            servicio.setEstrategia(vista.getEstrategia());   // <- se cambia la estrategia en tiempo de ejecución
            vista.mostrar(servicio.getTitulo(), servicio.ejecutar());
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
