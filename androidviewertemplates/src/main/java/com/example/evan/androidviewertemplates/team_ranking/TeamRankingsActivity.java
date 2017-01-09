package com.example.evan.androidviewertemplates.team_ranking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.example.evan.androidviewertemplates.MainActivity;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.ViewerActivity;


public class TeamRankingsActivity extends ViewerActivity {

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_team_rankings);
        Fragment fragment = getFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.teamRankingsActivityRelativeLayout, fragment, "").commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rankings, menu);
        return true;
    }


    public Fragment getFragment() {
        Fragment fragment = new TeamRankingsActivityFragment();
        Bundle arguments = new Bundle();
        arguments.putString("field", getIntent().getStringExtra("field"));
        arguments.putInt("team", getIntent().getIntExtra("team", 0));
        arguments.putBoolean("displayValueAsPercentage", getIntent().getBooleanExtra("displayValueAsPercentage", false));
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public Intent getMainActivityIntent() {
        return new Intent(this, MainActivity.class);
    }
}
