package modelo.dao;

import modelo.entidades.ActividadEconomica;
import modelo.entidades.HabitanteActividad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// actividades economicas por habitante y sustento por vivienda
public class HabitanteActividadDAO extends BaseDAO implements CrudDAO<HabitanteActividad> {

    private static final String SELECT =
            "SELECT ha.id_habitante_actividad, ha.id_habitante, ha.ingreso_mensual, "
          + "       a.id_actividad, a.nombre "
          + "FROM habitante_actividad ha "
          + "JOIN actividad_economica a ON a.id_actividad = ha.id_actividad ";

    @Override
    public boolean insertar(HabitanteActividad ha) throws SQLException {
        String sql = "INSERT INTO habitante_actividad (id_habitante, id_actividad, ingreso_mensual) VALUES (?,?,?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ha.getIdHabitante());
            ps.setInt(2, ha.getActividad().getIdActividad());
            ps.setBigDecimal(3, ha.getIngresoMensual());
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) ha.setIdHabitanteActividad(keys.getInt(1));
                }
            }
            return ok;
        }
    }

    @Override
    public boolean actualizar(HabitanteActividad ha) throws SQLException {
        String sql = "UPDATE habitante_actividad SET id_habitante=?, id_actividad=?, ingreso_mensual=? "
                   + "WHERE id_habitante_actividad=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, ha.getIdHabitante());
            ps.setInt(2, ha.getActividad().getIdActividad());
            ps.setBigDecimal(3, ha.getIngresoMensual());
            ps.setInt(4, ha.getIdHabitanteActividad());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "DELETE FROM habitante_actividad WHERE id_habitante_actividad = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public HabitanteActividad buscarPorId(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT + "WHERE ha.id_habitante_actividad = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public List<HabitanteActividad> listar() throws SQLException {
        List<HabitanteActividad> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public List<HabitanteActividad> listarPorHabitante(int idHabitante) throws SQLException {
        List<HabitanteActividad> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT + "WHERE ha.id_habitante = ?")) {
            ps.setInt(1, idHabitante);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

// todas las actividades de una vivienda
    public List<HabitanteActividad> listarPorVivienda(int idVivienda) throws SQLException {
        List<HabitanteActividad> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(
                SELECT + "JOIN habitante h ON h.id_habitante = ha.id_habitante "
                       + "WHERE h.id_vivienda = ? ORDER BY a.nombre")) {
            ps.setInt(1, idVivienda);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    private HabitanteActividad mapear(ResultSet rs) throws SQLException {
        ActividadEconomica a = new ActividadEconomica(rs.getInt("id_actividad"), rs.getString("nombre"));
        return new HabitanteActividad(rs.getInt("id_habitante_actividad"), rs.getInt("id_habitante"),
                a, rs.getBigDecimal("ingreso_mensual"));
    }
}
