package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertemplates.utils.Util;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsAdapter;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsFragment;
import com.example.evan.androidviewertools.utils.Constants;

/**
 * Created by Teo on 2/1/18.
 */
public class FirstPicklist extends TeamRankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setAllSortConstantsFalse();
        setListAdapter(new FirstPickListAdapter(getActivity().getApplicationContext()));
    }

    public static class FirstPickListAdapter extends TeamRankingsAdapter {

        public FirstPickListAdapter(Context context) {

            super(context, "picklistPosition", "picklistPosition", true);
            Log.e("RankByNumber", String.valueOf(Constants.sortByTeamNumber));
            Constants.lastFourMatches = false;
            Constants.firstPickList = true;
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
