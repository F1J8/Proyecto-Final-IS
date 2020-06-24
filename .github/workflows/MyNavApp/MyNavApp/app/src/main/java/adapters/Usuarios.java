package adapters;

public class Usuarios {
  
    private String Nombre, Facultad, Duracion, NombreE, Apellido, DNI, Telefono;
    private  int ID;



    public Usuarios(String nombre, String facultad, String duracion,String nombreE, String apellido, String DNI, String telefono, int ID) {
        Nombre = nombre;
        Facultad = facultad;
        Duracion = duracion;
        this.ID = ID;
        this. NombreE = nombreE;
        this. Apellido = apellido;
        this.DNI = DNI;
        this.Telefono = telefono;
    }
    
    public Usuarios() {
    
    }

    public String getNombreE() {
        return NombreE;
    }

    public void setNombreE(String nombreE) {
        this.NombreE = nombreE;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public String getNombre() {
        return Nombre;
    }
    
    public void setNombre(String nombre) {
        Nombre = nombre;
    }



    
    public String getFacultad() {
        return Facultad;
    }
    
    public void setFacultad(String facultad) {
        Facultad = facultad;
    }
    
    public String getDuracion() {
        return Duracion;
    }
    
    public void setDuracion(String duracion) {
        Duracion = duracion;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    

}
