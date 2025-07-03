package co.com.AutomatizacionDancePage.tasks;

import org.openqa.selenium.WebElement;

public class GetCssProperties {
    public static String getProperty(WebElement element, String property){
        return element.getCssValue(property);
    }
}
