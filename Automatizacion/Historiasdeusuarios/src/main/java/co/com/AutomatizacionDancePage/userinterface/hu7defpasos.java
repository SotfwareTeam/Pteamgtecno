package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class hu7defpasos extends PageObject {
    public static Target BTN_PROFESORES = Target.the(" boton de profesores").located(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[2]/img"));
    public static Target MODAL_PROFESORES = Target.the(" PROFESORES").located(By.xpath("//*[@id=\"profesoresModal\"]/div/div/div[2]/div/div[1]/div/div/h5"));
    public static Target MODAL_PROFESORES_SUB = Target.the(" PROFESORES").located(By.xpath("//*[@id=\"profesoresModal\"]/div/div/div[2]/div/div[1]/div/div/p"));

    public static Target MODAL_PROFESORES2 = Target.the(" PROFESORES2").located(By.xpath("//*[@id=\"profesoresModal\"]/div/div/div[2]/div/div[2]/div/div/h5"));
    public static Target MODAL_PROFESORES2_SUB = Target.the(" PROFESORES2").located(By.xpath("//*[@id=\"profesoresModal\"]/div/div/div[2]/div/div[2]/div/div/p"));
    public static Target MODAL_PROFESORES3 = Target.the(" PROFESORES3").located(By.xpath("//*[@id=\"profesoresModal\"]/div/div/div[2]/div/div[3]/div/div/h5"));
    public static Target MODAL_PROFESORES3_SUB = Target.the(" PROFESORES3").located(By.xpath("//*[@id=\"profesoresModal\"]/div/div/div[2]/div/div[3]/div/div/p"));
}