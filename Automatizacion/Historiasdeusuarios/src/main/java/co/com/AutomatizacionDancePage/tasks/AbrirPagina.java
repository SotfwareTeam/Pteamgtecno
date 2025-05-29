package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.userinterface.InicioDulceria;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirPagina implements Task {
    InicioDulceria inicioDulceria;
        public static AbrirPagina laPagina(){ return Tasks.instrumented(AbrirPagina.class);}

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.wasAbleTo(Open.browserOn(inicioDulceria));
    }
}
