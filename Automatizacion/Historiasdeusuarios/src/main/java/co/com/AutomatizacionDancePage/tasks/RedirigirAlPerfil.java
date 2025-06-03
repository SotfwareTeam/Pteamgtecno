package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import static co.com.AutomatizacionDancePage.userinterface.Redireccion.BTN_PERFIL;

public class RedirigirAlPerfil implements Task {
    public static RedirigirAlPerfil ahora() {
        return Instrumented.instanceOf(RedirigirAlPerfil.class).withProperties();
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_PERFIL)
        );
    }
}
