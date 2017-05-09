package frontend;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Test;
import org.xml.sax.SAXException;

import msd.front.end.CommitteeParser;

public class TestCommitteeParser {

	CommitteeParser data = new CommitteeParser();
	String uri = "mongodb://easy:easy@ds129090.mlab.com:29090/cs5500-msd";
	//CommitteeParser cp = new CommitteeParser();

	@After
	public void tearDown() throws Exception {
	}


	
	/**
	 * Test case to check if the data contains committee  information, is the parsing 
	 * successful	
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void isDataSaved() throws ParserConfigurationException, SAXException, IOException {

		data.readInput("Resources/cpTest", uri);
		boolean test = data.sendBackDataForTest();
		assertEquals(true, test);
	}


	/**
	 * Test case to check if the data contains Chair  information, is the parsing 
	 * successful	
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void containsChair() throws ParserConfigurationException, SAXException, IOException {
		data.readInput("Resources/cpTest", uri);	
		assertEquals(true, data.containsChair);
	}
	

	/**
	 * Test case to check if the data contains ExternalChair  information, is the parsing 
	 * successful	
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void containsExternalChair() throws ParserConfigurationException, SAXException, IOException {
		data.readInput("Resources/cpTest", uri);	
		assertEquals(true, data.containsEC);
	}
	

	/**
	 * Test case to check if the data not contain ProgramChair  information, is the parsing 
	 * successful	
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void doesNotHaveProgramChair() throws ParserConfigurationException, SAXException, IOException {
		data.readInput("Resources/comitteeMalformed", uri);	
		assertEquals(false, data.containsPC);
	}
	

	/**
	 * Test case to check if the data not contai GeneralChair  information, is the parsing 
	 * successful	
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void doesNotHaveGeneralChair() throws ParserConfigurationException, SAXException, IOException {
		data.readInput("Resources/comitteeMalformed", uri);	
		assertEquals(false, data.containsGC);
	}
	

	/**
	 * Test case to check if the entire file data was read and parsed
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */	
	@Test
	public void fileRead() throws ParserConfigurationException, SAXException, IOException {
		data.readInput("Resources/comitteeMalformed", uri);	
		assertEquals(true, data.fileRead);
	}
	

	/**
	 * Test case to check if the invalid folder path is handled
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void fileDoesNotExist() throws ParserConfigurationException, SAXException, IOException {
		data.readInput("/fakePath", uri);	
		assertEquals(false, data.fileExists);
	}
	
	/**
	 * Test case to check if the connection to mongo is closed
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void connectionToDBClosed() throws ParserConfigurationException, SAXException, IOException {
		data.readInput("/fakePath", uri);	
		assertEquals(true, data.isConnectionClosed);
	}
	
	@Test
	public void emptyCalls() {
		assertEquals(false, data.getHasConference());
		assertEquals(false, data.getHasArticle());
		assertEquals(false, data.getHasAuthor());
		assertEquals(false, data.getParserSuccess());	
	}
	
	
}