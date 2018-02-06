package com.example.siemaneczko.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int liczba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startgame();

    }

    private void startgame(){
        Random rand = new Random();
        liczba = rand.nextInt(100);
    }

    public void button(View view){
        TextView podana = (TextView)findViewById(R.id.edycja);
        int x = Integer.parseInt(podana.getText().toString());
        EditText edycja = (EditText) findViewById(R.id.edycja);


        if(x == liczba){
            Toast.makeText(this, "Zgadłeś!", Toast.LENGTH_SHORT).show();
            edycja.setBackgroundColor(Color.GREEN);
            startgame();
        }else if(x < liczba){
            Toast.makeText(this, "Liczba jest większa", Toast.LENGTH_SHORT).show();
            edycja.setBackgroundColor(Color.WHITE);
        } else {
            Toast.makeText(this, "Liczba jest mniejsza", Toast.LENGTH_SHORT).show();
            edycja.setBackgroundColor(Color.WHITE);
        }

    }

    public void buttonTwo(View view){
        TextView gora = (TextView) findViewById(R.id.gora);
        gora.setTextColor(Color.RED);

    }


}
