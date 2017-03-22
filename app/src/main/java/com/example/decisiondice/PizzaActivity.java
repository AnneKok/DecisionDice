package com.example.decisiondice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
    }

    public void rollAgain(View view) {
        Intent intent = new Intent(this, RollDice.class);
        startActivity(intent);
    }

    public void orderPizza(View view) {
        Intent intent = new Intent(this, PizzaConfirmActivity.class);
        startActivity(intent);
    }

}
