package com.example.evan.androidviewertemplates.drawer_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.evan.androidviewertemplates.match_details.MatchDetailsActivity;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.match_listing.MatchesAdapter;
import com.example.evan.androidviewertools.match_listing.MatchesFragment;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;


/**
 * Created by citruscircuits on 1/17/16.
 */
public class RecentMatchesFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.isInSeedingFragment = false;
        Log.e("Recent", "started");
        setListAdapter(new RecentMatchesAdapter(getActivity().getApplicationContext()));
    }


    /**
     * Created by citruscircuits on 1/17/16.
     */
    public static class RecentMatchesAdapter extends MatchesAdapter {

        public RecentMatchesAdapter(Context context) {
            super(context, false);
        }

        @Override
        public boolean secondaryFilter(Match value) {

           /*try {
               if((Integer)Utils.getObjectField(value, "blueScore") != null) {
                   Integer number = ((Integer) Utils.getObjectField(value, "number"));
                   Log.e("number", number + "");
                   return number <= StarManager.getCurrentMatchNumber();
               }else{
                   return false;
               }
            }catch (NullPointerException NPE){
                Log.e("number", "IS NULL");
            }
            finally {
                return true;
            }*/
            return (Integer) Utils.getObjectField(value, "blueScore") != null;
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
