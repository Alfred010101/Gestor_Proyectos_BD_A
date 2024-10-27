package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Alfred
 */
public class TableCellRenderer extends DefaultTableCellRenderer
{

    private final Color HOVER_COLOR = Color.decode("#aed6f1");
    private final Color DEFAULT_BACKGROUND = Color.WHITE;
    private final Color ALTERNATE_BACKGROUND = new Color(230, 240, 255);
    private final Color SELECTED_BACKGROUND = new Color(102, 178, 255);
    private int hoverRow = -1;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Aplicar efecto hover si no está seleccionado
        if (row == hoverRow && !isSelected)
        {
            c.setBackground(HOVER_COLOR);
        } else if (isSelected)
        {
            c.setBackground(SELECTED_BACKGROUND);
        } else
        {
            // Alternar colores de fondo entre filas pares e impares
            c.setBackground(row % 2 == 0 ? DEFAULT_BACKGROUND : ALTERNATE_BACKGROUND);
        }

        if (c instanceof JComponent jComponent)
        {
            jComponent.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        return c;
    }

    public void setHoverRow(int row)
    {
        this.hoverRow = row;
    }
}
