package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class ClassesUserInterface {
    public static Target BTN_CALENDARIO = Target.the(" Ingreso del Usuario").located(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/img"));
    public static Target BTN_CALENDARIOMODAL = Target.the("Ingreso del password").located(By.xpath("//*[@id=\"espaciosModal\"]/div/div/div[2]/button"));
    public static Target BOTON_MES_SIG = Target.the(" Mes siguiente").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div[1]/div[3]/button"));
    public static String xpathFecha1 =  "//*[@id=\"app\"]/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[3]";
    public static String xpathFecha2 =  "//*[@id=\"app\"]/div/div[1]/div[2]/div[2]/table/tbody/tr[2]/td[5]";
    public static Target BOTON_FECHA1 = Target.the(" Fecha seleccionada 1").located(By.xpath(xpathFecha1));
    public static Target DIA_FECHA1 = Target.the(" Fecha seleccionada 1").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div[2]/table/thead/tr/th[3]"));
    public static Target BOTON_FECHA2 = Target.the(" Fecha seleccionada 2").located(By.xpath(xpathFecha2));
    public static Target DIA_FECHA2 = Target.the(" Fecha seleccionada 1").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div[2]/table/thead/tr/th[5]"));
    public static Target BOTON_PROGRAMAR = Target.the(" Programar clase").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div[3]/button[2]"));
    public static Target INPUT_PROFESOR = Target.the(" Input profesor").located(By.xpath("//*[@id=\"profesor\"]"));
    public static Target INPUT_HORA = Target.the(" Input hora").located(By.xpath("//*[@id=\"hora\"]"));
    public static Target INPUT_CLASE = Target.the(" Input clase").located(By.xpath("//*[@id=\"clase\"]"));
    public static Target INPUT_DIFICULTAD = Target.the(" Input dificultad").located(By.xpath("//*[@id=\"dificultad\"]"));
    public static Target SELECT_PRINCIPIANTE = Target.the(" Opcion principiante").located(By.xpath("//*[@id=\"dificultad\"]/option[1]"));
    public static Target SELECT_MEDIO = Target.the(" Opcion medio").located(By.xpath("//*[@id=\"dificultad\"]/option[2]"));
    public static Target SELECT_DIFICIL = Target.the(" Opcion dificil").located(By.xpath("//*[@id=\"dificultad\"]/option[3]"));
    public static Target BOTON_GUARDAR = Target.the(" Guardar").located(By.xpath("//*[@id=\"programModal\"]/div/div/div[2]/form/div[5]/button[1]"));
    public static Target BOTON_INFORMACION = Target.the(" Informacion").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div[3]/button[1]"));
    public static Target BOTON_ELIMINAR = Target.the(" Eliminar").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div[3]/button[3]"));
    public static Target MODAL_ELIMINAR = Target.the(" Eliminar en modal").located(By.xpath("//*[@id=\"deleteModal\"]/div/div/div[3]/button[1]"));
    public static Target MODAL_CERRAR = Target.the(" Cerrar modal").located(By.xpath("//*[@id=\"infoModal\"]/div/div/div[3]/button"));
    public static Target VALIDACION_MES = Target.the(" Texto mes").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div[1]/div[2]/h3"));
    public static Target VALIDACION_TITULO_MODAL = Target.the(" Texto profesor").located(By.xpath("//*[@id=\"infoModal\"]/div/div/div[1]/h5"));
    public static Target VALIDACION_PROFESOR_O_ELIMINADO = Target.the(" Texto profesor").located(By.xpath("//*[@id=\"infoModal\"]/div/div/div[2]/div/p[1]"));
    public static Target VALIDACION_HORA = Target.the(" Texto hora").located(By.xpath("//*[@id=\"infoModal\"]/div/div/div[2]/div/p[2]"));
    public static Target VALIDACION_CLASE = Target.the(" Texto clase").located(By.xpath("//*[@id=\"infoModal\"]/div/div/div[2]/div/p[3]"));
    public static Target VALIDACION_DIFICULTAD = Target.the(" Texto dificultad").located(By.xpath("//*[@id=\"infoModal\"]/div/div/div[2]/div/p[4]"));


}
