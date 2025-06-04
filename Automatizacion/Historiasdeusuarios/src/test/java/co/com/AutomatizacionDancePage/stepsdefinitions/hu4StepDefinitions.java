package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.questions.Hu1Validation;
import co.com.AutomatizacionDancePage.tasks.Hu3Tasks;
import co.com.AutomatizacionDancePage.tasks.Hu4Tasks;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu4StepDefinitions {
    @Cuando("^el usuario selecciona una fecha con una clase programada$")
    public void elUsuarioSeleccionaUnaFechaConUnaClaseProgramada() {
        theActorInTheSpotlight().attemptsTo(Hu4Tasks.insertarDatos());
    }

    @Entonces("^se visualizan las clases programadas de esa fecha$")
    public void seVisualizanLasClasesProgramadasDeEsaFecha() {
        theActorInTheSpotlight().should(seeThat(Hu1Validation.hu1Validation()));
    }
}
