package msd.front.end;


import msd.core.Article;
import msd.core.Author;
import msd.core.AuthorData;
import msd.core.Committee;
import msd.core.Conference;
import msd.front.end.interfaces.DataStorage;

/*
 * This class is sued to instantiate the connection to mongo db
 * and provide helper methods to the parser
 */
public class ConnectMongoD {

	DataStorage store = new MongoDataStore();


	/**
	 * create connection to mongoDB
	 * @param uri : connection string of the mongoDB
	 */
	public ConnectMongoD(String uri){
		 store.createConnection(uri);
	}


	/**
	 * Method to store the conference details in the conference 
	 * collection in MongoDB
	 * @param conf : input conference to be stored
	 */
	public  void putConfInMongo(Conference conf){	
		store.storeData("conference", conf);
	}

	/**
	 * Method to store the article details in the article 
	 * collection in MongoDB
	 * @param article : input article to be stored
	 */
	public  void putArticleInMongo(Article article){
		store.storeData("article", article);
	}

	/**
	 * Method to store the committee details in the committee 
	 * collection in MongoDB
	 * @param committee : committee to be saved in mongo
	 */
	public  void putCommitteeInMongo(Committee committee){
		store.storeData("committee", committee);
	}

	/**
	 * Method to store the author details in the author 
	 * collection in MongoDB
	 * @param author : author to be saved in mongo
	 */
	public  void putAuthorInMongo(Author author){

		store.storeData("author", author);
	}
	
	/**
	 * Method to join the author and article collection
	 * by author name
	 */
	public void joinArticleAndAuthor() {
		store.performJoin();
	}

	/**
	 * Method to close the mongo db connection
	 */
	public void closeConnection()
	{
		store.closeConnection();
	}
	
	/**
	 * stores the authorData object in MongoDB
	 * @param authorData : authorData object to be stored
	 */
	public void putAuthorDataInMongo(AuthorData authorData) {
		store.storeData("authorData", authorData);
	}

}
