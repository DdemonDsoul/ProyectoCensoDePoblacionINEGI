package modelo.dao;

import modelo.entidades.Localidad;
import modelo.entidades.Municipio;
import modelo.entidades.TipoVivienda;
import modelo.entidades.Vivienda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

// crud viviendas (builder)
public class ViviendaDAO extends BaseDAO implements CrudDAO<Vivienda> {

    private static final String SELECT =
            "SELECT v.id_vivienda, v.calle, v.numero, v.colonia, v.num_habitaciones, "
          + "       v.agua, v.luz, v.drenaje, v.internet, "
          + "       t.id_tipo_vivienda, t.nombre AS nombre_tipo, "
          + "       l.id_localidad, l.clave_localidad, l.nombre AS nombre_localidad, l.ambito, "
          + "       m.id_municipio, m.clave_municipio, m.nombre AS nombre_municipio "
          + "FROM vivienda v "
          + "JOIN tipo_vivienda t ON t.id_tipo_vivienda = v.id_tipo_vivienda "
          + "JOIN localidad l ON l.id_localidad = v.id_localidad "
          + "JOIN municipio m ON m.id_municipio = l.id_municipio ";

    @Override
    public boolean insertar(Vivienda v) throws SQLException {
        String sql = "INSERT INTO vivienda (id_localidad, id_tipo_vivienda, calle, numero, colonia, "
                   + "num_habitaciones, agua, luz, drenaje, internet) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            llenar(ps, v);
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) v.setIdVivienda(keys.getInt(1));
                }
            }
            return ok;
        }
    }

    @Override
    public boolean actualizar(Vivienda v) throws SQLException {
        String sql = "UPDATE vivienda SET id_localidad=?, id_tipo_vivienda=?, calle=?, numero=?, colonia=?, "
                   + "num_habitaciones=?, agua=?, luz=?, drenaje=?, internet=? WHERE id_vivienda=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            llenar(ps, v);
            ps.setInt(11, v.getIdVivienda());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement("DELETE FROM vivienda WHERE id_vivienda = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Vivienda buscarPorId(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT + "WHERE v.id_vivienda = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public List<Vivienda> listar() throws SQLException {
        List<Vivienda> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT + "ORDER BY m.nombre, l.nombre, v.calle");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public List<Vivienda> listarPorLocalidad(int idLocalidad) throws SQLException {
        List<Vivienda> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(
                SELECT + "WHERE v.id_localidad = ? ORDER BY v.calle, v.numero")) {
            ps.setInt(1, idLocalidad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    private void llenar(PreparedStatement ps, Vivienda v) throws SQLException {
        ps.setInt(1, v.getLocalidad().getIdLocalidad());
        ps.setInt(2, v.getTipoVivienda().getIdTipoVivienda());
        ps.setString(3, v.getCalle());
        ps.setString(4, v.getNumero());
        ps.setString(5, v.getColonia());
        if (v.getNumHabitaciones() == null) ps.setNull(6, Types.INTEGER);
        else ps.setInt(6, v.getNumHabitaciones());
        ps.setBoolean(7, v.isAgua());
        ps.setBoolean(8, v.isLuz());
        ps.setBoolean(9, v.isDrenaje());
        ps.setBoolean(10, v.isInternet());
    }

    private Vivienda mapear(ResultSet rs) throws SQLException {
        Municipio m = new Municipio(rs.getInt("id_municipio"), rs.getString("clave_municipio"),
                rs.getString("nombre_municipio"));
        Localidad l = new Localidad(rs.getInt("id_localidad"), m, rs.getString("clave_localidad"),
                rs.getString("nombre_localidad"), rs.getString("ambito"));
        TipoVivienda t = new TipoVivienda(rs.getInt("id_tipo_vivienda"), rs.getString("nombre_tipo"));
        int hab = rs.getInt("num_habitaciones");
        Integer habitaciones = rs.wasNull() ? null : hab;

        return new Vivienda.Builder(l, t, rs.getString("calle"))
                .id(rs.getInt("id_vivienda"))
                .numero(rs.getString("numero"))
                .colonia(rs.getString("colonia"))
                .numHabitaciones(habitaciones)
                .agua(rs.getBoolean("agua"))
                .luz(rs.getBoolean("luz"))
                .drenaje(rs.getBoolean("drenaje"))
                .internet(rs.getBoolean("internet"))
                .build();
    }
}
