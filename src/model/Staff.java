package model;

/**
 *
 * @author Alfred
 */

public class Staff
{
    private final int id;
    private int role;
    private int department;
    private String name;
    private String apPaterno;
    private String apMaterno;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;

    public Staff(int id, int role, int departament, String name, String apPaterno, String apMaterno, String password, String email, String address, String phoneNumber)
    {
        this.id = id;
        this.role = role;
        this.department = departament;
        this.name = name;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
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
     * @return the department
     */
    public int getDepartment()
    {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(int department)
    {
        this.department = department;
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
     * @return the apPaterno
     */
    public String getApPaterno()
    {
        return apPaterno;
    }

    /**
     * @param apPaterno the apPaterno to set
     */
    public void setApPaterno(String apPaterno)
    {
        this.apPaterno = apPaterno;
    }

    /**
     * @return the apMaterno
     */
    public String getApMaterno()
    {
        return apMaterno;
    }

    /**
     * @param apMaterno the apMaterno to set
     */
    public void setApMaterno(String apMaterno)
    {
        this.apMaterno = apMaterno;
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
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
     @Override
    public String toString()
    {
        return "ID: " + getId() + "\tRol: " + getRole() + "\tDepartamento: " + getDepartment() 
                + "\tNombre: " + getName() + " " + getApPaterno() + " " + getApMaterno()
                + "\tPassword: " + getPassword() + "\tE-mail: " + getEmail() 
                + "\tDireccion: " + getAddress() + "\tTelefono: " + getPhoneNumber();
    }
}
