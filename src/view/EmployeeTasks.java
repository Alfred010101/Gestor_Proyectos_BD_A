package view;

import controller.CollaboratorController;
import controller.TaskController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import model.Task;
import view.forms.VtnModificarPersonalAdmin;
import view.forms.VtnNuevaTarea;

/**
 *
 * @author Alfred
 */
public class EmployeeTasks extends CardJPanel
{

    //Mantiene el id del usuario logeado
    private final int idEmployee;
    //arreglo para guardar los encabezados de la tabla de tareas
    private final String[] NOMBRE_CAMPOS_TABLA =
    {
        "Proyecto", "Titulo", "Estado", "Fecha de Inicio", "Fecha de Termino", "Fecha Marcada"
    };
    //Valores usados para implementar una consulta
    //Mantiene el dato de la columna Proyecto de la fila seleccionada
    private String elementoSeleccionadoProyecto = "";
    //Mantiene el dato de la columna Titulo de la fila seleccionada
    private String elementoSeleccionadoTitulo = "";

    //Mantiene una lista de los proyectos en los que colabora
    private final List<String> susProyectos;
    /**
     * Mantiene una arreglo de banderas que indican los proyectos a mostrar en
     * el filtrado de proyectos, marca como "true" los que estan activos en la
     * lista desplegable, sirve como bandera para generar las consultas.
     */
    private final boolean[] susProyectosFiltrados;
    //Mantiene una arreglo de los estados posibles de una tarea
    private final String[] estados;
    /**
     * Mantiene una arreglo de banderas que indican los estados de tareas a
     * mostrar en el filtrado de estados, marca como "true" los que estan
     * activos en la lista desplegable, sirve como bandera para generar las
     * consultas.
     */
    private final boolean[] estadosFiltrados;
    //Mantiene el nombre del campo por el cual seran ordenados los datos
    private String campoOrdenar = "fecha_programada_termino";
    //Mantiene la forma de ordenar los datos en la consulta
    private String formaOrdenar = "ASC";

    public EmployeeTasks(int idEmployee)
    {
        this.idEmployee = idEmployee;

        estados = new String[]
        {
            "Pendiente",
            "En Progreso",
            "Detenida",
            "En Revicion",
            "Completada",
            "Atrasada"
        };
        estadosFiltrados = new boolean[]
        {
            true, true, true, true, true, true
        };
        susProyectos = CollaboratorController.getSusProyectos(idEmployee);
        susProyectosFiltrados = new boolean[susProyectos.size()];
        Arrays.fill(susProyectosFiltrados, true);

        initComponets();
    }

    private void initComponets()
    {
        initPanelCenter();
        initPanelNorth();
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

        btnProyectos.addActionListener((e) ->
        {
            JPopupMenu popupProyectos = SeleccionarCampos.fitrarTareas(idEmployee, susProyectos, susProyectosFiltrados, estados, estadosFiltrados, true, campoOrdenar, formaOrdenar, 200, 160, (DefaultTableModel) tabla.getModel());
            popupProyectos.show(btnProyectos, 0, btnProyectos.getHeight());
        });

        btnEstados.addActionListener((e) ->
        {
            JPopupMenu popupEstados = SeleccionarCampos.fitrarTareas(idEmployee, susProyectos, susProyectosFiltrados, estados, estadosFiltrados, false, campoOrdenar, formaOrdenar, 120, 150, (DefaultTableModel) tabla.getModel());
            popupEstados.show(btnEstados, 0, btnEstados.getHeight());

        });

        JComboBox<String> cbxOrdenar = new JComboBox<>(new String[]
        {
            "Fecha Marcada", "Fecha de Inicio"
        });
        JComboBox<String> cbxFormasOrdenar = new JComboBox<>(new String[]
        {
            "Ascendente", "Descendente"
        });

        panelFiltrar.add(btnProyectos);
        panelFiltrar.add(btnEstados);
        panelFiltrar.add(new JLabel(new ImageIcon("src/assets/separador_Res.png")));
        panelFiltrar.add(new JLabel("Ordenar por campo"));
        panelFiltrar.add(cbxOrdenar);
        panelFiltrar.add(new JLabel("Forma"));
        panelFiltrar.add(cbxFormasOrdenar);

        cbxOrdenar.addActionListener((ActionEvent e) ->
        {
            campoOrdenar = switch (cbxOrdenar.getSelectedIndex())
            {
                case 1 ->
                    "fecha_inicio";
                default ->
                    "fecha_programada_termino";
            };
            SeleccionarCampos.filtrarTareas(idEmployee, susProyectos, susProyectosFiltrados, estados, estadosFiltrados, campoOrdenar, formaOrdenar, (DefaultTableModel) tabla.getModel());
        });
        cbxFormasOrdenar.addActionListener((ActionEvent e) ->
        {
            formaOrdenar = switch (cbxFormasOrdenar.getSelectedIndex())
            {
                case 1 ->
                    "DESC";
                default ->
                    "ASC";
            };
            SeleccionarCampos.filtrarTareas(idEmployee, susProyectos, susProyectosFiltrados, estados, estadosFiltrados, campoOrdenar, formaOrdenar, (DefaultTableModel) tabla.getModel());
        });

        tabbedPane.addTab("Inicio", panelInicio);
        tabbedPane.addTab("Filtrar & Ordenar", panelFiltrar);

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

    /**
     * Muestra la tabla por primera vez
     */
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

    /**
     * Permite extraer datos de una fila en columnas especificas
     */
    private void addSelection()
    {
        tabla.getSelectionModel().addListSelectionListener((ListSelectionEvent event) ->
        {
            if (!event.getValueIsAdjusting())
            {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1)
                {
                    int columnaNombre = getColumnIndexByName(tabla, "Proyecto");
                    if (columnaNombre != -1)
                    {
                        elementoSeleccionadoProyecto = (String) tabla.getValueAt(filaSeleccionada, columnaNombre);
                    }
                    columnaNombre = getColumnIndexByName(tabla, "Titulo");
                    if (columnaNombre != -1)
                    {
                        elementoSeleccionadoTitulo = (String) tabla.getValueAt(filaSeleccionada, columnaNombre);
                    }
                }
            }
        });
    }

    /**
     * Metodo ejecutado por el boton Agregar/Nuevo aqui va la accion a
     * implementar.
     */
    private void btnAgregarAddActionListener()
    {
        new VtnNuevaTarea().setVisible(true);
    }

    /**
     * Metodo ejecutado por el boton de Ver/Detalles aqui va la accion a
     * implementar.
     */
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

    /**
     * Metodo ejecutado por el boton de Modificar/Actualizar aqui va la accion a
     * implementar.
     */
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

    /**
     * Metodo ejecutado por el boton de Eliminar aqui va la accion a
     * implementar.
     */
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
