package com.example.evan.androidviewertemplates.drawer_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertemplates.match_details.MatchDetailsActivity;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.match_listing.MatchesAdapter;
import com.example.evan.androidviewertools.match_listing.MatchesFragment;


/**
 * Created by colinunger on 2/8/16.
 */
public class TeamScheduleFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        }

        @Override
        public boolean secondaryFilter(Match value) {
            return (value.redAllianceTeamNumbers.contains(teamNumber) || value.blueAllianceTeamNumbers.contains(teamNumber));
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
