package modelo.entidades;

// tipos de vivienda
public class TipoVivienda {
    private int idTipoVivienda;
    private String nombre;

    public TipoVivienda() { }

    public TipoVivienda(int idTipoVivienda, String nombre) {
        this.idTipoVivienda = idTipoVivienda;
        this.nombre = nombre;
    }

    public int getIdTipoVivienda() { return idTipoVivienda; }
    
    public void setIdTipoVivienda(int idTipoVivienda) { this.idTipoVivienda = idTipoVivienda; }
    
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() { return nombre; }
}
