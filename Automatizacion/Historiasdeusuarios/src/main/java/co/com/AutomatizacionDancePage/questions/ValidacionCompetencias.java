package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.hu9defpasos.MODAL_COMPETENCIAS;
import static jxl.biff.FormatRecord.logger;

public class ValidacionCompetencias implements Question<Boolean> {
    public static ValidacionCompetencias validacionCompetencias() { return new ValidacionCompetencias();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String texto = Text.of(MODAL_COMPETENCIAS).viewedBy(actor).asString();
            return "Información sobre las competencias y torneos.".equals(texto);
        } catch (Exception e) {
            logger.info("No encontró el texto de las competencias");
            return false;
        }
    }
}
