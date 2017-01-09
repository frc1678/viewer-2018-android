package com.example.evan.androidviewertemplates.team_details;

import android.content.Context;
import android.content.Intent;
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
    private String[][] fieldsToDisplay = {{}};

    private String[] sectionTitles = {};

    private String[] shouldDisplayAsPercentage = {};

    private String[] displayAsUnranked = {};

    private String[] shouldDisplayAsLongText = {};

    private String[] shouldDisplayAsFurtherInformation = {};

    private String[] notClickableFields = {};

    private String[] createListOnClick = {};

    private String[] rankInsteadOfGraph = {};


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
            intent.putExtra("teamNumber", teamNumber).putExtra("field", fieldName)
                    .putExtra("displayValueAsPercentage", Arrays.asList(getPercentageFields()).contains(getRowItem(section,row)));
            context.startActivity(intent);
        }
        return true;
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