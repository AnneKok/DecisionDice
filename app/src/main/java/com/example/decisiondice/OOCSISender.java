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
 * Singleton class to hold our OOCSI client.
 *
 * @author Anne Kok
 */
public class OOCSISender {

    private OOCSIClient sender = new OOCSIClient("group4");

    /**
     * Getter for OOCSI sender.
     *
     * @return OOCSI sender for the session.
     */
    public OOCSIClient getOOCSIsender() {return sender;}

    /**
     * Setter for OOCSI sender.
     *
     * @param sender the {@code OOCSIClient} to be set as sender for the session.
     */
    public void setOOCSIsender(OOCSIClient sender) {this.sender = sender;}

    private static final OOCSISender OOCSIholder = new OOCSISender();

    /**
     * Getter for the instance of this singleton class.
     *
     * @return singleton OOCSISender
     */
    public static OOCSISender getInstance() {return OOCSIholder;}

}
