
package controller;

import dao.ResourcesDAO;
import java.util.List;
import model.Resources;

/**
 *
 * @author Alfred
 */

public class ResourcesController
{
    public static Object[][] getResoucesData()
    {
        List<Resources> resources = ResourcesDAO.getResources();
        Object[][] data = new Object[resources.size()][7];
        for (int i = 0; i < resources.size(); i++)
        {
            data[i][0] = resources.get(i).getId();
            data[i][1] = resources.get(i).getName();
            data[i][2] = resources.get(i).getQuantityAvailable();
            data[i][3] = resources.get(i).getTotalAmount();
            data[i][4] = null;
            data[i][5] = null;
            data[i][6] = null;
        }
        return data;
    }
    
    public static List<Resources> getResources()
    {
        return ResourcesDAO.getResources();
    }
}
