package modelo.entidades;

// localidades
public class Localidad {
    public static final String AMBITO_URBANO = "Urbano";
    public static final String AMBITO_RURAL = "Rural";

    private int idLocalidad;
    private Municipio municipio;
    private String claveLocalidad;
    private String nombre;
    private String ambito;

    public Localidad() { }

    public Localidad(int idLocalidad, Municipio municipio, String claveLocalidad,
                     String nombre, String ambito) {
        this.idLocalidad = idLocalidad;
        this.municipio = municipio;
        this.claveLocalidad = claveLocalidad;
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public int getIdLocalidad() { return idLocalidad; }
    
    public void setIdLocalidad(int idLocalidad) { this.idLocalidad = idLocalidad; }
    
    public Municipio getMunicipio() { return municipio; }
    
    public void setMunicipio(Municipio municipio) { this.municipio = municipio; }
    
    public String getClaveLocalidad() { return claveLocalidad; }
    
    public void setClaveLocalidad(String claveLocalidad) { this.claveLocalidad = claveLocalidad; }
    
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getAmbito() { return ambito; }
    
    public void setAmbito(String ambito) { this.ambito = ambito; }

    @Override
    public String toString() { return nombre; }
}
