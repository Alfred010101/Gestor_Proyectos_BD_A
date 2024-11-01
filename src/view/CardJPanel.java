package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Alfred
 */
public abstract class CardJPanel extends JPanel
{

    protected JTable tabla;
    protected JScrollPane contenedorTabla;

    public CardJPanel()
    {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    protected abstract void reset();

    protected int getColumnIndexByName(JTable table, String columnName)
    {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++)
        {
            if (columnModel.getColumn(i).getHeaderValue().equals(columnName))
            {
                return i; // Retorna el índice de la columna
            }
        }
        return -1; // Retorna -1 si no se encuentra la columna
    }
}
