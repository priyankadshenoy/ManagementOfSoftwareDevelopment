package allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import frontend.FrontEndTestSuite;
import queryEngine.QueryEngineTestSuite;
import userInterface.UITestSuite;

@RunWith(Suite.class)
@SuiteClasses({FrontEndTestSuite.class, QueryEngineTestSuite.class, UITestSuite.class})
public class AllTests {

}
