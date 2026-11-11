package controlador;

import modelo.dao.LocalidadDAO;
import modelo.dao.MunicipioDAO;
import modelo.entidades.Localidad;
import vista.PanelLocalidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalidadController implements Recargable {
    private final PanelLocalidades vista;
    private final LocalidadDAO dao = new LocalidadDAO();
    private final MunicipioDAO municipioDAO = new MunicipioDAO();
    private List<Localidad> datos = new ArrayList<>();

    public LocalidadController(PanelLocalidades vista) {
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
            vista.setMunicipios(municipioDAO.listar());
            datos = dao.listar();
            vista.limpiarTabla();
            for (Localidad l : datos) {
                vista.getModelo().addRow(new Object[]{l.getIdLocalidad(), l.getMunicipio().getNombre(),
                        l.getClaveLocalidad(), l.getNombre(), l.getAmbito()});
            }
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void guardar() {
        if (vista.getMunicipio() == null || vista.getNombre().isEmpty()) {
            vista.error("Municipio y nombre son obligatorios."); return;
        }
        try {
            int i = vista.getFilaSeleccionada();
            Localidad l = (i >= 0) ? datos.get(i) : new Localidad();
            l.setMunicipio(vista.getMunicipio());
            l.setClaveLocalidad(vista.getClave());
            l.setNombre(vista.getNombre());
            l.setAmbito(vista.getAmbito());
            if (i >= 0) dao.actualizar(l); else dao.insertar(l);
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void eliminar() {
        int i = vista.getFilaSeleccionada();
        if (i < 0) { vista.error("Selecciona una localidad."); return; }
        if (!vista.confirmar("¿Eliminar la localidad seleccionada?")) return;
        try {
            dao.eliminar(datos.get(i).getIdLocalidad());
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
