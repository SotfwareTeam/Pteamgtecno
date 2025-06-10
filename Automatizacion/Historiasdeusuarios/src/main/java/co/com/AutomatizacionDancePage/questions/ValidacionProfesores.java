package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.hu7defpasos.MODAL_PROFESORES;
import static jxl.biff.FormatRecord.logger;

public class ValidacionProfesores implements Question<Boolean> {

    public static ValidacionProfesores validacionProfesores() { return new ValidacionProfesores();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String texto = Text.of(MODAL_PROFESORES).viewedBy(actor).asString();
            return "Profesor 1".equals(texto);
        } catch (Exception e) {
            logger.info("No encontró el texto del titulo");
            return false;
        }
    }



}

