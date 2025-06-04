package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.questions.Hu1Validation;
import co.com.AutomatizacionDancePage.tasks.AbrirVistaClases;
import co.com.AutomatizacionDancePage.tasks.Hu1Tasks;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu1StepDefinitions {

    @Dado("^que el usuario ingresa al calendario$")
        public void queElUsuarioIngresaAlCalendario() {
        theActorInTheSpotlight().wasAbleTo(AbrirVistaClases.laPagina());
    }

    @Cuando("^el usuario diligencia los campos requeridos$")
    public void elUsuarioDiligenciaLosCamposRequeridos(List<ClassesData> classes) {
        theActorInTheSpotlight().attemptsTo(Hu1Tasks.insertarDatos(classes));
    }

    @Entonces("^se visualiza la clase programada$")
    public void seVisualizaLaClaseProgramada() {
        theActorInTheSpotlight().should(seeThat(Hu1Validation.hu1Validation()));
    }
}