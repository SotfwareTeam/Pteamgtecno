package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import static co.com.AutomatizacionDancePage.userinterface.Redireccion.BTN_BASKET;

public class RedirigirAlBasket implements Task {
    public static RedirigirAlBasket ahora() {
        return Instrumented.instanceOf(RedirigirAlBasket.class).withProperties();
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_BASKET)
        );
    }
}
