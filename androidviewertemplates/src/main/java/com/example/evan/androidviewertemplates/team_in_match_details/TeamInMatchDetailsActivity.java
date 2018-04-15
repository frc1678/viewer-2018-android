package com.example.evan.androidviewertemplates.team_in_match_details;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.applidium.headerlistview.HeaderListView;
import com.example.evan.androidviewertemplates.MainActivity;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.ViewerActivity;


/**
 * Created by colinunger on 1/31/16.
 */

public class TeamInMatchDetailsActivity extends ViewerActivity {

    Integer teamNumber;
    Integer matchNumber;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_section_listview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setActionBarColor();
        Log.e("intent", "success");
        teamNumber = getIntent().getIntExtra("team", 1678);
        matchNumber = getIntent().getIntExtra("match", 1);
        setTitle("TeamTemplate " + teamNumber + " In Match " + matchNumber + " Details");

        HeaderListView teamDetailsHeaderListView = (HeaderListView) findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new TeamInMatchDetailsSectionAdapter(this, teamNumber, matchNumber));
    }

    public void setActionBarColor() {
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423"));
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }

    @Override
    public Intent getMainActivityIntent() {
        return new Intent(this, MainActivity.class);
    }
}

