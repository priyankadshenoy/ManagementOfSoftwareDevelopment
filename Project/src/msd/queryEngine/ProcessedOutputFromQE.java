package msd.queryEngine;

import msd.queryEngine.interfaces.OutputFormatter;

/**
 * Created by ps on 4/13/17.
 */
// After querying mongodb, the resultset is stored as objects according to query results
// Storing objects and returning to UI is the functionality of this class
public class ProcessedOutputFromQE implements OutputFormatter {

	int count;
	String title;
	String author;
	int year;
	String conferenceName;
	String bookTitle;
	String displayColumns;
	int citationCount;
	String university;
	String homePage;
	String fieldOfStudy;
	String region;
	String designation;
	String members;

	// sets value of author
	@Override
	public void setAuthor(String author) {
		this.author=author;
	}
	
	// sets value of title of conference
	@Override
	public void setTitle(String title) {
		this.title=title;
	}
	// sets value of book
	@Override
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	// sets conference name
	@Override
	public void setConferenceName(String conferenceName) {
		this.conferenceName=conferenceName;
	}
	// sets year
	@Override
	public void setYear(int year) {
		this.year=year;
	}
	
	// sets citation count
	@Override
	public void setCitationCount(int citationCount) {
		this.citationCount = citationCount;
	}
	
	// sets display column values
	@Override
	public void setColumnsToString(String displayColumns) {
		this.displayColumns = displayColumns;
	}
	
	// sets value of university for author
	@Override
	public void setUniversity(String university) {
		this.university = university;
	}
	
	// sets value of field of study for author
	@Override
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	
	// sets value of homepage for author
	@Override
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	// sets value of region for author
	@Override
	public void setRegion(String region) {
		this.region = region;
	}
	
	// gets value year for publication
	@Override
	public int getYear() {
		return year;
	}
	
	// gets title for paper
	@Override
	public String getTitle() {
		return title;
	}
	
	// gets author for paper
	@Override
	public String getAuthor() {
		return author;
	}

	// gets conference name for paper
	@Override
	public String getConferenceName() {
		return conferenceName;
	}
	
	// gets book title
	@Override
	public String getBookTitle() {
		return bookTitle;
	}
	
	// gets citation count for paper
	@Override
	public int getCitationCount() {
		return citationCount;
	}
	
	// gets article count
	@Override
	public int getCount() {
		return count;
	}

	// gets university for author
	@Override
	public String getUniversity() {
		return university;
	}
	
	// gets home page for author
	@Override
	public String getHomePage() {
		return homePage;
	}
	
	// gets field of study for author
	@Override
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	
	// gets region for author
	@Override
	public String getRegion() {
		return region;
	}
	
	// sets members of committee
	@Override
	public void setMembersOfCommittee(String members) {
		this.members=members;		
	}
	
	// sets designation of members of committee
	@Override
	public Object getDesignation() {
		return designation;
	}
	// gets members of committee
	@Override
	public Object getMembers() {
		return members;
	}
	// gets designation of members of committee
	@Override
	public void setDesignation(String designation) {
		this.designation = designation;		
	}
	
	//sets article count
	@Override
	public void setCount(int count) {
		this.count=count;
		
	}
	
	// sets column values on UI
	@Override
	public String columnsToString() {
		return displayColumns;
	}



}
