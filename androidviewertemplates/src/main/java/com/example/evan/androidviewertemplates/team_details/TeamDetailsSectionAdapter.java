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
public class TeamDetailsSectionAdapter extends MultitypeRankingsSectionAdapter {
    //todo
    private String[][] fieldsToDisplay = {
            {"matches", "VIEWER.matchesUntilNextMatchForTeam"},
            {"calculatedData.disabledPercentage", "calculatedData.incapacitatedPercentage"},
            {"calculatedData.avgGearsPlacedAuto", "calculatedData.avgHighShotsAuto",
                    "calculatedData.avgLowShotsAuto", "calculatedData.baselineReachedPercentage"},
            {"calculatedData.avgGearsPlacedTele", "calculatedData.avgGearLoaderIntakesTele","calculatedData.avgHighShotsTele",
                    "calculatedData.avgLowShotsTele", "calculatedData.avgKeyShotTime"},
            {"calculatedData.liftoffPercentage"},
            {"calculatedData.firstPickAbility", "calculatedData.overallSecondPickAbility", "calculatedData.avgDrivingAbility"},
            {"calculatedData.avgAgility", "calculatedData.avgSpeed", "calculatedData.avgBallControl", "calculatedData.avgGearControl", "calculatedData.avgDefense"},
            {"pitNotes", "pitProgrammingLanguage", "pitAvailableWeight", "pitOrganization", "pitDidUseStandardTankDrive", "pitDidDemonstrateCheesecakePotential", "pitDriveTrain"}
    };

    private String[] sectionTitles = {
            "Matches",
            "Status",
            "Auto",
            "Teleop",
            "Liftoff",
            "High Level",
            "Super Data",
            "Pit Data"
    };

    private String[] shouldDisplayAsPercentage = {
            "calculatedData.disabledPercentage",
            "calculatedData.incapacitatedPercentage",
            "calculatedData.baselineReachedPercentage",
            "calculatedData.liftoffPercentage"};

    private String[] displayAsUnranked = {
            "matches",
            "VIEWER.matchesUntilNextMatchForTeam",
            "pitSelectedImageUrl",
            "pitNotes",
            "pitProgrammingLanguage",
            "pitAvailableWeight",
            "pitOrganization",
            "pitDidUseStandardTankDrive",
            "pitDidDemonstrateCheesecakePotential",
            "pitDriveTrain",
            "superNotes"
    };

    private String[] shouldDisplayAsLongText = {
            "pitNotes",
    };

    private String[] shouldDisplayAsFurtherInformation = {
            "matches"
    };

    private String[] notClickableFields = {
            "VIEWER.matchesUntilNextMatchForTeam",
            "pitDetails",
            "pitSelectedImageUrl",
            "pitNotes",
            "pitProgrammingLanguage",
            "pitAvailableWeight",
            "pitOrganization",
            "pitDidUseStandardTankDrive",
            "pitDidDemonstrateCheesecakePotential",
            "superNotes"
    };

    private String[] createListOnClick = {
            "matches",
            /*"calculatedData.firstPickAbility",
            "calculatedData.overallSecondPickAbility",*/

    };

    private String[] rankInsteadOfGraph = {
            /*"calculatedData.disabledPercentage",
            "calculatedData.incapacitatedPercentage",
            "calculatedData.baselineReachedPercentage",
            "calculatedData.liftoffPercentage",*/
            "calculatedData.firstPickAbility",
            "calculatedData.overallSecondPickAbility",
            "calculatedData.avgDrivingAbility"
    };


    Integer teamNumber;
    private Context context;

    public TeamDetailsSectionAdapter(Context context, Integer teamNumber) {
        super(context);
        this.teamNumber = teamNumber;
        this.context = context;
    }

    @Override
    public String[][] getFieldsToDisplay() {
        return fieldsToDisplay;
    }

    @Override
    public String[] getSectionTitles() {
        return sectionTitles;
    }

    @Override
    public String[] getUnrankedFields() {
        return displayAsUnranked;
    }

    @Override
    public String[] getLongTextFields() {
        return shouldDisplayAsLongText;
    }

    @Override
    public String[] getPercentageFields() {
        return shouldDisplayAsPercentage;
    }

    @Override
    public String[] getFurtherInformationFields() {
        return shouldDisplayAsFurtherInformation;
    }

    @Override
    public String[] getNotClickableFields() {
        return notClickableFields;
    }

    @Override
    public String[] getNonDefaultClickResponseFields() {
        return createListOnClick;
    }

    @Override
    public String[] getRankInsteadOfGraphFields() {
        return rankInsteadOfGraph;
    }


    @Override
    public void handleNonDefaultClick(int section, int row) {
        String key = (String)getRowItem(section, row);
        if (key.equals("matches")) {
            Intent teamMatchesIntent = new Intent(context, MatchesActivity.class);
            teamMatchesIntent.putExtra("teamNumber", teamNumber).putExtra("field", "matches");
            context.startActivity(teamMatchesIntent);
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

    @Override
    public boolean isOtherTypeOfView(int section, int row) {
        return (Arrays.asList(shouldDisplayAsLongText).contains(getRowItem(section, row)) ||
                Arrays.asList(shouldDisplayAsFurtherInformation).contains(getRowItem(section, row)));
    }

    @Override
    public boolean onRowItemLongClick (AdapterView<?> parent, View view, int section, int row, long id) {
        if (!isUnranked(section, row)) {
            String fieldName = (String)getRowItem(section,row);
            Intent intent = new Intent(context, TeamRankingsActivity.class);
            if (fieldName.startsWith("VIEWER.")) {
                Intent rankDataArgs = new Intent();
                fieldName = Utils.getViewerObjectFieldRank(fieldName.replaceFirst("VIEWER.", ""), rankDataArgs, getViewerDataPointsClass());
                Log.e("fieldName", fieldName);
            }
            intent.putExtra("teamNumber", teamNumber).putExtra("field", fieldName)
                    .putExtra("displayValueAsPercentage", Arrays.asList(getPercentageFields()).contains(getRowItem(section,row)));
            context.startActivity(intent);
        }
        return true;
    }


    @Override
    public Intent getRankActivityIntent() {
        Log.e("ranking intent", "called");
        return new Intent(context, TeamRankingsActivity.class);
    }

    @Override
    public Intent getGraphActivityIntent() {
        Log.e("graph intent", "called");
        return new Intent(context, RankingsActivity.class);
    }

    @Override
    public Map<String, String> getKeysToTitles() {
        return SpecificConstants.KEYS_TO_TITLES;
    }

    @Override
    public Class<?> getViewerDataPointsClass() {
        return ViewerDataPoints.class;
    }
}