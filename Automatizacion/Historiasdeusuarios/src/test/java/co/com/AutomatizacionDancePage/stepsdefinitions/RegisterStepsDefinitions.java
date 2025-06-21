package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.models.CredencialesRegister;
import co.com.AutomatizacionDancePage.questions.ValidacionRegister;
import co.com.AutomatizacionDancePage.tasks.AbrirRegister;
import co.com.AutomatizacionDancePage.tasks.Register;
import cucumber.api.DataTable;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class RegisterStepsDefinitions {

    @Dado("^que el visitante está en la página de register$")
    public void queElVisitanteEstáEnLaPáginaDeRegister() {
        theActorInTheSpotlight().wasAbleTo(AbrirRegister.lapagina());
    }


    @Cuando("^ingrese los datos necesarios$")
    public void ingreseLosDatosNecesarios(List<CredencialesRegister> credenciales) {
        theActorInTheSpotlight().attemptsTo(Register.aute(credenciales));
    }


    @Entonces("^la cuenta debe ser creada y el usuario redirigido a la vista de login$")
    public void laCuentaDebeSerCreadaYElUsuarioRedirigidoALaVistaDeLogin() {
        theActorInTheSpotlight().should(seeThat(ValidacionRegister.validacionRegister()));
    }
}