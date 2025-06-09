package co.com.AutomatizacionDancePage.questions;

import co.com.AutomatizacionDancePage.utils.hooks.ClasesVariables;
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
        //String profesor = actor.recall(ClasesVariables.profesor.toString());
        //String profesorValidacion = "Profesor: " + profesor;

        try{
            //String texto1 = Text.of(VALIDACION_PROFESOR).viewedBy(actor).asString();
            //String texto2 = Text.of(VALIDACION_HORA).viewedBy(actor).asString();
            //String texto3 = Text.of(VALIDACION_CLASE).viewedBy(actor).asString();
            //String texto4 = Text.of(VALIDACION_DIFICULTAD).viewedBy(actor).asString();
            //return profesorValidacion.equals(texto1) && "Hora: 08:00:00".equals(texto2) &&
            //        "Clase: Salsa".equals(texto3) && "Dificultad: Principiante".equals(texto4);
            String texto1 = Text.of(VALIDACION_MES).viewedBy(actor).asString();
            String texto2 = Text.of(BOTON_FECHA1).viewedBy(actor).asString();
            String texto3 = Text.of(DIA_FECHA1).viewedBy(actor).asString();
            return "julio 2025".equals(texto1) && "1".equals(texto2) && "Tue".equals(texto3);
        } catch (Exception e){
            logger.info(" Validations doesn´t match");
            return false;
        }
    }
}
