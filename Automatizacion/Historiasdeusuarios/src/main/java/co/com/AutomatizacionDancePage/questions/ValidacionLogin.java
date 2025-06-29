package co.com.AutomatizacionDancePage.questions;

import co.com.AutomatizacionDancePage.models.CredencialesInicioSesion;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Text;


import static jxl.biff.FormatRecord.logger;
import static co.com.AutomatizacionDancePage.userinterface.autenticacion.MENSAJE_LOGIN;


public class ValidacionLogin implements Question<Boolean>{

    public static ValidacionLogin ValidacionLogin() {
        return new ValidacionLogin();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            CredencialesInicioSesion credenciales = actor.recall("credenciales-sesion");
            System.out.println(("Correo Electrónico: " + credenciales.getCorreo()));
            return MENSAJE_LOGIN.resolveFor(actor).isVisible();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
