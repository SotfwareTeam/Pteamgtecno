package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.WebDriver;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static jxl.biff.FormatRecord.logger;

public class Hu1Validation implements Question<Boolean> {
    public static Hu1Validation hu1Validation(){
        return new Hu1Validation();
    }

    private WebDriver driver;

    @Override
    public Boolean answeredBy(Actor actor){
        try{
            String texto1 = Text.of(VALIDACION_MES).viewedBy(actor).asString();
            String texto2 = Text.of(BOTON_FECHA1).viewedBy(actor).asString();
            //WebElement boton_fecha1 = driver.findElement(By.xpath(xpathFecha1));
            //String color = GetCssProperties.getProperty(boton_fecha1, "background-color");
            return "julio 2025".equals(texto1) && "1".equals(texto2);
            // || "#28a745".equals(color)
        } catch (Exception e){
            logger.info(" Validations doesn´t match");
            return false;
        }
    }
}
