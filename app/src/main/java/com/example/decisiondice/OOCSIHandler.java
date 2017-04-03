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
 * This class contains the OOCSI listener setup and the OOCSI handler method.
 * It handles incoming OOCSI events.
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

    Handler tweetBotHandler = new EventHandler() {
        /**
         * Posts tweet sent through OOCSI.
         *
         * @param oocsiEvent The OOCSI event to be handled. Should be at most {@code maxTweetLength}
         * characters.
         */
        @Override
        public void receive(OOCSIEvent oocsiEvent) {
            OOCSItweet = oocsiEvent.getString("tweet", "Default tweet. Something went wrong.");
            if (OOCSItweet.length() > maxTweetLength) {
                System.out.println("Error: The status is too long to be posted.");
            }
            System.out.println("[OOCSIHandler] Received a Tweet to be sent!");
            tweeter.postStatus(OOCSItweet);
        }
    };

    /**
     * Class to asynchronously set up our OOCSI listeners.
     */
    private class OOCSISetup extends AsyncTask<String, Void, String> {

        private Exception exception;

        /**
         * Regular AsyncTask doInBackground method.
         *
         * @param params channel name
         * @return result
         */
        protected String doInBackground(String... params) {
            String channel = params[0];
            OOCSIClient tweetBot = OOCSISender.getInstance().getOOCSIsender();
            tweetBot.subscribe("tweetBot", tweetBotHandler);
            return("Success");
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }


}