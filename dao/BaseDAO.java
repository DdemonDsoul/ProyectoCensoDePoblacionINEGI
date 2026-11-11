package modelo.dao;

import modelo.conexion.ConexionDB;

import java.sql.Connection;
import java.sql.SQLException;

// es la base y donde todos tienen la conexion a singleton
public abstract class BaseDAO {
    protected Connection getConexion() throws SQLException {
        return ConexionDB.getInstancia().getConexion();
    }
}
