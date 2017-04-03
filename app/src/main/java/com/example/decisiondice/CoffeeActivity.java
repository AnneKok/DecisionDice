package com.example.decisiondice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Class for activity to confirm a decision of 'coffee'.
 *
 * @author Anne Kok
 */
public class CoffeeActivity extends AppCompatActivity {

    int noOfSides = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        noOfSides = getIntent().getIntExtra("sides", 0);
    }

    /**
     * Lets the user roll the dice a second time. Called when the user presses the deny button.
     *
     * @param view the button that was pressed
     */
    public void rollAgain(View view) {
        Intent intent = new Intent(this, RollDice.class);
        intent.putExtra("category", "drinks");
        intent.putExtra("sides", noOfSides);
        startActivity(intent);
    }

    /**
     * Starts the coffee order. Called when the user presses the confirmation button.
     *
     * @param view the button that was pressed
     */
    public void orderCoffee(View view) {
        Intent intent = new Intent(this, CoffeeConfirmActivity.class);
        startActivity(intent);
    }
}
