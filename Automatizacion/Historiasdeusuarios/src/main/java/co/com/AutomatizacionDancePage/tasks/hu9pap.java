package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.AutomatizacionDancePage.userinterface.hu9defpasos.BTN_COMPETENCIAS;

public class hu9pap implements Task {
    public static hu9pap ahora() {
        return Instrumented.instanceOf(hu9pap.class).withProperties();
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_COMPETENCIAS)
        );
    }
}
