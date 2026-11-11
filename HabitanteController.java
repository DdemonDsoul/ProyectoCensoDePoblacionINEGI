package controlador;

import modelo.dao.HabitanteDAO;
import modelo.dao.ViviendaDAO;
import modelo.entidades.Habitante;
import vista.PanelHabitantes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class HabitanteController implements Recargable {
    private final PanelHabitantes vista;
    private final HabitanteDAO dao = new HabitanteDAO();
    private final ViviendaDAO viviendaDAO = new ViviendaDAO();
    private List<Habitante> datos = new ArrayList<>();

    public HabitanteController(PanelHabitantes vista) {
        this.vista = vista;
        vista.getBtnNuevo().addActionListener(e -> vista.limpiar());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnTodos().addActionListener(e -> cargar());
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int i = vista.getFilaSeleccionada();
            if (i >= 0 && i < datos.size()) vista.setDatos(datos.get(i));
        });
    }

    @Override
    public void cargar() {
        try {
            vista.setViviendas(viviendaDAO.listar());
            mostrar(dao.listar());
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void buscar() {
        try {
            mostrar(dao.buscarPorNombre(vista.getTextoBusqueda()));
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void mostrar(List<Habitante> lista) {
        datos = lista;
        vista.limpiarTabla();
        for (Habitante h : datos) {
            vista.getModelo().addRow(new Object[]{h.getIdHabitante(), h.getNombreCompleto(), h.getSexo(), h.getEdad(),
                    h.getFechaNacimiento(), h.getEstadoCivil(), h.getEscolaridad(), h.getEstatura(), h.getIdVivienda()});
        }
    }

    private void guardar() {
        if (vista.getVivienda() == null) { vista.error("Selecciona la vivienda donde vive."); return; }
        try {
            int i = vista.getFilaSeleccionada();
            LocalDate fecha = vista.getFecha().isEmpty() ? null : LocalDate.parse(vista.getFecha());
            Double estatura = vista.getEstatura().isEmpty() ? null : Double.valueOf(vista.getEstatura());

            // PATRÓN BUILDER: obligatorios en el constructor, opcionales encadenados
            Habitante h = new Habitante.Builder(vista.getVivienda().getIdVivienda(),
                    vista.getNombre(), vista.getApPaterno())
                    .id(i >= 0 ? datos.get(i).getIdHabitante() : 0)
                    .apellidoMaterno(vista.getApMaterno())
                    .sexo(vista.getSexo())
                    .fechaNacimiento(fecha)
                    .estadoCivil(vista.getEstadoCivil())
                    .escolaridad(vista.getEscolaridad())
                    .estatura(estatura)
                    .build();
            if (i >= 0) dao.actualizar(h); else dao.insertar(h);
            vista.limpiar();
            cargar();
        } catch (DateTimeParseException ex) {
            vista.error("La fecha debe tener formato AAAA-MM-DD (ej. 1990-05-20).");
        } catch (NumberFormatException ex) {
            vista.error("La estatura debe ser un número (ej. 1.75).");
        } catch (IllegalStateException ex) {
            vista.error(ex.getMessage());
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }

    private void eliminar() {
        int i = vista.getFilaSeleccionada();
        if (i < 0) { vista.error("Selecciona un habitante."); return; }
        if (!vista.confirmar("¿Eliminar al habitante seleccionado?")) return;
        try {
            dao.eliminar(datos.get(i).getIdHabitante());
            vista.limpiar();
            cargar();
        } catch (SQLException ex) { vista.error(Mensajes.deSQL(ex)); }
    }
}
