package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class DatosPago extends PageObject {
    public static Target INPUT_NOMBRE = Target.the("Ingreso del nombre").located(By.xpath("//*[@id=\"name\"]"));
    public static Target INPUT_APELLIDO = Target.the("Ingreso del apellido").located(By.xpath("/html/body/div/div/div[2]/form/div[1]/div[2]/input"));
    public static Target INPUT_EMAIL = Target.the("Ingreso del email").located(By.xpath("//*[@id=\"email\"]"));
    public static Target INPUT_DIRECCION = Target.the("Ingreso del direccion").located(By.xpath("//*[@id=\"address\"]"));
    public static Target INPUT_DIRECCION2 = Target.the("Ingreso del direccion secundaria").located(By.xpath("//*[@id=\"address2\"]"));
    public static Target INPUT_PAIS = Target.the("Ingreso del pais").located(By.xpath("//*[@id=\"country\"]"));
    public static Target NOMBRE_PAIS = Target.the("Ingreso del pais").located(By.xpath("//*[@id=\"country\"]/option[2]"));
    public static Target INPUT_CIUDAD = Target.the("Ingreso del ciudad").located(By.xpath("//*[@id=\"city\"]"));
    public static Target NOMBRE_CIUDAD = Target.the("Ingreso del pais").located(By.xpath("//*[@id=\"city\"]/option[2]"));
    public static Target INPUT_ZIP = Target.the("Ingreso del zip").located(By.xpath("//*[@id=\"zip\"]"));
    public static Target INPUT_NOMBRETARJETA = Target.the("Ingreso del nombre de la tarjeta").located(By.xpath("//*[@id=\"cc-name\"]"));
    public static Target INPUT_NUMEROTARJETA = Target.the("Ingreso del numero de la tarjeta").located(By.xpath("//*[@id=\"cc-number\"]"));
    public static Target INPUT_EXPIRACION = Target.the("Ingreso fecha de expiracion").located(By.xpath("//*[@id=\"cc-expiration\"]"));
    public static Target INPUT_CVV = Target.the("Ingreso del cvv").located(By.xpath("//*[@id=\"cc-cvv\"]"));
    public static Target BTN_CONTINUECHECKS = Target.the("Click Boton continuar chequeo").located(By.xpath("/html/body/div/div/div[2]/form/button"));
    public static Target MENSAJE_BASKET = Target.the("mensaje de login").locatedBy("/html/body/div/header/h1");
}
