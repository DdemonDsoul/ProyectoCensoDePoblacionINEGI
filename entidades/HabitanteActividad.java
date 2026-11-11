package modelo.entidades;

import java.math.BigDecimal;

// habitante_actividad
public class HabitanteActividad {
    private int idHabitanteActividad;
    private int idHabitante;
    private ActividadEconomica actividad;
    private BigDecimal ingresoMensual;

    public HabitanteActividad() { }

    public HabitanteActividad(int idHabitanteActividad, int idHabitante,
                              ActividadEconomica actividad, BigDecimal ingresoMensual) {
        this.idHabitanteActividad = idHabitanteActividad;
        this.idHabitante = idHabitante;
        this.actividad = actividad;
        this.ingresoMensual = ingresoMensual;
    }

    public int getIdHabitanteActividad() { return idHabitanteActividad; }
    
    public void setIdHabitanteActividad(int id) { this.idHabitanteActividad = id; }
    
    public int getIdHabitante() { return idHabitante; }
    
    public void setIdHabitante(int idHabitante) { this.idHabitante = idHabitante; }
    
    public ActividadEconomica getActividad() { return actividad; }
    
    public void setActividad(ActividadEconomica actividad) { this.actividad = actividad; }
    
    public BigDecimal getIngresoMensual() { return ingresoMensual; }
    
    public void setIngresoMensual(BigDecimal ingresoMensual) { this.ingresoMensual = ingresoMensual; }
}
