
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

public class TableCellRenderer extends DefaultTableCellRenderer {
//    private final Color HOVER_COLOR = new Color(240, 248, 255);
    private final Color HOVER_COLOR = Color.decode("#aed6f1");
    private final Color DEFAULT_BACKGROUND = Color.WHITE;
    private int hoverRow = -1;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Aplicar efecto hover
        if (row == hoverRow && !isSelected) {
            c.setBackground(HOVER_COLOR);
        } else if (isSelected) {
            c.setBackground(new Color(102, 178, 255));
        } else {
            c.setBackground(DEFAULT_BACKGROUND);
        }
        
        if (c instanceof JComponent jComponent) {
            jComponent.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        return c;
    }

    // Método para actualizar la fila en hover
    public void setHoverRow(int row) {
        this.hoverRow = row;
    }
}