package queryEngine;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import msd.queryEngine.QueryEngine;
import msd.queryEngine.interfaces.OutputFormatter;


public class TestQueryEngine {
	static MongoClient mongoClient;
	static MongoDatabase database;
	@After
	public void tearDown() throws Exception {
	}

	public static void setup(){
		String dbURI = "mongodb://easy:easy@ds129090.mlab.com:29090/cs5500-msd";
		mongoClient = new MongoClient(new MongoClientURI(dbURI));
		database = mongoClient.getDatabase( "cs5500-msd" );
	}
	@Test
	public void getAuthorForKeyWords() {
		setup();
		QueryEngine qe = new QueryEngine();
		qe.setDB(database);
		qe.getAuthorsByTitle("ancd", 0, 2000, 2007);
		assertEquals(true, QueryEngine.getAuthorForKeyWordsInvoked);
		mongoClient.close();
	}

	@Test
	public void getAuthorsOfACommittee() {
		setup();
		QueryEngine qe = new QueryEngine();
		qe.setDB(database);
		ArrayList<OutputFormatter> out = qe.getAuthorsByCommittee("oopsla", 2000, 2001);
		String s = "";
		for(OutputFormatter s1 : out) {
			s+= s1.getMembers();
		}
		String result = "Blah1Blah1Blah1";
		assertEquals(s, result);
		mongoClient.close();
	}

	@Test
	public void getAuthorsForConferenceName() {
		setup();
		QueryEngine qe = new QueryEngine();
		qe.setDB(database);
		qe.getAuthorsByConference("oopsla", 2000, 2001);
		assertEquals(true, QueryEngine.getAuthorsForConferenceNameInvoked);
		mongoClient.close();
	}

	@Test
	public void getAuthorsForCitationCount() {
		setup();
		QueryEngine qe = new QueryEngine();
		qe.setDB(database);
		qe.getAuthorsByCitationCount(0, 2000,2017);
		assertEquals(true, QueryEngine.getAuthorByCitationCountInvoked);
		mongoClient.close();
	}

	@Test
	public void getAuthorByArticleCount() {
		setup();
		QueryEngine qe = new QueryEngine();
		qe.setDB(database);
		qe.getAuthorsByArticleCount(0, 1970, 2017);
		assertEquals(true, QueryEngine.getAuthorByArticleCountInvoked);
		mongoClient.close();
	}

	@Test
	public void getAuthorsDetailsTest(){
		setup();
		QueryEngine qe = new QueryEngine();
		qe.setDB(database);
		qe.getAuthorsDetails("Nita Parekh");
		assertEquals(true, QueryEngine.getAuthorsDetailsInvoled);
		mongoClient.close();
	}

	@Test
	public void getAuthorsByArticleCountAndConferenceTest(){
		setup();
		QueryEngine qe = new QueryEngine();
		qe.setDB(database);
		qe.getAuthorsByArticleCountAndConference(1, "oopsla", 2000, 2001);
		assertEquals(true, QueryEngine.getAuthorsByArticleCountAndConferenceInvoked);
		mongoClient.close();
	}
}
