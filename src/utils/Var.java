package utils;

import dao.StaffDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Alfred
 */
public class Var
{

    public static final String PATHASSETS = "src/assets/";

    public static final String[] COLORES_BOTONES_NORTH =
    {
        "#556080", //colorEntered
        "#0277BD", //colorExited
        "#FFFFFF"  //colorPressed
    };

    public static enum MENU_ADMIN
    {
        PERSONAL,
        DEPARTAMENTOS,
        RECURSOS
    }

    public static enum MENU_USER
    {
        TAREAS,
        PROYECTOS,
        COLABORACIONES
    }

    public static MENU_ADMIN OPCION_ACTUAL;

    public static int OPCION_ACT = -1;

    public static final String[] DEPARTMENT_COLUMN_NAMES =
    {
        "ID", "Nombre", "Gefe Area", "Numero Tel"
    };

    public static final String[] RESOURCES_COLUMN_NAMES =
    {
        "ID", "Nombre", "Total", "Disponible", "Ver", "Editar", "Eliminar"
    };

    public static final String[] PERSONAL_COLUMN_NAMES =
    {
        "ID", "NOMBRE", "AP PATERNO", "AP MATERNO", "DEPARTAMENTO", "ROL", "EMAIL", "TELEFONO"
    };
    public static final String[] PERSONAL_CAMPOS =
    {
        "pk_id", "nombre", "ap_paterno", "ap_materno", "fk_departamento", "fk_rol", "email", "telefono"
    };
    
    public static Set<String> perosonalColumnName = 
            new LinkedHashSet<>(Arrays.asList(PERSONAL_COLUMN_NAMES));
    
    public static boolean[] columnasPerosonalSeleccionados = 
    {
        true, true, true, true, true, true, true, true
    };
    
    public static Set<String> perosonalColumnDeparts = StaffDAO.getStaffDepartmets();
    
    public static boolean[] columnasDepartSeleccionados = new boolean[StaffDAO.getStaffNoDepartmets()];
    
    public static Set<String> perosonalColumnRoles = StaffDAO.getStaffRoles();
    
    public static boolean[] columnasRolesSeleccionados = new boolean[StaffDAO.getStaffNoRoles()];

    public static Set<String> perosonalCampoConsulta = new LinkedHashSet<>();
    public static Set<String> perosonalRolesConsulta = new LinkedHashSet<>();
    public static Set<String> perosonalDepartamtosConsulta = new LinkedHashSet<>();

    public static final String[] CAMPOS_ORDENAR_PEROSNAL = 
    {
        "ID", "NOMBRE", "AP PATERNO", "AP MATERNO"
    };
    
    public static final String[] CAMPOS_ORDENAR_PEROSNAL_CONSULTA = 
    {
        "pk_id", "nombre", "ap_paterno", "ap_materno"
    };
    
    public static int opcOrdenadoPersonal = 0;
    public static boolean opcOrdenadoForPersonal = false;
    
    public static List<String> ordenarPersonasPor = new ArrayList<>(Arrays.asList(CAMPOS_ORDENAR_PEROSNAL));
    
    public static final String[] CAMPOS_ORDENAR_PRIORIDAD_PEROSNAL = 
    {
        "ACENDENTE", "DECENDENTE"
    };
    
    public static int filaSeleccionadaPersonal = -1;
    public static int idSeleccionadaPersonal = -1;
}
