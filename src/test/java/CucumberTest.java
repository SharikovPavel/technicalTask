/**
 * Created by Sharikov Pavel on 13.10.2017.
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, plugin = {"pretty"},
        glue = {"helperClasses", "stepDefs"},
        features = {"src/test/resources/features/"},
        tags ={"@LithelLoginYandex"}
)
public class CucumberTest {
}