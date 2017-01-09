package com.example.evan.androidviewertools.match_listing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.search_view.SearchableFirebaseListAdapter;
import com.example.evan.androidviewertools.search_view.SearchableListFragment;
import com.example.evan.androidviewertools.firebase_classes.Match;


public abstract class MatchesFragment extends SearchableListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        SearchableFirebaseListAdapter<Match> adapter = (SearchableFirebaseListAdapter)getListAdapter();
        int matchNumberClicked = adapter.filteredValues.get(position - getListView().getHeaderViewsCount()).number;

        Intent matchDetailsActivityIntent = getMatchDetailsActivityIntent();
        matchDetailsActivityIntent.putExtra("matchNumber", matchNumberClicked);

        startActivity(matchDetailsActivityIntent);
    }



    @Override
    public String[] getScopes() {
        return Constants.MATCH_SCOPES;
    }

    public abstract Intent getMatchDetailsActivityIntent();
}
