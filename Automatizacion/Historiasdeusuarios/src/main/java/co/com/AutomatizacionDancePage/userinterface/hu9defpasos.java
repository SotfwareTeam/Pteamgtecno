package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class hu9defpasos extends PageObject {
    public static Target BTN_COMPETENCIAS = Target.the(" boton de Competencias").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[3]/img"));
    public static Target MODAL_COMPETENCIAS = Target.the(" COMPETENCIAS").located(By.xpath("//*[@id=\"competenciasModal\"]/div/div/div[2]/p"));
    public static Target MODAL_TITULO_COMPETENCIAS = Target.the(" COMPETENCIAS").located(By.xpath("//*[@id=\"competenciasModal\"]/div/div/div[1]/h5"));
}