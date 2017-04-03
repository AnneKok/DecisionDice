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

/**
 * Subclass to order pizza via OOCSI.
 *
 * @author Anne Kok
 */
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
        sender.subscribe("choosePizza", pizzaResHandler);
        new OOCSIMessage(sender, "choosePizza").data("settings", "").data("address",
                "Dummy Lane 404").send();
        new OOCSIMessage(sender, "choosePizza").data("settings", "").data("twitterAccount" ,
                TwitterIDHolder.getInstance().getID()).send();

        super.setup();
        super.confirmOrder("pizza", twitterID);
    }

    Handler pizzaResHandler = new EventHandler() {
        @Override
        public void receive(OOCSIEvent oocsiEvent) {
            String pizzaRes = oocsiEvent.getString("response");
            PizzaConfirmActivity.super.postStatus("@" + twitterID + " " + pizzaRes);
        }
    };

}


