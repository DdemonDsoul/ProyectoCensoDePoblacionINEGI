package controlador;

import modelo.dao.ActividadEconomicaDAO;
import modelo.dao.HabitanteActividadDAO;
import modelo.dao.HabitanteDAO;
import modelo.entidades.Habitante;
import modelo.entidades.HabitanteActividad;
import vista.PanelActividadesHabitante;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActividadHabitanteController implements Recargable {
    private final PanelActividadesHabitante vista;
    private final HabitanteDAO habitanteDAO = new HabitanteDAO();
    private final ActividadEconomicaDAO actividadDAO = new ActividadEconomicaDAO();
    private final HabitanteActividadDAO dao = new HabitanteActividadDAO();
    private List<HabitanteActividad> datos = new ArrayList<>();

    public ActividadHabitanteController(PanelActividadesHabitante vista) {
        this.vista = vista;
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getCmbHabitante().addActionListener(e -> cargarActividades());
        vista.getBtnAgregar().addActionListener(e -> agregar());
        vista.getBtnQuitar().addActionListener(e -> quitar());
    }

    @Override
    public void cargar() {
        try {
            vista.setActividades(actividadDAO.listar());
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void buscar() {
        try {
            vista.setHabitantes(habitanteDAO.buscarPorNombre(vista.getTextoBusqueda()));
            cargarActividades();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void cargarActividades() {
        vista.getModelo().setRowCount(0);
        Habitante h = vista.getHabitante();
        if (h == null) return;
        try {
            datos = dao.listarPorHabitante(h.getIdHabitante());
            for (HabitanteActividad a : datos) {
                vista.getModelo().addRow(new Object[]{a.getIdHabitanteActividad(), a.getActividad().getNombre(), a.getIngresoMensual()});
            }
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void agregar() {
        Habitante h = vista.getHabitante();
        if (h == null || vista.getActividad() == null) { vista.error("Busca y selecciona un habitante y una actividad."); return; }
        try {
            BigDecimal ingreso = new BigDecimal(vista.getIngreso());
            if (ingreso.signum() < 0) { vista.error("El ingreso no puede ser negativo."); return; }
            dao.insertar(new HabitanteActividad(0, h.getIdHabitante(), vista.getActividad(), ingreso));
            vista.limpiarIngreso();
            cargarActividades();
        } catch (NumberFormatException ex) {
            vista.error("El ingreso debe ser un número (ej. 8500.00).");
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void quitar() {
        int v = vista.getTabla().getSelectedRow();
        if (v < 0) { vista.error("Selecciona una actividad de la tabla."); return; }
        try {
            dao.eliminar(datos.get(vista.getTabla().convertRowIndexToModel(v)).getIdHabitanteActividad());
            cargarActividades();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
