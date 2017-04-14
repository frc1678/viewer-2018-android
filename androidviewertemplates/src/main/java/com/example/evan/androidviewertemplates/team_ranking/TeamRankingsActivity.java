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
        refreshPage();
        setActionBarColor();
    }
    public void setActionBarColor(){
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423"));
        if(actionBar!=null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }
    public void refreshPage(){
        Fragment fragment = getFragment();
        Log.e("teamRankingActivity", "TRUE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.teamRankingsActivityRelativeLayout, fragment, "").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rankings, menu);
        return true;
    }
    public void setAllSortConstantsFalse(){
        Constants.sortByTeamNumber = false;
        Constants.sortByRank = false;
        Constants.sortByFirstPick = false;
        Constants.sortBySecondPick = false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        setAllSortConstantsFalse();
        if (id == R.id.byNumber) {
            Log.e("number", "clicked");
            setColorOfItem(item);
            lastMenuItem = item;
            setAllSortConstantsFalse();
            Constants.sortByTeamNumber = true;
            refreshPage();
            return true;
        }
        if (id == R.id.byRank){
            Log.e("sort by number", String.valueOf(Constants.sortByTeamNumber));
            Log.e("Rank", "clicked");
            setColorOfItem(item);
            lastMenuItem = item;
            setAllSortConstantsFalse();
            Constants.sortByRank = true;
            refreshPage();
            return true;
        }
        if (id == R.id.byFirstPick){
            Log.e("first pick", "cliked");
            setColorOfItem(item);
            lastMenuItem = item;
            setAllSortConstantsFalse();
            Constants.sortByFirstPick = true;
            refreshPage();
            return true;
        }
        if (id == R.id.bySecondPick){
            Log.e("second pick", "cliked");
            setColorOfItem(item);
            lastMenuItem = item;
            setAllSortConstantsFalse();
            Constants.sortBySecondPick = true;
            refreshPage();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void setColorOfItem(MenuItem item){
        if(lastMenuItem!= null) {
            SpannableString s1 = new SpannableString(lastMenuItem.getTitle());
            s1.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s1.length(), 0);
            lastMenuItem.setTitle(s1);
        }
        SpannableString s2 = new SpannableString(item.getTitle());
        s2.setSpan(new ForegroundColorSpan(Color.GRAY), 0, s2.length(), 0);
        item.setTitle(s2);
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
