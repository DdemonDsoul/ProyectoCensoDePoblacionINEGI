package modelo.estadisticas;

import java.sql.SQLException;
import java.util.List;

// strategy
public interface EstrategiaEstadistica {
    String getNombre();

    List<ResultadoConteo> calcular() throws SQLException;
}
