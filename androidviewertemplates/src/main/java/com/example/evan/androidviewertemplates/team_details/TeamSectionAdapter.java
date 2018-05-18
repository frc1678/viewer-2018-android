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


//This is the parent class for the two adapters: TeamDetailsSectionAdapter and LasFourMatchesSectionAdapter
public abstract class TeamSectionAdapter extends MultitypeRankingsSectionAdapter {

    private final String[] rankInsteadOfGraph;
    private final String[] createListOnClick;
    private final String[] notClickableFields;
    private final String[] shouldDisplayAsLongText;
    private final String[] shouldDisplayAsFurtherInformation;
    private final String[] displayAsUnranked;
    private final String[] shouldDisplayAsPercentage;
    private final String[] sectionTitles;
    private final String[][] fieldsToDisplay;

    Integer teamNumber;
    protected Context context;

    public TeamSectionAdapter(Context context, Integer teamNumber,
                       String[] rankInsteadOfGraph,
                       String[] createListOnClick,
                       String[] notClickableFields,
                       String[] shouldDisplayAsLongText,
                       String[] shouldDisplayAsFurtherInformation,
                       String[] displayAsUnranked,
                       String[] shouldDisplayAsPercentage,
                       String[] sectionTitles,
                       String[][] fieldsToDisplay) {
        super(context);
        this.teamNumber = teamNumber;
        this.context = context;
        this.rankInsteadOfGraph = rankInsteadOfGraph;
        this.createListOnClick = createListOnClick;
        this.notClickableFields = notClickableFields;
        this.shouldDisplayAsLongText = shouldDisplayAsLongText;
        this.shouldDisplayAsFurtherInformation = shouldDisplayAsFurtherInformation;
        this.displayAsUnranked = displayAsUnranked;
        this.shouldDisplayAsPercentage = shouldDisplayAsPercentage;
        this.sectionTitles = sectionTitles;
        this.fieldsToDisplay = fieldsToDisplay;
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
    public boolean isOtherTypeOfView(int section, int row) {
        return (Arrays.asList(shouldDisplayAsLongText).contains(getRowItem(section, row)) ||
                Arrays.asList(shouldDisplayAsFurtherInformation).contains(getRowItem(section, row)));
    }

    @Override
    public boolean onRowItemLongClick(AdapterView<?> parent, View view, int section, int row, long id) {
        if (!isUnranked(section, row)) {
            String fieldName = (String) getRowItem(section, row);
            Intent intent = new Intent(context, TeamRankingsActivity.class);
            if (fieldName.startsWith("VIEWER.")) {
                Intent rankDataArgs = new Intent();
                fieldName = Utils.getViewerObjectFieldRank(fieldName.replaceFirst("VIEWER.", ""), rankDataArgs, getViewerDataPointsClass());
            }
            String[] splitName = fieldName.split("\\.");
            Constants.rankFilterName = splitName[1];
            Log.e("FIELD NAME", Constants.rankFilterName);
            intent.putExtra("teamNumber", teamNumber).putExtra("field", fieldName)
                    .putExtra("displayValueAsPercentage", Arrays.asList(getPercentageFields()).contains(getRowItem(section, row)));
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


