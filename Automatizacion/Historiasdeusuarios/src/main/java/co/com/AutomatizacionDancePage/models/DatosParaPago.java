package co.com.AutomatizacionDancePage.models;

public class DatosParaPago {
    private String nombre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private String apellido;
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    private String direccion2;
    public String getDireccion2() {
        return direccion2;
    }
    public void setDireccion2(String direccion2) {this.direccion2 = direccion2;}
    private String country;
    private String city;
    private String zip;
    public String getZip() {return zip;}
    public void setZip(String zip) {this.zip = zip;}
    private String tarjeta;
    public String getTarjeta() {
        return tarjeta;
    }
    public void setTarjeta(String tarjeta) {this.tarjeta = tarjeta;}
    private String numero_tarjeta;
    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }
    public void setNumero_tarjeta(String numero_tarjeta) {this.numero_tarjeta = numero_tarjeta;}
    private String expiration;
    public String getExpiration() {
        return expiration;
    }
    public void setExpiration(String expiration) {this.expiration = expiration;}
    private String cvv;
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {this.cvv = cvv;}
    public DatosParaPago(String nombre, String apellido, String email, String direccion, String zip, String nombretarjeta, String numerotarjeta, String expiracion, String cvv,  String pais, String ciudad, String direccion2) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email ;
        this.direccion = direccion ;
        this.direccion2 = direccion2;
        this.zip = zip ;
        this.tarjeta = nombretarjeta;
        this.numero_tarjeta = numerotarjeta;
        this.expiration = expiracion;
        this.cvv = cvv;
        this.country = pais;
        this.city = ciudad;
    }
}


