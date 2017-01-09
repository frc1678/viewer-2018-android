package com.example.evan.androidviewertemplates.drawer_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evan.androidviewertemplates.match_details.MatchDetailsActivity;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.match_listing.MatchesAdapter;
import com.example.evan.androidviewertools.match_listing.MatchesFragment;


/**
 * Created by citruscircuits on 1/16/16.
 */
public class ScheduleFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ScheduleAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/16/16.
     */
    public static class ScheduleAdapter extends MatchesAdapter {
        public ScheduleAdapter(Context context) {
            super(context, true);
        }

        @Override
        public boolean secondaryFilter(Match value) {
            return true;
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
