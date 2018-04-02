package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertemplates.team_details.LastFourMatchesActivity;
import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertemplates.team_details.TeamDetailsSectionAdapter;
import com.example.evan.androidviewertemplates.utils.Util;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsAdapter;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsFragment;
import com.example.evan.androidviewertools.utils.Constants;


/**
 * Created by sam on 1/29/17.
 */
public class OverallSecondPickFragment extends TeamRankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setAllSortConstantsFalse();
        setListAdapter(new OverallSecondPickAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by colinunger on 1/28/16.
     */
    //some change
    public static class OverallSecondPickAdapter extends TeamRankingsAdapter {

        public OverallSecondPickAdapter(Context context) {
            super(context, "calculatedData.secondPickAbility", "calculatedData.secondPickAbility", false);
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
