package com.example.evan.androidviewertemplates.drawer_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertemplates.match_details.MatchDetailsActivity;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.match_listing.MatchesAdapter;
import com.example.evan.androidviewertools.match_listing.MatchesFragment;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.Utils;


/**
 * Created by citruscircuits on 1/17/16.
 */
public class UpcomingMatchesFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new UpcomingMatchesAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/17/16.
     */
    public static class UpcomingMatchesAdapter extends MatchesAdapter {

        public UpcomingMatchesAdapter(Context context) {
            super(context, true);
        }

        @Override
        public boolean secondaryFilter(Match value) {
            return (Integer)Utils.getObjectField(value, "blueScore") == null;
        }

        @Override
        public boolean shouldHighlightTextViewWithText(String text) {
            return false;
        }

        @Override
        public Intent getMatchDetailsActivityIntent() {
            return new Intent(context, MatchDetailsActivity.class);
        }
    }

    private Activity context = getActivity();
    @Override
    public Intent getMatchDetailsActivityIntent() {
        return new Intent(context, MatchDetailsActivity.class);
    }
}
