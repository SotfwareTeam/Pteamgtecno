package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.hu7defpasos.*;
import static jxl.biff.FormatRecord.logger;

public class ValidacionProfesores implements Question<Boolean> {

    public static ValidacionProfesores validacionProfesores() { return new ValidacionProfesores();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String Profe1 = Text.of(MODAL_PROFESORES).viewedBy(actor).asString();
            String Profe1_sub = Text.of(MODAL_PROFESORES_SUB).viewedBy(actor).asString();
            String Profe2 = Text.of(MODAL_PROFESORES2).viewedBy(actor).asString();
            String Profe2_sub = Text.of(MODAL_PROFESORES2_SUB).viewedBy(actor).asString();
            String Profe3 = Text.of(MODAL_PROFESORES3).viewedBy(actor).asString();
            String Profe3_sub = Text.of(MODAL_PROFESORES3_SUB).viewedBy(actor).asString();
            return Profe1.contains("Profesor 1") &&
                    Profe1_sub.contains("Experto en salsa y bachata.") &&
                    Profe2.contains("Profesor 2") &&
                    Profe2_sub.contains("Especialista en tango y danza contemporánea.") &&
                    Profe3.contains("Profesor 3") &&
                    Profe3_sub.contains("Instructor de hip-hop y breakdance.");
        } catch (Exception e) {
            logger.info("No encontró el texto del titulo");
            return false;
        }
    }



}
