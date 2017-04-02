package com.example.decisiondice;

/**
 * Created by Anne on 02/04/2017.
 */

import nl.tue.id.oocsi.*;
import nl.tue.id.oocsi.client.*;
import nl.tue.id.oocsi.client.behavior.*;
import nl.tue.id.oocsi.client.behavior.state.*;
import nl.tue.id.oocsi.client.data.*;
import nl.tue.id.oocsi.client.protocol.*;
import nl.tue.id.oocsi.client.services.*;
import nl.tue.id.oocsi.client.socket.*;

public class OOCSISender {

    private OOCSIClient sender = new OOCSIClient("group4");
    public OOCSIClient getOOCSIsender() {return sender;}
    public void setOOCSIsender(OOCSIClient sender) {this.sender = sender;}

    private static final OOCSISender OOCSIholder = new OOCSISender();
    public static OOCSISender getInstance() {return OOCSIholder;}

}
