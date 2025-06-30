package co.com.AutomatizacionDancePage.models;

public class ClassesData {
    private String profesor;
    private String hora;
    private String clase;

    public String getProfesor() {return profesor;}
    public void setProfesor(String profesor) {this.profesor = profesor;}

    public String getHora() {return hora;}
    public void setHora(String hora) {this.hora = hora;}

    public String getClase() {return clase;}
    public void setClase(String clase) {this.clase = clase;}


    public ClassesData(String profesor, String hora, String clase) {
        this.profesor = profesor;
        this.hora = hora;
        this.clase = clase;
    }
}
