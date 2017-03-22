package com.example.decisiondice;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import twitter4j.*;
import twitter4j.api.*;
import twitter4j.auth.*;
import twitter4j.conf.*;
import twitter4j.json.*;
import twitter4j.management.*;
import twitter4j.util.*;
import twitter4j.util.function.*;

import nl.tue.id.oocsi.*;
import nl.tue.id.oocsi.client.*;
import nl.tue.id.oocsi.client.behavior.*;
import nl.tue.id.oocsi.client.behavior.state.*;
import nl.tue.id.oocsi.client.data.*;
import nl.tue.id.oocsi.client.protocol.*;
import nl.tue.id.oocsi.client.services.*;
import nl.tue.id.oocsi.client.socket.*;

/**
 * This class is the main class for the Tweetbot module. It sets up
 * the Twitter API clients and the OOCSI receiver. This class also
 * contains a method to post a status to Twitter using the account
 * belonging to the credentials, namely @Tweetbot_DBSU10.
 *
 * @author  Marcin van de Ven
 * @author  Dianne Vasseur
 * @author  Manon Blankendaal
 * @author  Anne Kok
 */
public class Tweeter {

    TwitterFactory factory;
    Twitter twitter;
    TwitterStreamFactory streamFactory;
    TwitterStream twitterStream;

    StatusHandler statusHandler = new StatusHandler();
    OOCSIHandler oocsiHandler = new OOCSIHandler();

    Query query;
    Status status;
    StatusListener listener;

    int maxTweetLength = 140;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, 'at' HH:mm");

    /**
     * Sets up Twitter instance, stream listener, and OOCSI receiver.
     * The listener can monitor streaming Twitter data. The OOCSI receiver
     * monitors incoming OOCSI messages on the tweetBot channel.
     */
    public Tweeter() {

        // Setup Twitter instance
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("CdgOhLWmfD6gO4MqwAsQYpvHu")
                .setOAuthConsumerSecret("LueXAWdgCWluDHq2I5hGlMOw4RwPpx0fsD8RehXuqtMxmyx3Ii")
                .setOAuthAccessToken("831786162872262658-5TdHdOM7tU7eFD3wwJIFUTc7d6tW97z")
                .setOAuthAccessTokenSecret("qreucGdtDLrFoPdzyxVDBaqXQXVgd2Cm11H8jaJJzRjv2");
        Configuration conf = cb.build();

        // Setup Twitter REST API client
        factory = new TwitterFactory(conf);
        streamFactory = new TwitterStreamFactory(conf);
        twitter = factory.getInstance();

        // Setup Twitter Streaming API client
        setupStream();

        // Setup OOCSI receiver
        OOCSI oocsi = new OOCSI(this, "TweetReceiver", "oocsi.id.tue.nl");
        oocsi.subscribe("tweetBot");

    }

    /**
     * Handles OOCSI events.
     * Calls OOCSIHandler's method handleOOCSI.
     *
     * @param  event The incoming OOCSI event to be handled.
     */
    public void tweetBot(OOCSIEvent event) {
        oocsiHandler.handleOOCSI(event);
    }

    /**
     * Sets up streaming API client with listener and filter.
     * Listens for tweets containing @Tweetbot_DBSU10. It takes
     * about a minute to establish a connection. Before the message
     * "Receiving status stream" appears in the console, the
     * Twitter stream is not yet being monitored.
     */
    private void setupStream() {

        listener = new StatusListener() {
            public void onStatus(Status status) {
                statusHandler.handleStatus(status);
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onStallWarning(StallWarning stallWarning) {}
            public void onScrubGeo(long l1, long l2) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        TwitterStream twitterStream = streamFactory.getInstance();
        twitterStream.addListener(listener);
        twitterStream.filter("@Tweetbot_DBSU10");

    }

    /**
     * Posts a status to Twitter. Posts a new status (Tweet) on the
     * Tweetbot_DBSU10 account. Tweets cannot exceed 140 characters.
     * The same status can only be posted once every x hours. The
     * exact value of x is unknown (not released by Twitter).
     *
     * @param  newStatus The status to be posted, a string.
     * @throws  Exception if the status is not updated properly.
     */
    public void postStatus(String newStatus) {

        if (newStatus.length() > maxTweetLength) {
            System.out.println("Error: tweet was too long to send.");
            // For Challenge 2: Also reply over OOCSI that this error occurred.
        } else {
            try {
                status = twitter.updateStatus(newStatus);
                System.out.println("[Tweeter] Status updated!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends an order confirmation for a product to the Twitter
     * user who placed the order.
     *
     * @param  product The product that was ordered.
     * @param  username The Twitter user who ordered it.
     *  ordered.
     */
    public void confirmOrder(String product, String username) {
        String orderReply =
                "@" + username +
                        " Your " + product + " has been ordered!";
        postStatus(orderReply);
    }
}
