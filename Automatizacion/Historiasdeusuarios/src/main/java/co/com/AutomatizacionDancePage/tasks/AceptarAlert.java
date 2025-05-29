package co.com.AutomatizacionDancePage.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.Tasks;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;


public class AceptarAlert implements Task {

    public static AceptarAlert ahora() {
        return Tasks.instrumented(AceptarAlert.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = net.serenitybdd.core.Serenity.getWebdriverManager().getCurrentDriver();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
