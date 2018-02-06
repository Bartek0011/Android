package com.example.siemaneczko.textphotofromwww;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class MainActivity extends AppCompatActivity {

    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchData("http://api.icndb.com/jokes/random");
    }

    private void processData(String data){
        JSONObject json = null;
        try {
            json = new JSONObject(data);
            JSONObject value = json.getJSONObject("value");
            joke = value.getString("joke");

            runOnUiThread(new Runnable(){
                public void run(){
                    final TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(joke);
                }
            });

        } catch (JSONException e) {
            //Toast.makeText(this, "exception", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void fetchData(String urlString){
        Ion.with(this)
                .load(urlString)
                .asString()
                .setCallback(new FutureCallback<String>(){
                    public void onCompleted(Exception e, String data){
                        JSONObject json = null;
                        try {
                            json = new JSONObject(data);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                        assert json != null;
                        processData(json.toString());
                    }
                });
    }

    public void fetchDataPicture(String urlString){
        Ion.with(this)
                .load(urlString)
                .asString()
                .setCallback(new FutureCallback<String>(){
                    public void onCompleted(Exception e, String data){

                        JSONObject json = null;
                        try {
                            json = XML.toJSONObject(data);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                        processData(json.toString());
                    }
                });
    }

    public void buttonLeft(View view){
        fetchData("http://api.icndb.com/jokes/random");
    }


}
