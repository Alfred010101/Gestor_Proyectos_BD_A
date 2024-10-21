
package controller;

import dao.DepartmentDAO;

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
}
