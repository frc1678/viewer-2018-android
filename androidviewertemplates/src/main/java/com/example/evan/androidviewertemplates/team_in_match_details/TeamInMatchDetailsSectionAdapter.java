package com.example.evan.androidviewertemplates.team_in_match_details;

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
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by colinunger on 1/31/16.
 */
public class TeamInMatchDetailsSectionAdapter extends MultitypeRankingsSectionAdapter {
    //todo
    private String[][] fieldsToDisplay = {
            {"teamNumber","matchNumber"},
            {"didMakeAutoRun","calculatedData.canScoreOppositeSwitchAuto","numCubesFumbledAuto","calculatedData.numAllianceSwitchSuccessAuto",
            "calculatedData.numScaleSuccessAuto"},
            {"calculatedData.totalCubesPlaced","calculatedData.numAllianceSwitchSuccessTele","calculatedData.numScaleSuccessTele","calculatedData.numOpponentSwitchSuccessTele",
                    "numExchangeInput","numGroundIntakeTele","numHumanPortalIntakeTele"},
            {"didPark","climb"},
            {"rankSpeed","rankAgility","rankDefense","superNotes","numGoodDecisions","numBadDecisions"},
    };

    private String[] sectionTitles = {
            "Information",
            "Auto",
            "Tele",
            "Face The Boss",
            "Super Data",

    };

    private String[] shouldDisplayAsPercentage = {};

    private String[] displayAsUnranked = {
            "climb"
    };

    private String[] shouldDisplayAsLongText = {
            "superNotes",
            "climb"
    };

    private String[] rankInsteadOfGraph = {};

    private String[] shouldDisplayAsFurtherInformation = {};

    private Integer teamNumber;

    private Integer matchNumber;

    public TeamInMatchDetailsSectionAdapter(Context context, Integer teamNumber, Integer matchNumber) {
        super(context);
        this.teamNumber = teamNumber;
        this.matchNumber = matchNumber;
    }


    @Override
    public boolean isOtherTypeOfView(int section, int row) {
        return (Arrays.asList(shouldDisplayAsLongText).contains(getRowItem(section, row)));
    }

    @Override
    public void onRowItemClick(AdapterView<?> parent, View view, int section, int row, long id) {

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
    public String[] getFurtherInformationFields() { return shouldDisplayAsFurtherInformation; }

    @Override
    public String[] getNotClickableFields() {
        return displayAsUnranked;
    }

    @Override
    public String[] getNonDefaultClickResponseFields() {
        return new String[0];
    }

    @Override
    public String[] getRankInsteadOfGraphFields() {
        return rankInsteadOfGraph;
    }

    @Override
    public void handleNonDefaultClick(int section, int row) {
        String key = (String)getRowItem(section, row);

    }

    @Override
    public String getUpdatedAction() {
        return SpecificConstants.TEAM_IN_MATCH_DATAS_UPDATED_ACTION;
    }

    @Override
    public Object getObject() {
        return FirebaseLists.teamInMatchDataList.getFirebaseObjectByKey(teamNumber.toString() + "Q" + matchNumber.toString());
    }

    @Override
    public List<Object> getObjectList() {
        List<Object> objects = new ArrayList<>();
        Log.e("team NUMBER", String.valueOf(teamNumber));
        objects.addAll(Utils.getTeamInMatchDatasForTeamNumber(teamNumber));
        return objects;
    }

    @Override
    public boolean isRowEnabled(int section, int row) {
        return false;
    }

    @Override
    public Intent getRankActivityIntent() {
        return new Intent(context, TeamRankingsActivity.class);
    }

    @Override
    public Intent getGraphActivityIntent() {
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
