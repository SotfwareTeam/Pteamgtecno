package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.models.ClassesData;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.DoubleClick;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Hu4Tasks implements Task {
    public static Hu4Tasks insertarDatos(){
        return Instrumented.instanceOf(Hu4Tasks.class).withProperties();
    }

    @Override
    public <T extends Actor> void performAs(T actor){
        actor.attemptsTo(
                Click.on(BOTON_FECHA2),
                Click.on(BOTON_INFORMACION),
                WaitUntil.the(MODAL_CERRAR, isVisible()).forNoMoreThan(10).seconds(),
                WaitUntil.the(MODAL_CERRAR, isEnabled()).forNoMoreThan(10).seconds(),
                Click.on(MODAL_CERRAR),
                Click.on(BOTON_FECHA1),
                Click.on(BOTON_INFORMACION)
        );
    }
}
