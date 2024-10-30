
package controller;

import dao.DepartmentDAO;
import dao.StaffDAO;
import java.util.List;
import model.Department;

/**
 *
 * @author Alfred
 */

public class DepartmentController
{
    public static List<Department> getDepartments()
    {
        return DepartmentDAO.getDepartments();
    }
    
    public static String getDepartmentName(int id)
    {
        return DepartmentDAO.getDepartment(id).getName();
    }
    
    public static Object[][] getDepartmentData()
    {
        List<Department> departments = DepartmentDAO.getDepartments();
        Object[][] data = new Object[departments.size()][7];
        for (int i = 0; i < departments.size(); i++)
        {
            data[i][0] = departments.get(i).getId();
            data[i][1] = departments.get(i).getName();
            if (departments.get(i).getManager() > 0)
            {
                data[i][2] = StaffDAO.getStaff(departments.get(i).getManager()).getName();
            }                        
            data[i][3] = departments.get(i).getPhoneNumber();
            data[i][4] = null;
            data[i][5] = null;
            data[i][6] = null;
        }
        return data;
    }
    
    public static String getDepartmentJefe(int id)
    {
        return StaffDAO.getStaff(DepartmentDAO.getDepartment(id).getManager()).getName();
    }
    
    public static String getDepartmentGerente(int id)
    {
        return DepartmentDAO.getDepartmentGerente(id);
    }
}
