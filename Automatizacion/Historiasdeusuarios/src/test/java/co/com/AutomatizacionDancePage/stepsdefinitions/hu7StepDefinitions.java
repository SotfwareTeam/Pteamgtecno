package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.questions.ValidacionProfesores;
import co.com.AutomatizacionDancePage.questions.ValidacionRedireccionCalendarioTitulo;
import co.com.AutomatizacionDancePage.tasks.AbrirPagina;
import co.com.AutomatizacionDancePage.tasks.Redireccioneventos;
import co.com.AutomatizacionDancePage.tasks.hu7pap;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu7StepDefinitions {
    @Cuando("^hace clic en el botón Profesores$")
    public void haceClicEnElBotónProfesores() {
        theActorInTheSpotlight().attemptsTo(Redireccioneventos.ahora());
        theActorInTheSpotlight().attemptsTo(hu7pap.ahora());
    }


    @Entonces("^debe mostrarse una sección emergente con información gestionada por el administrador para losprofesores$")
    public void debeMostrarseUnaSecciónEmergenteConInformaciónGestionadaPorElAdministradorParaLosprofesores() {
        theActorInTheSpotlight().should(seeThat(ValidacionProfesores.validacionProfesores()));
    }

}
