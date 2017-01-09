package com.example.evan.androidviewertemplates;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.evan.androidviewertemplates.drawer_fragments.RecentMatchesFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.ScheduleFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.StarredMatchesFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.super_ability.SuperAbilityFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.TeamScheduleFragment;
import com.example.evan.androidviewertemplates.drawer_fragments.UpcomingMatchesFragment;
import com.example.evan.androidviewertemplates.utils.SpecificNavigationDrawerFragment;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.ViewerActivity;


public class MainActivity extends ViewerActivity
        implements SpecificNavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private SpecificNavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_main);


        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (v.hasVibrator()) {
            Log.i("Can Vibrate", "YES");
        } else {
            Log.i("Can Vibrate", "NO");
        }

        mNavigationDrawerFragment = (SpecificNavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        onSectionAttached(0);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        Log.e("test", "Logcat is up!");
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager(); // For AppCompat use getSupportFragmentManager
        //todo
        switch(position) {
            default:
            case 0:
                fragment = new RecentMatchesFragment();
                break;
            case 1:
                fragment = new UpcomingMatchesFragment();
                break;
            case 2:
                Bundle args = new Bundle();
                args.putInt("teamNumber", SpecificConstants.TEAM_NUMBER);
                fragment = new TeamScheduleFragment();
                fragment.setArguments(args);
                break;
            case 3:
                fragment = new StarredMatchesFragment();
                break;
            case 4:
                fragment = new ScheduleFragment();
                break;
            case 5:
                fragment = new SuperAbilityFragment();
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        onSectionAttached(position);
    }

    public void onSectionAttached(int number) {
        mTitle = SpecificConstants.DRAWER_TITLES[number];

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public Intent getMainActivityIntent() {
        return new Intent(this, this.getClass());
    }
}

