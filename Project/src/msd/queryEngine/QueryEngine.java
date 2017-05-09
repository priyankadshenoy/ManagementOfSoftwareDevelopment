package msd.queryEngine;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import msd.queryEngine.interfaces.ExecutionEngine;
import msd.queryEngine.interfaces.OutputFormatter;
import msd.user.inf.ui.Login;

import static com.mongodb.client.model.Filters.*;

import java.util.*;
import java.util.regex.Pattern;


public class QueryEngine implements ExecutionEngine{

	MongoDatabase database;
	MongoCollection<Document> collection;

	//Variable for testing purpose
	public static boolean getAuthorByArticleCountInvoked = false;
	public static boolean getAuthorsForConferenceNameInvoked = false;
	public static boolean getAuthorByCitationCountInvoked= false;
	public static boolean getAuthorsForArticleCountInvoked= false;
	public static boolean getAuthorForKeyWordsInvoked = false;
	public static boolean getAuthorsDetailsInvoled= false;
	public static boolean getAuthorsByArticleCountAndConferenceInvoked= false;
	public void setDB(MongoDatabase database) {
		this.database = database;
	}

	// setting up a collection for querying mongodb
	public void setUpConnection(String articleName){
		if (database == null){
			database = Login.database;
		}
		collection = database.getCollection(articleName);
	}
	
	@Override
	// get authors details
	public List<OutputFormatter> getAuthorsDetails(String authorName) {
		setUpConnection("authorData");
		FindIterable<Document> cursor = collection.find(eq("name", authorName));
		ArrayList<OutputFormatter> output = new ArrayList<OutputFormatter>();

		for(Document doc : cursor) {
			OutputFormatter outputFormatter = new ProcessedOutputFromQE();
			outputFormatter.setUniversity(doc.getString("university"));
			outputFormatter.setFieldOfStudy(doc.getString("fieldOfStudy"));
			outputFormatter.setHomePage(doc.getString("homePage"));
			System.out.println("region:::"+ doc.getString("region"));
			outputFormatter.setRegion(doc.getString("region"));
			output.add(outputFormatter);
		}
		getAuthorsDetailsInvoled = true;
		return output;
	}

	@Override
	// get authors for given keywords with specific citation count for given time line
	public List<OutputFormatter> getAuthorsByTitle(String keyword, int citeCount, int fromYear, int toYear) {
		String[] keys = keyword.split(",");
		setUpConnection("article");
		FindIterable<Document> cursor = null;
		List<OutputFormatter> output = new ArrayList<OutputFormatter>();
		for(String keyValue : keys) {
			if(!keyValue.equals("")){
				cursor = collection.find(
						and(
								eq("title", java.util.regex.Pattern.compile(keyValue.trim(), Pattern.CASE_INSENSITIVE)),
								gte("citationCountForAuth", citeCount),
								gte("year", fromYear),
								lte("year", toYear)));


				for(Document d : cursor) {
					OutputFormatter outputFormatter = new ProcessedOutputFromQE();
					outputFormatter.setAuthor(d.getString("author"));
					outputFormatter.setTitle(d.getString("title"));
					outputFormatter.setBookTitle(d.getString("bookTitle"));
					String confName = d.getString("key").split("/")[1];
					outputFormatter.setConferenceName(confName);
					outputFormatter.setCitationCount(d.getInteger("citationCountForAuth"));
					outputFormatter.setYear(d.getInteger("year"));
					outputFormatter.setColumnsToString("Title,Author,Year,ConferenceName,BookTitle,CitationCount");
					output.add(outputFormatter);
				}
			}
		}
		getAuthorForKeyWordsInvoked = true;
		return output;
	}


	@Override
	// get author for the given conference name and specific article count
	public ArrayList<OutputFormatter> getAuthorsByArticleCountAndConference(int articleCount, String conferenceName, int fromYear, int toYear) {
		setUpConnection("article");
		AggregateIterable<Document> cursorAggr = collection.aggregate(
				Arrays.asList(
						Aggregates.group("$author",Accumulators.sum("count", 1)),
						Aggregates.match(Filters.gte("count", articleCount))));


		List<OutputFormatter> output = new ArrayList<OutputFormatter>();
		for(Document d : cursorAggr) {
			String s = d.getString("_id");
			FindIterable<Document> cursorSearch = collection.find
					(and(
							eq("author", s),
							eq("key", java.util.regex.Pattern.compile(conferenceName, Pattern.CASE_INSENSITIVE)),
							gte("year", fromYear),
							lte("year", toYear)
							));


			for(Document doc : cursorSearch) {
				OutputFormatter outputFormatter = new ProcessedOutputFromQE();
				outputFormatter.setTitle(doc.getString("title"));
				outputFormatter.setAuthor(d.getString("author"));
				outputFormatter.setAuthor(doc.getString("author"));
				String confName = doc.getString("key").split("/")[1];
				outputFormatter.setConferenceName(confName);
				outputFormatter.setYear(doc.getInteger("year"));
				outputFormatter.setCount(d.getInteger("count"));
				outputFormatter.setColumnsToString("Author,ConferenceName,ArticleCount");
				output.add(outputFormatter);
			}
		}
		List<OutputFormatter> result = new ArrayList<OutputFormatter>();
		Set<String> titles = new HashSet<String>();
		for( OutputFormatter item : output ) {
			if(titles.add(item.getAuthor())) {
				result.add(item );
			}
		}
		getAuthorsByArticleCountAndConferenceInvoked= true;
		return (ArrayList<OutputFormatter>) result;
	}



	@Override
	// get author for the given conference name and specific article count
	public  ArrayList<OutputFormatter> getAuthorsByArticleCount(int articleCount, int fromYear, int toYear) {
		setUpConnection("article");
		AggregateIterable<Document> cursorAggr = collection.aggregate(
				Arrays.asList(Aggregates.group("$author",
						Accumulators.sum("count", 1)),
						Aggregates.match(Filters.gte("count", articleCount)))
				);

		Set<OutputFormatter> output = new HashSet<OutputFormatter>();
		for(Document d : cursorAggr) {
			String s = d.getString("_id");
			FindIterable<Document> cursorSearch = collection.find
					(and(
							eq("author", s),
							gte("year", fromYear),
							lte("year", toYear)));
			for(Document doc : cursorSearch) {
				OutputFormatter outputFormatter = new ProcessedOutputFromQE();
				outputFormatter.setAuthor(doc.getString("author"));
				outputFormatter.setTitle(doc.getString("title"));
				outputFormatter.setBookTitle(doc.getString("bookTitle"));
				String confName = doc.getString("key").split("/")[1];
				outputFormatter.setConferenceName(confName);
				outputFormatter.setYear(doc.getInteger("year"));
				outputFormatter.setCount(d.getInteger("count"));
				outputFormatter.setColumnsToString("author");
				outputFormatter.setColumnsToString("Author,ConferenceName,ArticleCount");
				output.add(outputFormatter);
			}

		}
		List<OutputFormatter> result = new ArrayList<OutputFormatter>();
		Set<String> titles = new HashSet<String>();
		for( OutputFormatter item : output ) {
			if(titles.add(item.getAuthor())) {
				result.add(item );
			}
		}
		getAuthorByArticleCountInvoked = true;
		return new ArrayList<OutputFormatter>(result);
	}

	@Override
	// get authors for citation count greater or equal to given count and for the given time line
	public ArrayList<OutputFormatter> getAuthorsByCitationCount(int citationCount, int fromYear, int toYear) {
		setUpConnection("article");
		
		FindIterable<Document> cursor = collection.find
				(and
						(gte("citationCountForAuth", citationCount),
								gte("year", fromYear),
								lte("year", toYear))
						);
		ArrayList<OutputFormatter> output = new ArrayList<OutputFormatter>();

		for(Document doc : cursor) {
			OutputFormatter outputFormatter = new ProcessedOutputFromQE();
			outputFormatter.setAuthor(doc.getString("author"));
			outputFormatter.setTitle(doc.getString("title"));
			outputFormatter.setBookTitle(doc.getString("bookTitle"));
			String confName = doc.getString("key").split("/")[1];
			outputFormatter.setConferenceName(confName);
			outputFormatter.setYear(doc.getInteger("year"));
			outputFormatter.setCitationCount(doc.getInteger("citationCountForAuth"));
			outputFormatter.setColumnsToString("Title,Author,Year,ConferenceName,BookTitle,CitationCount");
			output.add(outputFormatter);
		}
		getAuthorByCitationCountInvoked= true;
		return output;
	}


	@Override
	// get authors of a committee for the given time line
	public ArrayList<OutputFormatter> getAuthorsByCommittee(String committee, int fromYear, int toYear) {
		setUpConnection("committee");
		FindIterable<Document> cursor = collection.find(
				and(
						lte("year", toYear),
						gte("year", fromYear),
						eq("name", committee)
						));

		ArrayList<OutputFormatter> output = new ArrayList<OutputFormatter>();
		List<String> member = new ArrayList<>();
		List<String> designation = new ArrayList<>();

		for(Document d : cursor) {
			OutputFormatter outputFormatter=null;
			Object membersOfCommitte =d.get("members");
			String membersOfCommitteValues = membersOfCommitte.toString();
			membersOfCommitteValues= membersOfCommitteValues.substring(1, membersOfCommitteValues.length()-1);
			String[] li = membersOfCommitteValues.split(",");
			for(int i=0; i<li.length; i+=2){
				member.add(li[i]);
				designation.add(li[i+1]);
			}
			for(int i=0; i<member.size(); i++){
				outputFormatter= new ProcessedOutputFromQE();
				outputFormatter.setMembersOfCommittee(member.get(i));
				outputFormatter.setDesignation(designation.get(i));
				outputFormatter.setYear(d.getInteger("year"));
				outputFormatter.setColumnsToString("Year,Members,Designation");
				output.add(outputFormatter);
			}

		}
		return output;
	}

	@Override
	// finding authors by specific conference name for given time line
	public List<OutputFormatter> getAuthorsByConference(String conferenceName, int fromYear, int toYear) {

		ArrayList<OutputFormatter> output = new ArrayList<OutputFormatter>();
		setUpConnection("article");
		FindIterable<Document> cursor = collection.find(
				and(
						eq("key", java.util.regex.Pattern.compile(conferenceName)),
						gte("year", fromYear),
						lte("year", toYear)
						));

		for(Document d : cursor) {
			OutputFormatter outputFormatter = new ProcessedOutputFromQE();
			outputFormatter.setAuthor(d.getString("author"));
			outputFormatter.setTitle(d.getString("title"));
			outputFormatter.setBookTitle(d.getString("bookTitle"));
			String confName = d.getString("key").split("/")[1];
			outputFormatter.setConferenceName(confName);
			outputFormatter.setYear(d.getInteger("year"));
			outputFormatter.setColumnsToString("Title,Author,Year");
			output.add(outputFormatter);

		}
		getAuthorsForConferenceNameInvoked= true;
		return output;
	}
}