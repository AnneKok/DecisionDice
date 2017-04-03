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

/**
 * Subclass to order coffee via OOCSI.
 *
 * @author Anne Kok
 */
public class CoffeeConfirmActivity extends Tweeter {

    OOCSIClient sender;
    String twitterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_confirm);

        twitterID = TwitterIDHolder.getInstance().getID();

        // Order coffee via OOCSI
        sender = OOCSISender.getInstance().getOOCSIsender();
        sender.subscribe("coffee_channel", coffeeResHandler);
        new OOCSIMessage(sender, "coffee_channel")
                .data("caffee_who", new Random().nextInt(99999)+1)
                .data("caffee_amount", 1)
                .data("caffee_time_to_wait", 2)
                .send();

        super.setup();
        super.confirmOrder("coffee", twitterID);
    }

    /**
     * Handler method for the OOCSI response of the coffee module.
     */
    Handler coffeeResHandler = new EventHandler() {
        @Override
        public void receive(OOCSIEvent oocsiEvent) {
            int coffeeRes = oocsiEvent.getInt("output_type", 0);
                if(coffeeRes == 0 || coffeeRes == 2 | coffeeRes == 6) {
                    CoffeeConfirmActivity.super.postStatus("@" + twitterID + " " +
                            "Sorry, something went wrong with your order. Please try again!"
                    );
                } else if (coffeeRes == 3) {
                    CoffeeConfirmActivity.super.postStatus("@" + twitterID + " " +
                            "Your coffee is being prepared!"
                    );
                } else if (coffeeRes == 4) {
                    CoffeeConfirmActivity.super.postStatus("@" + twitterID + " " +
                            "Your coffee is ready!"
                    );
                }
        }
    };

}
