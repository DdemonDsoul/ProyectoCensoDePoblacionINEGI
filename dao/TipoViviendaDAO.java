package modelo.dao;

import modelo.entidades.TipoVivienda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//crud viviendas
public class TipoViviendaDAO extends BaseDAO implements CrudDAO<TipoVivienda> {

    @Override
    public boolean insertar(TipoVivienda t) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "INSERT INTO tipo_vivienda (nombre) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getNombre());
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) t.setIdTipoVivienda(keys.getInt(1));
                }
            }
            return ok;
        }
    }

    @Override
    public boolean actualizar(TipoVivienda t) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "UPDATE tipo_vivienda SET nombre = ? WHERE id_tipo_vivienda = ?")) {
            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getIdTipoVivienda());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "DELETE FROM tipo_vivienda WHERE id_tipo_vivienda = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public TipoVivienda buscarPorId(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "SELECT id_tipo_vivienda, nombre FROM tipo_vivienda WHERE id_tipo_vivienda = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? new TipoVivienda(rs.getInt(1), rs.getString(2)) : null;
            }
        }
    }

    @Override
    public List<TipoVivienda> listar() throws SQLException {
        List<TipoVivienda> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(
                "SELECT id_tipo_vivienda, nombre FROM tipo_vivienda ORDER BY nombre");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(new TipoVivienda(rs.getInt(1), rs.getString(2)));
        }
        return lista;
    }
}
