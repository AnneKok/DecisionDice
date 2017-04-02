package com.example.decisiondice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.tue.id.oocsi.*;
import nl.tue.id.oocsi.client.*;
import nl.tue.id.oocsi.client.behavior.*;
import nl.tue.id.oocsi.client.behavior.state.*;
import nl.tue.id.oocsi.client.data.*;
import nl.tue.id.oocsi.client.protocol.*;
import nl.tue.id.oocsi.client.services.*;
import nl.tue.id.oocsi.client.socket.*;

public class PizzaConfirmActivity extends Tweeter {

    OOCSIClient sender;
    String twitterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_confirm);

        twitterID = TwitterIDHolder.getInstance().getID();

        // Order pizza via OOCSI
        sender = OOCSISender.getInstance().getOOCSIsender();
        new OOCSIMessage(sender, "choosePizza").data("user", twitterID).send();

        super.setup();
        super.confirmOrder("pizza", twitterID);
    }

}


