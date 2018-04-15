package com.example.evan.androidviewertemplates.match_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.firebase_classes.TeamTemplate;
import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by colinunger on 2/4/16.
 */
public class MatchDetailsTeamCellAdapter extends BaseAdapter {
    //todo
    private String[] fields = {
            "calculatedData.autoRunPercentage",
            "calculatedData.climbPercentage",
            "calculatedData.avgCubesSpilledTele",
            "calculatedData.avgNumExchangeInputTele",//
            "calculatedData.avgCubesPlacedInScaleTele",//
            "calculatedData.avgAllianceSwitchCubesAuto",
    };

    private String[] fieldsToDisplayAsPercentages = {"calculatedData.climbPercentage", "calculatedData.autoRunPercentage"};
    private String[] reverseRankFields = {};
    private Integer teamNumber;

    private Context context;

    public MatchDetailsTeamCellAdapter(Context context, Integer teamNumber) {
        this.context = context;
        this.teamNumber = teamNumber;
    }

    @Override
    public int getCount() {
        return fields.length;
    }

    @Override
    public Object getItem(int position) {
        return fields[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.team_in_match_ranking_cell, parent, false);
        }

        TextView rankingTextView = (TextView) rowView.findViewById(R.id.rankingTextView);
        rankingTextView.setText(getRankTextOfRow(position));

        TextView teamNumberTextView = (TextView) rowView.findViewById(R.id.teamNumberTextView);
        teamNumberTextView.setText(SpecificConstants.KEYS_TO_TITLES.get(getItem(position)));
        if (StarManager.starredTeams.contains(SpecificConstants.KEYS_TO_TITLES.get(getItem(position)))) {
            teamNumberTextView.setBackgroundColor(Color.RED);
        }

        TextView valueTextView = (TextView) rowView.findViewById(R.id.valueTextView);
        TeamTemplate team = (TeamTemplate) FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
        if (Arrays.asList(fieldsToDisplayAsPercentages).contains(getItem(position))) {
            valueTextView.setText(Utils.dataPointToPercentage((Float) Utils.getObjectField(team, (String) getItem(position)), 0));
        } else {
            valueTextView.setText(Utils.getDisplayValue(team, (String) getItem(position)));
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent matchDetailsTeamCellClickedIntent = new Intent(context, TeamDetailsActivity.class);
                matchDetailsTeamCellClickedIntent.putExtra("teamNumber", teamNumber);
                context.startActivity(matchDetailsTeamCellClickedIntent);
            }
        });
        return rowView;
    }

    public String getRankTextOfRow(int position) {
        String fieldName = (String) getItem(position);
        TeamTemplate team = (TeamTemplate) FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
        List<Object> teams = new ArrayList<>();
        teams.addAll(FirebaseLists.teamsList.getValues());
        Integer rank = Utils.getRankOfObject(team, teams, fieldName, Arrays.asList(reverseRankFields).contains(fieldName));
        return (rank != null) ? (++rank).toString() : "?";
    }

    ;

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
        notifyDataSetChanged();
    }
}
