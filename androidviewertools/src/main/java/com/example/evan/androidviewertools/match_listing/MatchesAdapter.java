package com.example.evan.androidviewertools.match_listing;

import android.content.Context;
import android.content.Intent;
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

        Match match = (Match)getItem(position);

        if (StarManager.isImportantMatch(match.number)) {
            rowView.setBackgroundColor(Constants.STAR_COLOR);
        } else {
            rowView.setBackgroundColor(Color.TRANSPARENT);
        }

        TextView matchTextView = (TextView)rowView.findViewById(R.id.matchNumber);
        if (selectedScope.equals("Match")) {
            Integer matchNumber = Integer.valueOf((Integer) Utils.getObjectField(match,"number"));
            matchTextView.setText(Utils.highlightTextInString(matchNumber.toString(), searchString));
        } else {
            Integer matchNumber = Integer.valueOf((Integer) Utils.getObjectField(match,"number"));
            matchTextView.setText(matchNumber.toString());
        }
        List<Object> redTeams = Arrays.asList(Utils.getObjectField(match,"redAllianceTeamNumbers"));
        List<Object> blueTeams = Arrays.asList(Utils.getObjectField(match, "blueAllianceTeamNumbers"));
        List<Integer> tempRedAllianceTeams = (List<Integer>)(Object)redTeams.get(0);
        List<Integer> tempBlueAllianceTeams = (List<Integer>)(Object)blueTeams.get(0);
        List<Integer> teamsInMatch = new ArrayList<>();
        for(int i = 0; i < tempRedAllianceTeams.size(); i++){
            teamsInMatch.add(tempRedAllianceTeams.get(i));
        }
        for(int i = 0; i < tempBlueAllianceTeams.size(); i++){
            teamsInMatch.add(tempBlueAllianceTeams.get(i));
        }

        int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
        for (int i = 0; i < 6; i++) {
            TextView teamTextView = (TextView)rowView.findViewById(teamTextViewIDs[i]);
            if (selectedScope.equals("Team")) {
                teamTextView.setText(Utils.highlightTextInString(teamsInMatch.get(i).toString(), searchString));
            } else {
                teamTextView.setText(teamsInMatch.get(i).toString());
            }
            boolean shouldBold = shouldHighlightTextViewWithText(teamTextView.getText().toString());
            if (shouldBold) {
                teamTextView.setBackgroundColor(Color.YELLOW);
            } else {
                teamTextView.setBackgroundColor(Color.TRANSPARENT);
            }
        }

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

        rowView.setOnLongClickListener(new StarLongClickListener());
        rowView.setOnClickListener(new MatchClickListener());
        return rowView;
    }

    @Override
    public boolean filter(Match value, String scope) {
        List<Integer> teamsInMatch = new ArrayList<>();
        List<Object> redTeams = Arrays.asList(Utils.getObjectField(value,"redAllianceTeamNumbers"));
        List<Object> blueTeams = Arrays.asList(Utils.getObjectField(value, "blueAllianceTeamNumbers"));
        List<Integer> tempRedAllianceTeams = (List<Integer>)(Object)redTeams.get(0);
        List<Integer> tempBlueAllianceTeams = (List<Integer>)(Object)blueTeams.get(0);
        for(int i = 0; i < tempRedAllianceTeams.size(); i++){
            teamsInMatch.add(tempRedAllianceTeams.get(i));
        }
        for(int i = 0; i < tempBlueAllianceTeams.size(); i++){
            teamsInMatch.add(tempBlueAllianceTeams.get(i));
        }
        //Log.e("Please Reach", "Here");

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

    public abstract boolean secondaryFilter (Match value);

    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
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
