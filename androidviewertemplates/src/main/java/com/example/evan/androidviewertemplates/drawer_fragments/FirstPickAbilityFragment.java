package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertemplates.utils.Util;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsAdapter;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsFragment;
import com.example.evan.androidviewertools.utils.Constants;

/**
 * Created by sam on 1/29/17.
 */
public class FirstPickAbilityFragment extends TeamRankingsFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.isInSeedingFragment = false;
        Util.setAllSortConstantsFalse();
        setListAdapter(new FirstPickAbilityAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class FirstPickAbilityAdapter extends TeamRankingsAdapter {

        public FirstPickAbilityAdapter(Context context) {
            super(context, "calculatedData.firstPickAbility", "calculatedData.firstPickAbility", false);
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
