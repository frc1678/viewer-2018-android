package com.example.evan.androidviewertemplates.drawer_fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.evan.androidviewertemplates.match_details.MatchDetailsActivity;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.match_listing.MatchesAdapter;
import com.example.evan.androidviewertools.match_listing.MatchesFragment;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;

import org.jcodec.common.DictionaryCompressor;
import org.jcodec.common.RunLength;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Teo on 4/12/18.
 */

public class OurScheduleHighlightFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.isInSeedingFragment = false;
        Integer teamNumber = Integer.parseInt(FirebaseLists.teamsList.getKeys().get(0));
        setListAdapter(new TeamScheduleFragment.CitrusScheduleAdapter(getActivity().getApplicationContext(), teamNumber));
    }

    /**
     * Created by citruscircuits on 1/17/16.
     */
    public static class CitrusScheduleAdapter extends MatchesAdapter {
        Integer teamNumber;


        public CitrusScheduleAdapter(Context context, Integer teamNumber) {
            super(context, true);
            this.teamNumber = teamNumber;
        }

        @Override
        public boolean secondaryFilter(Match value) {
            List<Object> redTeams = Arrays.asList(Utils.getObjectField(value, "redAllianceTeamNumbers"));
            List<Object> blueTeams = Arrays.asList(Utils.getObjectField(value, "blueAllianceTeamNumbers"));
            List<Integer> redAllianceTeams = (List<Integer>)(Object)redTeams.get(0);
            List<Integer> blueAllianceTeams = (List<Integer>)(Object)blueTeams.get(0);
            return (redAllianceTeams.contains(teamNumber) || blueAllianceTeams.contains(teamNumber));
        }


        @Override
        public boolean shouldHighlightTextViewWithText(String text) {
            return text.equals(teamNumber.toString());
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


