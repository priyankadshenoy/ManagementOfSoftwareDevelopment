package msd.front.end;

/*********************************************************
 * Prerequisite: MongoDB local instance to be installed 
 *               on the system.
 * This class is used to parse the dblp.xml data
 *********************************************************/
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import msd.core.Article;
import msd.core.Author;
import msd.core.Conference;
import msd.front.end.interfaces.DataReader;

public class ReadXML extends DefaultHandler implements DataReader {

	private Conference conf;
	private Article article;
	private Author author;
	String text;
	static boolean hasConference = false;
	static boolean hasArticle = false;
	static boolean hasAuthor = false;
	static boolean testHasConference = false;
	static boolean testHasArticle = false;
	static boolean testHasAuthor = false;
	static boolean testParsingSuccess = false;
	static ConnectMongoD cm ;


	/**
	 * The default constructor
	 */
	public ReadXML() {
		
	}

	/**
	 * reads the xml using SAX parser
	 * @param xmlPath : path of the xml file
	 * @param uri : connection string of mongo
	 * @throws ParserConfigurationException : throws  ParserConfigurationException
	 * @throws SAXException : throws SAXException
	 * @throws IOException : throws IOException
	 */
	public ReadXML(String xmlPath, String uri) throws ParserConfigurationException, SAXException, IOException {
		cm = new ConnectMongoD(uri);
		SAXParserFactory spfac = SAXParserFactory.newInstance();

		//Now use the parser factory to create a SAXParser object
		SAXParser sp = spfac.newSAXParser();

		//Create an instance of this class; it defines all the handler methods 
		ReadXML handler = new ReadXML();
		//Finally, tell the parser to parse the input and notify the handler

		setTestHasArticle(false);
		sp.parse(xmlPath, handler);

		testParsingSuccess = true;
		
		cm.joinArticleAndAuthor();
		cm.closeConnection();
	}

	/*
	 * When the parser encounters plain text (not XML elements),
	 * it calls(this method, which accumulates them in a string buffer
	 */
	@Override
	public void characters(char[] buffer, int start, int length) {
		text = new String(buffer, start, length);
	}


	/*
	 * Every time the parser encounters the beginning of a new element,
	 * it calls this method, which resets the string buffer
	 */ 
	@Override
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {

		if ("proceedings".equalsIgnoreCase(qName)) {
			setHasConference(true);
			setTestHasConference(true);
			conf = new Conference();
			conf.setKey(attributes.getValue("key"));
			conf.setMdate(attributes.getValue("mdate"));
		}
		if ("article".equalsIgnoreCase(qName) || "inproceedings".equalsIgnoreCase(qName)) {
			setHasArticle(true);
			setTestHasArticle(true);
			article = new Article();
			article.setKey(attributes.getValue("key"));
			article.setMdate(attributes.getValue("mdate"));
		}
		
		if ("www".equalsIgnoreCase(qName)) {
			setHasAuthor(true);
			setTestHasAuthor(true);
			author = new Author();	
		}
	}

	/*
	 * When the parser encounters the end of an element, it calls this method
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		checkHasConference(uri, localName, qName);
		checkHasArticle(uri, localName, qName);
		checkHasAuthor(uri, localName, qName);
	}

	/**
	 * Method that helps in processing the conference data and store in mongo
	 * @param uri : connection string of mongo
	 * @param localName : given local name
	 * @param qName : tag name
	 *
	 */
	public void checkHasConference(String uri, String localName, String qName){

		if ("proceedings".equalsIgnoreCase(qName)) {
			cm.putConfInMongo(conf);
			setHasConference(false);

		}
		if(hasConference){
			if ("volume".equalsIgnoreCase(qName)) {
				conf.setVolume(text);
			} else if ("ee".equalsIgnoreCase(qName)) {
				conf.setEe(text);
			} else if ("editor".equalsIgnoreCase(qName)) {
				conf.setEditor(text);
			} else if("title".equalsIgnoreCase(qName)) {
				conf.setTitle(text);
			} else if("year".equalsIgnoreCase(qName)) {
					conf.setYear(Integer.parseInt(text));
			} else if("isbn".equalsIgnoreCase(qName)) {
				conf.setIsbn(text);
			} else if("booktitle".equalsIgnoreCase(qName)) {
				conf.setBookTitle(text);
			} else if("publisher".equalsIgnoreCase(qName)) {
				conf.setPublisher(text);
			}  
		}
	}

	/**
	 * Method that helps in processing the article data and store in mongo
	 * @param uri : connection string for mongo
	 * @param localName : local name
	 * @param qName : tag name
	 */
	public void checkHasArticle(String uri, String localName, String qName) {
		if ("article".equalsIgnoreCase(qName) || "inproceedings".equalsIgnoreCase(qName)) {
			cm.putArticleInMongo(article);
			setHasArticle(false);
		}
		if(hasArticle){
			if ("volume".equalsIgnoreCase(qName)) {
				article.setVolume(text);
			} else if ("ee".equalsIgnoreCase(qName)) {
				article.setEe(text);
			} else if ("journal".equalsIgnoreCase(qName)) {
				article.setJournalName(text);
			} else if ("conference".equalsIgnoreCase(qName)) {
				article.setConferenceName(text);
			}else if ("author".equalsIgnoreCase(qName)) {
				article.setAuthor(text);
			}else if ("title".equalsIgnoreCase(qName)) {
				article.setTitle(text);
			}else if ("pages".equalsIgnoreCase(qName)) {
				article.setPages(text);
			}else if ("year".equalsIgnoreCase(qName)) {
				article.setYearPublished(Integer.parseInt(text));
			}else if ("crossref".equalsIgnoreCase(qName)) {
				article.seCrossRef(text);
			}else if ("booktitle".equalsIgnoreCase(qName)) {
				article.setBooktitle(text);
			}
		}
	}

	/**
	 * Method that helps in processing the author data and store in mongo
	 * @param uri : connection string of mongoDB
	 * @param localName : input local name
	 * @param qName : tag name
	 */
	public void checkHasAuthor(String uri, String localName, String qName) {

		if(hasAuthor){
			if ("author".equalsIgnoreCase(qName)) {
				author.setName(text);
			} else if ("cite".equalsIgnoreCase(qName)) {
				author.setCitationCount(1);
			}
		}
		if ("www".equalsIgnoreCase(qName)) {
			cm.putAuthorInMongo(author);
			setHasAuthor(false);
		}
		
	}


	/**
	 * Method to instantiate the connection to mongo db
	 * 
	 */
	public void readInput(String inputFilePath, String uri) throws ParserConfigurationException, SAXException, IOException {
		new ReadXML(inputFilePath, uri);
	}



	public void sendInput(String input) {
		// Auto-generated method stub
	}

	/**************Getters and Setters*******************/
	public  boolean getHasConference() {
		return testHasConference;
	}

	public  boolean getHasArticle() {
		return testHasArticle;
	}

	public  boolean getHasAuthor() {
		return testHasAuthor;
	}

	public  boolean getParserSuccess() {
		return testParsingSuccess;
	}

	public static void setHasConference(boolean hasConference) {
		ReadXML.hasConference = hasConference;
	}

	public static void setHasArticle(boolean hasArticle) {
		ReadXML.hasArticle = hasArticle;
	}

	public static void setHasAuthor(boolean hasAuthor) {
		ReadXML.hasAuthor = hasAuthor;
	}

	public static void setTestHasConference(boolean hasConference) {
		ReadXML.testHasConference = hasConference;
	}

	public static void setTestHasArticle(boolean hasArticle) {
		ReadXML.testHasArticle = hasArticle;
	}

	public static void setTestHasAuthor(boolean hasAuthor) {
		ReadXML.testHasAuthor = hasAuthor;
	}

}



