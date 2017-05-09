package msd.queryEngine.interfaces;

import java.util.List;

import com.mongodb.client.MongoDatabase;

public interface ExecutionEngine {

	
	/**
	 * get authors of a committee for the given timeline
	 * @param committee:committee name requested from UI
	 * @param fromYear:start year set through UI for greater than equal to in QE
	 * @param toYear:end year set through UI for less than equal to in QE
	 * @return List:resultset according to query results
	 */
	public List<OutputFormatter> getAuthorsByCommittee(String committee, int fromYear, int toYear);

	
	/**
	 * get authors who have article count greater than or equal to the given count and for the given timeline
	 * @param articleCount:article count of author requested from UI
	 * @param fromYear:start year set through UI for greater than equal to in QE
	 * @param toYear:end year set through UI for less than equal to in QE
	 * @return List:resultset according to query results
	 */
	public List<OutputFormatter> getAuthorsByArticleCount(int articleCount, int fromYear, int toYear);

	
	/**
	 * get authors for given keywords with specific citation count for given timeline
	 * @param keyword:list of words for search set from UI
	 * @param citationCount:citation count for author set from UI
	 * @param fromYear:start year set through UI for greater than equal to in QE
	 * @param toYear:end year set through UI for less than equal to in QE
	 * @return List:resultset according to query results
	 */
	public List<OutputFormatter> getAuthorsByTitle(String keyword, int citationCount, int fromYear, int toYear);

	
	/**
	 * get authors for citation count greater or equal to given count and for the given timeline
	 * @param citationCount:citation count for author set from UI
	 * @param fromYear:start year set through UI for greater than equal to in QE
	 * @param toYear:end year set through UI for less than equal to in QE
	 * @return List:resultset according to query results
	 */
	public List<OutputFormatter> getAuthorsByCitationCount(int citationCount, int fromYear, int toYear);

	// get authors for the given conference name and specific article count
	/**
	 * 
	 * @param articleCount:article count of author requested from UI
	 * @param conferenceName:conference name set from UI
	 * @param fromYear:start year set through UI for greater than equal to in QE
	 * @param toYear:end year set through UI for less than equal to in QE
	 * @return List:resultset according to query results
	 */
	public List<OutputFormatter> getAuthorsByArticleCountAndConference(int articleCount, String conferenceName, int fromYear, int toYear);

	/**
	 * use to set the database to query form
	 * @param database:setting database connection
	 */
	public void setDB(MongoDatabase database);

	/**
	 * 
	 * @param authorName:getting authors name searched through UI
	 * @return List:resultset according to query results
	 */
	List<OutputFormatter> getAuthorsDetails(String authorName);
	
	/**
	 * 
	 * @param conferenceName:conference name set from UI
	 * @param fromYear:start year set through UI for greater than equal to in QE
	 * @param toYear:end year set through UI for less than equal to in QE
	 * @return List:resultset according to query results
	 */
	public List<OutputFormatter> getAuthorsByConference(String conferenceName, int fromYear, int toYear);
}