package modelo.dao;

import modelo.entidades.Habitante;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

// crud habutantes y consultas
public class HabitanteDAO extends BaseDAO implements CrudDAO<Habitante> {

    private static final String SELECT =
            "SELECT id_habitante, id_vivienda, nombre, apellido_paterno, apellido_materno, sexo, edad, "
          + "fecha_nacimiento, estado_civil, escolaridad, estatura FROM habitante ";

    @Override
    public boolean insertar(Habitante h) throws SQLException {
        String sql = "INSERT INTO habitante (id_vivienda, nombre, apellido_paterno, apellido_materno, sexo, "
                   + "edad, fecha_nacimiento, estado_civil, escolaridad, estatura) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            llenar(ps, h);
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) h.setIdHabitante(keys.getInt(1));
                }
            }
            return ok;
        }
    }

    @Override
    public boolean actualizar(Habitante h) throws SQLException {
        String sql = "UPDATE habitante SET id_vivienda=?, nombre=?, apellido_paterno=?, apellido_materno=?, "
                   + "sexo=?, edad=?, fecha_nacimiento=?, estado_civil=?, escolaridad=?, estatura=? "
                   + "WHERE id_habitante=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            llenar(ps, h);
            ps.setInt(11, h.getIdHabitante());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement("DELETE FROM habitante WHERE id_habitante = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Habitante buscarPorId(int id) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT + "WHERE id_habitante = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public List<Habitante> listar() throws SQLException {
        List<Habitante> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(
                SELECT + "ORDER BY apellido_paterno, apellido_materno, nombre");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

// quienes viven en la vivienda
    public List<Habitante> listarPorVivienda(int idVivienda) throws SQLException {
        List<Habitante> lista = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(
                SELECT + "WHERE id_vivienda = ? ORDER BY edad DESC")) {
            ps.setInt(1, idVivienda);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

// cuantos
    public int contarPorVivienda(int idVivienda) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(
                "SELECT COUNT(*) FROM habitante WHERE id_vivienda = ?")) {
            ps.setInt(1, idVivienda);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

// busqueda
    public List<Habitante> buscarPorNombre(String texto) throws SQLException {
        List<Habitante> lista = new ArrayList<>();
        String like = "%" + texto + "%";
        try (PreparedStatement ps = getConexion().prepareStatement(
                SELECT + "WHERE nombre LIKE ? OR apellido_paterno LIKE ? OR apellido_materno LIKE ? "
                       + "ORDER BY apellido_paterno, nombre LIMIT 500")) {
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    private void llenar(PreparedStatement ps, Habitante h) throws SQLException {
        ps.setInt(1, h.getIdVivienda());
        ps.setString(2, h.getNombre());
        ps.setString(3, h.getApellidoPaterno());
        ps.setString(4, h.getApellidoMaterno());
        if (h.getSexo() == null) ps.setNull(5, Types.CHAR); else ps.setString(5, String.valueOf(h.getSexo()));
        if (h.getEdad() == null) ps.setNull(6, Types.INTEGER); else ps.setInt(6, h.getEdad());
        if (h.getFechaNacimiento() == null) ps.setNull(7, Types.DATE);
        else ps.setDate(7, Date.valueOf(h.getFechaNacimiento()));
        ps.setString(8, h.getEstadoCivil());
        ps.setString(9, h.getEscolaridad());
        if (h.getEstatura() == null) ps.setNull(10, Types.DECIMAL); else ps.setDouble(10, h.getEstatura());
    }

    private Habitante mapear(ResultSet rs) throws SQLException {
        String sexoStr = rs.getString("sexo");
        Character sexo = (sexoStr == null || sexoStr.isEmpty()) ? null : sexoStr.charAt(0);
        int edad = rs.getInt("edad");
        Integer edadObj = rs.wasNull() ? null : edad;
        Date fecha = rs.getDate("fecha_nacimiento");
        double est = rs.getDouble("estatura");
        Double estObj = rs.wasNull() ? null : est;

        return new Habitante.Builder(rs.getInt("id_vivienda"), rs.getString("nombre"),
                rs.getString("apellido_paterno"))
                .id(rs.getInt("id_habitante"))
                .apellidoMaterno(rs.getString("apellido_materno"))
                .sexo(sexo)
                .edad(edadObj)
                .fechaNacimiento(fecha == null ? null : fecha.toLocalDate())
                .estadoCivil(rs.getString("estado_civil"))
                .escolaridad(rs.getString("escolaridad"))
                .estatura(estObj)
                .build();
    }
}
