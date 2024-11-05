package view;

import controller.ProjectController;
import controller.TaskController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Task;
import utils.Var;

/**
 *
 * @author Alfred
 */
public class SeleccionarCampos
{

    public static JPopupMenu listaCampos(JScrollPane contenedorTabla, JButton btnRoles, JButton btnDepart, DefaultComboBoxModel campOrdenar)
    {
        String[] elementos = Var.PERSONAL_COLUMN_NAMES;

        // Crear un array para almacenar el estado de los checkboxes (seleccionados o no)
        boolean[] noDeseleccionables = new boolean[elementos.length];
        noDeseleccionables[0] = true;
        noDeseleccionables[1] = true;

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);

            // Establecer el estado del checkbox y deshabilitar si es no deselectable
            checkBox.setSelected(Var.columnasPerosonalSeleccionados[index]);
            if (noDeseleccionables[index])
            {
                checkBox.setEnabled(false);  // Deshabilitar la opción para que no se pueda cambiar
            }

            panel.add(checkBox, BorderLayout.WEST);

            return panel;
        });

        lista.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int index = lista.locationToIndex(e.getPoint());
                if (index != -1 && !noDeseleccionables[index])
                {  // Solo cambiar si no es deseleccionable
                    Var.columnasPerosonalSeleccionados[index] = !Var.columnasPerosonalSeleccionados[index];
                    btnDepart.setVisible(Var.columnasPerosonalSeleccionados[4]);
                    btnRoles.setVisible(Var.columnasPerosonalSeleccionados[5]);
                    if (!Var.columnasPerosonalSeleccionados[4])
                    {
                        for (int i = 0; i < Var.columnasDepartSeleccionados.length; i++)
                        {
                            Var.columnasDepartSeleccionados[i] = true;
                        }
                    }
                    if (!Var.columnasPerosonalSeleccionados[5])
                    {
                        for (int i = 0; i < Var.columnasRolesSeleccionados.length; i++)
                        {
                            Var.columnasRolesSeleccionados[i] = true;
                        }
                    }
                    Var.ordenarPersonasPor.clear();
                    Var.ordenarPersonasPor.add(Var.CAMPOS_ORDENAR_PEROSNAL[0]);
                    Var.ordenarPersonasPor.add(Var.CAMPOS_ORDENAR_PEROSNAL[1]);
                    if (Var.columnasPerosonalSeleccionados[2])
                    {
                        Var.ordenarPersonasPor.add(Var.CAMPOS_ORDENAR_PEROSNAL[2]);
                    }
                    if (Var.columnasPerosonalSeleccionados[3])
                    {
                        Var.ordenarPersonasPor.add(Var.CAMPOS_ORDENAR_PEROSNAL[3]);
                    }
                    campOrdenar.removeAllElements();
                    for (String elemento : Var.ordenarPersonasPor)
                    {
                        campOrdenar.addElement(elemento);
                    }
//                    Var.opcOrdenadoPersonal = 0;
//                    campOrdenar = new JComboBox<>(Var.ordenarPersonasPor.toArray(String[]::new));
                    lista.repaint();

//                    boolean[] arr = new boolean[elementos.length];
//                    System.arraycopy(Var.columnasPerosonalSeleccionados, 0, arr, 0, arr.length);
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
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(140, 200));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(scrollPane, BorderLayout.CENTER);
        return popupMenu;
    }

    public static JPopupMenu listaRoles(JScrollPane contenedorTabla)
    {
        String[] elementos = Var.perosonalColumnRoles.toArray(String[]::new);

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);
            checkBox.setSelected(Var.columnasRolesSeleccionados[index]);
            panel.add(checkBox, BorderLayout.WEST);

            return panel;
        });

        lista.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int index = lista.locationToIndex(e.getPoint());
                if (index != -1)
                {
                    Var.columnasRolesSeleccionados[index] = !Var.columnasRolesSeleccionados[index];
                    lista.repaint();

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
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(200, 160));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(scrollPane, BorderLayout.CENTER);
        return popupMenu;
    }

    public static JPopupMenu fitrarTareas(int idEmpleado, List<String> proyectosLista, boolean[] proyectosSeleccionados, String[] estadosLista, boolean[] estadosSeleccionados, boolean sonProyectos, String ordenarPor, String forma, int width, int height, DefaultTableModel model)
    {
        String[] elements;
        if (sonProyectos)
        {
            elements = proyectosLista.toArray(String[]::new);
        } else
        {
            elements = estadosLista;
        }

        JList<String> lista = new JList<>(elements);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);
            if (sonProyectos)
            {
                checkBox.setSelected(proyectosSeleccionados[index]);
            } else
            {
                checkBox.setSelected(estadosSeleccionados[index]);
            }
            panel.add(checkBox, BorderLayout.WEST);
            return panel;
        });

        lista.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int index = lista.locationToIndex(e.getPoint());
                if (index != -1)
                {
                    if (sonProyectos)
                    {
                        proyectosSeleccionados[index] = !proyectosSeleccionados[index];

                    } else
                    {
                        estadosSeleccionados[index] = !estadosSeleccionados[index];
                    }
                    lista.repaint();
                    filtrarTareas(idEmpleado, proyectosLista, proyectosSeleccionados, estadosLista, estadosSeleccionados, ordenarPor, forma, model);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(width, height));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(scrollPane, BorderLayout.CENTER);
        return popupMenu;
    }

    public static void filtrarTareas(int idEmpleado, List<String> proyectosLista, boolean[] proyectosSeleccionados, String[] estadosLista, boolean[] estadosSeleccionados, String ordenarPor, String forma, DefaultTableModel model)
    {

        Set<String> proyectos = new HashSet<>();
        for (int i = 0; i < proyectosSeleccionados.length; i++)
        {
            if (proyectosSeleccionados[i])
            {
                proyectos.add(ProjectController.obtenerCampo("pk_id", "nombre", proyectosLista.get(i)));
            }
        }

        Set<String> estados = new HashSet<>();
        for (int i = 0; i < estadosSeleccionados.length; i++)
        {
            if (estadosSeleccionados[i])
            {
                estados.add(String.valueOf(i));
            }
        }

        List<Task> tareas = TaskController.getSusTareasFiltradas(idEmpleado, proyectos, estados, ordenarPor, forma);
        model.setRowCount(0);
        for (Task tarea : tareas)
        {
            model.addRow(new Object[]
            {
                tarea.getProject(),
                tarea.getTitulo(),
                tarea.getState(),
                tarea.getStartDate(),
                tarea.getEndDate(),
                tarea.getExpectedDate()
            });
        }
    }

    public static JPopupMenu listaDepartamentos(JScrollPane contenedorTabla)
    {
        String[] elementos = Var.perosonalColumnDeparts.toArray(String[]::new);

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);
            checkBox.setSelected(Var.columnasDepartSeleccionados[index]);
            panel.add(checkBox, BorderLayout.WEST);

            return panel;
        });

        lista.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int index = lista.locationToIndex(e.getPoint());
                if (index != -1)
                {
                    Var.columnasDepartSeleccionados[index] = !Var.columnasDepartSeleccionados[index];
                    lista.repaint();

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
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(140, 130));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(scrollPane, BorderLayout.CENTER);
        return popupMenu;
    }
}
