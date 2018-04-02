/**
 * This class for user database
 * @author Yuan Cui
 * @version 1.0
 */

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features="./features/user.feature",
        format={"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json"},
        snippets=SnippetType.CAMELCASE, glue="steps")

public class UserCucumberTest {

}
