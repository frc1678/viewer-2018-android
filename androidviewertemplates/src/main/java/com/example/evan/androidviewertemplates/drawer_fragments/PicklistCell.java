package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
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

public class PicklistCell extends RelativeLayout {
    private String dataName;
    private int max;
    private int min;
    Context context;
    String teamNumber;
    Integer teamPicklistPosition;
    String teamNumberValue;

    String string;
    Map<String, Integer> map = new HashMap<>();

    public PicklistCell(Context context, String teamNumber, Integer teamPicklistPosition) {
        super(context);

        this.teamNumber = teamNumber;
        this.teamPicklistPosition = teamPicklistPosition;
        this.context = context;
        teamNumberValue = Constants.picklistMap.get(teamNumber);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picklistcellprototype, this, true);

        TextView position = (TextView) findViewById(R.id.rankPosition);
        TextView number = (TextView) findViewById(R.id.teamNumber);
        TextView name = (TextView) findViewById(R.id.teamName);
        number.setText(teamNumber);
        position.setText(teamPicklistPosition.toString());
        name.setText(generateTeamNameAndSeed(teamNumber));
    }

    public String generateTeamNameAndSeed(String teamNumber) {
        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber);
        String teamRank = (Utils.fieldIsNotNull(team, "calculatedData.actualSeed") ? Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.actualSeed"), 2, "???") : "???");
        String teamName = (Utils.fieldIsNotNull(team, "name") ? Utils.roundDataPoint(Utils.getObjectField(team, "name"), 2, "???") : "???");
        String finalString = teamName + " | Rank: " + teamRank;
        return finalString;
    }
}