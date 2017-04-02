package com.example.decisiondice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Class which allows users to pick a category in which they want the
 * Decision Dice to make a decision (dinner, drinks, music).
 *
 * @author Anne Kok
 */
public class CategoryPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);
    }

    /**
     * Takes the user to the activity where they can pick dinner options.
     *
     * @param view the button that was pressed.
     */
    public void toDinnerOptions(View view) {
        Intent intent = new Intent(this, DinnerOptions.class);
        startActivity(intent);
    }

    /**
     * Takes the user to the activity where they can pick drinks options.
     *
     * @param view the button that was pressed.
     */
    public void toDrinksOptions(View view) {
        Intent intent = new Intent(this, DrinksOptions.class);
        startActivity(intent);
    }

    /**
     * Takes the user to the activity where they can pick music options.
     *
     * @param view the button that was pressed.
     */
    public void toMusicOptions(View view) {
        Intent intent = new Intent(this, MusicOptions.class);
        startActivity(intent);
    }
}
