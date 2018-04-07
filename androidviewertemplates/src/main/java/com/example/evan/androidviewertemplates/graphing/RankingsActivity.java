package com.example.evan.androidviewertemplates.graphing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.example.evan.androidviewertemplates.MainActivity;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.team_in_match_details.TeamInMatchDetailsActivity;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.ViewerActivity;
import com.example.evan.androidviewertools.graphing.TeamInMatchDataGraphFragment;

public class RankingsActivity extends ViewerActivity {
    private static Activity context;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_rankings);
        Log.e("In rankings Activity", "true");
        String field = getIntent().getStringExtra("field");
        setTitle(SpecificConstants.KEYS_TO_TITLES.get(field));
        context = this;
        setActionBarColor();

        Fragment fragment = new graphingFrag();
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString("field", SpecificConstants.DATA_TO_GRAPH.get(getIntent().getStringExtra("field")));
        argumentsBundle.putInt("team", getIntent().getIntExtra("team", 0));
        argumentsBundle.putBoolean("displayAsPercentage", getIntent().getBooleanExtra("displayAsPercentage", false));
        fragment.setArguments(argumentsBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, fragment, "").commit();
    }

    public void setActionBarColor(){
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423"));
        if(actionBar!=null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
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
