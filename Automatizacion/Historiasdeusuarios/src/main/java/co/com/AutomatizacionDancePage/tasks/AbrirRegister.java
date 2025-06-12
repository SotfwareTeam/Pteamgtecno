package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.userinterface.InicioRegister;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;


public class AbrirRegister implements Task{

    InicioRegister inicioRegister;

    public static AbrirRegister lapagina(){ return Tasks.instrumented(AbrirRegister.class);}

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.wasAbleTo(Open.browserOn(inicioRegister));

    }

}
