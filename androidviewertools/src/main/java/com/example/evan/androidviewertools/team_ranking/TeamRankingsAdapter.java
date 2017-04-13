package com.example.evan.androidviewertools.team_ranking;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.services.StarManager;

import java.util.ArrayList;
import java.util.List;

public abstract class TeamRankingsAdapter extends RankingsAdapter<Team> {
    public TeamRankingsAdapter(Context context, String rankFieldName, String valueFieldName, boolean isNotReversed) {
        super(context, rankFieldName, valueFieldName, isNotReversed);
    }

    @Override
    public boolean filter(Team value, String scope) {
        if(Constants.lastFourMatches) {
            Constants.sortByTeamNumber = true;
            Constants.lastFourMatches = false;
        }
        String teamName = (String)Utils.getObjectField(value, "name");
        Log.e("team name", teamName);
        Integer teamNumber = (Integer)Utils.getObjectField(value, "number");
        Log.e("team number", teamNumber + "");
        String teamNumberString = Integer.toString(teamNumber);
        return teamNumberString.indexOf(searchString) == 0;
    }

    @Override
    public List<Team> getFirebaseList() {
        return FirebaseLists.teamsList.getValues();
    }

    @Override
    public String getBroadcastAction() {
        return Constants.TEAMS_UPDATED_ACTION;
    }

    @Override
    public String getRankCellText(Team value) {
        Integer teamNumber = ((Integer) Utils.getObjectField(value,"number"));
        return Integer.toString(teamNumber);
    }

    @Override
    public boolean isImportant(String valueTitle) {
        return StarManager.isStarredTeam(Integer.parseInt(valueTitle));
    }

    @Override
    public void makeImportant(String valueTitle) {
        StarManager.addStarredTeam(Integer.parseInt(valueTitle));
    }

    @Override
    public void makeNoLongerImportant(String valueTitle) {
        StarManager.removeStarredTeam(Integer.parseInt(valueTitle));
    }

    @Override
    public void respondToClick(String valueTitle) {
        Integer teamNumberClicked = Integer.parseInt(valueTitle);
        Intent teamDetailsViewIntent = getTeamDetailsActivityIntent();
        teamDetailsViewIntent.putExtra("teamNumber", teamNumberClicked);
        teamDetailsViewIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(teamDetailsViewIntent);
    }

    @Override
    public List<Object> getOtherValuesForRanking() {
        return new ArrayList<Object>(FirebaseLists.teamsList.getValues());
    }

    public abstract Intent getTeamDetailsActivityIntent();
}
