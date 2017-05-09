package msd.queryEngine.interfaces;


public interface OutputFormatter {

	/**
	 * Overridden method to get cumulative data
	 * @return data in String format
	 */
	public String toString();
	/**
	 * Sets value of author to result obtained from mongoDB
	 * @param author:author value received from db
	 */
	public void setAuthor(String author);

	/**
	 * Sets value of title to result obtained from mongoDB 
	 * @param title:title value received from db
	 */
	public void setTitle(String title);

	/**
	 * Sets value of book title to result obtained from mongoDB
	 * @param bookTitle:booktitle value received from db
	 */
	public void setBookTitle(String bookTitle);

	/**
	 * Sets value of conference name to result obtained from mongoDB
	 * @param conferenceName: conference name received from db
	 */
	public void setConferenceName(String conferenceName);

	/**
	 * Sets value of year to result obtained from mongoDB
	 * @param year: year received from db
	 */
	public void setYear(int year);

	/**
	 * Sets value of citation count to result obtained from mongoDB
	 * @param cite: cite received from db
	 */
	public void setCitationCount(int cite);

	/**
	 * Sets value of columns to be displayed on UI
	 * @param displayColumns: String value according to query searched
	 */
	public void setColumnsToString(String displayColumns);

	/**
	 *  Sets value of columns to be displayed on UI
	 * @return: values to be displayed 
	 */
	public String columnsToString();

	/**
	 * Sets value of university for author to result obtained from mongoDB
	 * @param university: university name received from db
	 */
	public void setUniversity(String university);

	/**
	 * Sets value of field of study for author to result obtained from mongoDB
	 * @param fieldOfStudy: field received from db
	 */
	public void setFieldOfStudy(String fieldOfStudy);

	/**
	 * Sets value of home page for author to result obtained from mongoDB
	 * @param homePage : home page received from db
	 */
	public void setHomePage(String homePage);

	/**
	 * Sets value of region for author to result obtained from mongoDB
	 * @param region : region received from db
	 */
	public void setRegion(String region);

	/**
	 * Sets value of members of committee to result obtained from mongoDB
	 * @param value: value received from db
	 */
	public void setMembersOfCommittee(String value);

	/**
	 * Sets value of designation for members of committee obtained from mongoDB
	 * @param pc: designation of each member 
	 */
	public void setDesignation(String pc);

	/**
	 * 
	 * @param count:count received from db
	 */
	public void setCount(int count);

	// getters for the UI to read values
	/**
	 * gets year for display to UI
	 * @return: year to display
	 */
	public int getYear();

	/**
	 * gets title for display to UI
	 * @return: title to display
	 */
	public String getTitle();

	/**
	 * gets author for display to UI
	 * @return: author to display
	 */
	public String getAuthor();

	/**
	 * gets conference name for display to UI
	 * @return:conferenceName to display
	 */
	public String getConferenceName();

	/**
	 * gets book title for display to UI
	 * @return: book title to display
	 */
	public String getBookTitle();

	/**
	 * gets citation count for display to UI
	 * @return: citation count to display
	 */
	public int getCitationCount();

	/**
	 * gets article count for display to UI
	 * @return: article count to display
	 */
	public int getCount();

	/**
	 * gets university for author for display to UI
	 * @return: university to display
	 */
	public String getUniversity();

	/**
	 * gets home page for author for display to UI
	 * @return: home page to display
	 */
	public String getHomePage();

	/**
	 * gets field of study for author for display to UI
	 * @return: field to display
	 */
	public String getFieldOfStudy();

	/**
	 * gets region of author for display to UI
	 * @return: region to display
	 */
	public String getRegion();

	/**
	 * gets designation for author for display to UI
	 * @return: designation to display
	 */
	public Object getDesignation();

	/**
	 * gets members of committee for display to UI
	 * @return: members to display
	 */
	public Object getMembers();



}
