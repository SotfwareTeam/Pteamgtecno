package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.WebDriver;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static jxl.biff.FormatRecord.logger;

public class CancelClassValidation implements Question<Boolean> {
    public static CancelClassValidation hu2Validation(){return new CancelClassValidation();}

    private WebDriver driver;

    @Override
    public Boolean answeredBy(Actor actor){
        try{
            String texto1 = Text.of(VALIDACION_PROFESOR_O_ELIMINADO).viewedBy(actor).asString();
            String texto2 = Text.of(VALIDACION_TITULO_MODAL).viewedBy(actor).asString();
            return "No hay clase programada para este día.".equals(texto1) && "Información del Día".equals(texto2);
        } catch (Exception e){
            logger.info(" Validations doesn´t match");
            return false;
        }
    }
}
