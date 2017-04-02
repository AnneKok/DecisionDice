package com.example.decisiondice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import nl.tue.id.oocsi.*;
import nl.tue.id.oocsi.client.*;
import nl.tue.id.oocsi.client.behavior.*;
import nl.tue.id.oocsi.client.behavior.state.*;
import nl.tue.id.oocsi.client.data.*;
import nl.tue.id.oocsi.client.protocol.*;
import nl.tue.id.oocsi.client.services.*;
import nl.tue.id.oocsi.client.socket.*;
import twitter4j.Query;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

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
public class Tweeter extends AppCompatActivity {

    TwitterFactory factory;
    Twitter twitter;
    TwitterStreamFactory streamFactory;
    TwitterStream twitterStream;

    Query query;
    Status status;
    StatusListener listener;

    int maxTweetLength = 140;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, 'at' HH:mm");

    StatusHandler statusHandler = new StatusHandler(this);

    // TODO: decouple oocsiHandler from Tweeter class (where best to use this?)
    OOCSIHandler oocsiHandler = new OOCSIHandler(maxTweetLength, this);

    /**
     * Sets up Twitter instance, stream listener, and OOCSI receiver.
     * The listener can monitor streaming Twitter data. The OOCSI receiver
     * monitors incoming OOCSI messages on the tweetBot channel.
     */
    public void setup() {

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
     * Uses ASyncTask to complete function.
     *
     * @param  newStatus The status to be posted, a string.
     * @throws  Exception if the status is not updated properly.
     */
    public void postStatus(String newStatus) {
        new TweetPoster().execute(newStatus);
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


    /**
     * Asynchronously posts a tweet to Twitter.
     */
    private class TweetPoster extends AsyncTask<String, Void, String> {

        private Exception exception;

        /**
         * Usual AsyncTask doInBackground method.
         *
         * @param params text of tweet to be posted
         * @return result
         */
        protected String doInBackground(String... params) {
            String newStatus = params[0];
            if (newStatus.length() > maxTweetLength) {
                System.out.println("Error: tweet was too long to send.");
                // For Challenge 2: Also reply over OOCSI that this error occurred.
            } else {
                try {
                    status = twitter.updateStatus(newStatus);
                    System.out.println("[Tweeter] Status updated!");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("An exception occurred while posting the tweet.");
                }
            }
            return("Success");
        }
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

}

