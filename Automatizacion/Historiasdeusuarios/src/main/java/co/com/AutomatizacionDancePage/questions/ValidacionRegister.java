package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.Register.VALIDACION;
import static jxl.biff.FormatRecord.logger;


public class ValidacionRegister implements Question<Boolean>{
    public static ValidacionRegister validacionRegister() {
        return new ValidacionRegister();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String texto = Text.of(VALIDACION).viewedBy(actor).asString();
            return "¿Olvidaste tu contraseña?".equals(texto);
        } catch (Exception e) {
            logger.info(" No encontró el texto o hubo otro error");
            return false;
        }
    }
}
