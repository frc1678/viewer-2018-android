package com.example.evan.androidviewertemplates.team_ranking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsAdapter;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsFragment;
import com.example.evan.androidviewertools.utils.Constants;


public class TeamRankingsActivityFragment extends TeamRankingsFragment {
    String field;
    Activity context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        field = getArguments().getString("field");
        context = getActivity();
        Boolean displayValueAsPercentage = getArguments().getBoolean("displayValueAsPercentage");


        getActivity().setTitle(SpecificConstants.KEYS_TO_TITLES.get(field));
        setListAdapter(new TeamRankingsActivityAdapter(getActivity().getApplicationContext(), field, displayValueAsPercentage));
    }
    public Intent getTeamDetailsActivityIntent() {
        return new Intent(getActivity(), TeamDetailsActivity.class);
    }


    public static class TeamRankingsActivityAdapter extends TeamRankingsAdapter {
        private Boolean displayValueAsPercentage;
        public TeamRankingsActivityAdapter(Context context, String field, Boolean displayValueAsPercentage) {
            super(context, field, field, false);
            this.displayValueAsPercentage = displayValueAsPercentage;
        }
        @Override
        public Boolean displayValueAsPercentage() {
            return displayValueAsPercentage;
        }
        public Intent getTeamDetailsActivityIntent() {
            return new Intent(context, TeamDetailsActivity.class);
        }
    }
}
