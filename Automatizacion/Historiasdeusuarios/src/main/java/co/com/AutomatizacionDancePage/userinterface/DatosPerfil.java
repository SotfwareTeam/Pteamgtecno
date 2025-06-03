package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class DatosPerfil extends PageObject {
    public static Target INPUT_NOMBRE = Target.the("Ingreso del nombre").located(By.xpath("//*[@id=\"nombre\"]"));
    public static Target INPUT_EMAIL = Target.the("Ingreso del email").located(By.xpath("//*[@id=\"email\"]"));
    public static Target INPUT_DIRECCION = Target.the("Ingreso del direccion").located(By.xpath("//*[@id=\"direccion\"]"));
    public static Target INPUT_TELEFONO = Target.the("Ingreso del direccion secundaria").located(By.xpath("//*[@id=\"telefono\"]"));
    public static Target BTN_CONTINUECHECKS = Target.the("Click Boton continuar chequeo").located(By.xpath("//*[@id=\"app\"]/div/div/div[1]/div[2]/form/div[5]/div[1]/button"));
    public static Target MENSAJE_BASKET = Target.the("mensaje de login").locatedBy("/html/body/div/header/h1");
}
