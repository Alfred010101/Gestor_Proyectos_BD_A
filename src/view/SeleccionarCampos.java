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

    public static JPopupMenu listaCampos(JScrollPane contenedorTabla, JButton btnRoles, JButton btnDepart)
    {
        String[] elementos = Var.PERSONAL_COLUMN_NAMES;

        // Crear un array para almacenar el estado de los checkboxes (seleccionados o no)
        boolean[] noDeseleccionables = new boolean[elementos.length];
        noDeseleccionables[0] = true;
        noDeseleccionables[1] = true;

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);

            // Establecer el estado del checkbox y deshabilitar si es no deselectable
            checkBox.setSelected(Var.columnasPerosonalSeleccionados[index]);
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
                    Var.columnasPerosonalSeleccionados[index] = !Var.columnasPerosonalSeleccionados[index];
                    btnDepart.setVisible(Var.columnasPerosonalSeleccionados[4]);
                    btnRoles.setVisible(Var.columnasPerosonalSeleccionados[5]);                    
                    if (!Var.columnasPerosonalSeleccionados[4])
                    {
                        for (int i = 0; i < Var.columnasDepartSeleccionados.length; i++)
                        {
                            Var.columnasDepartSeleccionados[i] = true;
                        }
                    }
                    if (!Var.columnasPerosonalSeleccionados[5])
                    {
                        for (int i = 0; i < Var.columnasRolesSeleccionados.length; i++)
                        {
                            Var.columnasRolesSeleccionados[i] = true;
                        }
                    }
                    lista.repaint();

//                    boolean[] arr = new boolean[elementos.length];
//                    System.arraycopy(Var.columnasPerosonalSeleccionados, 0, arr, 0, arr.length);
                    JTable nuevaTabla = GenerateTable.getTableEmpleadosFiltros();
                    contenedorTabla.setViewportView(nuevaTabla);

                    contenedorTabla.revalidate();
                    contenedorTabla.repaint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(140, 200));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(scrollPane, BorderLayout.CENTER);
        return popupMenu;
    }

    public static JPopupMenu listaRoles(JScrollPane contenedorTabla)
    {
        String[] elementos = Var.perosonalColumnRoles.toArray(String[]::new);

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);
            checkBox.setSelected(Var.columnasRolesSeleccionados[index]);
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
                    Var.columnasRolesSeleccionados[index] = !Var.columnasRolesSeleccionados[index];
                    lista.repaint();
                    
                    JTable nuevaTabla = GenerateTable.getTableEmpleadosFiltros();
                    contenedorTabla.setViewportView(nuevaTabla);

                    contenedorTabla.revalidate();
                    contenedorTabla.repaint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(200, 160));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(scrollPane, BorderLayout.CENTER);
        return popupMenu;
    }

    public static JPopupMenu listaDepartamentos(JScrollPane contenedorTabla)
    {
        String[] elementos = Var.perosonalColumnDeparts.toArray(String[]::new);

        JList<String> lista = new JList<>(elementos);

        // Personalizar el JList para que use JCheckBox como renderizador
        lista.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) ->
        {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox(value);
            checkBox.setSelected(Var.columnasDepartSeleccionados[index]);
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
                    Var.columnasDepartSeleccionados[index] = !Var.columnasDepartSeleccionados[index];
                    lista.repaint();
                    
                    JTable nuevaTabla = GenerateTable.getTableEmpleadosFiltros();
                    contenedorTabla.setViewportView(nuevaTabla);

                    contenedorTabla.revalidate();
                    contenedorTabla.repaint();
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
