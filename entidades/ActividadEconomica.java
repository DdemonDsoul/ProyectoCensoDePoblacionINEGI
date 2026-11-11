package modelo.entidades;

// actividad_economica
public class ActividadEconomica {
    private int idActividad;
    private String nombre;

    public ActividadEconomica() { }

    public ActividadEconomica(int idActividad, String nombre) {
        this.idActividad = idActividad;
        this.nombre = nombre;
    }

    public int getIdActividad() { return idActividad; }
    
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }
    
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() { return nombre; }
}
