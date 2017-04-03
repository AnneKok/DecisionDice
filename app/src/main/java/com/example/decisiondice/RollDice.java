package com.example.decisiondice;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

// TODO: fix gestures import, uncomment related code in this class
// import gestures.*;

import java.util.Random;

import nl.tue.id.oocsi.*;
import nl.tue.id.oocsi.client.*;
import nl.tue.id.oocsi.client.behavior.*;
import nl.tue.id.oocsi.client.behavior.state.*;
import nl.tue.id.oocsi.client.data.*;
import nl.tue.id.oocsi.client.protocol.*;
import nl.tue.id.oocsi.client.services.*;
import nl.tue.id.oocsi.client.socket.*;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Class for rolling the dice.
 *
 * @author Anne Kok
 */
public class RollDice extends AppCompatActivity {

    int noOfSides = 0;
    Random gen = new Random();
    int decision = 0;

    // After this time-out, the decision will be shown to the user
    // TODO: make sure Gestures detection works during the timeout
    private static int TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);

        /*
        // Setup Gestures module
        OOCSIClient client = Gestures.newClient();
        Gestures.enable(this, client);

        */

        String category = getIntent().getStringExtra("category");
        noOfSides = getIntent().getIntExtra("sides", 0);
        if (noOfSides == 0) {
            Toast.makeText(getApplicationContext(),
                    "Something went wrong with selecting options." +
                            " Please try again.", LENGTH_LONG).show();
        }
        else if (category.equals("dinner")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(RollDice.this, PizzaActivity.class);
                    startActivity(i);
                    finish();
                }
            }, TIME_OUT);
        } else if (category.equals("drinks")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(RollDice.this, CoffeeActivity.class);
                    startActivity(i);
                    finish();
                }
            }, TIME_OUT);
        }
    }

/*
    Gesture.ROLL_LEFT.addListener(new GestureHandler() {
        @Override
        public void gesturePerformed(GestureEvent gestureEvent) {
            handleGesture(gestureEvent);
        }
    });

    Gesture.ROLL_RIGHT.addListener(new GestureHandler() {
        @Override
        public void gesturePerformed(GestureEvent gestureEvent) {
            handleGesture(gestureEvent);
        }
    });

    Gesture.FLIP_UP.addListener(new GestureHandler() {
        @Override
        public void gesturePerformed(GestureEvent gestureEvent) {
            handleGesture(gestureEvent);
        }
    });

    Gesture.FLIP_DOWN.addListener(new GestureHandler() {
        @Override
        public void gesturePerformed(GestureEvent gestureEvent) {
            handleGesture(gestureEvent);
        }
    });
*/

    /**
     * Handles a gesture by updating the decision to a random integer.
     *
     * @param gestureEvent the gesture to be handled
     */
/*
    private void handleGesture(GestureEvent gestureEvent) {
        gestureEvent.setFeedback(false)
        gestureEvent.setCancelled(true);
        decision = 1 + gen.nextInt(noOfSides);
    }
*/

    // TODO: pass selected options to the dice in an array
    // so that the decision is one of the options in the array using int decision-1 as array index

}
