package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.WebDriver;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static jxl.biff.FormatRecord.logger;

public class Hu2Validation implements Question<Boolean> {
    public static Hu2Validation hu2Validation(){return new Hu2Validation();}

    private WebDriver driver;

    @Override
    public Boolean answeredBy(Actor actor){
        try{
            String texto1 = Text.of(VALIDACION_MES).viewedBy(actor).asString();
            String texto2 = Text.of(BOTON_FECHA2).viewedBy(actor).asString();
            String texto3 = Text.of(DIA_FECHA2).viewedBy(actor).asString();
            //WebElement boton_fecha1 = driver.findElement(By.xpath(xpathFecha1));
            //String color = GetCssProperties.getProperty(boton_fecha1, "background-color");
            return "julio 2025".equals(texto1) && "10".equals(texto2) && "Thu".equals(texto3);
            // || "#28a745".equals(color)
        } catch (Exception e){
            logger.info(" Validations doesn´t match");
            return false;
        }
    }
}
