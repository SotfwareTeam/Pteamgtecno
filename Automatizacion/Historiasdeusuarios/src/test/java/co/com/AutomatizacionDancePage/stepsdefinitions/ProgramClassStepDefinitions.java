package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.questions.ProgramClassValidation;
import co.com.AutomatizacionDancePage.tasks.Hu1Tasks;
import co.com.AutomatizacionDancePage.tasks.Redireccioneventos;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu1StepDefinitions {
    @Dado("^que el usuario se ha autenticado$")
    public void queElUsuarioSeHaAutenticado() {
        theActorInTheSpotlight().attemptsTo(Redireccioneventos.ahora());
    }
    @Cuando("^el usuario diligencia los campos requeridos$")
    public void elUsuarioDiligenciaLosCamposRequeridos(List<ClassesData> classes) {
        theActorInTheSpotlight().attemptsTo(Hu1Tasks.insertarDatos(classes));
    }

    @Entonces("^se visualiza la clase programada$")
    public void seVisualizaLaClaseProgramada() {
        theActorInTheSpotlight().should(seeThat(ProgramClassValidation.hu1Validation()));
    }
}