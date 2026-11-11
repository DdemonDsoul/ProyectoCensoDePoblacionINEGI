package modelo.dao;

import modelo.entidades.Usuario;
import modelo.util.PasswordUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// datos y validacion
public class UsuarioDAO extends BaseDAO {

    // verificacion
    public Usuario autenticar(String usuario, String passwordPlano) throws SQLException {
        String sql = "SELECT id_usuario, usuario, password, nombre_completo "
                   + "FROM usuario WHERE usuario = ? AND password = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, PasswordUtil.hash(passwordPlano));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt("id_usuario"), rs.getString("usuario"),
                            rs.getString("password"), rs.getString("nombre_completo"));
                }
            }
        }
        return null;
    }

    // nuevo user
    public boolean registrar(Usuario u, String passwordPlano) throws SQLException {
        String sql = "INSERT INTO usuario (usuario, password, nombre_completo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getUsuario());
            ps.setString(2, PasswordUtil.hash(passwordPlano));
            ps.setString(3, u.getNombreCompleto());
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) u.setIdUsuario(keys.getInt(1));
                }
            }
            return ok;
        }
    }
}
