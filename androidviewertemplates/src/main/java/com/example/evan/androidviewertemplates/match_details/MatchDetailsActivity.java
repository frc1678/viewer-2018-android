package com.example.evan.androidviewertemplates.match_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.evan.androidviewertemplates.MainActivity;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.firebase_classes.Match;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.ViewerActivity;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;

import java.util.ArrayList;
import java.util.List;

public class MatchDetailsActivity extends ViewerActivity {
    private Integer matchNumber;
    private BroadcastReceiver matchesUpdatedReceiver;
    private BroadcastReceiver starReceiver;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_match_details);

        matchNumber = getIntent().getIntExtra("matchNumber", 0);
        setTitle("Match " + matchNumber + " Details");

        matchesUpdatedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateUI();
            }
        };

        starReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateUI();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(starReceiver, new IntentFilter(SpecificConstants.STARS_MODIFIED_ACTION));

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(matchesUpdatedReceiver, new IntentFilter(SpecificConstants.MATCHES_UPDATED_ACTION));
        TextView matchNumberTextView = (TextView)findViewById(R.id.matchDetailsMatchTitleTextView);
        matchNumberTextView.setOnLongClickListener(new StarLongClickListener());
        updateUI();
        setActionBarColor();
    }
    public void setActionBarColor(){
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423"));
        if(actionBar!=null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }

    private void updateUI() {
        Match match = (Match)FirebaseLists.matchesList.getFirebaseObjectByKey(matchNumber.toString());
        int[] teamCellIDs = {R.id.redTeamCell1, R.id.redTeamCell2, R.id.redTeamCell3, R.id.blueTeamCell1, R.id.blueTeamCell2, R.id.blueTeamCell3};
        List<Integer> allTeamNumbers = new ArrayList<>();
        Log.e("matchDetailsRedTeams", match.redAllianceTeamNumbers.toString());
        allTeamNumbers.addAll(match.redAllianceTeamNumbers);
        allTeamNumbers.addAll(match.blueAllianceTeamNumbers);
        for (int i = 0; i < teamCellIDs.length; i++) {
            MatchDetailsTeamCell matchDetailsTeamCell = (MatchDetailsTeamCell)findViewById(teamCellIDs[i]);
            matchDetailsTeamCell.update(allTeamNumbers.get(i), (i < 3));
        }

        if (StarManager.isImportantMatch(matchNumber)) {
            getWindow().getDecorView().setBackgroundColor(SpecificConstants.STAR_COLOR);
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }

        TextView matchDetailsMatchTitleTextView = (TextView)findViewById(R.id.matchDetailsMatchTitleTextView);
        matchDetailsMatchTitleTextView.setText("Q" + match.number.toString());

        TextView redAllianceScoreTextView = (TextView)findViewById(R.id.matchDetailsRedAllianceScore);
        TextView redAlliancePredictedScoreTextView = (TextView)findViewById(R.id.matchDetailsRedAlliancePredictedScore);
        TextView redAllianceWinChanceTextView = (TextView)findViewById(R.id.matchDetailsRedAllianceWinChance);

        redAllianceScoreTextView.setText(Utils.getMatchDisplayValue(match, "redScore"));
        redAlliancePredictedScoreTextView.setText(Utils.getMatchDisplayValue(match, "calculatedData.predictedRedScore"));
        redAllianceWinChanceTextView.setText(Utils.dataPointToPercentage((Float)Utils.getObjectField(match, "calculatedData.redWinChance"), 0));

        TextView blueAllianceScoreTextView = (TextView)findViewById(R.id.matchDetailsBlueAllianceScore);
        TextView blueAlliancePredictedScoreTextView = (TextView)findViewById(R.id.matchDetailsBlueAlliancePredictedScore);
        TextView blueAllianceWinChanceTextView = (TextView)findViewById(R.id.matchDetailsBlueAllianceWinChance);

        blueAllianceScoreTextView.setText(Utils.getMatchDisplayValue(match, "blueScore"));
        blueAlliancePredictedScoreTextView.setText(Utils.getMatchDisplayValue(match, "calculatedData.predictedBlueScore"));
        blueAllianceWinChanceTextView.setText(Utils.dataPointToPercentage((Float) Utils.getObjectField(match, "calculatedData.blueWinChance"), 0));
        //todo
    }


    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
            TextView matchNumberTextView = (TextView)v;
            String matchNumberString = matchNumberTextView.getText().toString().substring(1);
            Integer matchNumberClicked = Integer.parseInt(matchNumberString);
            if (StarManager.isImportantMatch(matchNumberClicked)) {
                StarManager.removeImportantMatch(matchNumberClicked);
            } else {
                StarManager.addImportantMatch(matchNumberClicked);
            }

            updateUI();
            return true;
        }
    }

    @Override
    public Intent getMainActivityIntent() {
        return new Intent(this, MainActivity.class);
    }
}
