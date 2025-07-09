package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.utils.hooks.ClasesVariables;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EditClassTasks implements Task {
    private List<ClassesData> classes;
    public EditClassTasks(List<ClassesData> classes) {this.classes = classes;}

    public static EditClassTasks insertarDatos(List<ClassesData> classes){
        return Instrumented.instanceOf(EditClassTasks.class).withProperties(classes);
    }

    @Override

    public <T extends Actor> void performAs(T actor){
        actor.attemptsTo(
                Click.on(BOTON_FECHA1),
                Click.on(BOTON_INFORMACION),
                WaitUntil.the(MODAL_CERRAR, isVisible()).forNoMoreThan(10).seconds(),
                WaitUntil.the(MODAL_CERRAR, isEnabled()).forNoMoreThan(10).seconds(),
                Click.on(MODAL_CERRAR),
                Scroll.to(BOTON_PROGRAMAR),
                Click.on(BOTON_PROGRAMAR),
                Click.on(INPUT_PROFESOR),
                Click.on(SELECT_PROFESOR),
                Click.on(INPUT_HORA),
                Enter.theValue(classes.get(0).getHora()).into(INPUT_HORA),
                Click.on(INPUT_CLASE),
                Enter.theValue(classes.get(0).getClase()).into(INPUT_CLASE),
                Click.on(INPUT_DIFICULTAD),
                Click.on(SELECT_DIFICIL),
                Click.on(BOTON_GUARDAR),
                Click.on(BOTON_INFORMACION)
        );

        actor.remember(ClasesVariables.profesor.toString(), classes.get(0).getProfesor());
        actor.remember(ClasesVariables.clase.toString(), classes.get(0).getClase());
    }
}
