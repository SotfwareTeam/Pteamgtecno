package co.com.AutomatizacionDancePage.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/autenticationlogin_dulceria.feature",
        glue = {"co.com.AutomatizacionDulceriaPage.stepsdefinitions", "co.com.AutomatizacionDulceriaPage.utils.hooks"},
        snippets = SnippetType.CAMELCASE)

public class AutenticacionDulceriaRunner {
}
