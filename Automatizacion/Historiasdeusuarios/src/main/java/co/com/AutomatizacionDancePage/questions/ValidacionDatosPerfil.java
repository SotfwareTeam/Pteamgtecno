package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.DatosPerfil.INPUT_NOMBRE;
import static jxl.biff.FormatRecord.logger;

public class ValidacionDatosPerfil implements Question<Boolean> {
    public static ValidacionDatosPerfil validacionDatosPerfil() {
        return new ValidacionDatosPerfil();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String texto = Text.of(INPUT_NOMBRE).viewedBy(actor).asString();
            return "Juan Peña".equals(texto);
        } catch (Exception e) {
            logger.info(" No encontró el texto o hubo otro error");
            return false;
        }
    }
}
