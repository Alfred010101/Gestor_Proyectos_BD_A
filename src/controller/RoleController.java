
package controller;

import dao.RoleDAO;
import model.Role;

/**
 *
 * @author Alfred
 */
public class RoleController
{
    public static Role getRole(int pkId)
    {
        return RoleDAO.getRole(pkId);
    }
    
    public static String getRoleName(int pkId)
    {
        return RoleDAO.getRole(pkId).getName();
    }
}
