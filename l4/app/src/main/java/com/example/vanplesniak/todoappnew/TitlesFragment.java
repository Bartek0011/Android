package com.example.vanplesniak.todoappnew;



        import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by aaa on 2017-04-09.
 */

public class TitlesFragment extends ListFragment{
    boolean mDualPane;
    boolean fragmentCreated;
    MyAdapter myAdapter;
    ArrayList<String> description;
    static final int GET_STARS_REQUEST = 1;
    static final int GET_DESCRIPTION_REQUEST = 2;
    static final int YES_NO_CALL = 3;
    static final String PREFS_NAME = "ZAPISPLIK";
    int positionToBeRemoved;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        description = new ArrayList<String>();
        /*
        for(int i=0; i<maxImg; ++i) {
            starList[i*(maxImg-1)] = 0.0f;
            starList[i*(maxImg-1) + 1] = 0.0f;
            starList[i*(maxImg-1) + 2] = 0.0f;
            starList[i*(maxImg-1) + 3] = 0.0f;
            myImageList.add(R.drawable.a);
            myImageList.add(R.drawable.b);
            myImageList.add(R.drawable.c);
            myImageList.add(R.drawable.d);
            description.add(getResources().getString(R.string.boliwia_descr));
            description.add(getResources().getString(R.string.japonia_descr));
            description.add(getResources().getString(R.string.portugalia_descr));
            description.add(getResources().getString(R.string.australia_descr));
        }
    */

        myAdapter = new MyAdapter(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                description);

        // Populate list with our static array of titles.
        setListAdapter(myAdapter);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                showDialogWindow("Delete warning", "Do you want to delete this item?");
                positionToBeRemoved = position;
                return true; // i juz nie uruchomi zwylego clicka
            }
        });

        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        // mDualPane = false;
        // if (getResources().getConfiguration().orientation
        //         == Configuration.ORIENTATION_LANDSCAPE)
        //     mDualPane = true;
        View detailsFrame = getActivity().findViewById(R.id.detailsFrameLayout); //.details
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        Button add = (Button) getActivity().findViewById(R.id.main_button_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) getActivity().findViewById(R.id.main_edit_text);
                //myAdapter.addData(et.getText().toString());
                //myAdapter.notifyDataSetChanged();
                if(!mDualPane) { // start new Activity
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), FurtherInfoActivity.class);
                    intent.putExtra("taskText", et.getText().toString());
                    startActivityForResult(intent, GET_DESCRIPTION_REQUEST);
                    et.setText("");
                } else { // show further info fragment on the right
                    FurtherInfoFragment fragment = new FurtherInfoFragment();
                    //fragment.registerListener((FurtherInfoFragment.OnCategoryAddedListener)getActivity());
                    Bundle args = new Bundle();
                    args.putString("taskText", et.getText().toString());
                    fragment.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.detailsFrameLayout, fragment).commit();
                    getFragmentManager().popBackStack();
                    et.setText("");
                }
            }
        });



        if(savedInstanceState != null) {

            fragmentCreated = savedInstanceState.getBoolean("fragmentCreated");
            List<String> title = new ArrayList<>();
            title = savedInstanceState.getStringArrayList("title");
            //myAdapter.setTaskDataTitle(title);
            //myAdapter.setTaskDataTitle(savedInstanceState.getStringArrayList("title"));
            ArrayList<String> date = savedInstanceState.getStringArrayList("date");
            //myAdapter.setDate(date);
            ArrayList<String> hour = savedInstanceState.getStringArrayList("hour");
            //myAdapter.setHour(hour);
            ArrayList<String> desc = savedInstanceState.getStringArrayList("desc");
            //myAdapter.setDescription(desc);
            for(int i=0; i<title.size(); ++i) {
                Log.i("ZAWARTOSC: ", title.get(i) + " ");
                myAdapter.updateData(title.get(i), date.get(i), hour.get(i));
            }
            myAdapter.notifyDataSetChanged();
        }
        else {
            fragmentCreated = false;
            //odczytywanie SharedPreferences
            //getActivity().getSharedPreferences(PREFS_NAME, 0).edit().clear().commit();
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
            String title = settings.getString("taskTitle", "SharedPref");
            if(!title.equals("SharedPref")) {
                String[] titleArr = title.split(",");

                String date = settings.getString("date", "SharedPref");
                String[] dateArr = date.split(",");

                String hour = settings.getString("hour", "SharedPref");
                String[] hourArr = hour.split(",");

                String desc = settings.getString("desc", "SharedPref");
                String[] descArr = desc.split(",");

                for(int i=0; i < titleArr.length; ++i)
                    Log.i("ODCZYT1 " + i + ": ", titleArr[i]);

                for(int i=0; i < dateArr.length; ++i)
                    Log.i("ODCZYT2 " + i + ": ", dateArr[i]);

                for(int i=0; i < hourArr.length; ++i)
                    Log.i("ODCZYT3 " + i + ": ", hourArr[i]);

                for(int i=0; i < descArr.length; ++i)
                    Log.i("ODCZYT4 " + i + ": ", descArr[i]);


                for (int i = 0; i < titleArr.length; ++i) {
                    myAdapter.updateData(titleArr[i], dateArr[i], hourArr[i]);
                }
                myAdapter.notifyDataSetChanged();
            }
        }

        if (mDualPane) {

            // In dual-pane mode, the list view highlights the selected item.
            // getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(0);
        }
    }

    public void showDialogWindow(String title, String message)
    {
        YesNoDialog dialog = new YesNoDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        dialog.setArguments(args);
        dialog.setTargetFragment(this, YES_NO_CALL);
        dialog.show(getFragmentManager(), "tag");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("fragmentCreated", fragmentCreated);
        outState.putStringArrayList("title", new ArrayList<String>(myAdapter.getTaskDataTitle()));
        outState.putStringArrayList("date",  new ArrayList<String>(myAdapter.getDate()));
        outState.putStringArrayList("hour", new ArrayList<String>(myAdapter.getHour()));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        getActivity().getSharedPreferences(PREFS_NAME, 0).edit().clear().commit();
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences prefs = this.getActivity().getSharedPreferences(
                PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        //zapis taskTitle
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myAdapter.getTaskDataTitle().size(); i++) {
            sb.append(myAdapter.getTaskDataTitle(i)).append(" ,");
        }
        if(myAdapter.getTaskDataTitle().size() > 0)
            sb.deleteCharAt(sb.length()-1);
        editor.putString("taskTitle", sb.toString());
        Log.i("ZAPIS1: ", sb.toString());
        sb.setLength(0); // czyszczenie StringBuildera

        //zapis daty
        for (int i = 0; i < myAdapter.getDate().size(); i++) {
            sb.append(myAdapter.getDate(i)).append(" ,");
        }
        if(myAdapter.getTaskDataTitle().size() > 0)
            sb.deleteCharAt(sb.length()-1);
        editor.putString("date", sb.toString());
        Log.i("ZAPIS2: ", sb.toString());
        sb.setLength(0); // czyszczenie StringBuildera

        //zapis godziny
        for (int i = 0; i < myAdapter.getHour().size(); i++) {
            sb.append(myAdapter.getHour(i)).append(" ,");
        }
        if(myAdapter.getTaskDataTitle().size() > 0)
            sb.deleteCharAt(sb.length()-1);
        editor.putString("hour", sb.toString());
        Log.i("ZAPIS3: ", sb.toString());
        sb.setLength(0); // czyszczenie StringBuildera

        //zapis opisu

        if(myAdapter.getTaskDataTitle().size() > 0)
            sb.deleteCharAt(sb.length()-1);
        editor.putString("desc", sb.toString());
        Log.i("ZAPIS4: ", sb.toString());
        sb.setLength(0); // czyszczenie StringBuildera

        // Commit the edits!
        editor.commit();
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    public void updateTitlesAdapter(String title, String date, String hour) {
        myAdapter.updateData(title, date, hour);
        myAdapter.notifyDataSetChanged();
    }

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    void showDetails(int position) {
        if (mDualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            // getListView().setItemChecked(position, true);

            DetailsFragment details = new DetailsFragment();
            if(fragmentCreated) {
                Bundle args = new Bundle();
                args.putString("title", myAdapter.getTaskDataTitle(position));
                args.putString("date", myAdapter.getDate(position));
                args.putString("hour", myAdapter.getHour(position));
                details.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.detailsFrameLayout, details).commit();
                getFragmentManager().popBackStack();


            } else {
                Bundle args = new Bundle();
                args.putString("title", "");
                args.putString("date", "");
                args.putString("hour", "");
                args.putString("desc", "");
                details.setArguments(args);
                getFragmentManager().beginTransaction().add(R.id.detailsFrameLayout, details).commit();
                fragmentCreated = true;
            }


            // DetailsFragment details = (DetailsFragment)
            //              getFragmentManager().findFragmentById(R.id.details);

            //details.updateFrag(myAdapter.getTaskDataTitle(position), myAdapter.getDate(position),
            //          myAdapter.getHour(position), myAdapter.getDesc(position));
            // }

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailsActivity.class);
            //intent.putExtra("multipane", true);
            intent.putExtra("taskTitle", myAdapter.getTaskDataTitle(position));
            intent.putExtra("taskDate", myAdapter.getDate(position));
            intent.putExtra("taskHour", myAdapter.getHour(position));
            startActivityForResult(intent, GET_STARS_REQUEST);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_STARS_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                //
            }
        } else if(requestCode == GET_DESCRIPTION_REQUEST && resultCode == RESULT_OK) {
            String taskTitle = data.getStringExtra("taskTitle");
            String taskDate = data.getStringExtra("taskDate");
            String taskHour = data.getStringExtra("taskHour");
            String taskDesc = data.getStringExtra("taskDesc");

            myAdapter.updateData(taskTitle, taskDate, taskHour);
            myAdapter.notifyDataSetChanged();
        } else if(requestCode == YES_NO_CALL) {
            if(resultCode == RESULT_OK) { // czyli usuwamy
                myAdapter.removeData(positionToBeRemoved);
                myAdapter.notifyDataSetChanged();

            }
        }
    }
}