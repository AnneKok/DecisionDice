package com.example.decisiondice;

import twitter4j.*;
import twitter4j.api.*;
import twitter4j.auth.*;
import twitter4j.conf.*;
import twitter4j.json.*;
import twitter4j.management.*;
import twitter4j.util.*;
import twitter4j.util.function.*;

/**
 * This class serves to handle received Twitter statuses (statuses containing our
 * group's Twitter handle).
 *
 * @author  Marcin van de Ven
 * @author  Dianne Vasseur
 * @author  Manon Blankendaal
 * @author  Anne Kok
 */
public class StatusHandler {

    Tweeter tweeter;

    public StatusHandler(Tweeter tweeter) {
        this.tweeter = tweeter;
    }

    /**
     * Handles an incoming Twitter status. Checks for cancellation of an order.
     *
     * @param  status The status to be processed.
     */
    public void handleStatus(Status status) {

        String tweetText = status.getText().toLowerCase();

        // Check for cancellation
        // TODO: Add real working cancel functionality (track orders)
        if(tweetText.contains("cancel")) {
            String cancelReply = "@" + status.getUser().getScreenName() +
                    " Your order has been cancelled.";
            // TODO: Cancel order
            tweeter.postStatus(cancelReply);
        }
    }

}
