package com.example.evan.androidviewertools.graphing;

import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertools.firebase_classes.TeamInMatchData;
import com.example.evan.androidviewertools.utils.Utils;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public abstract class TeamInMatchDataGraphFragment extends GraphFragment {
    Integer teamNumber;
    String field;
    Boolean displayAsPercentage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teamNumber = getArguments().getInt("team", 0);
        field = getArguments().getString("field");
        displayAsPercentage = getArguments().getBoolean("displayAsPercentage");
    }

    @Override
    public void onResume() {
        super.onResume();

        barChart.setTouchEnabled(true);
        barChart.highlightValues(null);
    }

    @Override
    public List<String> getLabels() {
        List<String> matchNumbersStrings = new ArrayList<>();
        for (TeamInMatchData teamInMatchData : Utils.getTeamInMatchDatasForTeamNumber(teamNumber)) {
            matchNumbersStrings.add(teamInMatchData.matchNumber.toString());
        }
        return matchNumbersStrings;
    }

    @Override
    public List<Float> getValues() {
        List<Float> dataValues = new ArrayList<>();
        for (TeamInMatchData teamInMatchData : Utils.getTeamInMatchDatasForTeamNumber(teamNumber)) {
            Object value =  Utils.getObjectField(teamInMatchData, field);
            if (value instanceof Integer) {
                dataValues.add(((Integer) value).floatValue());
            } else if (value instanceof Boolean) {
                dataValues.add((Boolean)value ? 1f : 0f);
            } else {
                if (displayAsPercentage && value != null) {
                    dataValues.add((Float) value * 100);
                    continue;
                }
                dataValues.add((Float) value);
            }
        }

        return dataValues;
    }

    @Override
    public OnChartValueSelectedListener getOnChartValueSelectedListener() {
        return new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                barChart.setTouchEnabled(false);
                Integer matchNumberClicked = Integer.parseInt(getLabels().get(e.getXIndex()));
                Intent teamInMatchDataIntent = getTeamInMatchDetailsIntent();
                teamInMatchDataIntent.putExtra("team", teamNumber);
                teamInMatchDataIntent.putExtra("match", matchNumberClicked);
                getActivity().startActivity(teamInMatchDataIntent);
            }

            @Override
            public void onNothingSelected() {

            }
        };
    }

    public abstract Intent getTeamInMatchDetailsIntent();
}
