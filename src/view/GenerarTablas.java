package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Alfred
 */
public class GenerarTablas
{

    /**
     * Configura la vista inicial de una tabla, asignando un DefaultTableModel
     *
     * @param data datos a insertar en la tabla
     * @param columnNames nombre de los encabezados de las columnas (Campos)
     * @return
     */
    public static JTable configTabla(Object[][] data, String[] columnNames)
    {
        JTable tabla = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        tabla.setModel(tableModel);

        // Estilizar la cabecera de la tabla
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(51, 153, 255));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        // Estilizar las filas
        tabla.setRowHeight(30); // Altura de las filas
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setSelectionForeground(Color.WHITE);

        // Crear un renderizador personalizado para la tabla
        TableCellRenderer cellRenderer = new TableCellRenderer();
        tabla.setDefaultRenderer(Object.class, cellRenderer);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tabla.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                int row = tabla.rowAtPoint(e.getPoint());
                cellRenderer.setHoverRow(row);
                tabla.repaint();
            }
        });

        tabla.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent e)
            {
                cellRenderer.setHoverRow(-1);
                tabla.repaint();
            }
        });
        return tabla;
    }
}
