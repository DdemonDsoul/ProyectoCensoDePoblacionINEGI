package controlador;

import modelo.dao.LocalidadDAO;
import modelo.dao.TipoViviendaDAO;
import modelo.dao.ViviendaDAO;
import modelo.entidades.Vivienda;
import vista.PanelViviendas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViviendaController implements Recargable {
    private final PanelViviendas vista;
    private final ViviendaDAO dao = new ViviendaDAO();
    private final LocalidadDAO localidadDAO = new LocalidadDAO();
    private final TipoViviendaDAO tipoDAO = new TipoViviendaDAO();
    private List<Vivienda> datos = new ArrayList<>();

    public ViviendaController(PanelViviendas vista) {
        this.vista = vista;
        vista.getBtnNuevo().addActionListener(e -> vista.limpiar());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int i = vista.getFilaSeleccionada();
            if (i >= 0 && i < datos.size()) vista.setDatos(datos.get(i));
        });
    }

    @Override
    public void cargar() {
        try {
            vista.setLocalidades(localidadDAO.listar());
            vista.setTipos(tipoDAO.listar());
            datos = dao.listar();
            vista.limpiarTabla();
            for (Vivienda v : datos) {
                String servicios = (v.isAgua() ? "Agua " : "") + (v.isLuz() ? "Luz " : "")
                        + (v.isDrenaje() ? "Drenaje " : "") + (v.isInternet() ? "Internet" : "");
                vista.getModelo().addRow(new Object[]{v.getIdVivienda(), v.getLocalidad().getNombre(),
                        v.getLocalidad().getMunicipio().getNombre(), v.getTipoVivienda().getNombre(),
                        v.getDireccion(), v.getNumHabitaciones(), servicios.trim()});
            }
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void guardar() {
        if (vista.getLocalidad() == null || vista.getTipo() == null) {
            vista.error("Selecciona localidad y tipo de vivienda."); return;
        }
        try {
            int i = vista.getFilaSeleccionada();
            // PATRÓN BUILDER: se arma la vivienda paso a paso; build() valida los obligatorios
            Vivienda v = new Vivienda.Builder(vista.getLocalidad(), vista.getTipo(), vista.getCalle())
                    .id(i >= 0 ? datos.get(i).getIdVivienda() : 0)
                    .numero(vista.getNumero())
                    .colonia(vista.getColonia())
                    .numHabitaciones(vista.getHabitaciones())
                    .agua(vista.isAgua()).luz(vista.isLuz())
                    .drenaje(vista.isDrenaje()).internet(vista.isInternet())
                    .build();
            if (i >= 0) dao.actualizar(v); else dao.insertar(v);
            vista.limpiar();
            cargar();
        } catch (IllegalStateException ex) {
            vista.error(ex.getMessage());
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void eliminar() {
        int i = vista.getFilaSeleccionada();
        if (i < 0) { vista.error("Selecciona una vivienda."); return; }
        if (!vista.confirmar("¿Eliminar la vivienda seleccionada?")) return;
        try {
            dao.eliminar(datos.get(i).getIdVivienda());
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
