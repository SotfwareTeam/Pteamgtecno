package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import co.com.AutomatizacionDancePage.models.DatosParaPago;

import java.util.List;

import static co.com.AutomatizacionDancePage.userinterface.DatosPago.*;

public class LlenadoDatosPago implements Task {
    private List<DatosParaPago> datos;
    public  LlenadoDatosPago(List<DatosParaPago> datos){
        this.datos = datos;
    }
    public static LlenadoDatosPago dat(List<DatosParaPago> datos){
        return Instrumented.instanceOf(LlenadoDatosPago.class).withProperties(datos);
    }

    @Override

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(INPUT_NOMBRE),
                Enter.theValue(datos.get(0).getNombre()).into(INPUT_NOMBRE),
                Click.on(INPUT_APELLIDO),
                Enter.theValue(datos.get(0).getApellido()).into(INPUT_APELLIDO),
                Click.on(INPUT_EMAIL),
                Enter.theValue(datos.get(0).getEmail()).into(INPUT_EMAIL),
                Click.on(INPUT_DIRECCION),
                Enter.theValue(datos.get(0).getDireccion()).into(INPUT_DIRECCION),
                Click.on(INPUT_DIRECCION2),
                Enter.theValue(datos.get(0).getDireccion2()).into(INPUT_DIRECCION2),
                Click.on(INPUT_PAIS),
                Click.on(NOMBRE_PAIS),
                Click.on(INPUT_CIUDAD),
                Click.on(NOMBRE_CIUDAD),
                Click.on(INPUT_ZIP),
                Enter.theValue(datos.get(0).getZip()).into(INPUT_ZIP),
                Click.on(INPUT_NOMBRETARJETA),
                Enter.theValue(datos.get(0).getTarjeta()).into(INPUT_NOMBRETARJETA),
                Click.on(INPUT_NUMEROTARJETA),
                Enter.theValue(datos.get(0).getNumero_tarjeta()).into(INPUT_NUMEROTARJETA),
                Click.on(INPUT_EXPIRACION),
                Enter.theValue(datos.get(0).getExpiration()).into(INPUT_EXPIRACION),
                Click.on(INPUT_CVV),
                Enter.theValue(datos.get(0).getCvv()).into(INPUT_CVV),
                Click.on(BTN_CONTINUECHECKS)

        );
    }
}
