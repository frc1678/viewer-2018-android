package com.example.evan.androidviewertemplates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.evan.androidviewertemplates.drawer_fragments.FirstPickAbilityFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.FirstPicklistFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.FunctionFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.OurScheduleHighlightFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.OverallSecondPickFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.PredictedSeedingFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.RecentMatchesFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.ScheduleFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.SeedingFrag;
import com.example.evan.androidviewertemplates.drawer_fragments.SeedingFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.StarredMatchesFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.super_ability.SuperAbilityFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.TeamScheduleFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.UpcomingMatchesFragment;
import com.example.evan.androidviewertemplates.firebase_classes.Match;
import com.example.evan.androidviewertemplates.firebase_classes.TeamTemplate;
import com.example.evan.androidviewertemplates.firebase_classes.TeamInMatchData;
import com.example.evan.androidviewertemplates.utils.SpecificNavigationDrawerFragment;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.ViewerActivity;
import com.example.evan.androidviewertools.match_listing.MatchesAdapter;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;


import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ViewerActivity
        implements SpecificNavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private SpecificNavigationDrawerFragment mNavigationDrawerFragment;


    private CharSequence mTitle;
    private Context context;
    private Map<Integer, String> fragmentTagNames;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Integer latestFragmentId;
    private BroadcastReceiver starReceiver;


    @Override
    public void onCreate() {
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        fragmentTagNames = new HashMap<>();
        if (v.hasVibrator()) {
            Log.i("Can Vibrate", "YES");
        } else {
            Log.i("Can Vibrate", "NO");
        }
        //Not game-specific ~ Keep.
        if (ViewerActivity.myPref.contains("highlightedTeams")) {
            if (!MatchesAdapter.getFromSharedHighlightedTeams().isEmpty()) {
                Constants.highlightedMatches = MatchesAdapter.getFromSharedHighlightedTeams();
            }
        }
        if (ViewerActivity.myPref.contains("teamsFromPicklist")) {
            if (!FunctionFragment.getFromSharedTeamsFromPicklist().toString().equals("")) {
                Constants.teamsFromPicklist = FunctionFragment.getFromSharedTeamsFromPicklist();
            } else {
                Constants.teamsFromPicklist = 0;
                }
            }
        initializeDrawer();
        setActionBarColor();
        broadcastListener();

    }

    public void initializeDrawer() {
        mNavigationDrawerFragment = (SpecificNavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        onSectionAttached(0);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.openDrawer(Gravity.LEFT);


    }

    public void setActionBarColor() {
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423")); //65C423
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        // For AppCompat use getSupportFragmentManager
        fragmentManager = getSupportFragmentManager();
        fragment = new ScheduleFragment();
        //todo
        fragmentManager = getSupportFragmentManager();
        prefs = getSharedPreferences("prefFile1", Context.MODE_PRIVATE);
        switch (position) {
            default:
            case 0:
                Constants.highlightTeamSchedule = false;
                fragment = new ScheduleFragment();
                latestFragmentId = position;
                break;
            case 1:
                Constants.highlightTeamSchedule = false;
                fragment = new UpcomingMatchesFragment();
                latestFragmentId = position;
                break;
            case 2:
                Constants.highlightTeamSchedule = false;
                fragment = new RecentMatchesFragment();
                latestFragmentId = position;
                break;
            case 3:
                Constants.highlightTeamSchedule = false;
                fragment = new StarredMatchesFragment();
                latestFragmentId = position;
                break;
            case 4:
                Constants.highlightTeamSchedule = true;
                Bundle args = new Bundle();
                args.putInt("teamNumber", SpecificConstants.TEAM_NUMBER);
                fragment = new OurScheduleHighlightFragment();
                latestFragmentId = position;
                fragment.setArguments(args);
                break;
            case 5:
                Constants.highlightTeamSchedule = false;
                Bundle args2 = new Bundle();
                args2.putInt("teamNumber", SpecificConstants.TEAM_NUMBER);
                fragment = new SeedingFrag();
                latestFragmentId = position;
                fragment.setArguments(args2);
                break;
            case 6:
                Constants.highlightTeamSchedule = false;
                fragment = new FirstPickAbilityFragment();
                latestFragmentId = position;
                break;
            case 7:
                Constants.highlightTeamSchedule = false;
                fragment = new OverallSecondPickFragment();
                latestFragmentId = position;
                break;

            case 8:
                Constants.highlightTeamSchedule = false;
                fragment = new SuperAbilityFragment();
                latestFragmentId = position;
                break;
            case 9:
                Constants.highlightTeamSchedule = false;
                fragment = new FirstPicklistFragment();
                latestFragmentId = position;
                break;
            case 10:
                fragment = new FunctionFragment();
                latestFragmentId = position;
                break;



        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        onSectionAttached(position);
        Log.e("title position", position + "");
        restoreActionBar(position);

    }

    public void onSectionAttached(int number) {
        mTitle = SpecificConstants.DRAWER_TITLES[number];

    }


    public void restoreActionBar(int titleIndex) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        onSectionAttached(titleIndex);
        actionBar.setTitle(mTitle);
        Log.e("title set", mTitle.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            //restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onDestroy() {
        /*editor.putString("key", "asdf").apply();
        Log.e("key", "saved on kill");*/
        super.onDestroy();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.refresh) {
            final Toast toast;
            toast = Toast.makeText(context, "Refreshing...", Toast.LENGTH_SHORT);
            toast.show();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                }
            };
            thread.start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Intent getMainActivityIntent() {
        return new Intent(this, this.getClass());
    }

    public void broadcastListener(){
        starReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Log.e("important matches", StarManager.importantMatches.toString());
                Log.e("recent match", Utils.getLastMatchPlayed() + " ");
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(starReceiver, new IntentFilter(SpecificConstants.NEW_MATCH_PLAYED_ACTION));
    }

}

