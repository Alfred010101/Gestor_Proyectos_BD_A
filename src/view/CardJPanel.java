
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Alfred
 */

public abstract class CardJPanel
{
    protected JPanel panelPricipal;
    
    public CardJPanel()
    {
        panelPricipal = new JPanel();
        panelPricipal.setBackground(Color.LIGHT_GRAY);
        panelPricipal.setLayout(new BorderLayout());
        panelPricipal.setBorder(new EmptyBorder(0, 10, 5, 10));
    }
    
    protected abstract void reset();
}
