package vista;

import modelo.entidades.Habitante;
import modelo.entidades.Vivienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelHabitantes extends JPanel {

    private JComboBox<Vivienda> cbVivienda;
    private JComboBox<String> cbSexo;

    private JTextField txtNombre;
    private JTextField txtApPaterno;
    private JTextField txtApMaterno;
    private JTextField txtFechaNacimiento;
    private JTextField txtEstadoCivil;
    private JTextField txtEscolaridad;
    private JTextField txtEstatura;
    private JTextField txtBusqueda;

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnTodos;

    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelHabitantes() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // --- FORMULARIO ARRIBA ---
        // Usamos un GridLayout de 5 filas x 4 columnas (Etiqueta, Campo, Etiqueta, Campo)
        JPanel formulario = new JPanel(new GridLayout(5, 4, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos del Habitante"));

        cbVivienda = new JComboBox<>();
        txtNombre = new JTextField();
        txtApPaterno = new JTextField();
        txtApMaterno = new JTextField();
        
        cbSexo = new JComboBox<>();
        cbSexo.addItem("M");
        cbSexo.addItem("F");

        txtFechaNacimiento = new JTextField();
        txtEstadoCivil = new JTextField();
        txtEscolaridad = new JTextField();
        txtEstatura = new JTextField();

        // Fila 1
        formulario.add(new JLabel("Vivienda:"));
        formulario.add(cbVivienda);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);

        // Fila 2
        formulario.add(new JLabel("Apellido Paterno:"));
        formulario.add(txtApPaterno);
        formulario.add(new JLabel("Apellido Materno:"));
        formulario.add(txtApMaterno);

        // Fila 3
        formulario.add(new JLabel("Sexo:"));
        formulario.add(cbSexo);
        formulario.add(new JLabel("Fecha Nac. (AAAA-MM-DD):"));
        formulario.add(txtFechaNacimiento);

        // Fila 4
        formulario.add(new JLabel("Estado Civil:"));
        formulario.add(txtEstadoCivil);
        formulario.add(new JLabel("Escolaridad:"));
        formulario.add(txtEscolaridad);

        // Fila 5
        formulario.add(new JLabel("Estatura:"));
        formulario.add(txtEstatura);
        formulario.add(new JLabel("")); // Espacio vac�o para cuadrar
        formulario.add(new JLabel(""));

        // --- PANEL DE B�SQUEDA ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtBusqueda = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        btnTodos = new JButton("Todos");
        
        panelBusqueda.add(new JLabel("Buscar por nombre:"));
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnTodos);

        JPanel superior = new JPanel(new BorderLayout());
        superior.add(formulario, BorderLayout.CENTER);
        superior.add(panelBusqueda, BorderLayout.SOUTH);

        // --- BOTONES INFERIORES ---
        JPanel botones = new JPanel();
        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        botones.add(btnNuevo);
        botones.add(btnGuardar);
        botones.add(btnEliminar);

        // --- TABLA ---
        modelo = new DefaultTableModel(new String[]{
                "ID", "Nombre", "Sexo", "Edad", "Nacimiento", "Estado Civil", "Escolaridad", "Estatura", "Vivienda"
        }, 0){
            @Override
            public boolean isCellEditable(int row, int column){ return false; }
        };

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // --- ENSAMBLAR TODO ---
        add(superior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    // ==========================
    // M�TODOS PARA EL CONTROLADOR
    // ==========================
    public JButton getBtnNuevo() { return btnNuevo; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnTodos() { return btnTodos; }

    public JTable getTabla() { return tabla; }
    public DefaultTableModel getModelo() { return modelo; }
    public int getFilaSeleccionada() { return tabla.getSelectedRow(); }

    public void setViviendas(List<Vivienda> viviendas){
        cbVivienda.removeAllItems();
        for(Vivienda v : viviendas){ cbVivienda.addItem(v); }
    }

    public Vivienda getVivienda(){
        return (Vivienda) cbVivienda.getSelectedItem();
    }

    public String getNombre() { return txtNombre.getText().trim(); }
    public String getApPaterno() { return txtApPaterno.getText().trim(); }
    public String getApMaterno() { return txtApMaterno.getText().trim(); }
    public String getSexo() { return (String) cbSexo.getSelectedItem(); }
    public String getFecha() { return txtFechaNacimiento.getText().trim(); }
    public String getEstadoCivil() { return txtEstadoCivil.getText().trim(); }
    public String getEscolaridad() { return txtEscolaridad.getText().trim(); }
    public String getEstatura() { return txtEstatura.getText().trim(); }
    public String getTextoBusqueda() { return txtBusqueda.getText().trim(); }

    public void setDatos(Habitante h){
        txtNombre.setText(h.getNombre());
        txtApPaterno.setText(h.getApellidoPaterno());
        txtApMaterno.setText(h.getApellidoMaterno() != null ? h.getApellidoMaterno() : "");
        cbSexo.setSelectedItem(String.valueOf(h.getSexo()));
        txtEstadoCivil.setText(h.getEstadoCivil() != null ? h.getEstadoCivil() : "");
        txtEscolaridad.setText(h.getEscolaridad() != null ? h.getEscolaridad() : "");
        txtEstatura.setText(h.getEstatura() == null ? "" : h.getEstatura().toString());
        txtFechaNacimiento.setText(h.getFechaNacimiento() == null ? "" : h.getFechaNacimiento().toString());
    }

    public void limpiar(){
        txtNombre.setText(""); 
        txtApPaterno.setText(""); 
        txtApMaterno.setText("");
        txtFechaNacimiento.setText(""); 
        txtEstadoCivil.setText(""); 
        txtEscolaridad.setText(""); 
        txtEstatura.setText("");
        cbSexo.setSelectedIndex(0);
        cbVivienda.setSelectedIndex(-1);
        tabla.clearSelection();
        txtNombre.requestFocus();
    }

    public void limpiarTabla(){ modelo.setRowCount(0); }

    public void error(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmar(String mensaje){
        return JOptionPane.showConfirmDialog(this, mensaje, "Confirmaci�n", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
