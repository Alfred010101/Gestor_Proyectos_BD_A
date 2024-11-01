package view;

import controller.CollaboratorController;
import controller.ProjectController;
import controller.TaskController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import model.Task;
import utils.Var;
import view.forms.VtnModificarPersonalAdmin;
import view.forms.VtnNuevoPersonal;

/**
 *
 * @author Alfred
 */
public class EmployeeTasks extends CardJPanel
{

    private final int idEmployee;

    private final String[] NOMBRE_CAMPOS_TABLA =
    {
        "Proyecto", "Titulo", "Estado", "Fecha de Inicio", "Fecha de Termino", "Fecha Marcada"
    };

    private String elementoSeleccionadoProyecto = "";
    private String elementoSeleccionadoTitulo = "";

    private Set<String> susProyectos;
    private boolean[] susProyectosFiltrados;    
    private final String[] estados;
    private final boolean[] estadosFiltrados;

    public EmployeeTasks(int idEmployee)
    {
        this.idEmployee = idEmployee;
        
        estados = new String[]
        {
            "Pendiente",
            "En Progreso",
            "Detenida",
            "En Revicion",
            "Completada"
        };
        estadosFiltrados = new boolean[]
        {
            true, true, true, true, true
        };
        susProyectos = CollaboratorController.getSusProyectos(idEmployee);
        susProyectosFiltrados = new boolean[susProyectos.size()];
        Arrays.fill(susProyectosFiltrados, true);

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

        /**
         * Inicio de la configuracion de la pestaña Inicio
         */
        JPanel panelInicio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInicio.setBackground(Color.decode("#ECEFF1"));
        JButton btnAgregar = GenerateComponents.crearBotonHerramineta("Agregar", "add_task_Res2.png");
        JButton btnVer = GenerateComponents.crearBotonHerramineta("Detalles", "expediente_Res2.png");
        JButton btnModificar = GenerateComponents.crearBotonHerramineta("Actualizar", "editar_Res2.png");
        JButton btnEliminar = GenerateComponents.crearBotonHerramineta("Eliminar", "borrar_Res2.png");

        panelInicio.add(btnAgregar);
        panelInicio.add(btnVer);
        panelInicio.add(btnModificar);
        panelInicio.add(btnEliminar);

        btnAgregar.addActionListener((e) ->
        {
            btnAgregarAddActionListener();
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
        /**
         * Fin de la configuracion de la pestaña Inicio
         */

        /**
         * Inicio de la configuracion de la pestaña Filtrar
         */
        JPanel panelFiltrar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnProyectos = GenerateComponents.crearBotonHerramineta("Proyectos", "filtrar_Res2.png");
        JButton btnEstados = GenerateComponents.crearBotonHerramineta("Estado", "filtrar_Res2.png");

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement(Var.CAMPOS_ORDENAR_PEROSNAL[0]);
        model.addElement(Var.CAMPOS_ORDENAR_PEROSNAL[1]);
        model.addElement(Var.CAMPOS_ORDENAR_PEROSNAL[2]);
        model.addElement(Var.CAMPOS_ORDENAR_PEROSNAL[3]);
        // Crear el JComboBox con las opciones
        JComboBox<String> cbxOrdenar = new JComboBox<>(model);
        JComboBox<String> cbxFormasOrdenar = new JComboBox<>(Var.CAMPOS_ORDENAR_PRIORIDAD_PEROSNAL);
        panelFiltrar.add(btnProyectos);
        panelFiltrar.add(btnEstados);

        /**
         *
         */
        btnProyectos.addActionListener((e) ->
        {
            JPopupMenu popupProyectos = SeleccionarCampos.fitrarTareasPorProyectos(susProyectos, susProyectosFiltrados);
            popupProyectos.show(btnProyectos, 0, btnProyectos.getHeight());
        });

        btnEstados.addActionListener((e) ->
        {
            JPopupMenu popupEstados = SeleccionarCampos.fitrarTareasPorProyectos(susProyectos, susProyectosFiltrados);
            popupEstados.show(btnEstados, 0, btnEstados.getHeight());
        });
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
                    int fila = nuevaTabla.getSelectedRow();
                    if (fila != -1)
                    {
//                        elementoSeleccionado = (int) nuevaTabla.getValueAt(fila, 0);
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
        tabbedPane.addTab("Filtrar", panelFiltrar);
        tabbedPane.addTab("Ordenar", panelOrdenar);
//        panelHerramientas.setBackground(Color.decode("#7f8c8d"));

        JPanel panelArriba = new JPanel();
        JPanel panelCentro = new JPanel();
        JPanel panelAbajo = new JPanel();
//        JPanel panelBuscar = new JPanel();
//        JButton btnEstados = GenerateComponents.crearBotonHerramineta("Roles", "agregar-tarea_Res.png");
//        panelBuscar.add(btnEstados);
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

        add(tabbedPane, BorderLayout.NORTH);

    }

    private void initPanelCenter()
    {
        initTabla();
        contenedorTabla = new JScrollPane(tabla);
        contenedorTabla.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(contenedorTabla, BorderLayout.CENTER);
    }

    @Override
    protected void reset()
    {

    }

    private void initTabla()
    {
        List<Task> tareas = TaskController.getMisTareas(idEmployee);
        Object[][] data = new Object[tareas.size()][6];
        for (int i = 0; i < tareas.size(); i++)
        {
            data[i][0] = tareas.get(i).getProject();
            data[i][1] = tareas.get(i).getTitulo();
            data[i][2] = tareas.get(i).getState();
            data[i][3] = tareas.get(i).getStartDate();
            data[i][4] = tareas.get(i).getEndDate();
            data[i][5] = tareas.get(i).getExpectedDate();
        }

        tabla = GenerarTablas.configTabla(data, this.NOMBRE_CAMPOS_TABLA);

        addSelection();
    }

    private void addSelection()
    {
        tabla.getSelectionModel().addListSelectionListener((ListSelectionEvent event) ->
        {
            if (!event.getValueIsAdjusting())
            {
                int fila = tabla.getSelectedRow();
                if (fila != -1)
                {
                    elementoSeleccionadoProyecto = (String) tabla.getValueAt(fila, 0);
                    elementoSeleccionadoTitulo = (String) tabla.getValueAt(fila, 1);
                }
            }
        });
    }

    private void btnAgregarAddActionListener()
    {
        new VtnNuevoPersonal().setVisible(true);
    }

    private void btnVerAddActionListener()
    {
        if (!elementoSeleccionadoProyecto.isEmpty() && !elementoSeleccionadoTitulo.isEmpty())
        {
            new VtnModificarPersonalAdmin().setVisible(true);
            System.out.println("Proyecto:" + elementoSeleccionadoProyecto + "\tTitulo:" + elementoSeleccionadoTitulo);
        } else
        {
            JOptionPane.showMessageDialog(this, "Seleccione la tarea de la cual desea tener mas información.", "Tarea no seleccionada.", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnModificarAddActionListener()
    {
        if (!elementoSeleccionadoProyecto.isEmpty() && !elementoSeleccionadoTitulo.isEmpty())
        {
            new VtnModificarPersonalAdmin().setVisible(true);
            System.out.println("Proyecto:" + elementoSeleccionadoProyecto + "\tTitulo:" + elementoSeleccionadoTitulo);
        } else
        {
            JOptionPane.showMessageDialog(this, "Seleccione la tarea que desea modificar.", "Tarea no seleccionada.", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnEliminarAddActionListener()
    {
        if (!elementoSeleccionadoProyecto.isEmpty() && !elementoSeleccionadoTitulo.isEmpty())
        {
            new VtnModificarPersonalAdmin().setVisible(true);
            System.out.println("Proyecto:" + elementoSeleccionadoProyecto + "\tTitulo:" + elementoSeleccionadoTitulo);
        } else
        {
            JOptionPane.showMessageDialog(this, "Seleccione la tarea que desea eliminar.", "Tarea no seleccionada.", JOptionPane.WARNING_MESSAGE);
        }
    }
}
