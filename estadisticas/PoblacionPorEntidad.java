package modelo.estadisticas;

import java.sql.SQLException;
import java.util.List;

// total entidad
public class PoblacionPorEntidad extends EstrategiaBase {
    @Override
    public String getNombre() { return "Población total del estado"; }

    @Override
    public List<ResultadoConteo> calcular() throws SQLException {
        return ejecutar("SELECT 'Coahuila de Zaragoza', COUNT(*) FROM habitante");
    }
}
