package com.example.omnip.miwokclear;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Numbers
        TextView numbers = (TextView) findViewById(R.id.numbersCat);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent numbersIntent = new Intent(MainActivity.this, numbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        //Family
        TextView family = (TextView) findViewById(R.id.familyCat);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent familyIntent = new Intent(MainActivity.this, familyActivity.class);
                startActivity(familyIntent);
            }
        });

        //Phrases
        TextView phrases = (TextView) findViewById(R.id.phrasesCat);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent phrasesIntent = new Intent(MainActivity.this, phrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });

        //Colors
        TextView color = (TextView) findViewById(R.id.colorsCat);
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent colorIntent = new Intent(MainActivity.this, colorActivity.class);
                startActivity(colorIntent);
            }
        });

    }

}
