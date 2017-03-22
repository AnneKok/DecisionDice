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

public class PizzaConfirmActivity extends AppCompatActivity {

    OOCSIClient sender;
    String twitterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_confirm);

        twitterID = TwitterIDHolder.getInstance().getID();

        // Order a pizza
        OOCSIClient sender = new OOCSIClient("group4");
        new OOCSIMessage(sender, "choosePizza").data("user", twitterID).send();

        // TODO: confirm order using confirmOrder method in Tweeter class
    }

}
