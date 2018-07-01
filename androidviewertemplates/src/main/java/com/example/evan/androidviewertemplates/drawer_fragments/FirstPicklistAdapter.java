package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.evan.androidviewertemplates.drawer_fragments.PicklistCell;
import com.example.evan.androidviewertools.utils.Constants;


import java.util.Map;

/**
 * Created by Teo on 3/10/2018.
 */

public class FirstPicklistAdapter extends BaseAdapter {
    Context context;
    Map<Integer, String> teams;

    public FirstPicklistAdapter(Context context, Map<Integer, String> teams) {

            super();
            this.context = context;
            this.teams = teams;


    }

    @Override
    public int getCount() {
        return teams.size();
    }


    @Override
    public Object getItem(int position) {
        return getItem(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String teamNumber = teams.get(position);
        Integer teamPicklistPosition = position;
        PicklistCell cell = new PicklistCell(context, teamNumber, teamPicklistPosition);
        convertView = cell;
        for (int i = 0; i < Constants.alreadySelectedOnPicklist.size(); i++) {
            if (Constants.alreadySelectedOnPicklist.get(i).toString().equals(teamNumber)){
                convertView.setBackgroundColor(Color.parseColor("#FFA4A4"));
            }
        }
        return convertView;
    }

}