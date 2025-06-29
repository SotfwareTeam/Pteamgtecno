package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class Redireccion  extends PageObject{
    public static Target BTN_PERFIL = Target.the(" Click Boton perfil").located(By.xpath("//*[@id=\"app\"]/div/nav/div/a[2]"));

    public static Target BTN_EVENTOS = Target.the(" Click Boton perfil").located(By.xpath("//*[@id=\"offcanvasNavbar\"]/div[2]/ul/li[2]/a"));

}