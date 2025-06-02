package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.questions.ValidacionRedireccionCalendarioTitulo;
import co.com.AutomatizacionDancePage.tasks.AbrirPagina;
import co.com.AutomatizacionDancePage.tasks.Redireccioneventos;
import co.com.AutomatizacionDancePage.tasks.hu6pap;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu6StepDefinitions {

    @Dado("^que el usuario se encuentra en la pagina de inicio$")
    public void queElUsuarioSeEncuentraEnLaPaginaDeInicio() {
        theActorInTheSpotlight().wasAbleTo(AbrirPagina.laPagina());
        theActorInTheSpotlight().attemptsTo(Redireccioneventos.ahora());
    }


    @Cuando("^hace clic en el botón Eventos de la barra de navegación y en el modulo calendario$")
    public void haceClicEnElBotónDeLaBarraDeNavegaciónYEnElModuloCalendario() {
        theActorInTheSpotlight().attemptsTo(hu6pap.ahora());
    }

    @Entonces("^debe visualizarse la página de calendario con las clases programadas$")
    public void debeVisualizarseLaPáginaDeCalendarioConLasClasesProgramadas() {
        theActorInTheSpotlight().should(seeThat(ValidacionRedireccionCalendarioTitulo.validacionRedireccionCalendarioTitulo()));
    }
}

