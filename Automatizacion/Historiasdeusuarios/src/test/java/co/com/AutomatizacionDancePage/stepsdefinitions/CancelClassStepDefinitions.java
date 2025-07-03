package co.com.AutomatizacionDancePage.stepsdefinitions;


import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.questions.CancelClassValidation;
import co.com.AutomatizacionDancePage.tasks.CancelClassTasks;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;


import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CancelClassStepDefinitions {
    @Cuando("^el usuario selecciona una clase programada y la cancela$")
    public void elUsuarioSeleccionaUnaClaseProgramadaYLaCancela(List<ClassesData> classes) {
        theActorInTheSpotlight().attemptsTo(CancelClassTasks.insertarDatos(classes));
    }

    @Entonces("^se deja de visualizar la clase programada$")
    public void seDejaDeVisualizarLaClaseProgramada() {
        theActorInTheSpotlight().should(seeThat(CancelClassValidation.hu2Validation()));
    }
}
