package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class Redireccion extends PageObject{
    public static Target BTN_BASKET = Target.the(" Click Boton Basket").located(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[4]/a"));
}
