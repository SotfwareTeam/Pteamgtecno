package co.com.AutomatizacionDancePage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.h6defpasos.MES_CALENDARIO;
import static co.com.AutomatizacionDancePage.userinterface.h6defpasos.TIL_CALENDARIO;
import static jxl.biff.FormatRecord.logger;

public class ValidacionRedireccionCalendarioTitulo implements Question<Boolean> {
    public static ValidacionRedireccionCalendarioTitulo validacionRedireccionCalendarioTitulo() { return new ValidacionRedireccionCalendarioTitulo();
    }

    @Override

    public Boolean answeredBy(Actor actor) {
        try {
            String textoTitulo = Text.of(TIL_CALENDARIO).viewedBy(actor).asString();
            String textoMes = Text.of(MES_CALENDARIO).viewedBy(actor).asString();
            return textoTitulo.contains("Programar Clase") &&
                    textoMes.contains("junio 2025");
        } catch (Exception e) {
            logger.info("No encontró los textos necesarios para validar");
            return false;
        }
    }



}