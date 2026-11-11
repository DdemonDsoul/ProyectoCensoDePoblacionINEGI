package controlador;

import modelo.dao.ActividadEconomicaDAO;
import modelo.entidades.ActividadEconomica;
import vista.PanelActividades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActividadController implements Recargable {
    private final PanelActividades vista;
    private final ActividadEconomicaDAO dao = new ActividadEconomicaDAO();
    private List<ActividadEconomica> datos = new ArrayList<>();

    public ActividadController(PanelActividades vista) {
        this.vista = vista;
        vista.getBtnNuevo().addActionListener(e -> vista.limpiar());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int i = vista.getFilaSeleccionada();
            if (i >= 0 && i < datos.size()) vista.setNombre(datos.get(i).getNombre());
        });
    }

    @Override
    public void cargar() {
        try {
            datos = dao.listar();
            vista.limpiarTabla();
            for (ActividadEconomica a : datos) vista.getModelo().addRow(new Object[]{a.getIdActividad(), a.getNombre()});
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void guardar() {
        if (vista.getNombre().isEmpty()) { vista.error("El nombre es obligatorio."); return; }
        try {
            int i = vista.getFilaSeleccionada();
            if (i >= 0) {
                ActividadEconomica a = datos.get(i);
                a.setNombre(vista.getNombre());
                dao.actualizar(a);
            } else {
                dao.insertar(new ActividadEconomica(0, vista.getNombre()));
            }
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void eliminar() {
        int i = vista.getFilaSeleccionada();
        if (i < 0) { vista.error("Selecciona una actividad."); return; }
        if (!vista.confirmar("¿Eliminar la actividad seleccionada?")) return;
        try {
            dao.eliminar(datos.get(i).getIdActividad());
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
