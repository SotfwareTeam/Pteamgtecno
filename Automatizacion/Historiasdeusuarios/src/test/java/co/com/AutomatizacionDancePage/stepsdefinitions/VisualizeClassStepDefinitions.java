package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.questions.VisualizeClassValidation;
import co.com.AutomatizacionDancePage.tasks.VisualizeClassTasks;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class VisualizeClassStepDefinitions {
    @Cuando("^el usuario selecciona una fecha con una clase programada$")
    public void elUsuarioSeleccionaUnaFechaConUnaClaseProgramada() {
        theActorInTheSpotlight().attemptsTo(VisualizeClassTasks.insertarDatos());
    }

    @Entonces("^se visualizan las clases programadas de esa fecha$")
    public void seVisualizanLasClasesProgramadasDeEsaFecha() {
        theActorInTheSpotlight().should(seeThat(VisualizeClassValidation.hu4Validation()));
    }
}
