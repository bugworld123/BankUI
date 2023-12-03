package co.wedevx.digitalbank.automation.ui.runners;

import io.cucumber.core.backend.Glue;
import io.cucumber.core.feature.GluePath;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ui/features")
@ConfigurationParameter(key= GLUE_PROPERTY_NAME,value="co.wedevx.digitalbank.automation.ui.steps")

public class UiRegressionRunner {



}
