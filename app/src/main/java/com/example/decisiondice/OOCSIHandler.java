package com.example.decisiondice;

import nl.tue.id.oocsi.*;
import nl.tue.id.oocsi.client.*;
import nl.tue.id.oocsi.client.behavior.*;
import nl.tue.id.oocsi.client.behavior.state.*;
import nl.tue.id.oocsi.client.data.*;
import nl.tue.id.oocsi.client.protocol.*;
import nl.tue.id.oocsi.client.services.*;
import nl.tue.id.oocsi.client.socket.*;

/**
 * This class contains the OOCSI handler method.
 * It handles incoming OOCSI events. In the future, it will
 * be expanded to allow more OOCSI communication.
 *
 * @author  Marcin van de Ven
 * @author  Dianne Vasseur
 * @author  Manon Blankendaal
 * @author  Anne Kok
 */
public class OOCSIHandler {

    String OOCSItweet;
    int maxTweetLength;
    Tweeter tweeter;

    public OOCSIHandler(int maxTweetLength, Tweeter tweeter) {
        this.maxTweetLength = maxTweetLength;
        this.tweeter = tweeter;
        // TODO: asynctask to fix NetworkOnMainThreadException for OOCSI receiver
        // OOCSI oocsi = new OOCSI(this, "TweetReceiver", "oocsi.id.tue.nl");
        // oocsi.subscribe("tweetBot");
    }

    /**
     * Posts tweet sent through OOCSI.
     *
     * @param  event The OOCSI event to be handled. Should be
     *  at most {@code maxTweetLength} characters.
     */
    public void tweetBot(OOCSIEvent event) {
        OOCSItweet = event.getString("tweet", "Default tweet. Something went wrong.");
        if (OOCSItweet.length() > maxTweetLength) {
            System.out.println("Error: The status is too long to be posted.");
        }
        System.out.println("[OOCSIHandler] Received a Tweet to be sent!");
        tweeter.postStatus(OOCSItweet);
    }

    // TODO: Add more OOCSI functionality?

}