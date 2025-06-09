package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.h6defpasos.TIL_CALENDARIO;
import static jxl.biff.FormatRecord.logger;

public class ValidacionRedireccionCalendarioTitulo implements Question<Boolean> {
    public static ValidacionRedireccionCalendarioTitulo validacionRedireccionCalendarioTitulo() { return new ValidacionRedireccionCalendarioTitulo();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String texto = Text.of(TIL_CALENDARIO).viewedBy(actor).asString();
            return "Programar Clase".equals(texto);
        } catch (Exception e) {
            logger.info("No encontró el texto del titulo");
            return false;
        }
    }



}
