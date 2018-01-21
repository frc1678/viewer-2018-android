package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsAdapter;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsFragment;
import com.example.evan.androidviewertools.utils.Constants;

/**
 * Created by Teo on 1/21/2018.
 */

public class LastFourMatchesFragment extends TeamRankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("seeding", "true");
        Constants.lastFourMatches = false;
        Constants.sortByTeamNumber = false;
        setListAdapter(new SeedingAdapter(getActivity().getApplicationContext()));
    }

    public static class SeedingAdapter extends TeamRankingsAdapter {

        public SeedingAdapter(Context context) {
            super(context, "calculatedData.actualSeed", "calculatedData.actualNumRPs", true);
            Log.e("RankByNumber", String.valueOf(Constants.sortByTeamNumber));
            Constants.lastFourMatches = false;
        }
        @Override
        public Intent getTeamDetailsActivityIntent(){
            return new Intent(context, TeamDetailsActivity.class);
        }
    }
    @Override
    public Intent getTeamDetailsActivityIntent(){
        return new Intent(getActivity(), TeamDetailsActivity.class);
    }
}
