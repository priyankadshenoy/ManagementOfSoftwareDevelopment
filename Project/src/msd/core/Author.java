package msd.core;

public class Author {
    String authorId;
    String name;
    int citationCount=0;
    
    /**
     * returns name of the author
     * @return name : name of the author
     */
    public String getName() {
        return name;
    }

    /**
     *  sets author name
     * @param name : name of the author
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns citation count
     * @return citationCount : citation count of the author
     */
    public int getCitationCount() {
        return citationCount;
    }

    
    /**
     * sets citationCount
     * @param citationCount : citation count of the author
     */
    public void setCitationCount(int citationCount) {
        this.citationCount += citationCount;
    }

}
