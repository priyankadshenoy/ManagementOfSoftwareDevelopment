package msd.front.end;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import msd.front.end.interfaces.DataReader;

// this is a adapter class for the committee parse and 
// author data parser
// both the parsers overrides readInput

/**
 * 
 * this is a adapter class for the committee parse and 
 * author data parser
 * both the parsers overrides readInput which in-turn invokes
 * the core parsing method
 *
 */
public class FrontEndAdapter implements DataReader {

	
	/**
	 * @param inputFilePath : path where the csv files are stored
	 * @param uri : connection string of the mongoDB, where the data should be finally stored
	 */
	@Override
	public void readInput(String inputFilePath, String uri)
			throws ParserConfigurationException, SAXException, IOException {
		
	}

	
	/**
	 * sends input
	 * @param input : input string
	 */
	@Override
	public void sendInput(String input) {
		
	}
	
	/**
	 * returns conference data is present or not
	 * @return conference : if data has conference or not
	 */
	@Override
	public boolean getHasConference() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * returns article is present or not
	 * @return article : if data has articles or not
	 */
	@Override
	public boolean getHasArticle() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * returns has author or not
	 * @return : if data has author or not
	 */
	@Override
	public boolean getHasAuthor() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * if parser successfully parsed or not
	 * @return boolean : get parser success
	 */
	@Override
	public boolean getParserSuccess() {
		// TODO Auto-generated method stub
		return false;
	}

}
