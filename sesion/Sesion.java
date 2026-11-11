package modelo.sesion;

import modelo.conexion.ConexionDB;
import modelo.entidades.Usuario;

public final class Sesion {

    private static Sesion instancia;
    private Usuario usuarioActual;

    private Sesion() { }

    public static synchronized Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public synchronized void iniciar(Usuario usuario) { this.usuarioActual = usuario; }
    public synchronized Usuario getUsuarioActual() { return usuarioActual; }
    public synchronized boolean estaActiva() { return usuarioActual != null; }

    public synchronized void cerrarSesion() { this.usuarioActual = null; }

    public static synchronized void cerrarSistema() {
        if (instancia != null) {
            instancia.usuarioActual = null;
            instancia = null;
        }
        ConexionDB.destruir();
    }
}
