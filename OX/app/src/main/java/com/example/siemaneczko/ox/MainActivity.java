package com.example.siemaneczko.ox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int ticker;
    private int[] tab = new int[16];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startgame();

    }

    private void startgame(){
        for(int i=0; i<=15; i++){
            tab[i] = 2;
        }
        ticker = 0;

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setText("");
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setText("");
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setText("");
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setText("");
        Button button5 = (Button)findViewById(R.id.button5);
        button5.setText("");
        Button button6 = (Button)findViewById(R.id.button6);
        button6.setText("");
        Button button7 = (Button)findViewById(R.id.button7);
        button7.setText("");
        Button button8 = (Button)findViewById(R.id.button8);
        button8.setText("");
        Button button9 = (Button)findViewById(R.id.button9);
        button9.setText("");
        Button button10 = (Button)findViewById(R.id.button10);
        button10.setText("");
        Button button11 = (Button)findViewById(R.id.button11);
        button11.setText("");
        Button button12 = (Button)findViewById(R.id.button12);
        button12.setText("");
        Button button13 = (Button)findViewById(R.id.button13);
        button13.setText("");
        Button button14 = (Button)findViewById(R.id.button14);
        button14.setText("");
        Button button15 = (Button)findViewById(R.id.button15);
        button15.setText("");
        Button button16 = (Button)findViewById(R.id.button16);
        button16.setText("");

    }



    private void checkgame(){
        if(ticker == 16) {
            Toast.makeText(this, "Remis", Toast.LENGTH_SHORT).show();
            startgame();
        }

        if( (tab[0] == 1 || tab[0] == 0) && (tab[0] == tab[1] && tab[1] == tab[2] &&  tab[2] == tab[3] ||
                tab[0] == tab[4] && tab[4] == tab[8] &&  tab[8] == tab[12] ||
                tab[0] == tab[5] && tab[5] == tab[10] &&  tab[10] == tab[15])) {
            if(tab[0] == 1) {
                Toast.makeText(this, "Wygrał X!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wygrało O!", Toast.LENGTH_SHORT).show();
            }
            startgame();
        }
        if( (tab[15] == 1 || tab[15] == 0) && (tab[12] == tab[13] && tab[13] == tab[14] &&  tab[14] == tab[15] ||
                tab[3] == tab[7] && tab[7] == tab[11] &&  tab[11] == tab[15])) {
            if(tab[15] == 1) {
                Toast.makeText(this, "Wygrał X!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wygrało O!", Toast.LENGTH_SHORT).show();
            }
            startgame();
        }
        if( (tab[4] == 1 || tab[4] == 0) && (tab[4] == tab[5] && tab[5] == tab[6] &&  tab[6] == tab[7])) {
            if(tab[4] == 1) {
                Toast.makeText(this, "Wygrał X!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wygrało O!", Toast.LENGTH_SHORT).show();
            }
            startgame();
        }
        if( (tab[8] == 1 || tab[8] == 0) && (tab[8] == tab[9] && tab[9] == tab[10] &&  tab[10] == tab[11])) {
            if(tab[8] == 1) {
                Toast.makeText(this, "Wygrał X!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wygrało O!", Toast.LENGTH_SHORT).show();
            }
            startgame();
        }
        if( (tab[1] == 1 || tab[1] == 0) && (tab[1] == tab[5] && tab[5] == tab[9] &&  tab[9] == tab[13])) {
            if(tab[1] == 1) {
                Toast.makeText(this, "Wygrał X!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wygrało O!", Toast.LENGTH_SHORT).show();
            }
            startgame();
        }
        if( (tab[2] == 1 || tab[2] == 0) && (tab[2] == tab[6] && tab[6] == tab[10] &&  tab[10] == tab[14])) {
            if(tab[2] == 1) {
                Toast.makeText(this, "Wygrał X!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wygrało O!", Toast.LENGTH_SHORT).show();
            }
            startgame();
        }
        if( (tab[3] == 1 || tab[3] == 0) && (tab[3] == tab[6] && tab[6] == tab[9] &&  tab[9] == tab[12])) {
            if(tab[3] == 1) {
                Toast.makeText(this, "Wygrał X!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wygrało O!", Toast.LENGTH_SHORT).show();
            }
            startgame();
        }

    }


    public void button1(View view){
        Button button1 = (Button)findViewById(R.id.button1);

        if(button1.getText() == "X" || button1.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button1.setText("X");
            tab[0] = 1;
        } else {
            button1.setText("O");
            tab[0] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button2(View view){
        Button button2 = (Button)findViewById(R.id.button2);
        if(button2.getText() == "X" || button2.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button2.setText("X");
            tab[1] = 1;
        } else {
            button2.setText("O");
            tab[1] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button3(View view){
        Button button3 = (Button)findViewById(R.id.button3);
        if(button3.getText() == "X" || button3.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button3.setText("X");
            tab[2] = 1;
        } else {
            button3.setText("O");
            tab[2] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button4(View view){
        Button button4 = (Button)findViewById(R.id.button4);
        if(button4.getText() == "X" || button4.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button4.setText("X");
            tab[3] = 1;
        } else {
            button4.setText("O");
            tab[3] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button5(View view){
        Button button5 = (Button)findViewById(R.id.button5);
        if(button5.getText() == "X" || button5.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button5.setText("X");
            tab[4] = 1;
        } else {
            button5.setText("O");
            tab[4] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button6(View view){
        Button button6 = (Button)findViewById(R.id.button6);
        if(button6.getText() == "X" || button6.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button6.setText("X");
            tab[5] = 1;
        } else {
            button6.setText("O");
            tab[5] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button7(View view){
        Button button7 = (Button)findViewById(R.id.button7);
        if(button7.getText() == "X" || button7.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button7.setText("X");
            tab[6] = 1;
        } else {
            button7.setText("O");
            tab[6] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button8(View view){
        Button button8 = (Button)findViewById(R.id.button8);
        if(button8.getText() == "X" || button8.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button8.setText("X");
            tab[7] = 1;
        } else {
            button8.setText("O");
            tab[7] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button9(View view){
        Button button9 = (Button)findViewById(R.id.button9);
        if(button9.getText() == "X" || button9.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button9.setText("X");
            tab[8] = 1;
        } else {
            button9.setText("O");
            tab[8] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button10(View view){
        Button button10 = (Button)findViewById(R.id.button10);
        if(button10.getText() == "X" || button10.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button10.setText("X");
            tab[9] = 1;
        } else {
            button10.setText("O");
            tab[9] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button11(View view){
        Button button11 = (Button)findViewById(R.id.button11);
        if(button11.getText() == "X" || button11.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button11.setText("X");
            tab[10] = 1;
        } else {
            button11.setText("O");
            tab[10] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button12(View view){
        Button button12 = (Button)findViewById(R.id.button12);
        if(button12.getText() == "X" || button12.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button12.setText("X");
            tab[11] = 1;
        } else {
            button12.setText("O");
            tab[11] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button13(View view){
        Button button13 = (Button)findViewById(R.id.button13);
        if(button13.getText() == "X" || button13.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button13.setText("X");
            tab[12] = 1;
        } else {
            button13.setText("O");
            tab[12] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button14(View view){
        Button button14 = (Button)findViewById(R.id.button14);
        if(button14.getText() == "X" || button14.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button14.setText("X");
            tab[13] = 1;
        } else {
            button14.setText("O");
            tab[13] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button15(View view){
        Button button15 = (Button)findViewById(R.id.button15);
        if(button15.getText() == "X" || button15.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button15.setText("X");
            tab[14] = 1;
        } else {
            button15.setText("O");
            tab[14] = 0;
        }
        ticker ++;
        checkgame();
    }
    public void button16(View view){
        Button button16 = (Button)findViewById(R.id.button16);
        if(button16.getText() == "X" || button16.getText() == "O"){
            return;
        }

        if(ticker%2 == 0){
            button16.setText("X");
            tab[15] = 1;
        } else {
            button16.setText("O");
            tab[15] = 0;
        }
        ticker ++;
        checkgame();
    }
}
