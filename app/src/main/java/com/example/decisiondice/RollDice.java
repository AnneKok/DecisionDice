package com.example.decisiondice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Random;

import nl.tue.dbsu10.group6.gestures.Gesture;
import nl.tue.dbsu10.group6.gestures.GestureEvent;
import nl.tue.dbsu10.group6.gestures.GestureHandler;
import nl.tue.dbsu10.group6.gestures.Gestures;
import nl.tue.id.oocsi.client.OOCSIClient;

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
    boolean rolling = true;

    // After this time-out, the decision will be shown to the user
    private static final int TIME_OUT = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);

        // Setup Gestures module
        rolling = true;
        OOCSIClient client = Gestures.newClient();
        Gestures.enable(this, client);
        addGestureListeners();

        String category = getIntent().getStringExtra("category");
        noOfSides = getIntent().getIntExtra("sides", 0);
        // Set decision in case no gestures are detected during rolling
        decision = 1 + gen.nextInt(noOfSides);
        if (noOfSides == 9999) {
            Toast.makeText(getApplicationContext(),
                    "Something went wrong with selecting options." +
                            " Please try again.", LENGTH_LONG).show();
        }
        else if (category.equals("dinner")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rolling = false;
                    Intent i = new Intent(RollDice.this, PizzaActivity.class);
                    i.putExtra("sides", noOfSides);
                    startActivity(i);
                    finish();
                }
            }, TIME_OUT);
        } else if (category.equals("drinks")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rolling = false;
                    Intent i = new Intent(RollDice.this, CoffeeActivity.class);
                    i.putExtra("sides", noOfSides);
                    startActivity(i);
                    finish();
                }
            }, TIME_OUT);
        }
    }

    GestureHandler gestureHandler = new GestureHandler() {
        @Override
        public void gesturePerformed(GestureEvent gestureEvent) {
            handleGesture(gestureEvent);
        }
    };

    /**
     * Handles a gesture by updating the decision to a random integer.
     * Only does this when the dice is still 'rolling' (prior to the time-out).
     *
     * @param gestureEvent the gesture to be handled
     */
    private void handleGesture(GestureEvent gestureEvent) {
        gestureEvent.setFeedback(false);
        gestureEvent.setCancelled(true);
        if (rolling) {
            decision = 1 + gen.nextInt(noOfSides);
        }
    }

    /**
     * Adds a listener for the four gestures.
     */
    private void addGestureListeners() {
        Gesture.ROLL_LEFT.addListener(gestureHandler);
        Gesture.ROLL_RIGHT.addListener(gestureHandler);
        Gesture.FLIP_UP.addListener(gestureHandler);
        Gesture.FLIP_DOWN.addListener(gestureHandler);
    }

    // TODO: pass selected options to the dice in an array
    // so that the decision is one of the options in the array using int decision-1 as array index
}
