package model;

/**
 *
 * @author Alfred
 */

public class Staff
{
    private int id;
    private int role;
    private int departament;
    private String name;
    private String user;
    private String password;
    private String email;
    private String address;
    private int phoneNumber;

    public Staff()
    {
    }

    public Staff(int id, int role, int departament, String name, String user, String password, String email, String address, int phoneNumber)
    {
        this.id = id;
        this.role = role;
        this.departament = departament;
        this.name = name;
        this.user = user;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the role
     */
    public int getRole()
    {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role)
    {
        this.role = role;
    }

    /**
     * @return the departament
     */
    public int getDepartament()
    {
        return departament;
    }

    /**
     * @param departament the departament to set
     */
    public void setDepartament(int departament)
    {
        this.departament = departament;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * @return the phoneNumber
     */
    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }    

    @Override
    public String toString()
    {
        return "ID: " + id + "\tRol: " + role + "\tDepartamento: " + departament 
                + "\tNombre: " + name + "\tUsuario: " + user 
                + "\tPassword: " + password + "\tE-mail: " + email 
                + "\tDireccion: " + address + "\tTelefono: " + phoneNumber;
    }
}
