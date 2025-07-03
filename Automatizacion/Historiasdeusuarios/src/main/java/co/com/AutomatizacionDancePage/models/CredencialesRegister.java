package co.com.AutomatizacionDancePage.models;

import cucumber.api.java.mk_latn.No;
import org.apache.xpath.objects.XString;

public class CredencialesRegister {
    private String nombres;

    private String apellido;

    private String numerodeidentificacion;

    private String telefono;

    private String edad;


    private String nombredeusuario;

    private String correo;

    private String contraseña;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getNumerodeidentificacion() {
        return numerodeidentificacion;
    }

    public void setNumerodeidentificacion(String numerodeidentificacion) {
        this.numerodeidentificacion = numerodeidentificacion;
    }

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEdad() {return edad;
    }

    public void setEdad(String edad) {this.edad = edad;
    }

    public String getNombredeusuario() {return nombredeusuario;
    }

    public void setNombredeusuario(String nombredeusuario) {
        this.nombredeusuario = nombredeusuario;
    }

    public String getCorreo() {return correo;}

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContraseña() {return contraseña;}

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;}



    public CredencialesRegister(String nombres, String apellido,String numerodeidentificacion, String telefono, String edad, String nombredeusuario, String correo, String contraseña) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.numerodeidentificacion = numerodeidentificacion;
        this.telefono = telefono;
        this.edad = edad;
        this.nombredeusuario = nombredeusuario;
        this.correo = correo;
        this.contraseña = contraseña;
    }
}