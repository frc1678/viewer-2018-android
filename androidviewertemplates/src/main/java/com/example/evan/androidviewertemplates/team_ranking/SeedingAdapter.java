package com.example.evan.androidviewertemplates.team_ranking;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.Constants;
import com.firebase.client.collection.LLRBNode;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teo on 3/10/2018.
 */

public class SeedingAdapter extends BaseAdapter {
    Context context;
    ArrayList<Integer> seedingTeamsList;

    public SeedingAdapter(Context context,ArrayList<Integer> seedingTeamList) {

        super();
        this.context = context;
        this.seedingTeamsList = seedingTeamList;


    }

    @Override
    public int getCount() {
        return seedingTeamsList.size();
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
        Integer teamNumber = seedingTeamsList.get(position);
        SeedingCell cell = new SeedingCell(context, teamNumber);
        convertView = cell;

        if (StarManager.starredTeams.contains(teamNumber)) {
            cell.setBackgroundColor(Constants.STAR_COLOR);
        }
        return convertView;
    }

}