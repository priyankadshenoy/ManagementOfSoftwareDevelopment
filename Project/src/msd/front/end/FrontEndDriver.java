package msd.front.end;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import msd.front.end.interfaces.DataReader;

/**
 * This main class runs the front end. i.e. runs the parser and populates
 * the mongo db database
 */
public class FrontEndDriver {

	private FrontEndDriver() {

		//Do Nothing
	}

	/**
	 * @param args : DBLP folder path, committee data folder path, author data dolder path
	 * @throws ParserConfigurationException : throws ParserConfigurationException
	 * @throws SAXException : throws SAXException
	 * @throws IOException : throws SAXException
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		DataReader data = new ReadXML();
		//for local connection
		//String uri = "mongodb://localhost:27017/DBLP";
		String uri = args[3];

		//for mLab connection on test data
		//String uri = "mongodb://easy:easy@ds129090.mlab.com:29090/cs5500-msd";

		//for mLab connection on completely parsed data
		//String uri = "mongodb://final:final@ds145790.mlab.com:45790/finaldata";
		data.readInput(args[0], uri);

		//To parse the committee data
		DataReader committee = new CommitteeParser();
		committee.readInput(args[1], uri);
		
		// author data parser
		DataReader authorsData = new AuthorDataParser();
		authorsData.readInput(args[2], uri);

	}
}
