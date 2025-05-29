package co.com.AutomatizacionDancePage.models;

public class DatosParaPerfil {
    private String nombre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    private String direccion;
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {this.direccion = direccion;}

    private String telefono;
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public DatosParaPerfil(String nombre, String email, String direccion, String telefono) {
        this.nombre = nombre;
        this.email = email ;
        this.direccion = direccion ;
        this.telefono = telefono;
    }
}


