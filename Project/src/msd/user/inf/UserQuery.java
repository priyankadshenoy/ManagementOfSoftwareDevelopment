package msd.user.inf;

import msd.user.inf.interfaces.InputCapture;


/**
 * Class that is used to set the user query supplied by the input
 * This class's object is visible to the Query Engine which 
 * extracts the user supplied inputs to run the corresponding 
 * query.
 */
public class UserQuery implements InputCapture {

	String keyword;
	int fromTime;
	int toTime;
	String conferenceName;
	int count;
	int paper;
	boolean confOrCpm;
	String authorName;

	public UserQuery() {
		/**
		 * default constructor
		 */
	}

	/**
	 * Parameterized constructor
	 * @param keyword: keyword set by the user
	 * @param fromTime: start time set by user
	 * @param toTime: end time set by user
	 * @param conferenceName: conference set by the user
	 * @param count: count set by the user
	 * @param paper: paper count set by the user
	 * @param confOrCpm: whether seatch is for conference or committee
	 */
	public UserQuery(String keyword, int fromTime,int toTime, String conferenceName, int count, int paper , boolean confOrCpm) {
		this.keyword = keyword;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.conferenceName = conferenceName;
		this.count = count;
		this.paper = paper;
		this.confOrCpm = confOrCpm;
	}

	/**
	 * Default toString() method override
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserQuery [keyword=" + keyword + ", fromTime=" + fromTime + ", toTime=" + toTime + ", conferenceName="
				+ conferenceName + ", count=" + count + ", paper=" + paper + ", confOrCpm=" + confOrCpm + "]";
	}


	/**
	 * Method to get the keyword
	 * @see msd.user.inf.interfaces.InputCapture#getKeyword()
	 */
	@Override
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Method to set the keyword
	 * @see msd.user.inf.interfaces.InputCapture#setKeyword(java.lang.String)
	 */
	@Override
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Method to get the start year
	 *  @see msd.user.inf.interfaces.InputCapture#getFromTime()
	 */
	@Override
	public int getFromTime() {
		return fromTime;
	}

	/**
	 * Method to set the start year
	 * @see msd.user.inf.interfaces.InputCapture#setFromTime(int)
	 */
	@Override
	public void setFromTime(int fromTime) {
		this.fromTime = fromTime;
	}

	/**
	 * Method to get the conference name
	 * @see msd.user.inf.interfaces.InputCapture#getConferenceName()
	 */
	@Override
	public String getConferenceName() {
		return conferenceName;
	}

	/**
	 * Method to set the conference name
	 * @see msd.user.inf.interfaces.InputCapture#setConferenceName(java.lang.String)
	 */
	@Override
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	/**
	 * Method to get the count
	 * @see msd.user.inf.interfaces.InputCapture#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/*
	 * Method to set the count
	 * @see msd.user.inf.interfaces.InputCapture#setCount(int)
	 */
	@Override
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Method to get paper count
	 * @see msd.user.inf.interfaces.InputCapture#getPaper()
	 */
	@Override
	public int getPaper() {
		return paper;
	}

	/**
	 * Method to set paper count
	 * @see msd.user.inf.interfaces.InputCapture#setPaper(int)
	 */
	@Override
	public void setPaper(int paper) {
		this.paper = paper;
	}

	/**
	 * Method to set the end time
	 * @see msd.user.inf.interfaces.InputCapture#getToTime()
	 */
	@Override
	public int getToTime() {
		return toTime;
	}

	/**
	 * Method to set the end year
	 * @see msd.user.inf.interfaces.InputCapture#setToTime(int)
	 */
	@Override
	public void setToTime(int toTime) {
		this.toTime = toTime;
	}

	/**
	 * Method to determine whether committee or conference
	 * @see msd.user.inf.interfaces.InputCapture#isConfOrCpm()
	 */
	@Override
	public boolean isConfOrCpm() {
		return confOrCpm;
	}

	/*
	 * Method to set whether it is conference or committee
	 * @see msd.user.inf.interfaces.InputCapture#setConfOrCpm(boolean)
	 */
	@Override
	public void setConfOrCpm(boolean confOrCpm) {
		this.confOrCpm = confOrCpm;
	}

	/**
	 * Method to set the author name 
	 * @see msd.user.inf.interfaces.InputCapture#setAuthorName(java.lang.String)
	 */
	@Override
	public void setAuthorName(String authorName) {
		this.authorName = authorName;

	}

	/**
	 * Method to get the author name
	 * @see msd.user.inf.interfaces.InputCapture#getAuthorName()
	 */
	@Override
	public String getAuthorName() {
		return authorName;
	}


}
