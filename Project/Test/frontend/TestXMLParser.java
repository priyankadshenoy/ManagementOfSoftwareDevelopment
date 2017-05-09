package frontend;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.xml.sax.SAXException;
import msd.front.end.*;
import msd.front.end.interfaces.DataReader;

public class TestXMLParser {

	 DataReader data = new ReadXML();
	String fileNotFound = "File Not Found!";
	String uri = "mongodb://easy:easy@ds129090.mlab.com:29090/cs5500-msd";

	/**
	 * Test case to check if the data contains conference information, is the parsing 
	 * successfu
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	
	@Test 
	public void conferenceDataExist() throws ParserConfigurationException, SAXException, IOException {
		 data.readInput("Resources/Test/testXml.xml", uri);
		assertEquals(true ,data.getHasConference());
	}
	
	
	/**
	 * Test case to check if the data contains article information, is the parsing 
	 * successful
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void articleDataExist() throws ParserConfigurationException, SAXException, IOException {
		 data.readInput("Resources/Test/testXmlExtended.xml", uri);
		assertEquals(true ,data.getHasArticle());
	}
	

	/**
	 * Test case to check if the data contains author information, is the parsing 
	 * successful
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test 
	public void authorDataExist() throws ParserConfigurationException, SAXException, IOException {
		 data.readInput("Resources/Test/testXmlExtended.xml", uri);
		assertEquals(true ,data.getHasAuthor());
	}
	
	/**
	 * Test case to check if the data does not contain article  information, is the parsing 
	 * successful
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test 
	public void articleDataNotExist() throws ParserConfigurationException, SAXException, IOException {
		 data.readInput("Resources/Test/testXml.xml", uri);
		assertEquals(false ,data.getHasArticle());
	}
	
	/**
	 *  Test case to check if the data contains inproceedings  information, is the parsing 
	 * successful
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test 
	public void inproceedingsDataExist() throws ParserConfigurationException, SAXException, IOException {
		 data.readInput("Resources/Test/testXml.xml", uri);
		assertEquals(false ,data.getHasArticle());
	}
	
	/**
	 * Test case to check if the data was parsed completely
	 */
	@Test
	public void parsingSuccess(){
		assertEquals(true, data.getParserSuccess());
	}

}
