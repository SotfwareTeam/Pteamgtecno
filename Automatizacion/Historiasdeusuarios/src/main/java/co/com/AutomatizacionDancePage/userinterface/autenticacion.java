package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class autenticacion extends PageObject {
    public static Target INPUT_USUARIO = Target.the(" Ingreso del Usuario").located(By.xpath("//*[@id=\"exampleInputEmail\"]"));
    public static Target INPUT_CLAVE = Target.the("Ingreso del password").located(By.xpath("//*[@id=\"exampleInputPassword\"]"));
    public static Target BTN_INICIOSESION = Target.the("Click Boton inicio sesion").located(By.xpath("/html/body/div/div/div/form/button"));

}
