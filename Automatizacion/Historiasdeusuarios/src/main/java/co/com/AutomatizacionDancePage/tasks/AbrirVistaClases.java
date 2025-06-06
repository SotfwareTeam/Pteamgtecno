package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.userinterface.VistaClases;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirVistaClases implements Task {
    VistaClases vistaClases;
    public static AbrirVistaClases laPagina(){ return Tasks.instrumented(AbrirVistaClases.class);}

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.wasAbleTo(Open.browserOn(vistaClases));
    }
}
