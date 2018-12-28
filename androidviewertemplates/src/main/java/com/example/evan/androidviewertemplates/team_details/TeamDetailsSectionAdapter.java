package com.example.evan.androidviewertemplates.team_details;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;


import com.example.evan.androidviewertemplates.graphing.RankingsActivity;
import com.example.evan.androidviewertemplates.team_ranking.TeamRankingsActivity;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertemplates.utils.ViewerDataPoints;
import com.example.evan.androidviewertools.team_details.MultitypeRankingsSectionAdapter;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by citruscircuits on 1/23/16.
 */
public class TeamDetailsSectionAdapter extends TeamSectionAdapter {
    //todo

    static String[] sectionTitles = {
            //May need to change depending on game
            "Matches",
            "Auto",
            "Teleop",
            "Status",
            "High Level",
            "Super Data",
            "Pit Data"
    };

    static String[][] fieldsToDisplay = {
            //Each {}, below correlates to its sectionTitles above
            {"matches", "VIEWER.matchesUntilNextMatchForTeam", "lfm"},
            {},
            {},
            {},
            {},
            {},
            {}
    };

    static String[] shouldDisplayAsPercentage = {
};

    static String[] displayAsUnranked = {
            "matches",
            "VIEWER.matchesUntilNextMatchForTeam",
    };

    static String[] shouldDisplayAsLongText = {
            //These variables should always be displayed as long text. These variables are non year specific variables
            "pitNotes",
            "superNotes",
            "pitSEALsNotes",
            "totalSuperNotes",
    };

    static String[] shouldDisplayAsFurtherInformation = {
            "matches",
            "superNotes",
            "totalSuperNotes",
            "lfm"

    };

    static String[] notClickableFields = {
            "VIEWER.matchesUntilNextMatchForTeam",
    };

    static String[] createListOnClick = {
            "matches",
            "lfm"
    };

    static String[] rankInsteadOfGraph = {
    };


    public TeamDetailsSectionAdapter(Context context, Integer teamNumber) {
        super(context, teamNumber,
                rankInsteadOfGraph,
                createListOnClick,
                notClickableFields,
                shouldDisplayAsLongText,
                shouldDisplayAsFurtherInformation,
                displayAsUnranked,
                shouldDisplayAsPercentage,
                sectionTitles,
                fieldsToDisplay);
    }


    @Override
    public void handleNonDefaultClick(int section, int row) {
        String key = (String) getRowItem(section, row);
        if (key.equals("matches")) {
            Intent teamMatchesIntent = new Intent(context, MatchesActivity.class);
            teamMatchesIntent.putExtra("teamNumber", teamNumber).putExtra("field", "matches");
            context.startActivity(teamMatchesIntent);
        } else if (key.equals("lfm")) {
            Intent lfmIntent = new Intent(context, LastFourMatchesActivity.class);
            lfmIntent.putExtra("teamNumber", teamNumber);
            context.startActivity(lfmIntent);
        }
    }
    @Override
    public String getUpdatedAction() {
        return Constants.TEAMS_UPDATED_ACTION;
    }

    @Override
    public Object getObject() {
        return FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
    }

    @Override
    public List<Object> getObjectList() {
        List<Object> teams = new ArrayList<>();
        teams.addAll(FirebaseLists.teamsList.getValues());
        return teams;
    }
}
