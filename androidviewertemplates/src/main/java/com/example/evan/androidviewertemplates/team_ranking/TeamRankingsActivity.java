package com.example.evan.androidviewertemplates.team_ranking;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evan.androidviewertemplates.MainActivity;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.ViewerActivity;
import com.example.evan.androidviewertools.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;


public class TeamRankingsActivity extends ViewerActivity {

    public MenuItem lastMenuItem;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_team_rankings);
        setActionBarColor();
    }
    public void setActionBarColor(){
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423"));
        if(actionBar!=null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);

    }

    public Fragment getFragment() {
        Fragment fragment = new TeamRankingsActivityFragment();
        Bundle arguments = new Bundle();
        arguments.putString("field", getIntent().getStringExtra("field"));
        Log.e("field",getIntent().getStringExtra("field"));
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
