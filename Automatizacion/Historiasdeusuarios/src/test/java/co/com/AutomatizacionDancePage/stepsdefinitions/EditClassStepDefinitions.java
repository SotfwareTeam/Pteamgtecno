package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.questions.EditClassValidation;
import co.com.AutomatizacionDancePage.tasks.EditClassTasks;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class EditClassStepDefinitions {
    @Cuando("^el usuario selecciona una clase programada y la edita$")
    public void elUsuarioSeleccionaUnaClaseProgramadaYLaEdita(List<ClassesData> classes) {
        theActorInTheSpotlight().attemptsTo(EditClassTasks.insertarDatos(classes));
    }

    @Entonces("^se visualiza la clase ya editada$")
    public void seVisualizaLaClaseYaEditada() {
        theActorInTheSpotlight().should(seeThat(EditClassValidation.hu3Validation()));
    }
}
