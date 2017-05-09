package msd.front.end.interfaces;

public interface DataParser {
	/* The interface is used to parse the raw data into the format suitable
	 * for querying.*/
	
		// identifies the format of the supplied input by analysing the input
		public String identifyFormat(String input) ;
		// parses the data into a suitable format once the format is identified
		public String parseData(String input, String format);
		// communicates the parsed data to the DataStore
	
}