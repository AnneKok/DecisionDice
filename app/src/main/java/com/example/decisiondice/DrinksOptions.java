package com.example.decisiondice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Subclass for drinks category.
 *
 * @author Anne Kok
 */
public class DrinksOptions extends Options {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_options);
    }

    /**
     * Called when the user presses the Finished button. Passes category to superclass.
     *
     * @param view the button that was pressed
     */
    public void toRollDice(View view) {
        super.toRollDice(view, "drinks");
    }
}
