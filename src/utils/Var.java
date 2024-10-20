package utils;

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
}
