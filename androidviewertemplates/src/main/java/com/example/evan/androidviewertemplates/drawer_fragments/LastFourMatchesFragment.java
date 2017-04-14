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
 * Created by sam on 1/29/17.
 */
public class LastFourMatchesFragment extends TeamRankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.lastFourMatches = true;
        setAllSortConstantsFalse();
        //Log.e("sort by Second Pick1", String.valueOf(Constants.sortBySecondPick));
        setListAdapter(new SeedingAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by colinunger on 1/28/16.
     */
    public static class SeedingAdapter extends TeamRankingsAdapter {

        public SeedingAdapter(Context context) {
            super(context, "calculatedData.actualSeed", "calculatedData.actualNumRPs", true);
        }
        @Override
        public Intent getTeamDetailsActivityIntent(){
            setAllSortConstantsFalse();
            //Log.e("sort by Second Pick2", String.valueOf(Constants.sortBySecondPick));
            return new Intent(context, TeamDetailsActivity.class);

        }
        public void setAllSortConstantsFalse(){
            Constants.sortByTeamNumber = false;
            Constants.sortByRank = false;
            Constants.sortByFirstPick = false;
            Constants.sortBySecondPick = false;
        }
    }
    @Override
    public Intent getTeamDetailsActivityIntent(){
        return new Intent(getActivity(), TeamDetailsActivity.class);
    }
    public void setAllSortConstantsFalse(){
        Constants.sortByTeamNumber = false;
        Constants.sortByRank = false;
        Constants.sortByFirstPick = false;
        Constants.sortBySecondPick = false;
    }
}
