package co.com.AutomatizacionDancePage.tasks;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.DriverTask;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;


public class AceptarAlert2 implements Task {

    public static DriverTask toAlert() {
        return new DriverTask(driver -> driver.switchTo().alert().accept());
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = net.serenitybdd.core.Serenity.getWebdriverManager().getCurrentDriver();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
