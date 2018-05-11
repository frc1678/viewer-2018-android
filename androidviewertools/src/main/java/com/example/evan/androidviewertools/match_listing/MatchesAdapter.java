package com.example.evan.androidviewertools.match_listing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;
import com.example.evan.androidviewertools.utils.ObjectFieldComparator;
import com.example.evan.androidviewertools.R;
import com.example.evan.androidviewertools.search_view.SearchableFirebaseListAdapter;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.services.StarManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MatchesAdapter extends SearchableFirebaseListAdapter<Match> {
    public Context context;


    public MatchesAdapter(Context context, boolean isNotReversed) {
        super(context, new ObjectFieldComparator("number", isNotReversed));
        this.context = context;

    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public int getCount() {
        return filteredValues.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredValues.get(position);
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
            rowView = inflater.inflate(R.layout.schedule_cell, parent, false);
        }
        try {
            Match match = (Match) getItem(position);

            if (StarManager.isImportantMatch(match.number) && !Constants.highlightTeamSchedule) {
                rowView.setBackgroundColor(Constants.STAR_COLOR);
            } else {
                rowView.setBackgroundColor(Color.TRANSPARENT);
            }

            TextView matchTextView = (TextView) rowView.findViewById(R.id.matchNumber);
            if (selectedScope.equals("Match")) {
                Integer matchNumber = Integer.valueOf((Integer) Utils.getObjectField(match, "number"));
                matchTextView.setText(Utils.highlightTextInString(matchNumber.toString(), searchString));
            } else {
                Integer matchNumber = Integer.valueOf((Integer) Utils.getObjectField(match, "number"));
                matchTextView.setText(matchNumber.toString());
            }
            List<Object> redTeams = Arrays.asList(Utils.getObjectField(match, "redAllianceTeamNumbers"));
            List<Object> blueTeams = Arrays.asList(Utils.getObjectField(match, "blueAllianceTeamNumbers"));
            List<Integer> tempRedAllianceTeams = (List<Integer>) (Object) redTeams.get(0);
            List<Integer> tempBlueAllianceTeams = (List<Integer>) (Object) blueTeams.get(0);
            List<Integer> teamsInMatch = new ArrayList<>();
            for (int i = 0; i < tempRedAllianceTeams.size(); i++) {
                teamsInMatch.add(tempRedAllianceTeams.get(i));
            }
            for (int i = 0; i < tempBlueAllianceTeams.size(); i++) {
                teamsInMatch.add(tempBlueAllianceTeams.get(i));
            }

            int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
            for (int i = 0; i < 6; i++) {
                TextView teamTextView = (TextView) rowView.findViewById(teamTextViewIDs[i]);

                if (selectedScope.equals("Team")) {
                    teamTextView.setText(Utils.highlightTextInString(teamsInMatch.get(i).toString(), searchString));
                } else {
                    teamTextView.setText(teamsInMatch.get(i).toString());
                }

                Integer team = Integer.parseInt(teamTextView.getText().toString());
                //todo Add


                TextView redScoreTextView = (TextView) rowView.findViewById(R.id.redScore);
                TextView blueScoreTextView = (TextView) rowView.findViewById(R.id.blueScore);

                if (match.redScore != null || match.blueScore != null) {
                    redScoreTextView.setText((match.redScore != null) ? match.redScore.toString() : "???");
                    blueScoreTextView.setText((match.blueScore != null) ? match.blueScore.toString() : "???");
                    redScoreTextView.setTextColor(Color.argb(255, 255, 0, 0));
                    blueScoreTextView.setTextColor(Color.argb(255, 0, 0, 255));
                } else {
                    redScoreTextView.setTextColor(Color.argb(75, 255, 0, 0));
                    blueScoreTextView.setTextColor(Color.argb(75, 0, 0, 255));
                    redScoreTextView.setText((Utils.fieldIsNotNull(match, "calculatedData.predictedRedScore")) ? Utils.roundDataPoint(Utils.getObjectField(match, "calculatedData.predictedRedScore"), 2, "???") : "???");
                    blueScoreTextView.setText((Utils.fieldIsNotNull(match, "calculatedData.predictedBlueScore")) ? Utils.roundDataPoint(Utils.getObjectField(match, "calculatedData.predictedBlueScore"), 2, "???") : "???");
                }

                TextView rankingPointDisplayBlue = (TextView) rowView.findViewById(R.id.rankingPointDisplayBlue);
                TextView rankingPointDisplayRed = (TextView) rowView.findViewById(R.id.rankingPointDisplayRed);

                Integer rankingPointsRed = Integer.valueOf(Utils.getObjectField(match, "calculatedData.actualRedRPs").toString());
                Integer rankingPointsBlue = Integer.valueOf(Utils.getObjectField(match, "calculatedData.actualBlueRPs").toString());

                rankingPointsRed = rankingPointsRed - 2;
                rankingPointsBlue = rankingPointsBlue - 2;

                if (rankingPointsRed > 0) {
                    if (rankingPointsRed == 1) {
                        rankingPointDisplayRed.setText("●");
                    } else if (rankingPointsRed == 2) {
                        rankingPointDisplayRed.setText("●●");
                    }
                }
                if (rankingPointsBlue > 0) {
                    if (rankingPointsBlue == 1) {
                        rankingPointDisplayBlue.setText("●");
                    } else if (rankingPointsBlue == 2) {
                        rankingPointDisplayBlue.setText("●●");
                    }
                }
            }
        }catch (NullPointerException NPE){
            Log.e("MATCH", "IS NULL");
        }

        if (Constants.highlightTeamSchedule) {
            rowView.setOnLongClickListener(new HighlightTeamListener());

        } else {
            rowView.setOnLongClickListener(new StarLongClickListener());
        }
        rowView.setOnClickListener(new MatchClickListener());
        return rowView;
    }

    @Override
    public boolean filter(Match value, String scope) {
        List<Integer> teamsInMatch = new ArrayList<>();
        try {
            List<Object> redTeams = Arrays.asList(Utils.getObjectField(value, "redAllianceTeamNumbers"));
            List<Object> blueTeams = Arrays.asList(Utils.getObjectField(value, "blueAllianceTeamNumbers"));
            List<Integer> tempRedAllianceTeams = (List<Integer>) (Object) redTeams.get(0);
            List<Integer> tempBlueAllianceTeams = (List<Integer>) (Object) blueTeams.get(0);
            for (int i = 0; i < tempRedAllianceTeams.size(); i++) {
                teamsInMatch.add(tempRedAllianceTeams.get(i));
            }
            for (int i = 0; i < tempBlueAllianceTeams.size(); i++) {
                teamsInMatch.add(tempBlueAllianceTeams.get(i));
            }
            //Log.e("Please Reach", "Here");
        }catch (NullPointerException NPE){

        }
        boolean found = false;
        try{
            //Log.e("Value", value.toString());
        }catch (NullPointerException NPE){
            Log.e("secondaryFilter", "NULL");
        }
        if (secondaryFilter(value)) {
            if (searchString.length() == 0) {
                found = true;
            } else if (scope.equals("Team")) {
                for (Integer team : teamsInMatch) {
                    if (team.toString().indexOf(searchString) == 0) {
                        found = true;
                    }
                }
            } else if (scope.equals("Match")) {
                found = value.number.toString().indexOf(searchString) == 0;
            }
        }

        return found;
    }

    @Override
    public String getBroadcastAction() {
        return Constants.MATCHES_UPDATED_ACTION;
    }

    @Override
    public List<Match> getFirebaseList() {
        return FirebaseLists.matchesList.getValues();
    }

    public boolean onStarredMatches(Integer team) {
        for(int i = 0; i < StarManager.starredTeams.size(); i++) {
            if(team.equals(StarManager.starredTeams.get(i))) {
                return  true;
            }
        }
        return false;
    }
    public boolean onHighlightedTeams(Integer team) {
        for (int i = 0; i < Constants.highlightedTeams.size(); i++) {
            if (team.equals(Constants.highlightedTeams.get(i))) {
                return true;
            }
        }
        return false;
    }
    public boolean onTeamPicklist(Integer team) {
        for (int i = 0; i < Constants.teamsFromPicklist; i++) {
            if (team.toString().equals(Constants.picklistMap.get(i))) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Integer> updateHighlightedTeams() {
        Log.e("highlightMat..", Constants.highlightedMatches.toString());
        Constants.highlightedTeams.clear();
        for (int p = 0; p < Constants.highlightedMatches.size(); p++) {
            Match match = (Match) FirebaseLists.matchesList.getFirebaseObjectByKey(Constants.highlightedMatches.get(p).toString());
            List<Integer> teamsInMatch = new ArrayList<>();
            List<Object> redTeams = Arrays.asList(Utils.getObjectField(match, "redAllianceTeamNumbers"));
            List<Object> blueTeams = Arrays.asList(Utils.getObjectField(match, "blueAllianceTeamNumbers"));
            List<Integer> tempRedAllianceTeams = (List<Integer>) (Object) redTeams.get(0);
            List<Integer> tempBlueAllianceTeams = (List<Integer>) (Object) blueTeams.get(0);
            Log.e("redTeams",redTeams.toString());
            Log.e("blueTeams",blueTeams.toString());
            for (int i = 0; i < tempRedAllianceTeams.size(); i++) {
                teamsInMatch.add(tempRedAllianceTeams.get(i));
                if (!Constants.highlightedTeams.contains(tempRedAllianceTeams.get(i))) {
                    Constants.highlightedTeams.add(tempRedAllianceTeams.get(i));
                }
                for (int g = 0; g < tempBlueAllianceTeams.size(); g++) {
                    teamsInMatch.add(tempBlueAllianceTeams.get(g));
                    if (!Constants.highlightedTeams.contains(tempBlueAllianceTeams.get(g))) {
                        Constants.highlightedTeams.add(tempBlueAllianceTeams.get(g));
                    }
                }
            }
        }


        return Constants.highlightedTeams;
    }
//todo Fix SharedPreferences: !!!
/*    SharedPreferences prefs = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
    SharedPreferences.Editor prefsEditor = prefs.edit();
    public void saveToSharedPreferences(ArrayList<Integer> data) {
        Gson gson = new Gson();
        List<String> textList = new ArrayList<String>();
        for (int i = 0; i < data.size(); i++) {
            textList.add(data.get(i).toString());
            Log.e("textList",textList.toString());
        }
        String jsonText = gson.toJson(textList);
        prefsEditor.putString("key", jsonText);
        prefsEditor.apply();
    }
    public void getFromSharedPreferences() {
        Gson gson = new Gson();
        String jsonText = prefs.getString("key", null);
        String[] text = gson.fromJson(jsonText, String[].class);  //EDIT: gso to gson
        Log.e("text",text.toString());
    }*/


    public abstract boolean secondaryFilter (Match value);

    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {

            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            //vibrator.vibrate(75);
            TextView matchNumberTextView = (TextView)v.findViewById(R.id.matchNumber);
            if (StarManager.isImportantMatch(Integer.parseInt(matchNumberTextView.getText().toString()))) {
                StarManager.removeImportantMatch(Integer.parseInt(matchNumberTextView.getText().toString()));
            } else {
                StarManager.addImportantMatch(Integer.parseInt(matchNumberTextView.getText().toString()));
            }
            notifyDataSetChanged();
            return true;
        }

    }
    private class HighlightTeamListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            TextView matchNumberTextView = (TextView)v.findViewById(R.id.matchNumber);
            Match match = (Match) FirebaseLists.matchesList.getFirebaseObjectByKey(matchNumberTextView.getText().toString());
            List<Integer> teamsInMatch = new ArrayList<>();
            List<Object> redTeams = Arrays.asList(Utils.getObjectField(match, "redAllianceTeamNumbers"));
            List<Object> blueTeams = Arrays.asList(Utils.getObjectField(match, "blueAllianceTeamNumbers"));
            List<Integer> tempRedAllianceTeams = (List<Integer>) (Object) redTeams.get(0);
            List<Integer> tempBlueAllianceTeams = (List<Integer>) (Object) blueTeams.get(0);

            for (int i = 0; i < tempRedAllianceTeams.size(); i++) {
                teamsInMatch.add(tempRedAllianceTeams.get(i));
            }
            for (int i = 0; i < tempBlueAllianceTeams.size(); i++) {
                teamsInMatch.add(tempBlueAllianceTeams.get(i));
            }

            if (!Constants.highlightedMatches.contains(Integer.parseInt(matchNumberTextView.getText().toString()))) {
                Constants.highlightedMatches.add(Integer.parseInt(matchNumberTextView.getText().toString()));
                updateHighlightedTeams();
            } else {
                Constants.highlightedMatches.remove(Constants.highlightedMatches.indexOf(Integer.parseInt(matchNumberTextView.getText().toString())));
                updateHighlightedTeams();
            }
            Log.e("highlightedTeamsFin",Constants.highlightedTeams.toString());
            Log.e("highlightedMatchesFin",Constants.highlightedMatches.toString());

            notifyDataSetChanged();
            return true;
        }

    }

    private class MatchClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            TextView matchNumberTextView = (TextView)v.findViewById(R.id.matchNumber);
            Integer matchNumberClicked = Integer.parseInt(matchNumberTextView.getText().toString());

            Intent matchDetailsActivityIntent = getMatchDetailsActivityIntent();
            matchDetailsActivityIntent.putExtra("matchNumber", matchNumberClicked);
            matchDetailsActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(matchDetailsActivityIntent);
        }
    }

    public abstract boolean shouldHighlightTextViewWithText(String text);

    public abstract Intent getMatchDetailsActivityIntent();


}
