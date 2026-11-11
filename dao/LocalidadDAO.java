package modelo.dao;

import modelo.entidades.Localidad;
import modelo.entidades.Municipio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// crud localidades
public class LocalidadDAO extends BaseDAO implements CrudDAO<Localidad> {

    private static final String SELECT =
            "SELECT l.id_localidad, l.clave_localidad, l.nombre, l.ambito, "
          + "       m.id_municipio, m.clave_municipio, m.nombre AS nombre_municipio "
          + "FROM localidad l JOIN municipio m ON m.id_municipio = l.id_municipio ";

    @Override
    public boolean insertar(Localidad l) throws SQLException {
        String sql = "INSERT INTO localidad (id_municipio, clave_localidad, nombre, ambito) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, l.getMunicipio().getIdMunicipio());
            ps.setString(2, l.getClaveLocalidad());
            ps.setString(3, l.getNombre());
            ps.setString(4, l.getAmbito());
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) l.setIdLocalidad(keys.getInt(1));
                }
            }
            return ok;
        }
    }

    @Override
    public boolean actualizar(Localidad l) throws SQLException {
        String sql = "UPDATE localidad SET id_municipio = ?, clave_localidad = ?, nombre = ?, ambito = ? "
                   + "WHERE id_localidad = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, l.getMunicipio().getIdMunicipio());
            ps.setString(2, l.getClaveLocalidad());
            ps.setString(3, l.getNombre());
            ps.setString(4, l.getAmbito());
            ps.setInt(5, l.getIdLocalidad());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement("DELETE FROM localidad WHERE id_localidad = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Localidad buscarPorId(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT + "WHERE l.id_localidad = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public List<Localidad> listar() throws SQLException {
        List<Localidad> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT + "ORDER BY m.nombre, l.nombre");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

// combo de localidades por municipio
    public List<Localidad> listarPorMunicipio(int idMunicipio) throws SQLException {
        List<Localidad> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(
                SELECT + "WHERE m.id_municipio = ? ORDER BY l.nombre")) {
            ps.setInt(1, idMunicipio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    private Localidad mapear(ResultSet rs) throws SQLException {
        Municipio m = new Municipio(rs.getInt("id_municipio"), rs.getString("clave_municipio"),
                rs.getString("nombre_municipio"));
        return new Localidad(rs.getInt("id_localidad"), m, rs.getString("clave_localidad"),
                rs.getString("nombre"), rs.getString("ambito"));
    }
}
