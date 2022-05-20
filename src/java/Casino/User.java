package Casino;

public class User {

    private int id;
    private String password;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNacimiento;
    private double credito;

    public User(int id, String password, String nombre, String apellido, String dni, String fechaNacimiento) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.credito = credito;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
 
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", credito=" + credito + '}';
    }

    
    
    public String toStringNombre() {
        return "" + nombre;
    }
    
    public String toStringPassword(){
        return "" + password;
    }

    public String toStringFullName() {
        return "" + nombre + " " + apellido;
    }

    public String toStringDNI() {
        return "" + dni;
    }

    public String toStringNacimiento() {
        return "" + fechaNacimiento;
    }
    
    public String toStringCredito(){
        return "" + credito;
    }
    
}
