
package controller;

import dao.DepartmentDAO;
import java.util.List;
import model.Department;

/**
 *
 * @author Alfred
 */

public class DepartmentController
{
    public static String getNameDepartment(int id)
    {
        return DepartmentDAO.getNameDepartment(id);
    }
    
    public static Object[][] getDepartmentData()
    {
        List<Department> departments = DepartmentDAO.getDepartments();
        Object[][] data = new Object[departments.size()][7];
        for (int i = 0; i < departments.size(); i++)
        {
            data[i][0] = departments.get(i).getId();
            data[i][1] = departments.get(i).getName();
            data[i][2] = StaffController.getEmployeeName(departments.get(i).getManager());            
            data[i][3] = departments.get(i).getPhoneNumber();
            data[i][4] = null;
            data[i][5] = null;
            data[i][6] = null;
        }
        return data;
    }
}
