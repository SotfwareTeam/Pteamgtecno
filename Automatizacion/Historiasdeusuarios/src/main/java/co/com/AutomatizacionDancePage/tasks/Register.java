package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.models.CredencialesRegister;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;

import java.util.List;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.MODAL_CERRAR;
import static co.com.AutomatizacionDancePage.userinterface.Register.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class Register implements Task{
    private List<CredencialesRegister> credenciales;

    public Register(List<CredencialesRegister> credenciales) {
        this.credenciales = credenciales;
    }

    public static Register aute(List<CredencialesRegister> credenciales) {
        return Instrumented.instanceOf(Register.class).withProperties(credenciales);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                JavaScriptClick.on(BTN_INGRESO),
                WaitUntil.the(INPUT_NOMBRE, isVisible()).forNoMoreThan(10).seconds(),
                WaitUntil.the(INPUT_NOMBRE, isEnabled()).forNoMoreThan(10).seconds(),
                Click.on(INPUT_NOMBRE),
                Enter.theValue(credenciales.get(0).getNombres()).into(INPUT_NOMBRE),
                Click.on(INPUT_APELLIDO),
                Enter.theValue(credenciales.get(0).getApellido()).into(INPUT_APELLIDO),
                Click.on(INPUT_TIPODEDOCUMENTO),
                Click.on(INPUT_CEDULA),
                Click.on(INPUT_NUMERODEDOCUMENTO),
                Enter.theValue(credenciales.get(0).getNumerodeidentificacion()).into(INPUT_NUMERODEDOCUMENTO),
                Click.on(INPUT_TELEFONO),
                Enter.theValue(credenciales.get(0).getTelefono()).into(INPUT_TELEFONO),
                Click.on(INPUT_GENERO),
                Click.on(INPUT_MASCULINO),
                Click.on(INPUT_EDAD),
                Enter.theValue(credenciales.get(0).getEdad()).into(INPUT_EDAD),
                Click.on(INPUT_NOMBREDEUSUARIO),
                Enter.theValue(credenciales.get(0).getNombredeusuario()).into(INPUT_NOMBREDEUSUARIO),
                Click.on(INPUT_CORREO),
                Enter.theValue(credenciales.get(0).getCorreo()).into(INPUT_CORREO),
                Click.on(INPUT_CONTRASEÑA),
                Enter.theValue(credenciales.get(0).getContraseña()).into(INPUT_CONTRASEÑA),

                Click.on(BTN_REGISTER),
                AceptarAlert2.toAlert(),

                Click.on(BTN_INICIARSESION)
        );
    }

}