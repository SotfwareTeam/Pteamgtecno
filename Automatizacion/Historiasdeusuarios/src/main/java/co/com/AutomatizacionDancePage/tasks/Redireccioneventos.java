package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.AutomatizacionDancePage.userinterface.Redireccion.BTN_EVENTOS;

public class Redireccioneventos implements Task {
    public static Redireccioneventos ahora() {
        return Instrumented.instanceOf(Redireccioneventos.class).withProperties();
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_EVENTOS)
        );
    }
}
