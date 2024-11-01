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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableModel;
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

    private final List<String> susProyectos;
    private final boolean[] susProyectosFiltrados;
    private final String[] estados;
    private final boolean[] estadosFiltrados;
    private String campoOrdenar = "fecha_programada_termino";
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
