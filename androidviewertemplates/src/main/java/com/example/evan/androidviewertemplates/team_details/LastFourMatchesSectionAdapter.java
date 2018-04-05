package com.example.evan.androidviewertemplates.team_details;

import android.content.Context;
import android.content.Intent;

import org.jcodec.common.ArrayUtil;

/**
 * Created by Katherine on 3/27/2018.
 */

public class LastFourMatchesSectionAdapter extends TeamAdapter {

    public LastFourMatchesSectionAdapter(Context context, Integer teamNumber) {
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

    static  String[] rankInsteadOfGraph = {};

    static  String[] createListOnClick = {
            "fullcomp"
    };

    static  String[] notClickableFields = {
    };

    static  String[] shouldDisplayAsFurtherInformation = {
            "fullcomp"
    };

    static  String[] shouldDisplayAsLongText = {
    };

    static  String[] displayAsUnranked = {
    };

    static  String[] shouldDisplayAsPercentage = {
            "lfmAutoRunPercentage",
            "lfmSoloClimbPercentage"
    };

    static  String[] sectionTitles = {
            "Auto",
            "Teleop",
            "Face The Boss",
            "Status",
            "High Level"
    };

    static String[][] fieldsToDisplay = {

            {"calculatedData.lfmAvgClimbTime","calculatedData.lfmAutoRunPercentage",},
            {"calculatedData.lfmTotalCubesPlaced","calculatedData.lfmMaxExchangeCubes" ,"calculatedData.lfmMaxScaleCubes","calculatedData.lfmAvgNumCubesFumbledTele","calculatedData.lfmAvgCubesPlacedInScaleTele","calculatedData.lfmAvgAllianceSwitchCubesTele", "calculatedData.lfmAvgNumExchangeInputTele"},
            {"calculatedData.lfmSoloClimbPercentage","calculatedData.lfmAssistedClimbPercentage", "calculatedData.lfmActiveLiftClimbPercentage", "calculatedData.lfmActiveNoClimbLiftClimbPercentage", "calculatedData.lfmActiveAssistClimbPercentage","calculatedData.lfmAvgClimbTime"},
            {"calculatedData.lfmIncapacitatedPercentage", "calculatedData.lfmDisabledPercentage"},
            {"calculatedData.lfmAvgDrivingAbility"},
    };

    static {
        for (String[] a : fieldsToDisplay) {
            rankInsteadOfGraph = (String[])ArrayUtil.addAll(a, rankInsteadOfGraph);
        }
    }

    @Override
    public void handleNonDefaultClick(int section, int row) {
        String key = (String)getRowItem(section, row);
        if (key.equals("fullcomp")) {
            Intent fullcompIntent = new Intent(context, TeamDetailsActivity.class);
            fullcompIntent.putExtra("teamNumber", teamNumber);
            context.startActivity(fullcompIntent);
        }
    }
}
