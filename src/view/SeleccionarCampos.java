package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Alfred
 */
public class SeleccionarCampos
{

    public static JPopupMenu lista(JScrollPane contenedorTabla, boolean[] seleccionados)
    {
        String[] elementos =
        {
            "<<TODOS>>", "ID", "ROL", "NOMBRE", "AP PATERNO", "AP MATERNO", "DEPARTAMENTO", "EMAIL", "TELEFONO"
        };

        // Crear un array para almacenar el estado de los checkboxes (seleccionados o no)
        boolean[] noDeseleccionables = new boolean[elementos.length];  // Para los elementos que no se pueden deseleccionar

        // Seleccionar por defecto
        
        // Elementos no deseleccionable
        noDeseleccionables[1] = true;
        noDeseleccionables[3] = true;

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);

            // Establecer el estado del checkbox y deshabilitar si es no deselectable
            checkBox.setSelected(seleccionados[index]);
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
                    if (index == 0)
                    {
                        for (int i = 0; i < seleccionados.length; i++)
                        {
                            seleccionados[i] = true;
                        }
                    } else
                    {
                        seleccionados[0] = false;
                        seleccionados[index] = !seleccionados[index];
                    }
                    lista.repaint();

                    boolean[] arr = new boolean[elementos.length - 1];
                    System.arraycopy(seleccionados, 1, arr, 0, arr.length);
                    JTable nuevaTabla = GenerateTable.getTableEmpleados(arr);
                    contenedorTabla.setViewportView(nuevaTabla);

                    contenedorTabla.revalidate();
                    contenedorTabla.repaint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(140, 220));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(scrollPane, BorderLayout.CENTER);
        return popupMenu;
    }
}
