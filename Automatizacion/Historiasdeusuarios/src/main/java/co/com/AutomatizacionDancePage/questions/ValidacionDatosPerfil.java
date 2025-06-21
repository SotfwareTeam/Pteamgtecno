package co.com.AutomatizacionDancePage.questions;

import co.com.AutomatizacionDancePage.models.DatosParaPerfil;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Value;

import static co.com.AutomatizacionDancePage.userinterface.DatosPerfil.*;

public class ValidacionDatosPerfil implements Question<Boolean> {

    public static ValidacionDatosPerfil validacionDatosPerfil() {
        return new ValidacionDatosPerfil();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        DatosParaPerfil datos = actor.recall("datosPerfil");

        String nombreActual = Value.of(INPUT_NOMBRE).viewedBy(actor).asString();
        String emailActual = Value.of(INPUT_EMAIL).viewedBy(actor).asString();
        String telefonoActual = Value.of(INPUT_TELEFONO).viewedBy(actor).asString();
        String direccionActual = Value.of(INPUT_DIRECCION).viewedBy(actor).asString();

        return datos.getNombre().equals(nombreActual) &&
                datos.getEmail().equals(emailActual) &&
                datos.getTelefono().equals(telefonoActual) &&
                datos.getDireccion().equals(direccionActual);
    }
}


