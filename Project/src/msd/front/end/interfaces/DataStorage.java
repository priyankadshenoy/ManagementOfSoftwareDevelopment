package msd.front.end.interfaces;


/* The interface is used to store the parsed data into the 
 * in memory representation choosen*/
public interface DataStorage {
	//initiate connection to the data store
	/**
	 * creates connection to DB
	 * @param uri : connection string of mongoDB
	 */
	public void createConnection(String uri);
	// store the data into an in memory structure
	/**
	 * based on the identifier creates the required object which needs to stored in mongoDB
	 * @param indentifier : identifier of the object
	 * @param type : input data object
	 */
	public void storeData(String indentifier, Object type);
	public void closeConnection();
	public void performJoin();
}