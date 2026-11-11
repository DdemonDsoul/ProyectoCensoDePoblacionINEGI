package modelo.dao;

import java.sql.SQLException;
import java.util.List;

// crud para todos
public interface CrudDAO<T> {
    boolean insertar(T objeto) throws SQLException;
    boolean actualizar(T objeto) throws SQLException;
    boolean eliminar(int id) throws SQLException;
    T buscarPorId(int id) throws SQLException;
    List<T> listar() throws SQLException;
}
