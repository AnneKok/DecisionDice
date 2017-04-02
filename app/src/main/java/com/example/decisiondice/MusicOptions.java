package com.example.decisiondice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Subclass for music category.
 *
 * @author Anne Kok
 */
public class MusicOptions extends Options {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_options);
    }

    /**
     * Called when the user presses the Finished button. Currently shows a toast
     * alerting the user that the music functionality has not been implemented.
     *
     * @param view the button that was pressed
     */
    public void toRollDice(View view) {
        // TODO: create music choices and add onCheckboxClicked functionality
        // super.toRollDice(view, "music");
        Toast.makeText(getApplicationContext(),
                "We're sorry, the music functionality has not been implemented yet.",
                Toast.LENGTH_LONG).show();
    }
}
