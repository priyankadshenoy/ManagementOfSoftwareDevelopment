package msd.core;

import java.util.ArrayList;
import java.util.List;

public class Article {
	List<String> author;
	String conferenceName;
	String journalName;
	String title;
	String pages;
	int yearPublished;
	String volume;
	String ee;
	String crossRef;
	String booktitle;
	String key;
	String mdate;
	
	/**
	 * constructor
	 */
	public Article() {
		this.author = new ArrayList<String>();
	}

	/**
	 * return key
	 * @return key: key of the article
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * sets key
	 * @param key : key of the article
 	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 *  return Mdate
	 * @return mdate : M date of the article
	 */
	public String getMdate() {
		return mdate;
	}
	
	/**
	 * sets M date
	 * @param mdate : M date of the article
	 */
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	
	/**
	 * returns author
	 * @return author : List of the authors for this article
	 */
	public List<String> getAuthor() {
		return author;
	}
	
	/**
	 * sets author
	 * @param author :  author for this article
	 */
	public void setAuthor(String author) {
		this.author.add(author);
	}
	/**
	 * returns conference name
	 * @return conferenceName: conference of the article
	 */
	public String getConferenceName() {
		return conferenceName;
	}
	
	/**
	 * sets conference name
	 * @param conferenceName : conference of the article
	 */
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	
	/**
	 * returns journal name
	 * @return journalName : journal of the article
	 */
	public String getJournalName() {
		return journalName;
	}
	
	/**
	 * sets journal name
	 * @param journalName : journal of the article
	 */
	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}
	
	/**
	 * returns title
	 * @return title : title of the article
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * sets title
	 * @param title : title of the article
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * returns pages
	 * @return pages : pages of the article
	 */
	public String getPages() {
		return pages;
	}
	
	/**
	 * set pages
	 * @param pages : pages of the article
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}
	
	/**
	 * returns published year 
	 * @return yearPublished : published year of the article
	 */
	public int getYearPublished() {
		return yearPublished;
	}
	
	/**
	 * sets year publish
	 * @param yearPublished : published year of the article
	 */
	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	
	/**
	 * returns volume
	 * @return volume : volume of the article
	 */
	public String getVolume() {
		return volume;
	}
	
	/**
	 * sets volume
	 * @param volume : volume of the article
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	/**
	 * returns ee
	 * @return ee : ee of the article
	 */
	public String getEe() {
		return ee;
	}
	
	/**
	 * sets ee
	 * @param ee : ee of the article
	 */
	public void setEe(String ee) {
		this.ee = ee;
	}
	
	/**
	 * returns cross ref
	 * @return : cross ref of the article
	 */
	public String getCrossRef() {
		return crossRef;
	}
	
	/**
	 * sets cross ref
	 * @param crossRef: cross ref of the article
	 */
	public void seCrossRef(String crossRef) {
		this.crossRef = crossRef;
	}
	
	/**
	 * returns book title 
	 * @return bookTitle : book title of the article
	 */
	public String getBooktitle() {
		return booktitle;
	}
	
	/**
	 * sets book title
	 * @param booktitle : book title of the article
	 */
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	
}
