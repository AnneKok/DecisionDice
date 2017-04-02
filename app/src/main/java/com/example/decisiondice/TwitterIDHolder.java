package com.example.decisiondice;

/**
 * Singleton class for holding the user's Twitter ID.
 *
 * @author Anne Kok
 */
public class TwitterIDHolder {
    private String ID;

    /**
     * Getter for the user's Twitter ID
     *
     * @return the user's Twitter username (ID)
     */
    public String getID() {return ID;}

    /**
     * Setter for the user's Twitter ID
     *
     * @param ID the user's Twitter username (ID)
     */
    public void setID(String ID) {this.ID = ID;}

    private static final TwitterIDHolder holder = new TwitterIDHolder();

    /**
     * Getter for the instance of this singleton class.
     *
     * @return singleton TwitterIDHolder
     */
    public static TwitterIDHolder getInstance() {return holder;}
}
