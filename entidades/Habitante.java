package modelo.entidades;

import java.time.LocalDate;
import java.time.Period;

// habitante (usa builder)
public class Habitante {
    private int idHabitante;
    private int idVivienda;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Character sexo;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private String estadoCivil;
    private String escolaridad;
    private Double estatura;

    private Habitante(Builder b) {
        this.idHabitante = b.idHabitante;
        this.idVivienda = b.idVivienda;
        this.nombre = b.nombre;
        this.apellidoPaterno = b.apellidoPaterno;
        this.apellidoMaterno = b.apellidoMaterno;
        this.sexo = b.sexo;
        this.fechaNacimiento = b.fechaNacimiento;
        this.edad = b.edad;
        this.estadoCivil = b.estadoCivil;
        this.escolaridad = b.escolaridad;
        this.estatura = b.estatura;
        // si no dan edad, con la fecha se puede sacar
        if (this.edad == null && this.fechaNacimiento != null) {
            this.edad = Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
        }
    }

    public int getIdHabitante() { return idHabitante; }
    public void setIdHabitante(int idHabitante) { this.idHabitante = idHabitante; }
    public int getIdVivienda() { return idVivienda; }
    public void setIdVivienda(int idVivienda) { this.idVivienda = idVivienda; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String a) { this.apellidoPaterno = a; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String a) { this.apellidoMaterno = a; }
    public Character getSexo() { return sexo; }
    public void setSexo(Character sexo) { this.sexo = sexo; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate f) { this.fechaNacimiento = f; }
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public String getEscolaridad() { return escolaridad; }
    public void setEscolaridad(String escolaridad) { this.escolaridad = escolaridad; }
    public Double getEstatura() { return estatura; }
    public void setEstatura(Double estatura) { this.estatura = estatura; }

    public String getNombreCompleto() {
        StringBuilder sb = new StringBuilder(nombre).append(' ').append(apellidoPaterno);

        if (apellidoMaterno != null && !apellidoMaterno.trim().isEmpty()) {
            sb.append(' ').append(apellidoMaterno);
        }

        return sb.toString();
    }

    @Override
    public String toString() { return getNombreCompleto(); }


    public static class Builder {
       // obligatorios
        private final int idVivienda;
        private final String nombre;
        private final String apellidoPaterno;
       // opcionales
        private int idHabitante;
        private String apellidoMaterno;
        private Character sexo;
        private Integer edad;
        private LocalDate fechaNacimiento;
        private String estadoCivil;
        private String escolaridad;
        private Double estatura;

        public Builder(int idVivienda, String nombre, String apellidoPaterno) {
            this.idVivienda = idVivienda;
            this.nombre = nombre;
            this.apellidoPaterno = apellidoPaterno;
        }

        public Builder id(int idHabitante) { this.idHabitante = idHabitante; return this; }
        public Builder apellidoMaterno(String a) { this.apellidoMaterno = a; return this; }
        public Builder sexo(Character s) { this.sexo = s; return this; }
        public Builder edad(Integer e) { this.edad = e; return this; }
        public Builder fechaNacimiento(LocalDate f) { this.fechaNacimiento = f; return this; }
        public Builder estadoCivil(String e) { this.estadoCivil = e; return this; }
        public Builder escolaridad(String e) { this.escolaridad = e; return this; }
        public Builder estatura(Double e) { this.estatura = e; return this; }

        public Habitante build() {
            if (idVivienda <= 0)
                throw new IllegalStateException("El habitante debe pertenecer a una vivienda");
            if (nombre == null || nombre.trim().isEmpty())
                throw new IllegalStateException("El nombre es obligatorio");
            if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty())
                throw new IllegalStateException("El apellido paterno es obligatorio");
            if (sexo != null && sexo != 'M' && sexo != 'F')
                throw new IllegalStateException("Sexo debe ser 'M' o 'F'");
            if (edad != null && (edad < 0 || edad > 130))
                throw new IllegalStateException("Edad inválida");
            if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now()))
                throw new IllegalStateException("La fecha de nacimiento no puede ser futura");
            return new Habitante(this);
        }
    }
}
