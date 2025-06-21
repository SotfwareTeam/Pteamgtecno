package co.com.AutomatizacionDancePage.questions;

import co.com.AutomatizacionDancePage.utils.hooks.ClasesVariables;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static jxl.biff.FormatRecord.logger;

public class Hu4Validation implements Question<Boolean> {
    public static Hu4Validation hu4Validation() {return new Hu4Validation();}

    @Override
    public Boolean answeredBy(Actor actor){
        String profesor = actor.recall(ClasesVariables.profesor.toString());
        String profesorValidacion = "Profesor: " + profesor;
        String clase = actor.recall(ClasesVariables.clase.toString());
        String claseValidacion = "Clase: " + clase;

        try{
            String texto1 = Text.of(VALIDACION_PROFESOR_O_ELIMINADO).viewedBy(actor).asString();
            String texto2 = Text.of(VALIDACION_HORA).viewedBy(actor).asString();
            String texto3 = Text.of(VALIDACION_CLASE).viewedBy(actor).asString();
            String texto4 = Text.of(VALIDACION_DIFICULTAD).viewedBy(actor).asString();
            return profesorValidacion.equals(texto1) && "Hora: 08:00:00".equals(texto2) &&
                    claseValidacion.equals(texto3) && "Dificultad: Principiante".equals(texto4);
        } catch (Exception e){
            logger.info(" Validations doesn´t match");
            return false;
        }
    }
}
