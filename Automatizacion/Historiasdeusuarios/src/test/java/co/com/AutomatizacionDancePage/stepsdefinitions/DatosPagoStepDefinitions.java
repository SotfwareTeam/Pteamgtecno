package co.com.AutomatizacionDancePage.stepsdefinitions;

import co.com.AutomatizacionDancePage.models.DatosParaPago;
import co.com.AutomatizacionDancePage.questions.ValidacionDatosPago;
import co.com.AutomatizacionDancePage.tasks.RedirigirAlBasket;
import co.com.AutomatizacionDancePage.tasks.LlenadoDatosPago;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class DatosPagoStepDefinitions {
    @Dado("^el usuario se dirija a la pantalla de basket$")
    public void elUsuarioSeDirijaALaPantallaDeBasket() {
        theActorInTheSpotlight().attemptsTo(RedirigirAlBasket.ahora());
    }

    @Cuando("^ingrese las credenciales correctas \\(nombre, apellido, email, direccion, country, city, zip, tarjeta, numero_tarjeta, expiration, cvv\\)$")
    public void ingreseLasCredencialesCorrectasNombreApellidoEmailDireccionCountryCityZipTarjetaNumero_tarjetaExpirationCvv(List<DatosParaPago> datos) {
        theActorInTheSpotlight().attemptsTo(LlenadoDatosPago.dat(datos));
    }

    @Entonces("^se debe verificar que el usuario haya completado sus datos y recargar la pagina de inicio de Dulceria com campos vacios$")
    public void seDebeVerificarQueElUsuarioHayaCompletadoSusDatosYRecargarLaPaginaDeInicioDeDulceriaComCamposVacios() {
        theActorInTheSpotlight().should(seeThat(ValidacionDatosPago.validacionDatosPago()));
    }

}
