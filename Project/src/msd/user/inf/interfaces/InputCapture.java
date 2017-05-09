package msd.user.inf.interfaces;

/************************************************************
	The User Interface Component
 *************************************************************/

// Interface for capturing the user inputs from the user interface(screen)
public	interface InputCapture {

	public String getKeyword();

	/**
	 * set the keywords supplied by user 
	 * @param keyword: The keyword provided by the user
	 */
	public void setKeyword(String keyword) ;

	/**
	 * Method return the start year
	 * @return year: the start year
	 */
	public int getFromTime();

	/**
	 *  set the timeFrame supplied by user 
	 * @param fromTime: the start year
	 */
	public void setFromTime(int fromTime) ; 

	/**
	 * get the conerence name
	 * @return conference: the conference name
	 */
	public String getConferenceName();

	/**
	 *  set the Conference name supplied by user
	 * @param conferenceName: the conference name
	 */
	public void setConferenceName(String conferenceName) ;

	/**
	 * get the count
	 * @return count: the count 
	 */
	public int getCount();

	/**
	 *  set Citation count supplied by user
	 * @param count: the citation count
	 */
	public void setCount(int count) ;

	/**
	 * get the paper count
	 * @return papercount: the number of papers
	 */
	public int getPaper();

	/**
	 *  set Number of Papers supplied by user  
	 * @param paper: the paper count
	 */
	public void setPaper(int paper) ;

	/**
	 * get the end year
	 * @return year: the send year
	 */
	public int getToTime();
	
	/**
	 * set the end year
	 * @param toTime: the end year
	 */
	public void setToTime(int toTime);

	/**
	 * get whether conference or committee
	 * @return bool: true for committee false otherwise
	 */
	public boolean isConfOrCpm();

	/**
	 * set whether conference or committee
	 * @param confOrCpm: true or false
	 */
	public void setConfOrCpm(boolean confOrCpm);

	/**
	 * set authors name
	 * @param authorName: the authors name
	 */
	public void setAuthorName(String authorName);

	/**
	 * get authors name
	 * @return author: the name of the author
	 */
	public String getAuthorName();
}



