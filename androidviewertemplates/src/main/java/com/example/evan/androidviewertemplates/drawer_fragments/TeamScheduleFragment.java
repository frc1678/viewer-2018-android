package com.example.evan.androidviewertemplates.drawer_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertemplates.match_details.MatchDetailsActivity;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.match_listing.MatchesAdapter;
import com.example.evan.androidviewertools.match_listing.MatchesFragment;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;

import java.util.Arrays;
import java.util.List;


/**
 * Created by colinunger on 2/8/16.
 */
public class TeamScheduleFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Constants.isInSeedingFragment = false;
        Integer teamNumber = getArguments().getInt("teamNumber");

        setListAdapter(new CitrusScheduleAdapter(getActivity().getApplicationContext(), teamNumber));
    }

    /**
     * Created by citruscircuits on 1/17/16.
     */
    public static class CitrusScheduleAdapter extends MatchesAdapter {
        Integer teamNumber;


        public CitrusScheduleAdapter(Context context, Integer teamNumber) {
            super(context, true);
            this.teamNumber = teamNumber;
            Constants.highlightTeamSchedule = true;
        }

        @Override
        public boolean secondaryFilter(Match value) {
            List<Object> redTeams = Arrays.asList(Utils.getObjectField(value, "redAllianceTeamNumbers"));
            List<Object> blueTeams = Arrays.asList(Utils.getObjectField(value, "blueAllianceTeamNumbers"));
            List<Integer> redAllianceTeams = (List<Integer>) (Object) redTeams.get(0);
            List<Integer> blueAllianceTeams = (List<Integer>) (Object) blueTeams.get(0);
            return (redAllianceTeams.contains(teamNumber) || blueAllianceTeams.contains(teamNumber));
        }

        @Override
        public boolean shouldHighlightTextViewWithText(String text) {
            return text.equals(teamNumber.toString());
        }

        @Override
        public Intent getMatchDetailsActivityIntent() {
            return new Intent(context, MatchDetailsActivity.class);
        }
    }

    private Activity context = getActivity();

    @Override
    public Intent getMatchDetailsActivityIntent() {
        return new Intent(context, MatchDetailsActivity.class);
    }
}
