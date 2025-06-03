package co.com.AutomatizacionDancePage.stepsdefinitions;



import co.com.AutomatizacionDancePage.models.DatosParaPerfil;
import co.com.AutomatizacionDancePage.questions.ValidacionDatosPerfil;
import co.com.AutomatizacionDancePage.tasks.AbrirPagina;
import co.com.AutomatizacionDancePage.tasks.LlenadoDatosPerfil;
import co.com.AutomatizacionDancePage.tasks.RedirigirAlPerfil;

import cucumber.api.DataTable;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class hu5StepDefinitions {
    @Dado("^que el usuario se encuentra en la pagina de perfil$")
    public void queElUsuarioSeEncuentraEnLaPaginaDePerfil() {
        theActorInTheSpotlight().wasAbleTo(AbrirPagina.laPagina());
        theActorInTheSpotlight().attemptsTo(RedirigirAlPerfil.ahora());
    }

    @Cuando("^el usuario hace clic en el ícono de perfil ubicado en la parte superior derecha y registra nueva informacion$")
    public void elUsuarioHaceClicEnElÍconoDePerfilUbicadoEnLaParteSuperiorDerechaYRegistraNuevaInformacion(List<DatosParaPerfil> datos) {
        theActorInTheSpotlight().attemptsTo(LlenadoDatosPerfil.dat(datos));
    }

    @Entonces("^se debe mostrar que la informacion cambio y poder salir del aplicativo$")
    public void seDebeMostrarQueLaInformacionCambioYPoderSalirDelAplicativo() {
        ;
    }

}







