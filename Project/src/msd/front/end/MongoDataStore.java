package msd.front.end;


import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.result.UpdateResult;

import msd.core.Article;
import msd.core.Author;
import msd.core.AuthorData;
import msd.core.Committee;
import msd.core.Conference;
import msd.front.end.interfaces.DataStorage;

/**
 * This is where the mongo database connection takes place 
 * and the parsed data is stored in individual collections
 */
public class MongoDataStore implements DataStorage{

	MongoClient mongoClient ;
	MongoDatabase database;
	MongoCollection<Document> collection;

	/**
	 * Method to instantiate connection to mongo db
	 * @param uri : connection string of the mongo db
	 */
	@Override
	public void createConnection(String uri) {		
		mongoClient= new MongoClient(new MongoClientURI(uri));
		String[] splitTheUri = uri.split("/");
		System.out.println("here::"+ splitTheUri[3]);
		database = mongoClient.getDatabase(splitTheUri[3]);
	}

	/**
	 * Method to close the mongo db connection
	 */
	public void closeConnection() {
		mongoClient.close();
	}


	/**
	 * Method identifies what data needs to be stored in which collection
	 * @param indentifier : tag identifier
	 * @param type : input object type
	 */
	@Override
	public void storeData(String indentifier, Object type) {
		if("conference".equalsIgnoreCase(indentifier)) {
			Conference conf = (Conference)type;
			putInConf(conf);	
		} else if("article".equalsIgnoreCase(indentifier)) {
			Article article = (Article)type;
			putInArticle(article);	
		} else if ("committee".equalsIgnoreCase(indentifier)) {
			Committee committee = (Committee)type;
			putInCommittee(committee);	
		} else if("author".equalsIgnoreCase(indentifier)) {
			Author author = (Author)type;
			putInAuthor(author);	
		} else if("authorData".equalsIgnoreCase(indentifier)) {
			AuthorData authorData = (AuthorData)type;
			putInAuthorData(authorData);	
		}
	}

	/**
	 * saves author data
	 * @param authorData : saves the given author Data object in mongo db
	 */
	private void putInAuthorData(AuthorData authorData) {
		collection = database.getCollection("authorData");
		Document doc = new Document("name", authorData.getName())
				.append("university", authorData.getUniversity())
				.append("fieldOfStudy", authorData.getFieldOfStudy())
				.append("homePage", authorData.getHomePage())
				.append("region", authorData.getRegion());
		collection.insertOne(doc);
		System.out.println(collection.count());
	}

	/**
	 * Method to store the conference details in the conference 
	 * collection in MongoDB
	 * @param conf : given conf data to be stored in mongodb
	 */
	public void putInConf(Conference conf) {
		collection = database.getCollection("conference");
		Document doc = new Document("volume", conf.getVolume())
				.append("editor", conf.getEditor())
				.append("ee", conf.getEe())
				.append("key", conf.getKey())
				.append("mdate", conf.getMdate())
				.append("year", conf.getYear())
				.append("publisher", conf.getPublisher())
				.append("title", conf.getTitle())
				.append("isbn", conf.getIsbn())
				.append("booKtitle", conf.getBookTitle());
		collection.insertOne(doc);
		System.out.println(collection.count());
	}

	/**
	 * Method to store the article details in the article 
	 * collection in MongoDB
	 * @param article : given article to be stored in mongo
	 */
	public void putInArticle(Article article) {
		collection = database.getCollection("article");
		if(containsReqdConference(article)){
			for(String author: article.getAuthor()) {
				Document doc = new Document("volume", article.getVolume())
						.append("key", article.getKey())
						.append("mdate", article.getMdate())
						.append("author", author)
						.append("ee", article.getEe())
						.append("pages", article.getPages())
						.append("year", article.getYearPublished())
						.append("title", article.getTitle())
						.append("conferenceName", article.getConferenceName())
						.append("journalName", article.getJournalName())
						.append("bookTitle", article.getBooktitle())
						.append("crossRef", article.getCrossRef());
				collection.insertOne(doc);
			}
		}

		System.out.println(collection.count());
	}


	/**
	 * Method to store the committee details in the committee 
	 * collection in MongoDB
	 * @param committee : committee data
	 */
	public void putInCommittee(Committee committee) {
		collection = database.getCollection("committee");

		// forming the document required to add into mongo collection
		Document doc = new Document("members", committee.getMembers())
				.append("name", committee.getName())
				.append("fullName", committee.getFullName())
				.append("year", committee.getYear());

		collection.insertOne(doc);
		System.out.println(collection.count());
	}

	/**
	 * Method to store the author details in the author 
	 * collection in MongoDB
	 * @param author : author obejct to be stored in mongo
	 */
	public void putInAuthor(Author author) {
		collection = database.getCollection("author");
		Document doc = new Document("name", author.getName())
				.append("citationCount", author.getCitationCount());

		collection.insertOne(doc);

		System.out.println(collection.count());
	}

	/**
	 * Helper method to check if the article was published in the
	 * required conferences
	 * @param article : check the article is to stored or not
	 * @return boolean : to store this article or not
	 */
	public boolean containsReqdConference(Article article) {
		return article.getKey().contains("conf/oopsla") ||
				article.getKey().contains("conf/pldi") ||
				article.getKey().contains("conf/ecoop") ||
				article.getKey().contains("conf/icfp") || 
				article.getKey().contains("journals/tse") ||
				article.getKey().contains("journals/toplas") ||
				article.getKey().contains("journals/jacm") ||
				article.getKey().contains("journals/ieeesp");
	}

	/**
	 * For ease of accessing the data, this method joins the article 
	 * and the author data on author name
	 */
	public void performJoin() {
		MongoCollection<Document> collection = database.getCollection("author");
		MongoCollection<Document> articleCollection = database.getCollection("article");

		AggregateIterable<Document> cursor1 = articleCollection.aggregate(
				Arrays.asList(Aggregates.group("$author", Accumulators.sum("count", 1))));
		for(Document d: cursor1) {
			String author = d.getString("_id");
			System.out.println(author);
			AggregateIterable<Document> cursor = collection.aggregate(
					Arrays.asList(
							Aggregates.match(eq("name", author)),
							Aggregates.group("$name", Accumulators.sum("citationCount", "$citationCount"))
							)
					);


			for(Document d1 : cursor) {
				UpdateResult updateResult = articleCollection.updateMany(eq("author", d1.getString("_id")), set("citationCountForAuth", d1.getInteger("citationCount")));
				System.out.println(updateResult.getModifiedCount());
			}
		}
	}
}
