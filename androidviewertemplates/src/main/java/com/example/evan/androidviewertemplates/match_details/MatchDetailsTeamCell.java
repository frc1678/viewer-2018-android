package com.example.evan.androidviewertemplates.match_details;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;


/**
 * Created by colinunger on 2/13/16.
 */
public class MatchDetailsTeamCell extends LinearLayout {
    private Integer teamNumber;
    private boolean isRed;

    public MatchDetailsTeamCell(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.match_details_team_cell, this, true);
    }

    public MatchDetailsTeamCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.match_details_team_cell, this, true);
    }

    public MatchDetailsTeamCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.match_details_team_cell, this, true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MatchDetailsTeamCell(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.match_details_team_cell, this, true);
    }

    private class MatchDetailsTeamClickedListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Log.e("test", "CLICKED!");
            TextView teamNumberTextView = (TextView)v.findViewById(R.id.matchDetailsTeamCellTeamNumberTextView);
            String teamNumberText = teamNumberTextView.getText().toString();
            Integer teamNumber = Integer.parseInt(teamNumberText);
            Intent matchDetailsTeamCellClickedIntent = new Intent(getContext(), TeamDetailsActivity.class);
            matchDetailsTeamCellClickedIntent.putExtra("teamNumber", teamNumber);
            getContext().startActivity(matchDetailsTeamCellClickedIntent);
        }
    }

    public void update(Integer teamNumber, boolean isRed) {
        this.teamNumber = teamNumber;
        this.isRed = isRed;
        updateUI();
    }

    public void updateUI() {
        TextView teamNumberTextView = (TextView)findViewById(R.id.matchDetailsTeamCellTeamNumberTextView);
        teamNumberTextView.setText(teamNumber.toString());
        teamNumberTextView.setTextColor((isRed) ? Color.RED : Color.BLUE);
        teamNumberTextView.setOnClickListener(new MatchDetailsTeamClickedListener());

        ListView listView = (ListView)findViewById(R.id.matchDetailsTeamCellTeamValues);
        listView.setAdapter(new MatchDetailsTeamCellAdapter(getContext(), teamNumber));
    }
}
