package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Text;


import static jxl.biff.FormatRecord.logger;
import static co.com.AutomatizacionDancePage.userinterface.autenticacion.MENSAJE_LOGIN;


public class ValidacionLogin implements Question<Boolean>{

    public static ValidacionLogin ValidacionLogin() {
        return new ValidacionLogin();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            String texto = Text.of(MENSAJE_LOGIN).viewedBy(actor).asString();
            return "Somos una academia dedicada a enseñar los mejores estilos de baile, brindando una experiencia única para todos los niveles de habilidad.".equals(texto);
        } catch (Exception e) {
            logger.info(" No encontró el texto o hubo otro error");
            return false;
        }
    }
}
