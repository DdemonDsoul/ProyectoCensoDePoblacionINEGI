package modelo.estadisticas;

import java.sql.SQLException;
import java.util.List;

// por sexo
public class PoblacionPorSexo extends EstrategiaBase {
    @Override
    public String getNombre() { return "Población por sexo"; }

    @Override
    public List<ResultadoConteo> calcular() throws SQLException {
        return ejecutar(
            "SELECT CASE sexo WHEN 'M' THEN 'Hombres' WHEN 'F' THEN 'Mujeres' ELSE 'No especificado' END, "
          + "COUNT(*) AS total FROM habitante GROUP BY sexo ORDER BY total DESC");
    }
}
