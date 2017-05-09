package msd.user.inf;

import msd.user.inf.interfaces.InputCapture;

/**
 *  Using the adapter design pattern, this new class adapts to the 
 *  functionalities of the input capture interface.
 */
public class AuthorQuery extends UserQuery implements InputCapture {
	
	String authorName;
	
	/**
	 * The parameterized constructor to set the authors name
	 * @param authorName: The author name whose details are to be viewed
	 */
	public AuthorQuery(String authorName) {
		super();
		this.authorName = authorName;
	}

	public AuthorQuery() {
		/**
		 * default constructor
		 */
	}

	/**
	 * Method to get authors name
	 * @return authorName: The current authors name
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Method to set the authors name
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
