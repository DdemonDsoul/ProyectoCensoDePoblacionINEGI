package controlador;

import modelo.dao.TipoViviendaDAO;
import modelo.entidades.TipoVivienda;
import vista.PanelTiposVivienda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoViviendaController implements Recargable {
    private final PanelTiposVivienda vista;
    private final TipoViviendaDAO dao = new TipoViviendaDAO();
    private List<TipoVivienda> datos = new ArrayList<>();

    public TipoViviendaController(PanelTiposVivienda vista) {
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
            for (TipoVivienda t : datos) vista.getModelo().addRow(new Object[]{t.getIdTipoVivienda(), t.getNombre()});
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void guardar() {
        if (vista.getNombre().isEmpty()) { vista.error("El nombre es obligatorio."); return; }
        try {
            int i = vista.getFilaSeleccionada();
            if (i >= 0) {
                TipoVivienda t = datos.get(i);
                t.setNombre(vista.getNombre());
                dao.actualizar(t);
            } else {
                dao.insertar(new TipoVivienda(0, vista.getNombre()));
            }
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void eliminar() {
        int i = vista.getFilaSeleccionada();
        if (i < 0) { vista.error("Selecciona un tipo de vivienda."); return; }
        if (!vista.confirmar("¿Eliminar el tipo seleccionado?")) return;
        try {
            dao.eliminar(datos.get(i).getIdTipoVivienda());
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
