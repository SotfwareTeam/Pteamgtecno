package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.hu9defpasos.MODAL_COMPETENCIAS;
import static co.com.AutomatizacionDancePage.userinterface.hu9defpasos.MODAL_TITULO_COMPETENCIAS;
import static jxl.biff.FormatRecord.logger;

public class ValidacionCompetencias implements Question<Boolean> {
    public static ValidacionCompetencias validacionCompetencias() { return new ValidacionCompetencias();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String Modal_sub = Text.of(MODAL_COMPETENCIAS).viewedBy(actor).asString();
            String titulo = Text.of(MODAL_TITULO_COMPETENCIAS).viewedBy(actor).asString();
            return Modal_sub.contains("Información sobre las competencias y torneos.") &&
                    titulo.contains("Detalles de Competencias");
        } catch (Exception e) {
            logger.info("No encontró el texto de las competencias");
            return false;
        }
    }
}