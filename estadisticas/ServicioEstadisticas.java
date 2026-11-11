package modelo.estadisticas;

import java.sql.SQLException;
import java.util.List;

/**
 * La vista elige una estrategia y el contexto la ejecuta sin
 * saber como se calcula. Para agregar un reporte nuevo solo se crea otra
 * clase que implemente estrategiaestadistica.
 */
public class ServicioEstadisticas {
    private EstrategiaEstadistica estrategia;

    public ServicioEstadisticas() { }
    public ServicioEstadisticas(EstrategiaEstadistica estrategia) { this.estrategia = estrategia; }

    public void setEstrategia(EstrategiaEstadistica estrategia) { this.estrategia = estrategia; }
    public EstrategiaEstadistica getEstrategia() { return estrategia; }

    public String getTitulo() {
        return estrategia == null ? "" : estrategia.getNombre();
    }

    public List<ResultadoConteo> ejecutar() throws SQLException {
        if (estrategia == null) {
            throw new IllegalStateException("No se ha seleccionado una estrategia estadística");
        }
        return estrategia.calcular();
    }
}
