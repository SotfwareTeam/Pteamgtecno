package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import static co.com.AutomatizacionDancePage.userinterface.Redireccion.BTN_PERFIL;

public class RedirigirPerfil implements Task {
    public static RedirigirPerfil ahora() {
        return Instrumented.instanceOf(RedirigirPerfil.class).withProperties();
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_PERFIL)
        );
    }
}
