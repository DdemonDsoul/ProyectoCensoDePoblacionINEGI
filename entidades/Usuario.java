package modelo.entidades;

// users
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String password;
    private String nombreCompleto;

    public Usuario() { }

    public Usuario(int idUsuario, String usuario, String password, String nombreCompleto) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdUsuario() { return idUsuario; }
    
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    
    public String getUsuario() { return usuario; }
    
    public void setUsuario(String usuario) { this.usuario = usuario; }
    
    public String getPassword() { return password; }
    
    public void setPassword(String password) { this.password = password; }
    
    public String getNombreCompleto() { return nombreCompleto; }
    
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    @Override
    public String toString() { return nombreCompleto; }
}
