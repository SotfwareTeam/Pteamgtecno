package co.com.AutomatizacionDancePage.userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class Register extends PageObject {

    public static Target BTN_INGRESO = Target.the(" Boton de registro").located(By.xpath("//*[@id=\"app\"]/body/div/div[3]/div[1]/button"));
    public static Target INPUT_NOMBRE  = Target.the(" Campo de nombre").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[1]/input"));

    public static Target INPUT_APELLIDO  = Target.the(" Confirmacion de apellido").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[2]/input"));

    public static Target INPUT_TIPODEDOCUMENTO  = Target.the(" Campo tipo de documento").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[3]/select"));

    public static Target INPUT_CEDULA  = Target.the(" Campo cedula").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[3]/select/option[2]"));
    public static Target INPUT_NUMERODEDOCUMENTO  = Target.the(" Campo de numero de documento").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[4]/input"));

    public static Target INPUT_TELEFONO = Target.the(" Campo de telefono").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[5]/input"));

    public static Target INPUT_GENERO= Target.the(" Campo de genero").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[6]/select"));

    public static Target INPUT_MASCULINO= Target.the(" Campo masculino").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[6]/select/option[2]"));

    public static Target INPUT_EDAD = Target.the(" Campo de edad").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[7]/input"));

    public static Target INPUT_NOMBREDEUSUARIO = Target.the(" Campo de nombre de usuario").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[8]/input"));

    public static Target INPUT_CORREO = Target.the(" Eleccion de correo").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/div[9]/input"));

    public static Target INPUT_CONTRASEÑA = Target.the(" Eleccion de contraseña").located(By.xpath("/html/body/div[1]/body/div/div[2]/form/div[10]/input"));

    public static Target BTN_REGISTER = Target.the(" Boton de registrarse").located(By.xpath("//*[@id=\"app\"]/body/div/div[2]/form/button"));

    public static Target BTN_INICIARSESION = Target.the("Boton de Login").located(By.xpath( "//*[@id=\"app\"]/body/div/div[3]/div[2]/button"));
    public static Target VALIDACION = Target.the(" Texto de Validacion").located(By.xpath("//*[@id=\"app\"]/body/div/div[1]/form/a"));
}
