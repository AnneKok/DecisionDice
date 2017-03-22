package com.example.decisiondice;

/**
 * Created by Anne on 22/03/2017.
 */

public class TwitterIDHolder {
    private String ID;
    public String getID() {return ID;}
    public void setID(String ID) {this.ID = ID;}

    private static final TwitterIDHolder holder = new TwitterIDHolder();
    public static TwitterIDHolder getInstance() {return holder;}
}
