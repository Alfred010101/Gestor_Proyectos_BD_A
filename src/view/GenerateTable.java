
package view;

import controller.StaffController;
import controller.TaskController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Staff;
import model.Task;
import utils.Var;

/**
 *
 * @author Alfred
 */
public class GenerateTable
{
    public static JScrollPane getTableTareas(int idEmpleado)
    {
        List<Task> tareas = TaskController.getMisTareas(idEmpleado);
        Object[][] data = new Object[tareas.size()][7];
        for (int i = 0; i < tareas.size(); i++)
        {
//            data[i][0] = empleados.get(i).getResponsible();
            data[i][0] = tareas.get(i).getState();
            data[i][1] = tareas.get(i).getStartDate();
            data[i][2] = tareas.get(i).getEndDate();
            data[i][3] = tareas.get(i).getExpectedDate();
            data[i][4] = null;
            data[i][5] = null;
            data[i][6] = null;
        }
        String[] columnNames =
        {
            "Estado", "Fecha de Inicio", "Fecha de Termino", "Fecha Marcada", "Ver", "Editar", "Eliminar"
        };
//        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column >= 4; // Solo la columna de botones es editable
            }
        };

        // Crear la JTable con el modelo de datos personalizado
        JTable table = new JTable(tableModel);

//        // Estilizar la cabecera de la tabla
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(51, 153, 255));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        header.setPreferredSize(new Dimension(100, 40)); // Altura de la cabecera

        // Estilizar las filas
        table.setRowHeight(30); // Altura de las filas
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Estilizar la selección de filas
        table.setSelectionForeground(Color.WHITE);
//        table.setFillsViewportHeight(true);       

        // Crear un renderizador personalizado para la tabla
        TableCellRenderer cellRenderer = new TableCellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++)
        {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Agregar un detector de mouse para el efecto hover
        table.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                int row = table.rowAtPoint(e.getPoint());
                cellRenderer.setHoverRow(row);  // Establecer la fila que tiene hover
                table.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                cellRenderer.setHoverRow(-1); // Elimina hover
                table.repaint();
            }
        });

        // Agregar la tabla a un JScrollPane para hacerla desplazable
        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(Color.WHITE);
//        JViewport viewport = scrollPane.getViewport();
//        viewport.setBackground(Color.RED);

//        // Establecer el comportamiento de desplazamiento horizontal
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        
//        // Permitir que la tabla no ajuste automáticamente el ancho
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Ajustar el tamaño de las columnas manualmente
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        //table.getColumnModel().getColumn(4).setPreferredWidth(120);

        Dimension tableSize = table.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(tableSize.width, table.getRowHeight() * table.getRowCount()));
        return scrollPane;
    }
    
//    public static JScrollPane getTableEmpleados()
//    {
//        List<Staff> empleados = StaffController.getEmployees();
//        Object[][] data = new Object[empleados.size()][9];
//        for (int i = 0; i < empleados.size(); i++)
//        {
//            data[i][0] = empleados.get(i).getId();
//            data[i][1] = RoleController.getRole(empleados.get(i).getId());
//            data[i][2] = empleados.get(i).getName();
//            data[i][3] = DepartmentController.getDepartmentName(empleados.get(i).getDepartment());
//            data[i][4] = empleados.get(i).getEmail();
//            data[i][5] = empleados.get(i).getPhoneNumber();
//            data[i][6] = null;
//            data[i][7] = null;
//            data[i][8] = null;
//        }
//        String[] columnNames =
//        {
//            "ID", "Rol", "Nombre", "Ap Paterno", "Ap Materno", "Departamento", "E-mail", "Numero Tel"
//        };
//        
//        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
//        {
//            @Override
//            public boolean isCellEditable(int row, int column)
//            {
//                return false;
//            }
//        };
//
//        // Crear la JTable con el modelo de datos personalizado
//        JTable table = new JTable(tableModel);
//
//        // Estilizar la cabecera de la tabla
//        JTableHeader header = table.getTableHeader();
//        header.setBackground(new Color(51, 153, 255));
//        header.setForeground(Color.WHITE);
//        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        header.setPreferredSize(new Dimension(header.getWidth(), 30));
//
//        // Estilizar las filas
//        table.setRowHeight(30); // Altura de las filas
//        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        table.setSelectionForeground(Color.WHITE);
//        
//        // Crear un renderizador personalizado para la tabla
//        TableCellRenderer cellRenderer = new TableCellRenderer();
//        for (int i = 0; i < table.getColumnCount(); i++)
//        {
//            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
//        }
//        
//        table.addMouseMotionListener(new MouseAdapter()
//        {
//            @Override
//            public void mouseMoved(MouseEvent e)
//            {
//                int row = table.rowAtPoint(e.getPoint());
//                cellRenderer.setHoverRow(row);  // Establecer la fila que tiene hover
//                table.repaint();
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e)
//            {
//                cellRenderer.setHoverRow(-1); // Elimina hover
//                table.repaint();
//            }
//        });
//  
//        JScrollPane scrollPane = new JScrollPane(table);
////        scrollPane.setBackground(Color.RED);
//        return scrollPane;
//    }
    
    public static JTable getTable(Object[][] data, Object[] columnNames)
    {
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false; 
            }
        };
        
        JTable table = new JTable(tableModel);
        
        // Estilizar la cabecera de la tabla
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(51, 153, 255));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        // Estilizar las filas
        table.setRowHeight(30); // Altura de las filas
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionForeground(Color.WHITE);
        
        // Crear un renderizador personalizado para la tabla
        TableCellRenderer cellRenderer = new TableCellRenderer();
        table.setDefaultRenderer(Object.class, cellRenderer);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        table.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                cellRenderer.setHoverRow(row);
                table.repaint();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                cellRenderer.setHoverRow(-1);
                table.repaint();
            }
        });
        
//        JScrollPane scrollPane = new JScrollPane(table);
        return table;
    }
    
    public static JTable getTableEmpleados()
    {
        List<Staff> empleados = StaffController.getEmployees();
        Object[][] data = new Object[empleados.size()][8];
        for (int i = 0; i < empleados.size(); i++)
        {
            data[i][0] = empleados.get(i).getId();
            data[i][1] = StaffController.getEmployeeRole(empleados.get(i).getRole());
            data[i][2] = empleados.get(i).getName();
            data[i][3] = empleados.get(i).getApPaterno();
            data[i][4] = empleados.get(i).getApMaterno();
            data[i][5] = StaffController.getEmployeeDepartment(empleados.get(i).getDepartment());
            data[i][6] = empleados.get(i).getEmail();
            data[i][7] = empleados.get(i).getPhoneNumber();
        }
        
        String[] columnNames =
        {
            "ID", "Rol", "Nombre", "Ap Paterno", "Ap Materno", "Departamento", "E-mail", "Numero Tel"
        };
        
        return getTable(data, columnNames);
    }
    
    public static JTable getTableEmpleados(boolean[] campos)
    {
        int cont = 0;
        for(int i = 0; i < campos.length; i++)
            if (campos[i])
                cont++;
 
        int index = 0;
        int noEmpleados = 0;
        
        if (campos[1] && campos[5])
        {
            
        }else if(campos[1])
        {
            
        }else if(campos[5])
        {
            
        }else
        {
            
        }
        
        List<Staff> empleados = StaffController.getEmployees();
        Object[][] data = new Object[empleados.size()][cont];
        for (int i = 0; i < empleados.size(); i++)
        {
            if (campos[0])
                data[i][index++] = empleados.get(i).getId();
            if (campos[1])
                data[i][index++] = StaffController.getEmployeeRole(empleados.get(i).getRole());
            if (campos[2])
                data[i][index++] = empleados.get(i).getName();
            if (campos[3])
                data[i][index++] = empleados.get(i).getApPaterno();
            if (campos[4])
                data[i][index++] = empleados.get(i).getApMaterno();
            if (campos[5])
                data[i][index++] = StaffController.getEmployeeDepartment(empleados.get(i).getDepartment());
            if (campos[6])
                data[i][index++] = empleados.get(i).getEmail();
            if (campos[7])
                data[i][index++] = empleados.get(i).getPhoneNumber();
            index = 0;
        }

        Var.perosonalColumnName.clear();
        for (int i = 0; i < campos.length; i++)
        {
            if (campos[i])
            {
               Var.perosonalColumnName.add(Var.PERSONAL_COLUMN_NAMES[i]);
            }
        }
        return getTable(data, Var.perosonalColumnName.toArray());
    }
    
    public static JTable getTableEmpleadosFiltros()
    {
        boolean[] campos = Var.columnasPerosonalSeleccionados;
        boolean[] roles = Var.columnasRolesSeleccionados;
        boolean[] departamentos = Var.columnasDepartSeleccionados;
        
        int cont = 0;
        for(int i = 0; i < campos.length; i++)
            if (campos[i])
                cont++;
 
        int index = 0;
        
        Var.perosonalCampoConsulta.clear();
        for (int i = 0; i < campos.length; i++)
        {
            if (campos[i])
            {
                Var.perosonalCampoConsulta.add(Var.PERSONAL_CAMPOS[i]);
            }
        }
        
        List<String> rolesName = new ArrayList<>(Var.perosonalColumnRoles);
        Var.perosonalRolesConsulta.clear();
        for (int i = 0; i < roles.length; i++)
        {
            if (roles[i])
            {
                Var.perosonalRolesConsulta.add(rolesName.get(i));
            }
        }
        
        List<String> departamentosName = new ArrayList<>(Var.perosonalColumnDeparts);
        Var.perosonalDepartamtosConsulta.clear();
        for (int i = 0; i < departamentos.length; i++)
        {
            if (departamentos[i])
            {
                Var.perosonalDepartamtosConsulta.add(departamentosName.get(i));
            }
        }
        
        System.out.println(Var.perosonalCampoConsulta);
        System.out.println(Var.perosonalRolesConsulta);
        System.out.println(Var.perosonalDepartamtosConsulta);
        
        List<Staff> empleados = StaffController.getEmployees();
        Object[][] data = new Object[empleados.size()][cont];
        for (int i = 0; i < empleados.size(); i++)
        {
            if (campos[0])
                data[i][index++] = empleados.get(i).getId();
            if (campos[1])
                data[i][index++] = StaffController.getEmployeeRole(empleados.get(i).getRole());
            if (campos[2])
                data[i][index++] = empleados.get(i).getName();
            if (campos[3])
                data[i][index++] = empleados.get(i).getApPaterno();
            if (campos[4])
                data[i][index++] = empleados.get(i).getApMaterno();
            if (campos[5])
                data[i][index++] = StaffController.getEmployeeDepartment(empleados.get(i).getDepartment());
            if (campos[6])
                data[i][index++] = empleados.get(i).getEmail();
            if (campos[7])
                data[i][index++] = empleados.get(i).getPhoneNumber();
            index = 0;
        }

        Var.perosonalColumnName.clear();
        for (int i = 0; i < campos.length; i++)
        {
            if (campos[i])
            {
               Var.perosonalColumnName.add(Var.PERSONAL_COLUMN_NAMES[i]);
            }
        }
        return getTable(data, Var.perosonalColumnName.toArray());
    }
}
