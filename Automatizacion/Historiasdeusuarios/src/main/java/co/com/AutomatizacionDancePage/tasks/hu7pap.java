package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.AutomatizacionDancePage.userinterface.hu7defpasos.BTN_PROFESORES;

public class hu7pap implements Task {
    public static hu7pap ahora() {
        return Instrumented.instanceOf(hu7pap.class).withProperties();
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_PROFESORES)
        );
    }
}
