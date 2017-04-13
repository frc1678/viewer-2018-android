package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsAdapter;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsFragment;
import com.example.evan.androidviewertools.utils.Constants;

/**
 * Created by sam on 1/29/17.
 */
public class LastFourMatchesFragment extends TeamRankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SeedingAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by colinunger on 1/28/16.
     */
    public static class SeedingAdapter extends TeamRankingsAdapter {

        public SeedingAdapter(Context context) {
            super(context, "calculatedData.actualSeed", "calculatedData.actualNumRPs", true);
            Constants.lastFourMatches = true;
        }
        @Override
        public Intent getTeamDetailsActivityIntent(){
            Constants.sortByTeamNumber = false;
            return new Intent(context, TeamDetailsActivity.class);
        }
    }
    @Override
    public Intent getTeamDetailsActivityIntent(){
        return new Intent(getActivity(), TeamDetailsActivity.class);
    }
}
