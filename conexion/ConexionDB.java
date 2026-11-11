package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// el conn es un singleton q trampota

public final class ConexionDB {

// CAMBIENLAAAAA
	private static final String URL =
	        "jdbc:mysql://localhost:3306/censo_inegi?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Monterrey";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "";

    private static ConexionDB instancia;
    private Connection conexion;

    private ConexionDB() { }

    public static synchronized ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public synchronized Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        }
        return conexion;
    }

    public synchronized void cerrar() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            } finally {
                conexion = null;
            }
        }
    }

    public static synchronized void destruir() {
        if (instancia != null) {
            instancia.cerrar();
            instancia = null;
        }
    }
}