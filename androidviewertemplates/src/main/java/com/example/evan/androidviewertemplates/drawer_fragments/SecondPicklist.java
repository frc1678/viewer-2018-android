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
public class SecondPicklist extends TeamRankingsFragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("seeding", "true");
        Util.setAllSortConstantsFalse();
        setListAdapter(new SecondPickListAdapter(getActivity().getApplicationContext()));


    }



    public static class SecondPickListAdapter extends TeamRankingsAdapter {

        public SecondPickListAdapter(Context context) {
            super(context, "", "", true);
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
