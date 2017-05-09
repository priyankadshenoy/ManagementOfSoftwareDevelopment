package queryEngine;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import msd.queryEngine.QueryFormatter;
import msd.queryEngine.interfaces.OutputFormatter;
import msd.user.inf.AuthorQuery;
import msd.user.inf.UserQuery;
import msd.user.inf.interfaces.InputCapture;

public class TestQueryFormatter {

	@After
	public void tearDown() throws Exception {
	}
	MongoClient mongoClient = null;
	MongoDatabase database = null;
	public void setup(){
		String dbURI = "mongodb://easy:easy@ds129090.mlab.com:29090/cs5500-msd";
		mongoClient=new MongoClient(new MongoClientURI(dbURI));
		database=mongoClient.getDatabase( "cs5500-msd" );
	}

	@Test
	public void getAuthorForKeyWords() {
		// to check whether query by keywords is invoked or not

		/*String keyword, int fromTime,int toTime, String conferenceName, int count, int paper , boolean confOrCpm */
		setup();
		UserQuery uq = new UserQuery("n", 2000, 2001, "Empty", -1, -1, false);
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		qf.transformUserQueryObject(uq);
		assertEquals(true, QueryFormatter.titleTest);
		mongoClient.close();
	}
	@Test
	public void getAuthorForKeyWordsWithCitationCount() {
		// to check whether query by keywords is invoked or not
		setup();
		UserQuery uq = new UserQuery("Empty", 2000, 2001, "oopsla", 0, -1, false );
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		qf.transformUserQueryObject(uq);
		assertEquals(true, QueryFormatter.citeTest);
		mongoClient.close();
	}

	@Test
	public void getAuthorForconference() {
		// to test whether query by conference is invoked or not
		setup();
		UserQuery uq = new UserQuery("Empty", 2013, 2017, "Empty", -1, 1, false );
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		qf.transformUserQueryObject(uq);
		assertEquals(true, QueryFormatter.articleTest);
		mongoClient.close();
	}
	@Test
	public void getAuthorForconferenceWhenCountZero() {
		// to test whether query by conference is invoked or not when counts are > -1
		setup();
		UserQuery uq = new UserQuery("Empty", 1999, 2001, "eds", -1, -1, false);
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		qf.transformUserQueryObject(uq);
		assertEquals(true, QueryFormatter.articleConferenceTest);
		mongoClient.close();
	}

	@Test
	public void getAuthorForconferenceWhenCount() {
		// to test whether query by conference is invoked or not when counts are >
		setup();
		UserQuery uq = new UserQuery("Empty", 1999, 2001, "eds", -1, 1, false );
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		qf.transformUserQueryObject(uq);
		assertEquals(true, QueryFormatter.articleConferenceTestBoth);
		mongoClient.close();
	}
	@Test
	public void getAuthorForcommittee() {
		// to test whether query for committee is invoked or not
		setup();
		UserQuery uq = new UserQuery("Empty", 2223, 2223, "oopsla", -1, -1, true);
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		qf.transformUserQueryObject(uq);
		assertEquals(true, QueryFormatter.committeeTest);
		mongoClient.close();
	}
	@Test
	public void getAuthorDetails() {
		//to test whether query by citation count is invoked or not
		setup();
		InputCapture in = new AuthorQuery("Kui Ren");
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		List<OutputFormatter> test =qf.transformUserQueryObject(in);
		for(OutputFormatter t: test){
			t.getAuthor();
			t.getFieldOfStudy();
			t.getHomePage();
			t.getRegion();
			t.getUniversity();
		}

		assertEquals(true, QueryFormatter.authorTest);
		mongoClient.close();
	}

	@Test
	public void getNothing() {
		// to test whether nothing is queried for when user query is wrong
		setup();
		UserQuery uq = new UserQuery("Empty", 2013, 2017, "Empty", -1, -1, false );
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		List<OutputFormatter> a = qf.transformUserQueryObject(uq);
		assertEquals(null, a);
		mongoClient.close();
	}

	@Test
	public void getDataForProcessedOutput() {
		// to test whether nothing is queried for when user query is wrong
		setup();
		UserQuery uq = new UserQuery("n", 2000, 2001, "Empty", -1, -1, false);
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		List<OutputFormatter> a = qf.transformUserQueryObject(uq);
		for(OutputFormatter out: a){
			out.getTitle();
			out.getConferenceName();
			out.getBookTitle();
			out.getCount();
			out.getCitationCount();
		}
		assertEquals(true, QueryFormatter.titleTest);
		mongoClient.close();
	}

	@Test
	public void getAuthorForconferenceSetters() {
		// to test whether query by conference is invoked or not
		setup();
		UserQuery uq = new UserQuery("Empty", 2000, 2017, "oopsla", -1, 1, true );
		QueryFormatter qf = new QueryFormatter();
		qf.createConnection(database);
		List<OutputFormatter> test=qf.transformUserQueryObject(uq);
		for(OutputFormatter t: test){
			t.getMembers();
			t.getDesignation();
		}
		assertEquals(true, QueryFormatter.committeeTest);
		mongoClient.close();
	}
}
