package com.example.vanplesniak.todoappnew;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {
    public List<String> getTaskDataTitle() {
        return taskDataTitle;
    }


    public List<String> getDate() {
        return date;
    }

    public List<String> getHour() {
        return hour;
    }

    public void setTaskDataTitle(List<String> taskDataTitle) {
        this.taskDataTitle = taskDataTitle;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public void setHour(List<String> hour) {
        this.hour = hour;
    }

    private List<String> taskDataTitle;
    private List<String> date;
    private List<String> hour;
    private LayoutInflater inflater;


    public MyAdapter(Context context, int layoutId, List<String> list) {
        super(context, layoutId, list);
        taskDataTitle = list;
        inflater = LayoutInflater.from(context);

        date = new ArrayList<String>();
        hour = new ArrayList<String>();
    }

    String getTaskDataTitle(int index)
    {
        return taskDataTitle.get(index);
    }
    String getDate(int index)
    {
        return date.get(index);
    }
    String getHour(int index)
    {
        return hour.get(index);
    }

    public void updateData(String titleEdit, String dateEdit, String hourEdit) {
        taskDataTitle.add(titleEdit);
        date.add(dateEdit);
        hour.add(hourEdit);
    }

    public void removeData(int position) {
        taskDataTitle.remove(position);
        date.remove(position);
        hour.remove(position);
    }



    @Override
    public View getView(int index, View row, ViewGroup parent) {
        if(row == null)
            row = inflater.inflate(R.layout.mylistlayout, parent, false);

        TextView text = (TextView) row.findViewById(R.id.list_row_text);
        text.setText(taskDataTitle.get(index));

        TextView textDate = (TextView) row.findViewById(R.id.list_layout_date);
        textDate.setText(date.get(index));
        //textDate.setText("12 Wed 2017");

        TextView textHour = (TextView) row.findViewById(R.id.list_layout_hour);
        textHour.setText(hour.get(index));
        //textHour.setText("8:39");

        return row;
    }
}
