package com.example.decisiondice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import nl.tue.id.oocsi.*;
import nl.tue.id.oocsi.client.*;
import nl.tue.id.oocsi.client.behavior.*;
import nl.tue.id.oocsi.client.behavior.state.*;
import nl.tue.id.oocsi.client.data.*;
import nl.tue.id.oocsi.client.protocol.*;
import nl.tue.id.oocsi.client.services.*;
import nl.tue.id.oocsi.client.socket.*;

public class CoffeeConfirmActivity extends Tweeter {

    OOCSIClient sender;
    String twitterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_confirm);

        twitterID = TwitterIDHolder.getInstance().getID();

        // Order coffee
        OOCSIClient sender = new OOCSIClient("group4");
        new OOCSIMessage(sender, "coffee_channel")
                .data("caffee_who", new Random().nextInt(99999)+1)
                .data("caffee_amount", 1)
                .data("caffee_time_to_wait", 2)
                .send();

        super.setup();
        super.confirmOrder("coffee", twitterID);
    }

}
