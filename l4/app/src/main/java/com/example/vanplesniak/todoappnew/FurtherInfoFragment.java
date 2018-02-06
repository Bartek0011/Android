package com.example.vanplesniak.todoappnew;

        import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FurtherInfoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.further_info_layout, container, false);
      /*  if(getArguments().getInt("isDualPane") == 1) {
            EditText editText = (EditText) view.findViewById(R.id.furtherInfoTitleEditView);
            editText.setText(getArguments().getString("taskText", "DEFAULT"));
        } */

        if(getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            EditText editText = (EditText) v.findViewById(R.id.furtherInfoTitleEditView);
            editText.setText(getArguments().getString("taskText", "DEFAULT"));
        }

        Button b = (Button)v.findViewById(R.id.furtherInfoButtonAdd);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE) {
                    EditText editTitle = (EditText) getActivity().findViewById(R.id.furtherInfoTitleEditView);
                    EditText editDate = (EditText) getActivity().findViewById(R.id.furtherInfoDateEditView);
                    EditText editHour = (EditText) getActivity().findViewById(R.id.furtherInfoHourEditView);
                    String title = editTitle.getText().toString();
                    String date = editDate.getText().toString();
                    String hour = editHour.getText().toString();
                    //meAddListener.refreshList(title, date, hour, description);
                    TitlesFragment titles = (TitlesFragment)getActivity().getFragmentManager().findFragmentById(R.id.titles);


                } else {
                    getActivity().finish();
                }
            }
        });

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void updateFrag(String text)
    {
        EditText editText = (EditText) getActivity().findViewById(R.id.furtherInfoTitleEditView);
        editText.setText(text);
    }
}
