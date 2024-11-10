package view;

import com.toedter.components.JTitlePanel;
import controller.CollaboratorController;
import controller.ProjectController;
import controller.TaskController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import model.Staff;
import model.Task;
import view.forms.VtnNuevoProyecto;
/**
 * Panel principal Seleccion de proyeto y Creacion de nuevo proyecto
 *
 * @author Alfred
 */
public class EmployeeProjects extends CardJPanel
{

    private final int idEmployee;
    private String proyectoSeleccionado = "";
    private final List<String> susProyectos;

    private JPanel panelInicio;
    private CardLayout cardLayoutInicio;
    private CardLayout cardLayoutFiltrar;

    //arreglo para guardar los encabezados de la tabla de tareas
    private final String[] NOMBRE_CAMPOS_TABLA_TAREAS =
    {
        "Responsable", "Titulo", "Estado", "Fecha de Inicio", "Fecha de Termino", "Fecha Marcada"
    };
    private final String[] NOMBRE_CAMPOS_TABLA_COLABORADORES =
    {
        "Nombre", "AP Paterno", "AP Materno", "Correo"
    };
    private JTable tablaTareas;
    private JTable tablaColaboradores;

    private String elementoSeleccionadoResponsable = "";
    private String elementoSeleccionadoTitulo = "";
    private String elementoSeleccionadoNombre = "";
    private String elementoSeleccionadoCorreo = "";

    public EmployeeProjects(int idEmployee)
    {
        this.idEmployee = idEmployee;
        susProyectos = ProjectController.getSusProyectos(idEmployee);
        if (!susProyectos.isEmpty())
        {
            proyectoSeleccionado = susProyectos.get(0);
        }
        initComponets();
    }

    private void initComponets()
    {
        initPanelSeleccion();
        initTablasPest();
    }

    private void initPanelSeleccion()
    {
        JPanel panelSeleccionProyecto = new JPanel(new BorderLayout());
        panelSeleccionProyecto.setBackground(Color.LIGHT_GRAY);
        JPanel panelContenedorNorth = new JPanel();
        panelContenedorNorth.setBackground(Color.decode("#CFD8DC"));
        JComboBox<String> comboBox = new JComboBox<>(susProyectos.toArray(String[]::new));
        comboBox.addActionListener((ActionEvent e) ->
        {
            String seleccion = (String) comboBox.getSelectedItem();
            if (proyectoSeleccionado.compareTo(seleccion) != 0)
            {
                proyectoSeleccionado = seleccion;
                establecerCambioProyecto();
            }
        });
        JButton btnNuevoProyecto = GenerateComponents.crearBotonHerramineta("Nuevo Proyecto", "add_proyecto_Res2.png");
        panelContenedorNorth.add(btnNuevoProyecto);
        panelContenedorNorth.add(new JLabel("Seleccione un Proyecto"));
        panelContenedorNorth.add(comboBox);

        btnNuevoProyecto.addActionListener((e) ->
        {
            btnNuevoProyectoAddActionListener();
        });

        JTabbedPane tabbedPane = new JTabbedPane();

        cardLayoutInicio = new CardLayout();
        panelInicio = new JPanel(cardLayoutInicio);

        JPanel panelTareas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregarTarea = GenerateComponents.crearBotonHerramineta("Agregar", "agregar_Res2.png");
        JButton btnVerTarea = GenerateComponents.crearBotonHerramineta("Detalles", "expediente_Res2.png");
        JButton btnModificarTarea = GenerateComponents.crearBotonHerramineta("Actualizar", "editar_Res2.png");
        JButton btnEliminarTarea = GenerateComponents.crearBotonHerramineta("Eliminar", "borrar_Res2.png");

        btnAgregarTarea.addActionListener((e) ->
        {
            btnAgregarTareaAddActionListener();
        });
        btnVerTarea.addActionListener((e) ->
        {
            btnVerTareaAddActionListener();
        });
        btnModificarTarea.addActionListener((e) ->
        {
            btnModificarTareaAddActionListener();
        });
        btnEliminarTarea.addActionListener((e) ->
        {
            btnEliminarTareaaddActionListener();
        });

        panelTareas.add(btnAgregarTarea);
        panelTareas.add(btnVerTarea);
        panelTareas.add(btnModificarTarea);
        panelTareas.add(btnEliminarTarea);

        JPanel panelColaboradores = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregarColaborador = GenerateComponents.crearBotonHerramineta("Agregar", "agregar_Res2.png");
        JButton btnVerColaborador = GenerateComponents.crearBotonHerramineta("Detalles", "expediente_Res2.png");
        JButton btnModificarColaborador = GenerateComponents.crearBotonHerramineta("Actualizar", "editar_Res2.png");
        JButton btnEliminarColaborador = GenerateComponents.crearBotonHerramineta("Eliminar2", "borrar_Res2.png");

        btnAgregarColaborador.addActionListener((e) ->
        {
            btnAgregarColaboradorAddActionListener();
        });
        btnVerColaborador.addActionListener((e) ->
        {
            btnVerColaboradorAddActionListener();
        });
        btnModificarColaborador.addActionListener((e) ->
        {
            btnModificarColaboradorAddActionListener();
        });
        btnEliminarColaborador.addActionListener((e) ->
        {
            btnEliminarColaboradorAddActionListener();
        });

        panelColaboradores.add(btnAgregarColaborador);
        panelColaboradores.add(btnVerColaborador);
        panelColaboradores.add(btnModificarColaborador);
        panelColaboradores.add(btnEliminarColaborador);

        panelInicio.add(panelTareas, "Tareas");
        panelInicio.add(panelColaboradores, "Colaboradores");

        JPanel panelFiltar = new JPanel();

        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnDetallesProyecto = GenerateComponents.crearBotonHerramineta("Detalles", "expediente_Res2.png");
        JButton btnModificarProyecto = GenerateComponents.crearBotonHerramineta("Actualizar", "editar_Res2.png");
        JButton btnEliminarProyecto = GenerateComponents.crearBotonHerramineta("Eliminar", "borrar_Res2.png");

        panelInfo.add(btnDetallesProyecto);
        panelInfo.add(btnModificarProyecto);
        panelInfo.add(btnEliminarProyecto);

        tabbedPane.addTab("Inicio", panelInicio);
        tabbedPane.addTab("Filtrar", panelFiltar);
        tabbedPane.addTab("Sobre Proyecto", panelInfo);

        panelSeleccionProyecto.add(panelContenedorNorth, BorderLayout.NORTH);
        panelSeleccionProyecto.add(tabbedPane, BorderLayout.CENTER);

        add(panelSeleccionProyecto, BorderLayout.NORTH);
    }

    private void initTablasPest()
    {
        initTablas();
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panelTareas = new JPanel(new BorderLayout());
        JScrollPane contenedorTareas = new JScrollPane(tablaTareas);
        contenedorTareas.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelTareas.add(contenedorTareas, BorderLayout.CENTER);

        JPanel panelColaboradores = new JPanel(new BorderLayout());
        JScrollPane contenedorColaboradores = new JScrollPane(tablaColaboradores);
        contenedorColaboradores.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelColaboradores.add(contenedorColaboradores, BorderLayout.CENTER);

        tabbedPane.addTab("Tareas", panelTareas);
        tabbedPane.addTab("Colaboradores", panelColaboradores);

        tabbedPane.addChangeListener((ChangeEvent e) ->
        {
            int index = tabbedPane.getSelectedIndex();
            switch (index)
            {
                case 0 ->
                    cardLayoutInicio.first(panelInicio);
                case 1 ->
                    cardLayoutInicio.last(panelInicio);
                default ->
                {
                }
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }

    @Override
    protected void reset()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void initTablas()
    {
        List<Task> tareas = TaskController.getTareasProyecto(idEmployee, proyectoSeleccionado);
        Object[][] data = new Object[tareas.size()][6];
        for (int i = 0; i < tareas.size(); i++)
        {
            data[i][0] = tareas.get(i).getResponsible();
            data[i][1] = tareas.get(i).getTitulo();
            data[i][2] = tareas.get(i).getState();
            data[i][3] = tareas.get(i).getStartDate();
            data[i][4] = tareas.get(i).getEndDate();
            data[i][5] = tareas.get(i).getExpectedDate();
        }

        tablaTareas = GenerarTablas.configTabla(data, this.NOMBRE_CAMPOS_TABLA_TAREAS);

        List<Staff> colaboradores = CollaboratorController.getColaboradores(idEmployee, proyectoSeleccionado);
        data = new Object[colaboradores.size()][4];
        for (int i = 0; i < colaboradores.size(); i++)
        {
            data[i][0] = colaboradores.get(i).getName();
            data[i][1] = colaboradores.get(i).getApPaterno();
            data[i][2] = colaboradores.get(i).getApMaterno();
            data[i][3] = colaboradores.get(i).getEmail();
        }

        tablaColaboradores = GenerarTablas.configTabla(data, this.NOMBRE_CAMPOS_TABLA_COLABORADORES);
        addSelection();
    }

    /**
     * Permite extraer datos de una fila en columnas especificas
     */
    private void addSelection()
    {
        tablaTareas.getSelectionModel().addListSelectionListener((ListSelectionEvent event) ->
        {
            if (!event.getValueIsAdjusting())
            {
                int filaSeleccionada = tablaTareas.getSelectedRow();
                if (filaSeleccionada != -1)
                {
                    int columnaNombre = getColumnIndexByName(tablaTareas, "Responsable");
                    if (columnaNombre != -1)
                    {
                        elementoSeleccionadoResponsable = (String) tablaTareas.getValueAt(filaSeleccionada, columnaNombre);
                    }
                    columnaNombre = getColumnIndexByName(tablaTareas, "Titulo");
                    if (columnaNombre != -1)
                    {
                        elementoSeleccionadoTitulo = (String) tablaTareas.getValueAt(filaSeleccionada, columnaNombre);
                    }
                }
            }
        });

        tablaColaboradores.getSelectionModel().addListSelectionListener((ListSelectionEvent event) ->
        {
            if (!event.getValueIsAdjusting())
            {
                int filaSeleccionada = tablaColaboradores.getSelectedRow();
                if (filaSeleccionada != -1)
                {
                    int columnaNombre = getColumnIndexByName(tablaColaboradores, "Nombre");
                    if (columnaNombre != -1)
                    {
                        elementoSeleccionadoNombre = (String) tablaColaboradores.getValueAt(filaSeleccionada, columnaNombre);
                    }
                    columnaNombre = getColumnIndexByName(tablaColaboradores, "Correo");
                    if (columnaNombre != -1)
                    {
                        elementoSeleccionadoCorreo = (String) tablaColaboradores.getValueAt(filaSeleccionada, columnaNombre);
                    }
                }
            }
        });
    }

    private void establecerCambioProyecto()
    {
        List<Task> tareas = TaskController.getTareasProyecto(idEmployee, proyectoSeleccionado);
        ((DefaultTableModel) tablaTareas.getModel()).setRowCount(0);
        for (Task tarea : tareas)
        {
            ((DefaultTableModel) tablaTareas.getModel()).addRow(new Object[]
            {
                tarea.getResponsible(),
                tarea.getTitulo(),
                tarea.getState(),
                tarea.getStartDate(),
                tarea.getEndDate(),
                tarea.getExpectedDate()
            });
        }

        List<Staff> colaboradores = CollaboratorController.getColaboradores(idEmployee, proyectoSeleccionado);
        ((DefaultTableModel) tablaColaboradores.getModel()).setRowCount(0);
        for (Staff colaborador : colaboradores)
        {
            ((DefaultTableModel) tablaColaboradores.getModel()).addRow(new Object[]
            {
                colaborador.getName(),
                colaborador.getApPaterno(),
                colaborador.getApMaterno(),
                colaborador.getEmail()
            });
        }
    }

    private void btnAgregarTareaAddActionListener()
    {
        System.out.println("Responsable : " + elementoSeleccionadoResponsable);
        System.out.println("Titulo : " + elementoSeleccionadoTitulo);
    }

    private void btnVerTareaAddActionListener()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void btnModificarTareaAddActionListener()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void btnEliminarTareaaddActionListener()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void btnAgregarColaboradorAddActionListener()
    {
        System.out.println("Nombre : " + elementoSeleccionadoNombre);
        System.out.println("Correo: " + elementoSeleccionadoCorreo);
    }

    private void btnVerColaboradorAddActionListener()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void btnModificarColaboradorAddActionListener()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void btnEliminarColaboradorAddActionListener()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void btnNuevoProyectoAddActionListener()
    {
        new VtnNuevoProyecto().setVisible(true);
    }

}
