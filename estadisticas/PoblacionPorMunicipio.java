package modelo.estadisticas;

import java.sql.SQLException;
import java.util.List;

// habitantes por municipio
public class PoblacionPorMunicipio extends EstrategiaBase {
    @Override
    public String getNombre() { return "Población por municipio"; }

    @Override
    public List<ResultadoConteo> calcular() throws SQLException {
        return ejecutar(
            "SELECT m.nombre, COUNT(h.id_habitante) AS total "
          + "FROM municipio m "
          + "LEFT JOIN localidad l ON l.id_municipio = m.id_municipio "
          + "LEFT JOIN vivienda v ON v.id_localidad = l.id_localidad "
          + "LEFT JOIN habitante h ON h.id_vivienda = v.id_vivienda "
          + "GROUP BY m.id_municipio, m.nombre ORDER BY total DESC, m.nombre");
    }
}
