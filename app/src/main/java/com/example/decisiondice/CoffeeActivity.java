package com.example.decisiondice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoffeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
    }

    public void rollAgain(View view) {
        Intent intent = new Intent(this, RollDice.class);
        intent.putExtra("category", "drinks");
        startActivity(intent);
    }

    public void orderCoffee(View view) {
        Intent intent = new Intent(this, CoffeeConfirmActivity.class);
        startActivity(intent);
    }
}
