package modelo.estadisticas;

import java.sql.SQLException;
import java.util.List;

// habitantes por tipo de vivienda
public class PoblacionPorTipoVivienda extends EstrategiaBase {
    @Override
    public String getNombre() { return "Habitantes por tipo de vivienda"; }

    @Override
    public List<ResultadoConteo> calcular() throws SQLException {
        return ejecutar(
            "SELECT t.nombre, COUNT(h.id_habitante) AS total "
          + "FROM tipo_vivienda t "
          + "LEFT JOIN vivienda v ON v.id_tipo_vivienda = t.id_tipo_vivienda "
          + "LEFT JOIN habitante h ON h.id_vivienda = v.id_vivienda "
          + "GROUP BY t.id_tipo_vivienda, t.nombre ORDER BY total DESC, t.nombre");
    }
}
