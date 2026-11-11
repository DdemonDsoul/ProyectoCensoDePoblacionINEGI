package modelo.estadisticas;

import java.sql.SQLException;
import java.util.List;

// habitantes por localidad
public class PoblacionPorLocalidad extends EstrategiaBase {
    private final Integer idMunicipio;

    public PoblacionPorLocalidad() { this(null); }
    public PoblacionPorLocalidad(Integer idMunicipio) { this.idMunicipio = idMunicipio; }

    @Override
    public String getNombre() { return "Población por localidad"; }

    @Override
    public List<ResultadoConteo> calcular() throws SQLException {
        String base =
            "SELECT CONCAT(l.nombre, ' (', m.nombre, ')'), COUNT(h.id_habitante) AS total "
          + "FROM localidad l "
          + "JOIN municipio m ON m.id_municipio = l.id_municipio "
          + "LEFT JOIN vivienda v ON v.id_localidad = l.id_localidad "
          + "LEFT JOIN habitante h ON h.id_vivienda = v.id_vivienda ";
        String fin = "GROUP BY l.id_localidad, l.nombre, m.nombre ORDER BY total DESC, l.nombre";
        return (idMunicipio == null)
                ? ejecutar(base + fin)
                : ejecutar(base + "WHERE m.id_municipio = ? " + fin, idMunicipio);
    }
}
