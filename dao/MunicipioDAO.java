package modelo.dao;

import modelo.entidades.Municipio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// crud municipios
public class MunicipioDAO extends BaseDAO implements CrudDAO<Municipio> {

    @Override
    public boolean insertar(Municipio m) throws SQLException {
        String sql = "INSERT INTO municipio (clave_municipio, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getClaveMunicipio());
            ps.setString(2, m.getNombre());
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) m.setIdMunicipio(keys.getInt(1));
                }
            }
            return ok;
        }
    }

    @Override
    public boolean actualizar(Municipio m) throws SQLException {
        String sql = "UPDATE municipio SET clave_municipio = ?, nombre = ? WHERE id_municipio = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, m.getClaveMunicipio());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getIdMunicipio());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement("DELETE FROM municipio WHERE id_municipio = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Municipio buscarPorId(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "SELECT id_municipio, clave_municipio, nombre FROM municipio WHERE id_municipio = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public List<Municipio> listar() throws SQLException {
        List<Municipio> lista = new ArrayList<>();
        String sql = "SELECT id_municipio, clave_municipio, nombre FROM municipio ORDER BY nombre";
        try (PreparedStatement ps = getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Municipio mapear(ResultSet rs) throws SQLException {
        return new Municipio(rs.getInt("id_municipio"), rs.getString("clave_municipio"), rs.getString("nombre"));
    }
}
