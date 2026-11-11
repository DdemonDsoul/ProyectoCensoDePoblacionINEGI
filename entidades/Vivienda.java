package modelo.entidades;

// vivienda y tambien posee builder
public class Vivienda {
    private int idVivienda;
    private Localidad localidad;
    private TipoVivienda tipoVivienda;
    private String calle;
    private String numero;
    private String colonia;
    private Integer numHabitaciones;
    private boolean agua;
    private boolean luz;
    private boolean drenaje;
    private boolean internet;

    private Vivienda(Builder b) {
        this.idVivienda = b.idVivienda;
        this.localidad = b.localidad;
        this.tipoVivienda = b.tipoVivienda;
        this.calle = b.calle;
        this.numero = b.numero;
        this.colonia = b.colonia;
        this.numHabitaciones = b.numHabitaciones;
        this.agua = b.agua;
        this.luz = b.luz;
        this.drenaje = b.drenaje;
        this.internet = b.internet;
    }

    public int getIdVivienda() { return idVivienda; }
    public void setIdVivienda(int idVivienda) { this.idVivienda = idVivienda; }
    public Localidad getLocalidad() { return localidad; }
    public void setLocalidad(Localidad localidad) { this.localidad = localidad; }
    public TipoVivienda getTipoVivienda() { return tipoVivienda; }
    public void setTipoVivienda(TipoVivienda tipoVivienda) { this.tipoVivienda = tipoVivienda; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }
    public Integer getNumHabitaciones() { return numHabitaciones; }
    public void setNumHabitaciones(Integer numHabitaciones) { this.numHabitaciones = numHabitaciones; }
    public boolean isAgua() { return agua; }
    public void setAgua(boolean agua) { this.agua = agua; }
    public boolean isLuz() { return luz; }
    public void setLuz(boolean luz) { this.luz = luz; }
    public boolean isDrenaje() { return drenaje; }
    public void setDrenaje(boolean drenaje) { this.drenaje = drenaje; }
    public boolean isInternet() { return internet; }
    public void setInternet(boolean internet) { this.internet = internet; }

    public String getDireccion() {
        StringBuilder sb = new StringBuilder(calle);
        if (numero != null && !numero.trim().isEmpty()) {
            sb.append(" #").append(numero);
        }
        if (colonia != null && !colonia.trim().isEmpty()) {
            sb.append(", ").append(colonia);
        }
        return sb.toString();
    }

    @Override
    public String toString() { return "#" + idVivienda + " - " + getDireccion(); }

    
    public static class Builder {
       // obligatorios
        private final Localidad localidad;
        private final TipoVivienda tipoVivienda;
        private final String calle;
       // opcionales
        private int idVivienda;
        private String numero;
        private String colonia;
        private Integer numHabitaciones;
        private boolean agua, luz, drenaje, internet;

        public Builder(Localidad localidad, TipoVivienda tipoVivienda, String calle) {
            this.localidad = localidad;
            this.tipoVivienda = tipoVivienda;
            this.calle = calle;
        }

        public Builder id(int idVivienda) { this.idVivienda = idVivienda; return this; }
        public Builder numero(String numero) { this.numero = numero; return this; }
        public Builder colonia(String colonia) { this.colonia = colonia; return this; }
        public Builder numHabitaciones(Integer n) { this.numHabitaciones = n; return this; }
        public Builder agua(boolean v) { this.agua = v; return this; }
        public Builder luz(boolean v) { this.luz = v; return this; }
        public Builder drenaje(boolean v) { this.drenaje = v; return this; }
        public Builder internet(boolean v) { this.internet = v; return this; }

        public Vivienda build() {
            if (localidad == null)
                throw new IllegalStateException("La localidad es obligatoria");
            if (tipoVivienda == null)
                throw new IllegalStateException("El tipo de vivienda es obligatorio");
            if (calle == null || calle.trim().isEmpty())
                throw new IllegalStateException("La calle es obligatoria");
            if (numHabitaciones != null && numHabitaciones < 0)
                throw new IllegalStateException("Número de habitaciones inválido");
            return new Vivienda(this);
        }
    }
}
