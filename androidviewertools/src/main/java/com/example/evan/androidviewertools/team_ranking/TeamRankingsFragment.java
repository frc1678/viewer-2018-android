package com.example.evan.androidviewertools.team_ranking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.search_view.SearchableListFragment;


public abstract class TeamRankingsFragment extends SearchableListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        TeamRankingsAdapter adapter = (TeamRankingsAdapter)getListAdapter();
        int teamNumberClicked = adapter.filteredValues.get(position - getListView().getHeaderViewsCount()).number;

        Intent teamDetailsViewIntent = getTeamDetailsActivityIntent();
        teamDetailsViewIntent.putExtra("teamNumber", teamNumberClicked);

        startActivity(teamDetailsViewIntent);
    }

    @Override
    public String[] getScopes() {
        return Constants.TEAM_SCOPES;
    }
    public abstract Intent getTeamDetailsActivityIntent();
}
