package com.example.decisiondice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Superclass for activities where the user can select their options.
 *
 * @author Anne Kok
 */
public class Options extends AppCompatActivity {

    int numberOptionsChecked = 0;
    int min = 2;
    int max = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Called when the user taps the Finished button. Takes the user to the
     * next activity where they are prompted to roll the dice if enough options
     * are selected. Prompts the user to select more or fewer options otherwise.
     *
     * @param view the button that was pressed
     * @param category the category of options (e.g. dinner, drinks, music)
     */
    public void toRollDice(View view, String category) {
        if(optionsChecked()) {
            Intent intent = new Intent(this, RollDice.class);
            intent.putExtra("category", category);
            intent.putExtra("sides", numberOptionsChecked);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please select at least 2 and at most 6 options.", LENGTH_LONG).show();
        }
    }

    /**
     * Keeps count of how many options are selected.
     *
     * @param view the checkbox pressed
     */
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        if(checked) {
            numberOptionsChecked++;
        } else {
            numberOptionsChecked--;
        }
    }

    /**
     * Checks whether enough options are selected.
     *
     * @return whether enough options are selected
     */
    protected boolean optionsChecked() {
        if (numberOptionsChecked >= min && numberOptionsChecked <= max){
            return true;
        } else {
            return false;
        }
    }
}
