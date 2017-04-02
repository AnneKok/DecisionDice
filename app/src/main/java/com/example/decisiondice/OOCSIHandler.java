package com.example.decisiondice;

import android.os.AsyncTask;

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
    String channelName = "TweetReceiver";
    int maxTweetLength;
    Tweeter tweeter;

    public OOCSIHandler(int maxTweetLength, Tweeter tweeter) {
        this.maxTweetLength = maxTweetLength;
        this.tweeter = tweeter;
        new OOCSISetup().execute(channelName);
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

    public void pizzaResponse(OOCSIEvent pizzaRes) {
        // handle pizza response
    }

    // TODO: Add more OOCSI functionality?

    private class OOCSISetup extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... params) {
            String channel = params[0];
            OOCSI oocsi = new OOCSI(this, channel, "oocsi.id.tue.nl");

            // Subscribe to TweetBot channel
            oocsi.subscribe("tweetBot");

            // Subscribe to Pizza channel and setup undefined variables address and Twitter ID
            oocsi.subscribe("choosePizza", "pizzaResponse");
            oocsi.channel("choosePizza").data("settings", "").data("address", "Dummy Lane 404").send();
            oocsi.channel("choosePizza").data("settings", "").data("twitterAccount" ,
                    TwitterIDHolder.getInstance().getID()).send();

            return("Success");
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }


}