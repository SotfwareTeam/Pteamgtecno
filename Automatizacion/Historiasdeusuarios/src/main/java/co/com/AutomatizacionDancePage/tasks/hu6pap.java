package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.AutomatizacionDancePage.userinterface.h6defpasos.*;

public class hu6pap implements Task {
    public static hu6pap ahora() {
        return Instrumented.instanceOf(hu6pap.class).withProperties();
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_CALENDARIO),
                Click.on(BTN_CALENDARIOMODAL),
                RefreshPage.now()
        );
    }
}
