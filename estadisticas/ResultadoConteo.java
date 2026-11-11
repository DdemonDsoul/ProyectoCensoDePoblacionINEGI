package modelo.estadisticas;

// resultados
public class ResultadoConteo {
    private final String etiqueta;
    private final long total;

    public ResultadoConteo(String etiqueta, long total) {
        this.etiqueta = etiqueta;
        this.total = total;
    }

    public String getEtiqueta() { return etiqueta; }
    public long getTotal() { return total; }

    @Override
    public String toString() { return etiqueta + ": " + total; }
}
