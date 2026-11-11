package modelo.dao;

import modelo.entidades.ActividadEconomica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// crud asi bien economista
public class ActividadEconomicaDAO extends BaseDAO implements CrudDAO<ActividadEconomica> {

    @Override
    public boolean insertar(ActividadEconomica a) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "INSERT INTO actividad_economica (nombre) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getNombre());
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) a.setIdActividad(keys.getInt(1));
                }
            }
            return ok;
        }
    }

    @Override
    public boolean actualizar(ActividadEconomica a) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "UPDATE actividad_economica SET nombre = ? WHERE id_actividad = ?")) {
            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getIdActividad());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "DELETE FROM actividad_economica WHERE id_actividad = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public ActividadEconomica buscarPorId(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "SELECT id_actividad, nombre FROM actividad_economica WHERE id_actividad = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? new ActividadEconomica(rs.getInt(1), rs.getString(2)) : null;
            }
        }
    }

    @Override
    public List<ActividadEconomica> listar() throws SQLException {
        List<ActividadEconomica> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(
                "SELECT id_actividad, nombre FROM actividad_economica ORDER BY nombre");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(new ActividadEconomica(rs.getInt(1), rs.getString(2)));
        }
        return lista;
    }
}
