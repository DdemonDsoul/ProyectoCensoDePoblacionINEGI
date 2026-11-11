package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public abstract class BaseCrudView extends JPanel {

    protected JTable tabla;

    public void mostrarMensaje(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje
        );
    }

    public boolean confirmarEliminacion() {

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el registro?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        return opcion == JOptionPane.YES_OPTION;
    }

    public DefaultTableModel getModeloTabla() {

        return (DefaultTableModel)
                tabla.getModel();
    }

    public void limpiarTabla() {

        getModeloTabla().setRowCount(0);
    }

    public void agregarFila(Object[] fila) {

        getModeloTabla().addRow(fila);
    }
}