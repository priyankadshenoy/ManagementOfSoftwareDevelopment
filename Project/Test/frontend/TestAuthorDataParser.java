package frontend;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import msd.front.end.AuthorDataParser;
import msd.front.end.FrontEndAdapter;

public class TestAuthorDataParser {

	AuthorDataParser adp = new AuthorDataParser();
	// test mlab db
	String uri = "mongodb://easy:easy@ds129090.mlab.com:29090/cs5500-msd";

	
	@Test
	public void test1() throws ParserConfigurationException,FileNotFoundException, SAXException, IOException {

		// checking if the methods recognizes the folder exists or not 
		FrontEndAdapter fea = new FrontEndAdapter();
		fea.readInput("/fakepath", uri);
		fea.sendInput("");
		adp.readInput("/fakePath", uri);	
		assertEquals(false, adp.fileExists );
	}
	
	@Test
	public void test2() throws ParserConfigurationException,FileNotFoundException, SAXException, IOException {
		adp = new AuthorDataParser();
		// parsing the csv files and checking whether the generated hash is valid or not
		adp.readInput("Resources/authorTestData", uri);	
		assertEquals(true, adp.getHash().size()> 0? true : false);
	}
}