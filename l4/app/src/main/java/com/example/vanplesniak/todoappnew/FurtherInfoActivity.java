package com.example.vanplesniak.todoappnew;


        import android.app.Activity;
        import android.content.Intent;
        import android.content.res.Configuration;
        import android.os.Bundle;
        import android.widget.EditText;


public class FurtherInfoActivity extends Activity {


    @Override
    public void finish() {
        EditText taskTitle = (EditText)findViewById(R.id.furtherInfoTitleEditView);
        EditText taskDate = (EditText)findViewById(R.id.furtherInfoDateEditView);
        EditText taskHour = (EditText)findViewById(R.id.furtherInfoHourEditView);

        Intent data = new Intent();
        data.putExtra("taskTitle", taskTitle.getText().toString());
        data.putExtra("taskDate", taskDate.getText().toString());
        data.putExtra("taskHour", taskHour.getText().toString());
        setResult(RESULT_OK, data);
        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_further_info);
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }


        FurtherInfoFragment details = (FurtherInfoFragment)
                getFragmentManager().findFragmentById(R.id.details_in_furtherInfo);

        String text = getIntent().getStringExtra("taskText");
        details.updateFrag(text);

    }
}
