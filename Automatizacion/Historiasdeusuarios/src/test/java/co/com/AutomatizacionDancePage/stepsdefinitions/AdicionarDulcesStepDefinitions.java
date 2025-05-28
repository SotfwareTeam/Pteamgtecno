package co.com.AutomatizacionDancePage.stepsdefinitions;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;

public class AdicionarDulcesStepDefinitions {

    @Cuando("^el usuario hace clic en la pestaña \"([^\"]*)\" del menú superior$")
    public void elUsuarioHaceClicEnLaPestañaDelMenúSuperior(String arg1) {
    }


    @Entonces("^debe mostrarse la sección con todos los dulces disponibles$")
    public void debeMostrarseLaSecciónConTodosLosDulcesDisponibles() {
    }

    @Cuando("^el usuario selecciona el producto \"([^\"]*)\"$")
    public void elUsuarioSeleccionaElProducto(String arg1) {
    }

    @Cuando("^hace clic en el botón \"([^\"]*)\"$")
    public void haceClicEnElBotón(String arg1) {
    }

    @Entonces("^el carrito debe reflejar (\\d+) artículo añadido correctamente$")
    public void elCarritoDebeReflejarArtículoAñadidoCorrectamente(int arg1) {
    }
}
