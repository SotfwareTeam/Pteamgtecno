package co.com.AutomatizacionDancePage.stepsdefinitions;


import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.questions.Hu1Validation;
import co.com.AutomatizacionDancePage.questions.Hu2Validation;
import co.com.AutomatizacionDancePage.tasks.Hu2Tasks;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;


import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu2StepDefinitions {
    @Cuando("^el usuario selecciona una clase programada y la cancela$")
    public void elUsuarioSeleccionaUnaClaseProgramadaYLaCancela(List<ClassesData> classes) {
        theActorInTheSpotlight().attemptsTo(Hu2Tasks.insertarDatos(classes));
    }

    @Entonces("^se deja de visualizar la clase programada$")
    public void seDejaDeVisualizarLaClaseProgramada() {
        theActorInTheSpotlight().should(seeThat(Hu2Validation.hu2Validation()));
    }
}
