package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class h6defpasos extends PageObject {
    public static Target BTN_CALENDARIO = Target.the(" Ingreso del Usuario").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/img"));
    public static Target BTN_CALENDARIOMODAL = Target.the("Ingreso del password").located(By.xpath("//*[@id=\"espaciosModal\"]/div/div/div[2]/a"));
    public static Target TIL_CALENDARIO = Target.the("Click Boton inicio sesion").located(By.xpath(""));

}
