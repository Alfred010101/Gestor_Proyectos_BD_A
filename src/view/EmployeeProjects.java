package view;

import controller.CollaboratorController;
import controller.ProjectController;
import controller.TaskController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import model.Staff;
import model.Task;

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

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelInicio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = GenerateComponents.crearBotonHerramineta("Agregar", "agregar_Res2.png");
        JButton btnVer = GenerateComponents.crearBotonHerramineta("Detalles", "expediente_Res2.png");
        JButton btnModificar = GenerateComponents.crearBotonHerramineta("Actualizar", "editar_Res2.png");
        JButton btnEliminar = GenerateComponents.crearBotonHerramineta("Eliminar", "borrar_Res2.png");

        panelInicio.add(btnAgregar);
        panelInicio.add(btnVer);
        panelInicio.add(btnModificar);
        panelInicio.add(btnEliminar);

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
}
