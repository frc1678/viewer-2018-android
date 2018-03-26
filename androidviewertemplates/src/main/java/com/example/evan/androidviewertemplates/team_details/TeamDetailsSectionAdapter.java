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
            {"calculatedData.avgCubesPlacedInScaleAuto","calculatedData.canScoreBothSwitchSidesAuto","calculatedData.avgAllianceSwitchCubesAuto","calculatedData.percentSuccessOppositeSwitchSideAuto",
            "calculatedData.autoRunPercentage","calculatedData.allianceSwitchFailPercentageAuto","calculatedData.scaleFailPercentageAuto","calculatedData.avgAllianceSwitchCubesAuto"},
            {"calculatedData.avgCubesSpilledTele","calculatedData.totalCubesPlaced","calculatedData.maxScaleCubes","calculatedData.avgNumCubesFumbledTele","calculatedData.avgCubesPlacedInScaleTele","calculatedData.avgOpponentSwitchCubesTele",
            "calculatedData.avgAllianceSwitchCubesTele","calculatedData.avgNumExchangeInputTele","calculatedData.avgNumGroundPortalIntakeTele",
            "calculatedData.avgNumHumanPortalIntakeTele","calculatedData.allianceSwitchFailPercentageTele","calculatedData.scaleFailPercentageTele"},
            {"calculatedData.soloClimbPercentage", "calculatedData.assistedClimbPercentage", "calculatedData.activeLiftClimbPercentage", "calculatedData.activeNoClimbLiftClimbPercentage", "calculatedData.activeAssistClimbPercentage", "calculatedData.avgClimbTime"},
            {"calculatedData.incapacitatedPercentage","calculatedData.disabledPercentage"},
            {"calculatedData.avgDrivingAbility"},
            {"calculatedData.avgSpeed","calculatedData.avgAgility","calculatedData.avgDefense",
                    "calculatedData.totalNumGoodDecisions","calculatedData.totalNumBadDecisions","totalSuperNotes"},
            {"pitDriveTrain","pitCanCheesecake","pitRobotDimensions","pitClimberType","pitSEALsNotes",
            "pitAvailableWeight","pitProgrammingLanguage","pitRampTime"}


    };

    private String[] sectionTitles = {
            "Matches",
            "Auto",
            "Teleop",
            "Face The Boss",
            "Status",
            "High Level",
            "Super Data",
            "Pit Data"

    };

    private String[] shouldDisplayAsPercentage = {
            "calculatedData.disabledPercentage",
            "calculatedData.incapacitatedPercentage",
            "calculatedData.baselineReachedPercentage",
            "calculatedData.liftoffPercentage",
            "calculatedData.soloClimbPercentage",
            "calculatedData.assistedClimbPercentage",
            "calculatedData.activeLiftClimbPercentage",
            "calculatedData.activeNoClimbLiftClimbPercentage",
            "calculatedData.activeAssistClimbPercentage"};

    private String[] displayAsUnranked = {
            "matches",
            "VIEWER.matchesUntilNextMatchForTeam",
            "pitSelectedImageUrl",
            "pitNotes",
            "pitProgrammingLanguage",
            "pitCanCheesecake",
            "pitAvailableWeight",
            "pitOrganization",
            "pitDriveTrain",
            "superNotes",
            "climb",
            "totalSuperNotes",
            "pitRampTime"
    };

    private String[] shouldDisplayAsLongText = {
            "pitNotes",
            "superNotes",
            "pitSEALsNotes",
            "totalSuperNotes",
            "pitRampTime"

    };

    private String[] shouldDisplayAsFurtherInformation = {
            "matches",
            "superNotes",
            "totalSuperNotes",

    };

    private String[] notClickableFields = {
            "VIEWER.matchesUntilNextMatchForTeam",
            "pitDetails",
            "pitSelectedImageUrl",
            "pitNotes",
            "pitProgrammingLanguage",
            "pitAvailableWeight",
            "pitOrganization",
            "pitDriveTrain",
            "pitCanCheesecake",
            "pitRampTime",
            "calculatedData.maxScaleCubes",
            "calculatedData.totalCubesPlaced"
    };

    private String[] createListOnClick = {
            "matches"
            /*"calculatedData.firstPickAbility",
            "calculatedData.overallSecondPickAbility",*/
    };

    private String[] rankInsteadOfGraph = {

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
            }
            String[] splitName = fieldName.split("\\.");
            Constants.rankFilterName = splitName[1];
            Log.e("FIELD NAME", Constants.rankFilterName);
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