package com.example.siemaneczko.hangman;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String word;
    private String[] tword;
    private String[] kword;
    private int badpoints;
    private String editstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        newgame();
    }

    public void newgame(){
        badpoints = 0;
        Button bclick = (Button)findViewById(R.id.bclick);
        bclick.setVisibility(View.VISIBLE);
        change1();

        try {
            Resources res = getResources();
            String[] words = res.getStringArray(R.array.words);
            int rand = new Random().nextInt(words.length);
            word = words[rand];
        } catch (Exception e) {
            e.printStackTrace();
        }

        setArray();
        showWord();

    }

    public void setArray(){
        tword = word.split("");
        kword = tword;

        for(int j=1; j < kword.length; j++)
            kword[j] = "?";

        tword = word.split("");
    }

    public void showWord(){
        TextView letter = (TextView)findViewById(R.id.letter);
        letter.setText(Arrays.toString(kword));

    }

    public void showKey(){
        TextView letter = (TextView)findViewById(R.id.letter);
        letter.setText(Arrays.toString(tword));

    }

    public void newclick(View view) {
        newgame();
    }

    public void click(View view) {
        EditText edit = (EditText)findViewById(R.id.edit);
        editstr = edit.getText().toString();

        checkEdit();
    }

    public void checkEdit(){
        boolean shot = false;

        Button bclick = (Button)findViewById(R.id.bclick);
        for(int i=1; i < tword.length; i++){
            if (Objects.equals(tword[i], editstr)){
                kword[i] = editstr;
                shot = true;
            }
        }




        for(int i=1; i < kword.length; i++) {
            if (!Objects.equals(kword[i], tword[i]))
                break;



            if (i+1 == kword.length) {
                Toast.makeText(this, "you survived!", Toast.LENGTH_SHORT).show();
                bclick.setVisibility(View.GONE);
            }
        }


        if(!shot){
            badpoints ++;
            change1();
            if (badpoints == 6) {
                Toast.makeText(this, "you died", Toast.LENGTH_SHORT).show();
                showKey();
                bclick.setVisibility(View.GONE);
            }
        }



        showWord();
    }


    public void change1(){
        ImageView img = (ImageView)findViewById(R.id.img);
        switch (badpoints){
            case 0: img.setImageResource(R.drawable.w1);
                break;
            case 1: img.setImageResource(R.drawable.w2);
                break;
            case 2: img.setImageResource(R.drawable.w3);
                break;
            case 3: img.setImageResource(R.drawable.w4);
                break;
            case 4: img.setImageResource(R.drawable.w5);
                break;
            case 5: img.setImageResource(R.drawable.w6);
                break;
            case 6: img.setImageResource(R.drawable.w7);
                break;
        }

    }

}
