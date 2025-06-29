package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class autenticacion extends PageObject{

    public static Target INPUT_CORREO = Target.the(" Ingreso del correo").located(By.xpath("/html/body/div[1]/body/div/div[1]/form/div[2]/input"));

    public static Target INPUT_CONTRASEÑA = Target.the("Ingreso del password").located(By.xpath("/html/body/div[1]/body/div/div[1]/form/div[3]/input"));

    public static Target BTN_INICIOSESION = Target.the("Click Boton inicio sesion").located(By.xpath("/html/body/div[1]/body/div/div[1]/form/button"));

    public static Target MENSAJE_LOGIN = Target.the("mensaje de login").located(By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/form/div[2]/input"));
}
