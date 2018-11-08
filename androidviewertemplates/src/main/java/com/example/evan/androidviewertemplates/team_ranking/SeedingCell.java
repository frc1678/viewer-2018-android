package com.example.evan.androidviewertemplates.team_ranking;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teo on 3/14/2018.
 */

public class SeedingCell extends RelativeLayout {

    Context context;
    Integer teamNumber;


    public SeedingCell(Context context, Integer teamNumber) {
        super(context);

        this.teamNumber = teamNumber;
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.seeding_cell, this, true);

        TextView team = (TextView) findViewById(R.id.team);
        team.setTypeface(null, Typeface.BOLD);
        TextView predictedSeedTV = (TextView) findViewById(R.id.predicted_seed);
        TextView predictedRankingPointsTV = (TextView) findViewById(R.id.predicted_ranking_points);
        TextView currentRankingPointsTV = (TextView) findViewById(R.id.current_ranking_points);
        TextView rankingTextView = (TextView) findViewById(R.id.rankingTextView);

        rankingTextView.setTextSize(20);
        team.setTextSize(20);
        predictedSeedTV.setTextSize(20);
        predictedRankingPointsTV.setTextSize(20);
        currentRankingPointsTV.setTextSize(20);

        if (teamNumber!=null){
            Team teamTeam = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
            String predictedSeed = (Utils.fieldIsNotNull(teamTeam, "calculatedData.predictedSeed") ? Utils.roundDataPoint(Utils.getObjectField(teamTeam, "calculatedData.predictedSeed"), 2, "???") : "???");
            String predictedRankingPoints = (Utils.fieldIsNotNull(teamTeam, "calculatedData.predictedTotalNumRPs") ? Utils.roundDataPoint(Utils.getObjectField(teamTeam, "calculatedData.predictedTotalNumRPs"), 2, "???") : "???");
            String currentRP = (Utils.fieldIsNotNull(teamTeam, "calculatedData.actualNumRPs") ? Utils.roundDataPoint(Utils.getObjectField(teamTeam, "calculatedData.actualNumRPs"), 2, "???") : "???");
            String currentSeed = (Utils.fieldIsNotNull(teamTeam, "calculatedData.actualSeed") ? Utils.roundDataPoint(Utils.getObjectField(teamTeam, "calculatedData.actualSeed"), 2, "???") : "???");


            rankingTextView.setText(currentSeed);
            team.setText(teamNumber.toString());
            predictedSeedTV.setText(predictedSeed);
            predictedRankingPointsTV.setText(predictedRankingPoints);
            currentRankingPointsTV.setText(currentRP);
        } else {
            rankingTextView.setText("???");
            team.setText("???");
            predictedSeedTV.setText("???");
            predictedRankingPointsTV.setText("???");
            currentRankingPointsTV.setText("???");
        }


    }

    public String generateTeamNameAndSeed(String teamNumber) {
        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber);
        String teamRank = (Utils.fieldIsNotNull(team, "calculatedData.actualSeed") ? Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.actualSeed"), 2, "???") : "???");
        String teamName = (Utils.fieldIsNotNull(team, "name") ? Utils.roundDataPoint(Utils.getObjectField(team, "name"), 2, "???") : "???");
        String finalString = teamName + " | Rank: " + teamRank;
        return finalString;
    }
}