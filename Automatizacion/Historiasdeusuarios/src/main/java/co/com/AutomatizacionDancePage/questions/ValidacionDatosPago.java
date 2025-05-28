package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import static co.com.AutomatizacionDancePage.userinterface.DatosPago.MENSAJE_BASKET;
import static jxl.biff.FormatRecord.logger;

public class ValidacionDatosPago implements Question<Boolean> {
    public static ValidacionDatosPago validacionDatosPago() {
        return new ValidacionDatosPago();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String texto = Text.of(MENSAJE_BASKET).viewedBy(actor).asString();
            return "Your Basket".equals(texto);
        } catch (Exception e) {
            logger.info(" No encontró el texto o hubo otro error");
            return false;
        }
    }
}
