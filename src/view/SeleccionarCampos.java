package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import utils.Var;

/**
 *
 * @author Alfred
 */
public class SeleccionarCampos
{

    public static JPopupMenu listaCampos(JScrollPane contenedorTabla, boolean[] seleccionados, JButton btnRoles, JButton btnDepart)
    {
        String[] elementos =
        {
            "<<TODOS>>", "ID", "ROL", "NOMBRE", "AP PATERNO", "AP MATERNO", "DEPARTAMENTO", "EMAIL", "TELEFONO"
        };

        // Crear un array para almacenar el estado de los checkboxes (seleccionados o no)
        boolean[] noDeseleccionables = new boolean[elementos.length];
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
                        btnRoles.setVisible(true);
                        btnDepart.setVisible(true);
                    } else
                    {
                        seleccionados[0] = false;
                        seleccionados[index] = !seleccionados[index];
                        if (!seleccionados[2])
                        {
                            btnRoles.setVisible(false);
                        } else
                        {
                            btnRoles.setVisible(true);
                        }
                        if (!seleccionados[6])
                        {
                            btnDepart.setVisible(false);
                        } else
                        {
                            btnDepart.setVisible(true);
                        }

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

    public static JPopupMenu listaRoles(JScrollPane contenedorTabla, boolean[] seleccionados)
    {
        String[] elementos = Var.perosonalColumnRoles.toArray(String[]::new);

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);
            checkBox.setSelected(seleccionados[index]);
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
                    seleccionados[index] = !seleccionados[index];
                    lista.repaint();
                    boolean[] arr = new boolean[elementos.length - 1];
                    System.arraycopy(seleccionados, 1, arr, 0, arr.length);
//                    JTable nuevaTabla = GenerateTable.getTableEmpleados(arr);
//                    contenedorTabla.setViewportView(nuevaTabla);
//
//                    contenedorTabla.revalidate();
//                    contenedorTabla.repaint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(180, 130));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(scrollPane, BorderLayout.CENTER);
        return popupMenu;
    }
    
    public static JPopupMenu listaDepartamentos(JScrollPane contenedorTabla, boolean[] seleccionados)
    {
        String[] elementos = Var.perosonalColumnDepart.toArray(String[]::new);

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);
            checkBox.setSelected(seleccionados[index]);
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
                    seleccionados[index] = !seleccionados[index];
                    lista.repaint();
                    boolean[] arr = new boolean[elementos.length - 1];
                    System.arraycopy(seleccionados, 1, arr, 0, arr.length);
//                    JTable nuevaTabla = GenerateTable.getTableEmpleados(arr);
//                    contenedorTabla.setViewportView(nuevaTabla);
//
//                    contenedorTabla.revalidate();
//                    contenedorTabla.repaint();
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
