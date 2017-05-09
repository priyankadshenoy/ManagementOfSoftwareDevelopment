package msd.queryEngine;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import msd.queryEngine.interfaces.ExecutionEngine;
import msd.queryEngine.interfaces.OutputFormatter;
import msd.queryEngine.interfaces.QueryGenerator;
import msd.user.inf.interfaces.InputCapture;

//receives data from UI and calls different methods in QE according to the input
public class QueryFormatter implements QueryGenerator{

	ExecutionEngine ee = new QueryEngine();
	public void createConnection(MongoDatabase testDatabaseName) {
		ee.setDB(testDatabaseName);
	}
	public static boolean titleTest = false;
	public static boolean citeTest = false;
	public static boolean articleTest = false;
	public static boolean articleConferenceTest = false;
	public static boolean articleConferenceTestBoth = false;
	public static boolean committeeTest = false;
	public static boolean authorTest = false;

	@Override
	// according to the input received from user calls methods to get result set
	public List<OutputFormatter> transformUserQueryObject(InputCapture userQuery) {
		String keyword = userQuery.getKeyword();
		int fromYear = userQuery.getFromTime();
		int toYear = userQuery.getToTime();
		String conferenceName = userQuery.getConferenceName();
		int citationCount = userQuery.getCount();
		int articleCount = userQuery.getPaper();
		boolean flag = userQuery.isConfOrCpm();
		String authorName = userQuery.getAuthorName();
		// finding author details
		if(authorName != null && !authorName.isEmpty()){
			authorTest= true;
			return ee.getAuthorsDetails(authorName);
		}

		// finding authors who have published articles with specific keywords
		// finding authors who have published articles with specific keywords and citation count

		else if(!(keyword.equals("Empty"))){
			int citeCount = citationCount == -1 ? 0 : citationCount;
			titleTest = true;
			return ee.getAuthorsByTitle(keyword, citeCount, fromYear,toYear);

		}

		// finding authors with specific citation count
		else if(keyword.equals("Empty") && citationCount > -1){
			citeTest = true;
			return ee.getAuthorsByCitationCount(citationCount, fromYear,toYear);
		}

		// finding authors with specific article count
		else if(conferenceName.equals("Empty") && articleCount > -1) {
			articleTest = true;
			return ee.getAuthorsByArticleCount(articleCount, fromYear, toYear);
		}

		// finding authors with specific article count in a conference
		else if(!(conferenceName.equals("Empty")) && !flag){
			if(articleCount > -1){
				articleConferenceTestBoth=true;
				return ee.getAuthorsByArticleCountAndConference(articleCount, conferenceName, fromYear, toYear);
			}
			else {
				articleConferenceTest=true;
				return ee.getAuthorsByConference(conferenceName, fromYear, toYear);
			}
		}

		// finding authors with part of specific committee
		else if(!(conferenceName.equals("Empty")) && flag){
			committeeTest=true;
			return ee.getAuthorsByCommittee(conferenceName, fromYear, toYear);
		}

		// if none of the above condition satisfy then we will assume wrong use case
		return null;
	}

}
