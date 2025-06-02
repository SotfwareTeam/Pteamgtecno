package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.questions.ValidacionCompetencias;
import co.com.AutomatizacionDancePage.tasks.Redireccioneventos;
import co.com.AutomatizacionDancePage.tasks.hu9pap;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu9StepDefinitions {
    @Cuando("^hace clic en el botón \"([^\"]*)\" de la barra de navegación y en el modulo Competencias$")
    public void haceClicEnElBotónDeLaBarraDeNavegaciónYEnElModuloCompetencias(String arg1) {
        theActorInTheSpotlight().attemptsTo(Redireccioneventos.ahora());
        theActorInTheSpotlight().attemptsTo(hu9pap.ahora());

    }


    @Entonces("^debe mostrarse una sección emergente con información gestionada por el administrador para las competencias$")
    public void debeMostrarseUnaSecciónEmergenteConInformaciónGestionadaPorElAdministradorParaLasCompetencias() {
        theActorInTheSpotlight().should(seeThat(ValidacionCompetencias.validacionCompetencias()));
    }

}
