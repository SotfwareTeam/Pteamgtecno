package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import co.com.AutomatizacionDancePage.models.DatosParaPerfil;

import java.util.List;

import static co.com.AutomatizacionDancePage.userinterface.DatosPerfil.*;

public class LlenadoDatosPerfil implements Task {
    private List<DatosParaPerfil> datos;
    public LlenadoDatosPerfil(List<DatosParaPerfil> datos){
        this.datos = datos;
    }
    public static LlenadoDatosPerfil dat(List<DatosParaPerfil> datos){
        return Instrumented.instanceOf(LlenadoDatosPerfil.class).withProperties(datos);
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(INPUT_NOMBRE),
                Enter.theValue(datos.get(0).getNombre()).into(INPUT_NOMBRE),
                Click.on(INPUT_EMAIL),
                Enter.theValue(datos.get(0).getEmail()).into(INPUT_EMAIL),
                Click.on(INPUT_TELEFONO),
                Enter.theValue(datos.get(0).getTelefono()).into(INPUT_TELEFONO),
                Click.on(INPUT_DIRECCION),
                Enter.theValue(datos.get(0).getDireccion()).into(INPUT_DIRECCION),
                Click.on(BTN_CONTINUECHECKS),
                AceptarAlert.ahora(),
                Click.on(INPUT_NOMBRE),
                Enter.theValue(datos.get(0).getNombre()).into(INPUT_NOMBRE),
                Click.on(INPUT_EMAIL),
                Enter.theValue(datos.get(0).getEmail()).into(INPUT_EMAIL),
                Click.on(INPUT_TELEFONO),
                Enter.theValue(datos.get(0).getTelefono()).into(INPUT_TELEFONO),
                Click.on(INPUT_DIRECCION),
                Enter.theValue(datos.get(0).getDireccion()).into(INPUT_DIRECCION),
                Click.on(BTN_CONTINUECHECKS)



        );
    }
}
