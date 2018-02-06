package com.example.vanplesniak.todoappnew;


import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;




public class DetailsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.details_layout, container, false);
        if(getArguments() != null) {
            TextView title = (TextView) v.findViewById(R.id.furtherInfoTitleEditViewDetails);
            title.setText(getArguments().getString("title", "TITLE"));
            TextView date = (TextView) v.findViewById(R.id.furtherInfoDateEditViewDetails);
            date.setText(getArguments().getString("date", "DATE"));
            TextView hour = (TextView) v.findViewById(R.id.furtherInfoHourEditViewDetails);
            hour.setText(getArguments().getString("hour", "HOUR"));

        }
        Button b = (Button)v.findViewById(R.id.furtherInfoButtonAddDetails);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE) {

                } else {
                    getActivity().finish();
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void updateFrag(String title, String date, String hour)
    {
        TextView titleL = (TextView) getActivity().findViewById(R.id.furtherInfoTitleEditViewDetails);
        titleL.setText(title);

        TextView dateL = (TextView) getActivity().findViewById(R.id.furtherInfoDateEditViewDetails);
        dateL.setText(date);

        TextView hourL = (TextView) getActivity().findViewById(R.id.furtherInfoHourEditViewDetails);
        hourL.setText(hour);

    }
}
