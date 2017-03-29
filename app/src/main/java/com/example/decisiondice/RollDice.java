package com.example.decisiondice;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RollDice extends AppCompatActivity {

    // After this time-out, the decision will be shown to the user
    // TODO: use Gestures detection rather than a timeout
    private static int TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);
        String category = getIntent().getStringExtra("category");
        if (category.equals("dinner")) {
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
}
