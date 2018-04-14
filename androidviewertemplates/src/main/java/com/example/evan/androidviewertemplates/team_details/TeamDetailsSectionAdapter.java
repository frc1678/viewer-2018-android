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
public class TeamDetailsSectionAdapter extends TeamAdapter {
    //todo
    static String[][] fieldsToDisplay = {
            {"matches", "VIEWER.matchesUntilNextMatchForTeam", "lfm"},
            {"calculatedData.avgCubesPlacedInScaleAuto", "calculatedData.canScoreBothSwitchSidesAuto", "calculatedData.avgAllianceSwitchCubesAuto", "calculatedData.percentSuccessOppositeSwitchSideAuto",
                    "calculatedData.autoRunPercentage", "calculatedData.allianceSwitchSuccessPercentageAuto", "calculatedData.scaleSuccessPercentageAuto", "calculatedData.avgAllianceSwitchCubesAuto"},
            {"calculatedData.avgTotalCubesPlaced","calculatedData.avgCubesSpilledTele", "calculatedData.avgScaleCubesBy100s" ,"calculatedData.avgScaleCubesBy120s", "calculatedData.maxScaleCubes", "calculatedData.avgNumCubesFumbledTele", "calculatedData.avgCubesPlacedInScaleTele", "calculatedData.avgOpponentSwitchCubesTele",
                    "calculatedData.avgAllianceSwitchCubesTele", "calculatedData.avgNumExchangeInputTele", "calculatedData.avgNumGroundPortalIntakeTele",
                    "calculatedData.avgNumHumanPortalIntakeTele", "calculatedData.allianceSwitchSuccessPercentageTele", "calculatedData.scaleSuccessPercentageTele"},
            {"calculatedData.soloClimbPercentage", "calculatedData.assistedClimbPercentage", "calculatedData.activeLiftClimbPercentage", "calculatedData.activeNoClimbLiftClimbPercentage", "calculatedData.activeAssistClimbPercentage", "calculatedData.avgClimbTime"},
            {"calculatedData.incapacitatedPercentage", "calculatedData.disabledPercentage"},
            {"calculatedData.avgDrivingAbility"},
            {"calculatedData.avgSpeed", "calculatedData.avgAgility", "calculatedData.avgDefense",
                    "calculatedData.totalNumGoodDecisions", "calculatedData.totalNumBadDecisions", "totalSuperNotes"},
            {"pitDriveTrain", "pitCanCheesecake", "pitRobotDimensions", "pitClimberType", "pitSEALsNotes",
                    "pitAvailableWeight", "pitProgrammingLanguage", "pitRampTime"}
    };

    static String[] sectionTitles = {
            "Matches",
            "Auto",
            "Teleop",
            "Face The Boss",
            "Status",
            "High Level",
            "Super Data",
            "Pit Data"
    };

    static String[] shouldDisplayAsPercentage = {
            "calculatedData.disabledPercentage",
            "calculatedData.percentSuccessOppositeSwitchSideAuto",
            "calculatedData.autoRunPercentage",
            "calculatedData.allianceSwitchSuccessPercentageAuto",
            "calculatedData.scaleSuccessPercentageAuto",
            "calculatedData.allianceSwitchSuccessPercentageTele",
            "calculatedData.scaleSuccessPercentageTele",
            "calculatedData.assistedClimbPercentage",
            "calculatedData.disabledPercentage",
            "calculatedData.incapacitatedPercentage",
            "calculatedData.baselineReachedPercentage",
            "calculatedData.liftoffPercentage",
            "calculatedData.soloClimbPercentage",
            "calculatedData.assistedClimbPercentage",
            "calculatedData.activeLiftClimbPercentage",
            "calculatedData.activeNoClimbLiftClimbPercentage",
            "calculatedData.activeAssistClimbPercentage"};

    static String[] displayAsUnranked = {
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

    static String[] shouldDisplayAsLongText = {
            "pitNotes",
            "superNotes",
            "pitSEALsNotes",
            "totalSuperNotes",
            "pitRampTime"

    };

    static String[] shouldDisplayAsFurtherInformation = {
            "matches",
            "superNotes",
            "totalSuperNotes",
            "lfm"

    };

    static String[] notClickableFields = {
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
    };

    static String[] createListOnClick = {
            "matches",
            "lfm"
    };

    static String[] rankInsteadOfGraph = {
            "calculatedData.canScoreBothSwitchSidesAuto",
            "calculatedData.percentSuccessOppositeSwitchSideAuto",
            "calculatedData.avgSpeed",
            "calculatedData.avgAgility",
            "calculatedData.avgDefense",
            "calculatedData.allianceSwitchSuccessPercentageAuto",
            "calculatedData.allianceSwitchSuccessPercentageTele",
            "calculatedData.scaleSuccessPercentageAuto",
            "calculatedData.maxScaleCubes",
            "calculatedData.scaleSuccessPercentageTele"
    };


    public TeamDetailsSectionAdapter(Context context, Integer teamNumber) {
        super(context,teamNumber,
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
        String key = (String)getRowItem(section, row);
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
}
