package controlador;

import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;
import modelo.sesion.Sesion;
import vista.LoginView;

import java.sql.SQLException;

/** Valida usuario y contraseña con el modelo y abre el menú principal. */
public class LoginController {
    private final LoginView vista;
    private final UsuarioDAO dao = new UsuarioDAO();

    public LoginController(LoginView vista) {
        this.vista = vista;
        vista.getBtnEntrar().addActionListener(e -> entrar());
        vista.getBtnSalir().addActionListener(e -> salir());
        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) { salir(); }
        });
    }

    public void iniciar() { vista.setVisible(true); }

    private void entrar() {
        if (vista.getUsuario().isEmpty() || vista.getPassword().isEmpty()) {
            vista.error("Captura usuario y contraseña.");
            return;
        }
        try {
            Usuario u = dao.autenticar(vista.getUsuario(), vista.getPassword());
            if (u == null) {
                vista.error("Usuario o contraseña incorrectos.");
                vista.limpiarPassword();
                return;
            }
            Sesion.getInstancia().iniciar(u);          // se guarda en el Singleton de sesión
            vista.dispose();
            new MenuController().iniciar();
        } catch (SQLException ex) {
            vista.error("No se pudo conectar a la base de datos:\n" + ex.getMessage());
        }
    }

    private void salir() {
        Sesion.cerrarSistema();
        System.exit(0);
    }
}
