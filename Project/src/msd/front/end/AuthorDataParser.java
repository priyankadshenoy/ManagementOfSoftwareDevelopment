package msd.front.end;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import msd.core.AuthorData;

/**
 * 
 * authorDataParser extends FrontEndAdapter
 * this parser the author data from csv files 
 * obtained from csrankings.org
 *
 */
public class AuthorDataParser extends FrontEndAdapter {

	public boolean fileExists = false;
	BufferedReader br = null;
	Map<String, AuthorData> hash = new HashMap<String, AuthorData>();

	/**
	 * This method parses all the required csv files and forms a hash map,
	 * having key as author name and value as author data object.
	 * finally these objects are written into mongoDB
	 *  
	 * @param path : path of the csv files
	 * @param uri : connection string of the mongoDB, where the data should be finally stored
	 */
	public void authorDataParser(String path, String uri) {

		// reading three files namely homepages.csv, generated-author-info.csv
		// and country-info.csv
		System.out.println(uri);
		ConnectMongoD cm = new ConnectMongoD(uri);
		try {
		
			String filePath = path+"/homepages.csv";
			
			// if the file is not present, throwing an exception
			File homepagesFile = new File(filePath);
			if(homepagesFile.isFile()) {
				fileExists = true;
				String line = "";
				// parsing HOMEPAGES of different authors 
				// split the comma separated line to get individual data
				br = new BufferedReader(new FileReader(homepagesFile));
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					AuthorData a = new AuthorData();
					a.setName(data[0].trim());
					a.setHomePage(data[1].trim());
					hash.put(data[0].trim(), a);
				}
			}
			
			filePath = path+"/generated-author-info.csv";
			File generatedAuthorInfoFile = new File(filePath);
			// if the file is not present, throwing an exception
			if(generatedAuthorInfoFile.isFile()) {
				fileExists = true;
				String line = "";
				// parsing university and field of study of the author
				br = new BufferedReader(new FileReader(generatedAuthorInfoFile));
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");

					// if the author name doesn't exists in the hash map
					// add it to the hash map
					if(hash.get(data[0]) == null){
						AuthorData a = new AuthorData();
						a.setName(data[0].trim());
						a.setUniversity(data[1].trim());
						a.setFieldOfStudy(data[2].trim());
						hash.put(data[0].trim(), a);
					} else {
						AuthorData a = hash.get(data[0].trim());
						a.setUniversity(data[1].trim());
						a.setFieldOfStudy(data[2].trim());
						hash.put(data[0].trim(), a);
					}

				}
			}

			filePath = path+"/country-info.csv";
			File countryInfoFile = new File(filePath);
			// if the file is not present, throwing an exception
			if(countryInfoFile.isFile()) {
				fileExists = true;
				String line = "";
				//parsing the region info of the author
				br = new BufferedReader(new FileReader(countryInfoFile));
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					String university = data[0].trim();
					String region = data[1].trim();
					// for each university, if its in the hash map, updating 
					// author's region
					for(Entry<String, AuthorData> e : hash.entrySet()) {
						AuthorData a = e.getValue();
						if(a.getUniversity() != null && a.getUniversity().equals(university)) {
							a.setRegion(region);
						}
					}
				}
			}

			// for each entry in hashmap adding an entry in the dataBase
			for(AuthorData a : hash.values()) {
				cm.putAuthorDataInMongo(a);
			}
		} catch (Exception e) {
			System.err.println("Exception : "+e);
		}

	}


	
	
	/**
	 * @param inputFilePath : path where the csv files are stored
	 * @param uri : connection string of the mongoDB, where the data should be finally stored
	 */
	@Override
	public void readInput(String inputFilePath, String uri) throws ParserConfigurationException, SAXException, IOException {
		this.authorDataParser(inputFilePath, uri);
	}

	/**
	 * gets the hash map of the parser
	 * @return : hash map of the author data, author name is the key and value is authorData object
	 */
	public Map<String, AuthorData> getHash() {
		return hash;
	}

}
