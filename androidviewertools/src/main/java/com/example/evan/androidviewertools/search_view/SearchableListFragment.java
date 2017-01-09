package com.example.evan.androidviewertools.search_view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;


public abstract class SearchableListFragment extends ListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchView searchView = new SearchView(getActivity().getApplicationContext(), getScopes()) {
            @Override
            public void search(String searchString, String scope) {
                ((SearchableFirebaseListAdapter)getListAdapter()).searchWithTextInScope(searchString, scope);
            }
        };

        getListView().addHeaderView(searchView);

    }

    public abstract String[] getScopes();

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((SearchableFirebaseListAdapter)getListAdapter()).cleanup();
    }
}
