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
 * This class contains a handler for Twitter statuses.
 *
 * @author  Marcin van de Ven
 * @author  Dianne Vasseur
 * @author  Manon Blankendaal
 * @author  Anne Kok
 */
public class StatusHandler extends Tweeter {

    /**
     * Handles a Twitter status. Checks whether the status asks
     * for a deadline, coffee, or pizza. Replies with the next
     * deadline or an order confirmation accordingly. Also checks
     * for cancellations of an order and replies confirmatorily.
     *
     * @param  status The status to be processed.
     */
    public void handleStatus(Status status) {

        System.out.println("[StatusHandler] " + status.getUser().getName() + " : " + status.getText());
        String tweetText = status.getText().toLowerCase();

        // Check for deadline request and handle it
        if(tweetText.contains("deadline")) {
            Deadline nextDeadline = deadlineHandler.getNextDeadline();
            String requestReply = "@" + status.getUser().getScreenName() +
                    " The next deadline is for "
                    + nextDeadline.getItem() + ", on "
                    + dateFormat.format(nextDeadline.getCal().getTime()) + ".";
            postStatus(requestReply);
        }

        // Check for product order and handle it
        checkProduct("coffee", status);
        checkProduct("pizza", status);

        // Check for cancellation
        if(tweetText.contains("cancel")) {
            String cancelReply = "@" + status.getUser().getScreenName() +
                    " Your order has been cancelled.";
            // For Challenge 2: cancel order here
            postStatus(cancelReply);
        }
    }

    /**
     * Checks for a product order in a Twitter status.
     *
     * @param  product The product {@code String} to check for.
     * @param  status The Twitter status to be checked.
     */
    private void checkProduct(String product, Status status) {
        String tweetText = status.getText().toLowerCase();
        if(tweetText.contains("i want " + product)
                || tweetText.contains("order " + product)) {
            // For Challenge 2: order product here
            confirmOrder(product, status);
        }
    }

    /**
     * Sends an order confirmation for a product to the Twitter
     * user who placed the order.
     *
     * @param  product The product that was ordered.
     * @param  status The Twitter status in which the product was
     *  ordered.
     */
    private void confirmOrder(String product, Status status) {
        String orderReply =
                "@" + status.getUser().getScreenName() +
                        " Your " + product + " has been ordered!";
        postStatus(orderReply);
    }

}
