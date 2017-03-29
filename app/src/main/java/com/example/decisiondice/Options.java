package com.example.decisiondice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Anne on 17/03/2017.
 */

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /** Called when the user taps the Finished button */
    public void toRollDice(View view, String category) {
        Intent intent = new Intent(this, RollDice.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
