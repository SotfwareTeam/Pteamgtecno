package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.models.CredencialesInicioSesion;
import co.com.AutomatizacionDancePage.tasks.RedirigirPerfil;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;


import java.util.List;

import static co.com.AutomatizacionDancePage.userinterface.autenticacion.*;


public class Autenticarse implements Task{

    private List<CredencialesInicioSesion> credenciales;


    public  Autenticarse(List<CredencialesInicioSesion> credenciales){
        this.credenciales = credenciales;
    }

    public static Autenticarse aute(List<CredencialesInicioSesion> credenciales){
        return Instrumented.instanceOf(Autenticarse.class).withProperties(credenciales);
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(INPUT_CORREO),
                Enter.theValue(credenciales.get(0).getCorreo()).into(INPUT_CORREO),
                Click.on(INPUT_CONTRASEÑA),
                Enter.theValue(credenciales.get(0).getContraseña()).into(INPUT_CONTRASEÑA),
                Click.on(BTN_INICIOSESION)

        );
    }

}