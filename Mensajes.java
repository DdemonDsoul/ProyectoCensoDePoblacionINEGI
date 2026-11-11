package controlador;

import java.sql.SQLException;

/** Convierte errores técnicos de MySQL en mensajes entendibles. */
final class Mensajes {
    private Mensajes() { }

    static String deSQL(SQLException e) {
        if (e.getErrorCode() == 1451) return "No se puede eliminar: hay registros relacionados con este dato.";
        if (e.getErrorCode() == 1062) return "Ya existe un registro con ese valor (clave duplicada).";
        return "Error de base de datos: " + e.getMessage();
    }
}
