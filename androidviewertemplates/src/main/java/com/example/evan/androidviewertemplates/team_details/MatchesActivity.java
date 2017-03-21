package com.example.evan.androidviewertemplates.team_details;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.drawer_fragments.TeamScheduleFragment;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.utils.Constants;

/**
 * Created by sam on 1/21/17.
 */
public class MatchesActivity extends ViewerActivity {
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_rankings);
        String field = getIntent().getStringExtra("field");
        setTitle(SpecificConstants.KEYS_TO_TITLES.get(field));

        Fragment fragment = new TeamScheduleFragment();
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putInt("teamNumber", getIntent().getIntExtra("teamNumber", 0));
        fragment.setArguments(argumentsBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, fragment, "").commit();
        setActionBarColor();
    }
    public void setActionBarColor(){
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423"));
        if(actionBar!=null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }



}
