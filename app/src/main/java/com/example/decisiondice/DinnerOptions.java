package com.example.decisiondice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Subclass for dinner category.
 *
 * @author Anne Kok
 */
public class DinnerOptions extends Options {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_options);
    }

    /**
     * Called when the user presses the Finished button. Passes category to superclass.
     *
     * @param view the button that was pressed
     */
    public void toRollDice(View view) {
        super.toRollDice(view, "dinner");
    }
}
