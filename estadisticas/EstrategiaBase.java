package modelo.estadisticas;

import modelo.conexion.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// codigo de las estrategias
abstract class EstrategiaBase implements EstrategiaEstadistica {

    protected List<ResultadoConteo> ejecutar(String sql, Object... parametros) throws SQLException {
        List<ResultadoConteo> resultados = new ArrayList<>();
        try (PreparedStatement ps = ConexionDB.getInstancia().getConexion().prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultados.add(new ResultadoConteo(rs.getString(1), rs.getLong(2)));
                }
            }
        }
        return resultados;
    }
}
