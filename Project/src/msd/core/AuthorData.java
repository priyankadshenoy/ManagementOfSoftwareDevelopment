package msd.core;

public class AuthorData {

	String name;
	String homePage;
	String university;
	String fieldOfStudy;
	String region;
	
	/**
	 * returns name of the author
     * @return name: name of the author
     */
	public String getName() {
		return name;
	}
	
	/**
	 * sets name of the author
	 * @param name : name of the author
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * returns home page of the author
	 * @return homePage : home page of the author
	 */
	public String getHomePage() {
		return homePage;
	}
	
	/**
	 * sets home page of the author
	 * @param homePage : home page of the author
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	/**
	 * returns university of the author
	 * @return university : university of the author
	 */
	public String getUniversity() {
		return university;
	}
	
	/**
	 * sets university of the user
	 * @param university : university of the author
	 */
	public void setUniversity(String university) {
		this.university = university;
	}
	
	/**
	 * returns field of study of the author
	 * @return fieldOfStudy : field of study of author
	 */
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	
	/**
	 * sets field of study of author
	 * @param fieldOfStudy : field of study of author
	 */
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	
	/**
	 * returns region of the author
	 * @return region : region of the author
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * sets region of the author
	 * @param region : region of the author
	 */
	public void setRegion(String region) {
		this.region = region;
	}
}
