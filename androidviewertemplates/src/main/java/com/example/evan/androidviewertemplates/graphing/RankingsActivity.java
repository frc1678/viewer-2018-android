package com.example.evan.androidviewertemplates.graphing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.evan.androidviewertemplates.MainActivity;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.team_in_match_details.TeamInMatchDetailsActivity;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.ViewerActivity;
import com.example.evan.androidviewertools.graphing.TeamInMatchDataGraphFragment;

public class RankingsActivity extends ViewerActivity {
    private boolean isShowingGraph;

    private Integer teamNumber;
    private static Activity context;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_rankings);
        String field = getIntent().getStringExtra("field");
        Log.e("graphing field", field);
        setTitle(SpecificConstants.KEYS_TO_TITLES.get(field));
        Log.e("setTitle", SpecificConstants.KEYS_TO_TITLES.get(field));
        context = this;

        Fragment fragment = new graphingFrag();
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString("field", SpecificConstants.DATA_TO_GRAPH.get(getIntent().getStringExtra("field")));
        //Log.e("bundleFieldString", SpecificConstants.DATA_TO_GRAPH.get(getIntent().getStringExtra("field")));
        argumentsBundle.putInt("team", getIntent().getIntExtra("team", 0));
        //Log.e("bundleTeam", Integer.toString(getIntent().getIntExtra("team", 0)));
        argumentsBundle.putBoolean("displayAsPercentage", getIntent().getBooleanExtra("displayAsPercentage", false));
        //Log.e("bundlePercentageBool", String.valueOf(getIntent().getBooleanExtra("displayAsPercentage", false)));
        fragment.setArguments(argumentsBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, fragment, "").commit();
        Log.e("after transaction", "true");

    }


    @Override
    public Intent getMainActivityIntent() {
        return new Intent(this, MainActivity.class);
    }

    public static class graphingFrag extends TeamInMatchDataGraphFragment{
        @Override
        public Intent getTeamInMatchDetailsIntent() {
            return new Intent(context, TeamInMatchDetailsActivity.class);
        }
    }
}
