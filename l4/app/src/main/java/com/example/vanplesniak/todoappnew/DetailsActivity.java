package com.example.vanplesniak.todoappnew;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;




public class DetailsActivity extends Activity {


    @Override
    public void finish() {
        Intent data = new Intent();
        setResult(RESULT_OK, data);
        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }


        DetailsFragment details = (DetailsFragment)
                getFragmentManager().findFragmentById(R.id.details_in_activity);

        String title = getIntent().getStringExtra("taskTitle");
        String date = getIntent().getStringExtra("taskDate");
        String hour = getIntent().getStringExtra("taskHour");
        details.updateFrag(title, date, hour);

    }
}
