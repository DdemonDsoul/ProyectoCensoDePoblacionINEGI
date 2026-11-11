package modelo.entidades;

// municipios
public class Municipio {
    private int idMunicipio;
    private String claveMunicipio;
    private String nombre;

    public Municipio() { }

    public Municipio(int idMunicipio, String claveMunicipio, String nombre) {
        this.idMunicipio = idMunicipio;
        this.claveMunicipio = claveMunicipio;
        this.nombre = nombre;
    }

    public int getIdMunicipio() { return idMunicipio; }
    
    public void setIdMunicipio(int idMunicipio) { this.idMunicipio = idMunicipio; }
    
    public String getClaveMunicipio() { return claveMunicipio; }
    
    public void setClaveMunicipio(String claveMunicipio) { this.claveMunicipio = claveMunicipio; }
    
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }

    // puede mostrar en combobox asi bien directo
    @Override
    public String toString() { return nombre; }
}
