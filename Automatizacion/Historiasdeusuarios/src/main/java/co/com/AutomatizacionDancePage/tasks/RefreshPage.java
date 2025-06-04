package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class RefreshPage implements Task {
    public static RefreshPage now() {
        return new RefreshPage();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().navigate().refresh();
    }
}