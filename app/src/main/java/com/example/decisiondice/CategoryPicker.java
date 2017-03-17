package com.example.decisiondice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CategoryPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);
    }

    public void toDinnerOptions(View view) {
        Intent intent = new Intent(this, DinnerOptions.class);
        startActivity(intent);
    }

    public void toDrinksOptions(View view) {
        Intent intent = new Intent(this, DrinksOptions.class);
        startActivity(intent);
    }

    public void toMusicOptions(View view) {
        Intent intent = new Intent(this, MusicOptions.class);
        startActivity(intent);
    }
}
