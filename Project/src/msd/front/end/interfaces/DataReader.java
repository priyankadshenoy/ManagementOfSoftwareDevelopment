package msd.front.end.interfaces;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/*************************************************************
The Front End Component:
 *************************************************************/

/* The interface is used to fetch the data from the source.
 * it is then read and sent to the DataParser*/
public interface DataReader {
	// read the data from source (eg. from DBLP datastore)
	/**
	 * 
	 * @param inputFilePath : path where the csv files are stored
	 * @param uri : connection string of the mongoDB, where the data should be finally stored
	 * @throws ParserConfigurationException : throws ParserConfigurationException
	 * @throws SAXException : throws SAXException
	 * @throws IOException : throws IOException
	 */
	
	void readInput(String inputFilePath, String uri) throws ParserConfigurationException, SAXException, IOException ;
	// send the data to the DataParer
	void sendInput(String input) ;
	boolean getHasConference();
	boolean getHasArticle();
	boolean getHasAuthor();
	boolean getParserSuccess();
}



