package controlador;

import modelo.dao.HabitanteActividadDAO;
import modelo.dao.HabitanteDAO;
import modelo.dao.ViviendaDAO;
import modelo.entidades.Vivienda;
import vista.PanelDetalleVivienda;

import java.sql.SQLException;
import java.util.Collections;

public class DetalleViviendaController implements Recargable {
    private final PanelDetalleVivienda vista;
    private final ViviendaDAO viviendaDAO = new ViviendaDAO();
    private final HabitanteDAO habitanteDAO = new HabitanteDAO();
    private final HabitanteActividadDAO actividadDAO = new HabitanteActividadDAO();

    public DetalleViviendaController(PanelDetalleVivienda vista) {
        this.vista = vista;
        vista.getCmbVivienda().addActionListener(e -> mostrarDetalle());
    }

    @Override
    public void cargar() {
        try {
            vista.setViviendas(viviendaDAO.listar());
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(vista, Mensajes.deSQL(ex), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDetalle() {
        Vivienda v = vista.getVivienda();
        if (v == null) { vista.mostrar(Collections.emptyList(), 0, Collections.emptyList()); return; }
        try {
            vista.mostrar(habitanteDAO.listarPorVivienda(v.getIdVivienda()),
                    habitanteDAO.contarPorVivienda(v.getIdVivienda()),
                    actividadDAO.listarPorVivienda(v.getIdVivienda()));
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(vista, Mensajes.deSQL(ex), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
