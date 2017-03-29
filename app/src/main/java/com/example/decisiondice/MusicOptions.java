package com.example.decisiondice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MusicOptions extends Options {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_options);
    }

    public void toRollDice(View view) {
        // TODO: create music choice
        // super.toRollDice(view, "music");
        Toast.makeText(getApplicationContext(),
                "We're sorry, the music functionality has not been implemented yet.",
                Toast.LENGTH_LONG).show();
    }
}
