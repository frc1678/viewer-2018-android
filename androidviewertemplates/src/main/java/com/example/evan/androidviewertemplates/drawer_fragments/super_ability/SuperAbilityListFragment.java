package com.example.evan.androidviewertemplates.drawer_fragments.super_ability;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsAdapter;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsFragment;
import com.example.evan.androidviewertools.utils.Constants;


/**
 * Created by colinunger on 1/31/16.
 */
public class SuperAbilityListFragment extends TeamRankingsFragment {
    private String field;
    private Activity context = getActivity();

    public SuperAbilityListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.isInSeedingFragment = false;
        field = getArguments().getString("field");
        setListAdapter(new SuperAbilityListAdapter(getActivity().getApplicationContext(), field));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View secondPickTeamTitleListHeader = View.inflate(getActivity().getApplicationContext(), R.layout.second_pick_list_header, null);
        TextView secondPickTeamTitleTextView = (TextView)secondPickTeamTitleListHeader.findViewById(R.id.secondPickTeamTitleTextView);
        secondPickTeamTitleTextView.setText(SpecificConstants.KEYS_TO_TITLES.get(field));
        Log.e("super data field", field);
        getListView().addHeaderView(secondPickTeamTitleListHeader, null, false);
    }

    public Intent getTeamDetailsActivityIntent() {
        return new Intent(context, TeamDetailsActivity.class);
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class SuperAbilityListAdapter extends TeamRankingsAdapter {

        public SuperAbilityListAdapter(Context context, String field) {
            super(context, field, field, false);
        }
        public Intent getTeamDetailsActivityIntent() {
            return new Intent(context, TeamDetailsActivity.class);
        }
    }
}
