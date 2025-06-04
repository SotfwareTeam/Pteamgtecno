package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.questions.Hu1Validation;
import co.com.AutomatizacionDancePage.questions.Hu2Validation;
import co.com.AutomatizacionDancePage.tasks.Hu2Tasks;
import co.com.AutomatizacionDancePage.tasks.Hu3Tasks;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu3StepDefinitions {
    @Cuando("^el usuario selecciona una clase programada y la edita$")
    public void elUsuarioSeleccionaUnaClaseProgramadaYLaEdita(List<ClassesData> classes) {
        theActorInTheSpotlight().attemptsTo(Hu3Tasks.insertarDatos(classes));
    }

    @Entonces("^se visualiza la clase ya editada$")
    public void seVisualizaLaClaseYaEditada() {
        theActorInTheSpotlight().should(seeThat(Hu1Validation.hu1Validation()));
    }
}
