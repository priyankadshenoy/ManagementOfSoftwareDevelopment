package msd.queryEngine.interfaces;

import java.util.List;

import msd.user.inf.interfaces.InputCapture;

public interface QueryGenerator {

	// Interface to generate query using the user supplied input
	// to transform the given user query object to the required format
	//public List<OutputFormatter> transformUserQueryObject(UserQuery userQuery) ;
	/**
	 * Receives data in the form set by user for search, transforms into different method calls
	 * according to inputs received
	 * @param userQuery:Object received from UI
	 * @return List:Containing result set after query processing
	 */
	public List<OutputFormatter> transformUserQueryObject(InputCapture userQuery) ;

}
