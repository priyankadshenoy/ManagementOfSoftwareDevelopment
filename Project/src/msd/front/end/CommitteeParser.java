package msd.front.end;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import msd.core.Committee;

/**
 * This is used to parse the committee data i,e, parse 
 * all the text files and create an object for each committee.
 *
 */
public class CommitteeParser extends FrontEndAdapter {
	Committee c;
	public boolean containsChair = false;
	public boolean containsPC = false;
	public boolean containsGC = false;
	public boolean containsEC = false;
	public boolean fileRead = true;
	public boolean fileExists = false;
	public boolean isConnectionClosed = false;
	
	/**
	 * reads all the files in the path provided and parser the data of the committee based on the 
	 * data format
	 * @param path : path of the folder of the committee data
	 * @param uri : connection string of the mongoDB
	 * @throws FileNotFoundException : when the files are not found in the given folder
	 */
	public void listFilesForFolder(String path, String uri) throws FileNotFoundException {

		ConnectMongoD cm = new ConnectMongoD(uri);

		File folder = new File(path);
		// if folder does not exist
		if(!folder.exists()){
			fileExists = false;
		}
		else {
			// looping over the files in the given folder
			for (final File fileEntry : folder.listFiles()) {

				// if file not found throw an exception
				// if file found start reading the contents of the file
				if(fileEntry.isFile()) {
					fileExists= true;
					// parsing committee name and year of the committee
					c = new Committee();
					String temp = fileEntry.getName();
					if(temp.contains("-pc")) {
						String committeeName = temp.substring(0, temp.length()-11);
						String year = temp.substring(temp.length()-11, temp.length()-7);
						c.setYear(Integer.parseInt(year));
						c.setName(committeeName);
					} else {
						/**
						 * This is because, there is one malformed file which is the format
						 * xxx-yyyy.txt
						 *	usual format is xxxyyyy-pc.txt
						 */
						String committeeName = temp.substring(0, temp.length()-9);
						String year = temp.substring(temp.length()-8, temp.length()-4);
						c.setYear(Integer.parseInt(year));
						c.setName(committeeName);
					}

					Scanner fileData = null;
					try {
						fileData = new Scanner(fileEntry);
						while (fileData.hasNextLine()) {
							String line = fileData.nextLine();

							String l = line.trim();
							if(! "".equals(l)) {

								// checking for roles, if no roles just add as a member
								// for every role, adding the role in the format name,"role"
								// roles can be PC, GC, CC, EC
								if(l.substring(1, 2).equals(":")) {

									String role = l.substring(0,1);
									if ("P".equals(role)) {
										containsPC = true;
										c.addMember(l.substring(2, l.length())+",ProgamChair");
									} else if ("G".equals(role)) {
										containsGC = true;
										c.addMember(l.substring(2, l.length())+",GeneralChair");
									} else if ("C".equals(role)) {
										containsChair = true;
										c.addMember(l.substring(2, l.length())+",ConferenceChair");
									} else if ("E".equals(role)) {
										containsEC = true;
										c.addMember(l.substring(2, l.length())+",ExternalChair");
									}
								} else {
									c.addMember(l+",Member");
								}
							}
						}
					}
					catch(Exception e) {
						System.out.println("Exception:"+e);	
					} finally {

						if(fileData != null)
							fileData.close();
						fileRead = true;
						sendBackDataForTest();
					} 
					// push to DB
					cm.putCommitteeInMongo(c);
				} 
			}
		}
		cm.closeConnection();
		isConnectionClosed =true;
	}

	/**
	 * send if the hashmap is null or has data
	 * @return if the hashmap is null or has data
	 */
	public boolean sendBackDataForTest() {
		return c !=null;
	}

	
	/**
	 * reads the input parameters and passes on to the core parser method
	 * @param inputFilePath : path where the csv files are stored
	 * @param uri : connection string of the mongoDB, where the data should be finally stored
	 */
	@Override
	public void readInput(String inputFilePath, String uri) throws ParserConfigurationException, SAXException, IOException {

		this.listFilesForFolder(inputFilePath, uri);
	}
}
