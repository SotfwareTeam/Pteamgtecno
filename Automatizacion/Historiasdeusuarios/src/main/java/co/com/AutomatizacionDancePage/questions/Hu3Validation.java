package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static jxl.biff.FormatRecord.logger;

public class Hu3Validation implements Question<Boolean> {
    public static Hu3Validation hu3Validation(){return new Hu3Validation();}

    @Override
    public Boolean answeredBy(Actor actor){
        try{
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
