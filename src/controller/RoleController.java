
package controller;

import dao.RoleDAO;

/**
 *
 * @author Alfred
 */
public class RoleController
{
    public static String getRole(int id)
    {
        return RoleDAO.getRole(id);
    }
}
