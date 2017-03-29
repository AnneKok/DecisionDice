package com.example.decisiondice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DrinksOptions extends Options {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_options);
    }

    public void toRollDice(View view) {
        super.toRollDice(view, "drinks");
    }
}
