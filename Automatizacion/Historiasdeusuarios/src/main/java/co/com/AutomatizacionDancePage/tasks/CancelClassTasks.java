package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.models.ClassesData;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CancelClassTasks implements Task {

    private List<ClassesData> classes;
    public CancelClassTasks(List<ClassesData> classes) {this.classes = classes;}
    public static CancelClassTasks insertarDatos(List<ClassesData> classes){
        return Instrumented.instanceOf(CancelClassTasks.class).withProperties(classes);
    }

    @Override

    public <T extends Actor> void performAs(T actor){
        actor.attemptsTo(
                Click.on(BTN_CALENDARIO),
                Click.on(BTN_CALENDARIOMODAL),
                RefreshPage.now(),
                Click.on(BOTON_MES_SIG),
                Click.on(BOTON_FECHA2),
                Click.on(BOTON_PROGRAMAR),
                Click.on(INPUT_PROFESOR),
                Enter.theValue(classes.get(0).getProfesor()).into(INPUT_PROFESOR),
                Click.on(INPUT_HORA),
                Enter.theValue(classes.get(0).getHora()).into(INPUT_HORA),
                Click.on(INPUT_CLASE),
                Enter.theValue(classes.get(0).getClase()).into(INPUT_CLASE),
                Click.on(INPUT_DIFICULTAD),
                Click.on(SELECT_MEDIO),
                Click.on(BOTON_GUARDAR),
                Click.on(BOTON_ELIMINAR),
                WaitUntil.the(MODAL_ELIMINAR, isVisible()).forNoMoreThan(10).seconds(),
                WaitUntil.the(MODAL_ELIMINAR, isEnabled()).forNoMoreThan(10).seconds(),
                Click.on(MODAL_ELIMINAR),
                Click.on(BOTON_INFORMACION)
        );
    }
}
