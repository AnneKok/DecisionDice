package com.example.decisiondice;

/**
 * Singleton class for holding the user's Twitter ID.
 *
 * @author Anne Kok
 */
public class TwitterIDHolder {
    private String ID;
    public String getID() {return ID;}
    public void setID(String ID) {this.ID = ID;}

    private static final TwitterIDHolder holder = new TwitterIDHolder();
    public static TwitterIDHolder getInstance() {return holder;}
}
