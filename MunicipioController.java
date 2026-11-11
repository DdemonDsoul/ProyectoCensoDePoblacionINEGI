package controlador;

import modelo.dao.MunicipioDAO;
import modelo.entidades.Municipio;
import vista.PanelMunicipios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Conecta PanelMunicipios (vista) con MunicipioDAO (modelo). */
public class MunicipioController implements Recargable {
    private final PanelMunicipios vista;
    private final MunicipioDAO dao = new MunicipioDAO();
    private List<Municipio> datos = new ArrayList<>();

    public MunicipioController(PanelMunicipios vista) {
        this.vista = vista;
        vista.getBtnNuevo().addActionListener(e -> vista.limpiar());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int i = vista.getFilaSeleccionada();
            if (i >= 0 && i < datos.size()) vista.setDatos(datos.get(i).getClaveMunicipio(), datos.get(i).getNombre());
        });
    }

    @Override
    public void cargar() {
        try {
            datos = dao.listar();
            vista.limpiarTabla();
            for (Municipio m : datos) vista.getModelo().addRow(new Object[]{m.getIdMunicipio(), m.getClaveMunicipio(), m.getNombre()});
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void guardar() {
        if (vista.getClave().isEmpty() || vista.getNombre().isEmpty()) { vista.error("Clave y nombre son obligatorios."); return; }
        try {
            int i = vista.getFilaSeleccionada();
            if (i >= 0) {
                Municipio m = datos.get(i);
                m.setClaveMunicipio(vista.getClave());
                m.setNombre(vista.getNombre());
                dao.actualizar(m);
            } else {
                dao.insertar(new Municipio(0, vista.getClave(), vista.getNombre()));
            }
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void eliminar() {
        int i = vista.getFilaSeleccionada();
        if (i < 0) { vista.error("Selecciona un municipio."); return; }
        if (!vista.confirmar("¿Eliminar el municipio seleccionado?")) return;
        try {
            dao.eliminar(datos.get(i).getIdMunicipio());
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
