
package view;

import controller.ResourcesController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import utils.Var;
import view.forms.VtnModificarPersonalAdmin;
import view.forms.VtnNuevoPersonal;

/**
 *
 * @author Alfred
 */

public class AdminResources extends CardJPanel
{
    private final int idEmployee;
    private JTable tabla;
    private JScrollPane contenedorTabla;

    public AdminResources(int idEmployee)
    {
        this.idEmployee = idEmployee;
        for (int i = 0; i < Var.columnasDepartSeleccionados.length; i++)
        {
            Var.columnasDepartSeleccionados[i] = true;
        }
        for (int i = 0; i < Var.columnasRolesSeleccionados.length; i++)
        {
            Var.columnasRolesSeleccionados[i] = true;
        }
        initComponets();
    }

    private void initComponets()
    {
        initPanelNorth();
        initPanelCenter();
    }

    private void initPanelNorth()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(0, 0, 10, 0));

        JPanel panelInicio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInicio.setBackground(Color.decode("#ECEFF1"));
        JButton btnAgregar = GenerateComponents.crearBotonHerramineta("Agregar", "add_recurso_Res2.png");
        JButton btnBuscar = GenerateComponents.crearBotonHerramineta("Buscar", "buscar_Res2.png");
        JButton btnForm = GenerateComponents.crearBotonHerramineta("Ver Form", "coleccion_Res2.png");
        JButton btnVer = GenerateComponents.crearBotonHerramineta("Detalles", "expediente_Res2.png");
        JButton btnModificar = GenerateComponents.crearBotonHerramineta("Actualizar", "editar_Res2.png");
        JButton btnEliminar = GenerateComponents.crearBotonHerramineta("Eliminar", "borrar_Res2.png");

        panelInicio.add(btnAgregar);
        panelInicio.add(btnBuscar);
        panelInicio.add(btnForm);
        panelInicio.add(btnVer);
        panelInicio.add(btnModificar);
        panelInicio.add(btnEliminar);

        btnAgregar.addActionListener((e) ->
        {
            btnAgregarAddActionListener();
        });
        btnBuscar.addActionListener((e) ->
        {
            btnBuscarAddActionListener();
        });
        btnForm.addActionListener((e) ->
        {
            btnFormAddActionListener();
        });
        btnVer.addActionListener((e) ->
        {
            btnVerAddActionListener();
        });
        btnModificar.addActionListener((e) ->
        {
            btnModificarAddActionListener();
        });
        btnEliminar.addActionListener((e) ->
        {
            btnEliminarAddActionListener();
        });

//        JPanel panelFiltrar = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JButton btnCampos = GenerateComponents.crearBotonHerramineta("Ver Campos", "filtrar_Res2.png");
//        JButton btnRoles = GenerateComponents.crearBotonHerramineta("Roles", "filtrar_Res2.png");
//        JButton btnDepartamentos = GenerateComponents.crearBotonHerramineta("Departamentos", "filtrar_Res2.png");

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement(Var.CAMPOS_ORDENAR_PEROSNAL[0]);
        model.addElement(Var.CAMPOS_ORDENAR_PEROSNAL[1]);
        model.addElement(Var.CAMPOS_ORDENAR_PEROSNAL[2]);
        model.addElement(Var.CAMPOS_ORDENAR_PEROSNAL[3]);
        // Crear el JComboBox con las opciones
        JComboBox<String> cbxOrdenar = new JComboBox<>(model);
        JComboBox<String> cbxFormasOrdenar = new JComboBox<>(Var.CAMPOS_ORDENAR_PRIORIDAD_PEROSNAL);
//        panelFiltrar.add(btnCampos);
//        panelFiltrar.add(btnDepartamentos);
//        panelFiltrar.add(btnRoles);
//
//        /**
//         *
//         */
//        btnCampos.addActionListener((e) ->
//        {
//            JPopupMenu popupCampos = SeleccionarCampos.listaCampos(contenedorTabla, btnRoles, btnDepartamentos, model);
//            popupCampos.show(btnCampos, 0, btnCampos.getHeight());
//        });
//
//        btnRoles.addActionListener((e) ->
//        {
//            JPopupMenu popupRoles = SeleccionarCampos.listaRoles(contenedorTabla);
//            popupRoles.show(btnRoles, 0, btnRoles.getHeight());
//        });
//
//        btnDepartamentos.addActionListener((e) ->
//        {
//            JPopupMenu popupDepartamentos = SeleccionarCampos.listaDepartamentos(contenedorTabla);
//            popupDepartamentos.show(btnDepartamentos, 0, btnDepartamentos.getHeight());
//        });
        /**
         *
         */

        cbxOrdenar.addActionListener((ActionEvent e) ->
        {
            Var.opcOrdenadoPersonal = cbxOrdenar.getSelectedIndex();
            JTable nuevaTabla = GenerateTable.getTableEmpleadosFiltros();
            contenedorTabla.setViewportView(nuevaTabla);
            contenedorTabla.revalidate();
            contenedorTabla.repaint();
            nuevaTabla.getSelectionModel().addListSelectionListener((ListSelectionEvent event) ->
            {
                if (!event.getValueIsAdjusting())
                {
                    int filaSeleccionada = nuevaTabla.getSelectedRow();
                    if (filaSeleccionada != -1)
                    {
                        Var.filaSeleccionadaPersonal = filaSeleccionada;
                        Var.idSeleccionadaPersonal = (int) nuevaTabla.getValueAt(filaSeleccionada, 0);
                    }
                }
            });
        });
        cbxFormasOrdenar.addActionListener((ActionEvent e) ->
        {
            Var.opcOrdenadoForPersonal = (cbxFormasOrdenar.getSelectedIndex() == 1);
            JTable nuevaTabla = GenerateTable.getTableEmpleadosFiltros();
            contenedorTabla.setViewportView(nuevaTabla);
            contenedorTabla.revalidate();
            contenedorTabla.repaint();
            nuevaTabla.getSelectionModel().addListSelectionListener((ListSelectionEvent event) ->
            {
                if (!event.getValueIsAdjusting())
                {
                    int filaSeleccionada = nuevaTabla.getSelectedRow();
                    if (filaSeleccionada != -1)
                    {
                        Var.filaSeleccionadaPersonal = filaSeleccionada;
                        Var.idSeleccionadaPersonal = (int) nuevaTabla.getValueAt(filaSeleccionada, 0);
                    }
                }
            });
        });

        JPanel panelOrdenar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelOrdenar.add(new JLabel("Ordenar por campo"));
        panelOrdenar.add(cbxOrdenar);
        panelOrdenar.add(new JLabel("Forma"));
        panelOrdenar.add(cbxFormasOrdenar);

        JPanel panelBuscar = new JPanel(new BorderLayout());

        tabbedPane.addTab("Inicio", panelInicio);
//        tabbedPane.addTab("Buscar", panelBuscar);
//        tabbedPane.addTab("Filtrar", panelFiltrar);
        tabbedPane.addTab("Ordenar", panelOrdenar);
//        panelHerramientas.setBackground(Color.decode("#7f8c8d"));

        JPanel panelArriba = new JPanel();
        JPanel panelCentro = new JPanel();
        JPanel panelAbajo = new JPanel();
//        JPanel panelBuscar = new JPanel();
//        JButton btnRoles = GenerateComponents.crearBotonHerramineta("Roles", "agregar-tarea_Res.png");
//        panelBuscar.add(btnRoles);
        panelArriba.add(new JLabel("Id"));
        JTextField filtarId = new JTextField(10);
        panelArriba.add(filtarId);
        panelArriba.add(new JButton("Buscar"));

        panelCentro.add(new JLabel("Nombre"));
        JTextField filtarNombre = new JTextField(15);
        panelCentro.add(filtarNombre);
        panelCentro.add(new JLabel("Apellido Paterno"));
        JTextField filtarApPaterno = new JTextField(15);
        panelCentro.add(filtarApPaterno);
        panelCentro.add(new JLabel("Apellido Materno"));
        JTextField filtarApMaterno = new JTextField(15);
        panelCentro.add(filtarApMaterno);

        panelAbajo.add(new JButton("Buscar"));
        panelBuscar.add(panelArriba, BorderLayout.NORTH);
        panelBuscar.add(panelCentro, BorderLayout.CENTER);
        panelBuscar.add(panelAbajo, BorderLayout.SOUTH);

//        panelPricipal.add(tabbedPane, BorderLayout.NORTH);

    }

    private void initPanelCenter()
    {
        tabla = GenerateTable.getTableRecursos();
        tabla.getSelectionModel().addListSelectionListener((ListSelectionEvent event) ->
        {
            if (!event.getValueIsAdjusting())
            {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1)
                {
                    Var.filaSeleccionadaPersonal = filaSeleccionada;
                    Var.idSeleccionadaPersonal = (int) tabla.getValueAt(filaSeleccionada, 0);
                }
            }
        });
        contenedorTabla = new JScrollPane(tabla);
        contenedorTabla.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
//        panelPricipal.add(contenedorTabla, BorderLayout.CENTER);
    }

    @Override
    protected void reset()
    {

    }

    private void btnAgregarAddActionListener()
    {
        new VtnNuevoPersonal().setVisible(true);
    }

    private void btnBuscarAddActionListener()
    {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void btnFormAddActionListener()
    {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void btnVerAddActionListener()
    {
        new VtnModificarPersonalAdmin().setVisible(true);
    }

    private void btnModificarAddActionListener()
    {
        new VtnModificarPersonalAdmin().setVisible(true);
    }

    private void btnEliminarAddActionListener()
    {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
