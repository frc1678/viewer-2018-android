package com.example.evan.androidviewertemplates.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.applidium.headerlistview.HeaderListView;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.utils.Constants;

/**
 * Created by Katherine on 3/27/2018.
 */

public class LastFourMatchesActivity extends TeamActivity {
    @Override
    public void onCreate() {
        super.onCreate();
        setTitle("Team " + teamNumber + " Details: LFM");

        teamDetailsHeaderListView.setAdapter(new LastFourMatchesSectionAdapter(this, teamNumber));

        reload();
    }
}
