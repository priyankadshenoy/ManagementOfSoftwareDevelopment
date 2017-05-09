package frontend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestXMLParser.class, TestCommitteeParser.class, TestAuthorDataParser.class})
public class FrontEndTestSuite {

}
