package com.example.evan.androidviewertemplates.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.preference.PreferenceActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applidium.headerlistview.HeaderListView;
import com.example.evan.androidviewertemplates.*;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.*;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;

/**
 * Created by Katherine on 3/27/2018.
 */

public class TeamActivity extends com.example.evan.androidviewertools.ViewerActivity {


    @Override
    public Intent getMainActivityIntent() {
        return new Intent(this, MainActivity.class);
    }

    Integer teamNumber;
    BroadcastReceiver teamUpdatedReceiver;
    BroadcastReceiver starReceiver;
    View teamDetailsHeaderView;
    LinearLayout headerPhotoLinearLayout;
    HeaderListView teamDetailsHeaderListView;


    @Override
    public void onCreate() {
        setContentView(com.example.evan.androidviewertemplates.R.layout.activity_section_listview);
        setActionBarColor();
        teamNumber = getIntent().getIntExtra("teamNumber", 1678);


        teamDetailsHeaderListView = (HeaderListView) findViewById(R.id.teamDetailsHeaderListView);

        teamDetailsHeaderListView.setAdapter(new TeamDetailsSectionAdapter(this, teamNumber));
        teamDetailsHeaderView = getLayoutInflater().inflate(R.layout.team_details_header, null);
        teamDetailsHeaderListView.getListView().addHeaderView(teamDetailsHeaderView, null, false);
        headerPhotoLinearLayout = (LinearLayout)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamPhotoLinearLayout);

        teamUpdatedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reload();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(teamUpdatedReceiver, new IntentFilter(Constants.TEAMS_UPDATED_ACTION));

        starReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reload();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(starReceiver, new IntentFilter(Constants.STARS_MODIFIED_ACTION));
    }

    public void setActionBarColor() {
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423"));
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }
    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
            TextView teamNumberTextView = (TextView)v;
            Integer teamNumber = Integer.parseInt(teamNumberTextView.getText().toString());
            if (StarManager.isStarredTeam(teamNumber)) {
                StarManager.removeStarredTeam(teamNumber);
            } else {
                StarManager.addStarredTeam(teamNumber);
            }
            reload();
            return true;
        }
    }
    public void reload() {
        HeaderListView teamDetailsHeaderListView = (HeaderListView) findViewById(R.id.teamDetailsHeaderListView);
        View teamDetailsHeaderView = teamDetailsHeaderListView.getChildAt(0);
        if (StarManager.isStarredTeam(teamNumber)) {
            teamDetailsHeaderView.setBackgroundColor(Constants.STAR_COLOR);
        } else {
            teamDetailsHeaderView.setBackgroundColor(Color.TRANSPARENT);
        }

        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());

        TextView teamDetailsTeamNumberTextView = (TextView) teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNumberTextView);
        teamDetailsTeamNumberTextView.setText(Utils.getDisplayValue(team, "number"));
        teamDetailsTeamNumberTextView.setOnLongClickListener(new StarLongClickListener());

        TextView teamDetailsTeamNameTextView = (TextView) teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNameTextView);
        teamDetailsTeamNameTextView.setText(Utils.getDisplayValue(team, "name"));

        TextView teamDetailsSeedingTextView = (TextView) teamDetailsHeaderListView.findViewById(R.id.teamDetailsSeeding);
        teamDetailsSeedingTextView.setText((Utils.fieldIsNotNull(team, "calculatedData.actualSeed")) ? Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.actualSeed"), 2, "???") : "???");

        TextView teamDetailsPredictedSeedingTextView = (TextView) teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeeding);
        teamDetailsPredictedSeedingTextView.setText((Utils.fieldIsNotNull(team, "calculatedData.predictedSeed")) ? Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.predictedSeed"), 2, "???") : "???");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(teamUpdatedReceiver);
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(starReceiver);
    }
}