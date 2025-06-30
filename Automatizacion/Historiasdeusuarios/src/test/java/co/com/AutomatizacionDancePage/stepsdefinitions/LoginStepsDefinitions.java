package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.models.CredencialesInicioSesion;
import co.com.AutomatizacionDancePage.questions.ValidacionLogin;
import co.com.AutomatizacionDancePage.tasks.AbrirPagina;
import co.com.AutomatizacionDancePage.tasks.Autenticarse;
import co.com.AutomatizacionDancePage.tasks.RedirigirPerfil;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepsDefinitions {
    @Dado("^que el usuario se encuentra en la pagina de inicio de sesion de Dance$")
    public void queElUsuarioSeEncuentraEnLaPaginaDeInicioDeSesionDeDance() {
        theActorInTheSpotlight().wasAbleTo(AbrirPagina.laPagina());
    }

    @Cuando("^ingrese las credenciales correctas \\(usuario y contrasena\\)$")
    public void ingreseLasCredencialesCorrectasUsuarioYContrasena(List<CredencialesInicioSesion> credenciales) {
        theActorInTheSpotlight().remember("credenciales-sesion", credenciales.get(0));
        theActorInTheSpotlight().attemptsTo(Autenticarse.aute(credenciales));
    }

    @Entonces("^se debe verificar que el usuario haya sido autenticado correctamente y redirigido a su pagina de inicio de Dance$")
    public void seDebeVerificarQueElUsuarioHayaSidoAutenticadoCorrectamenteYRedirigidoASuPaginaDeInicioDeDance() {
        theActorInTheSpotlight().attemptsTo(RedirigirPerfil.ahora());
        theActorInTheSpotlight().should(seeThat(ValidacionLogin.ValidacionLogin()));
    }

}
