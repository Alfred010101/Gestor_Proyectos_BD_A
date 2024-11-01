
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
}
