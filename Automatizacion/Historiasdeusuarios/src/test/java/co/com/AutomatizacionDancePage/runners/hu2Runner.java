package co.com.AutomatizacionDancePage.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/hu11.feature",
        glue = {"co.com.AutomatizacionDancePage.stepsdefinitions", "co.com.AutomatizacionDancePage.utils.hooks"},
        snippets = SnippetType.CAMELCASE)

public class hu2Runner {
}
