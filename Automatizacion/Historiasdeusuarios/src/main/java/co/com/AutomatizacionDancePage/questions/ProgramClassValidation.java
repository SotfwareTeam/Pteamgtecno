package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.awt.*;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static jxl.biff.FormatRecord.logger;

public class ProgramClassValidation implements Question<Boolean> {
    public static ProgramClassValidation hu1Validation(){
        return new ProgramClassValidation();
    }

    @Override
    public Boolean answeredBy(Actor actor){
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement element = driver.findElement(By.xpath(xpathFecha1));

        try{
            String colorRgb = element.getCssValue("background-color");
            String colorHex = Color.fromString(colorRgb).asHex();
            String texto1 = Text.of(VALIDACION_MES).viewedBy(actor).asString();
            String texto2 = Text.of(BOTON_FECHA1).viewedBy(actor).asString();
            String texto3 = Text.of(DIA_FECHA1).viewedBy(actor).asString();
            return "agosto 2025".equals(texto1) && "1".equals(texto2) &&
                    "Fri".equals(texto3) && "#28a745".equals(colorHex);
        } catch (Exception e){
            logger.info(" Validations doesn´t match");
            return false;
        }
    }
}
