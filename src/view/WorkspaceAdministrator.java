package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import model.Staff;
import utils.Var;

/**
 *
 * @author Alfred
 */
public class WorkspaceAdministrator extends PlantillaPrincipal
{

    public WorkspaceAdministrator(int id)
    {
        super(id);
        initComponents();
    }

    private void initComponents()
    {
        initMenu();
        initWorckspace();
        pack();
    }

    @Override
    protected void initMenu()
    {
        JButton btnPersonal = GenerateButton.crearBotonConIcono("Personal", "jefe-de-equipo_Res.png", 0);
        
        JButton btnDepartamentos = GenerateButton.crearBotonConIcono("Departamentos", "departamento-de-la-compania_Res.png", 1);

        JButton btnRecursos = GenerateButton.crearBotonConIcono("Recursos", "en-stock_Res.png", 2);


        btnPersonal.addActionListener((e) ->
        {
            if (Var.OPCION_ACT != 0)
            {
                setBackgroundMenusAdmin(0, btnPersonal, btnDepartamentos, btnRecursos);
                cardWork.show(panelCenter, "Card Personal");
            }
        });

        btnDepartamentos.addActionListener((e) ->
        {
            if (Var.OPCION_ACT != 1)
            {
                setBackgroundMenusAdmin(1, btnPersonal, btnDepartamentos, btnRecursos);
                cardWork.show(panelCenter, "Card Departamentos");
            }
        });

        btnRecursos.addActionListener((e) ->
        {
            if (Var.OPCION_ACT != 2)
            {
                setBackgroundMenusAdmin(2, btnPersonal, btnDepartamentos, btnRecursos);
                cardWork.show(panelCenter, "Card Recursos");
            }
        });

        panelWest.add(btnPersonal);
        panelWest.add(btnDepartamentos);
        panelWest.add(btnRecursos);
    }    

    @Override
    protected void initWorckspace()
    {
//        panelCenter.add(new JPanel(), "null");
        panelCenter.add(new AdminEmployees(employee.getId()).panelPricipal, "Card Personal");
        panelCenter.add(new AdminDepartments(employee.getId()).panelPricipal, "Card Departamentos");
        panelCenter.add(new AdminResources(employee.getId()).panelPricipal, "Card Recursos");
    }
}
