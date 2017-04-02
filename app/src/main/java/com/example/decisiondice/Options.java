package com.example.decisiondice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Anne on 17/03/2017.
 */

public class Options extends AppCompatActivity {

    int numberOptionsChecked = 0;
    int min = 2;
    int max = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /** Called when the user taps the Finished button */
    public void toRollDice(View view, String category) {
        if(optionsChecked()) {
            Intent intent = new Intent(this, RollDice.class);
            intent.putExtra("category", category);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please select at least 2 and at most 6 options.", LENGTH_LONG).show();
        }
    }

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

    protected boolean optionsChecked() {
        if (numberOptionsChecked >= min && numberOptionsChecked <= max){
            return true;
        } else {
            return false;
        }
    }
}
