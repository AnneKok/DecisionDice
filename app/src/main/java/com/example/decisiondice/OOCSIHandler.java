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
public class OOCSIHandler extends Tweeter {

    String OOCSItweet;

    /**
     * Handles OOCSI events.
     *
     * @param  event The OOCSI event to be handled. Should be
     *  at most {@code maxTweetLength} characters.
     */
    public void handleOOCSI(OOCSIEvent event) {

        OOCSItweet = event.getString("tweet", "Default tweet. Something went wrong.");
        if (OOCSItweet.length() > maxTweetLength) {
            System.out.println("Error: The status is too long to be posted.");
        }
        System.out.println("[OOCSIHandler] Received a Tweet to be sent!");
        postStatus(OOCSItweet);
    }

}