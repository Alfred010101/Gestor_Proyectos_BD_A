
package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 * @author Alfred
 */

class ButtonRenderer extends JPanel implements TableCellRenderer {
    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinear botones a la izquierda
        setOpaque(true); // Hacer que el panel sea opaco
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Limpiar el panel
        removeAll();

        // Crear los botones
        JButton button1 = new JButton("Ver");
        JButton button2 = new JButton("Ediar");
        JButton button3 = new JButton("Eliminar");

        // Agregar los botones al panel
        add(button1);
        add(button2);
        add(button3);

        // Cambiar el color de fondo según la selección
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }

        // Redibujar el panel
        revalidate();
        repaint();

        return this;
    }
}