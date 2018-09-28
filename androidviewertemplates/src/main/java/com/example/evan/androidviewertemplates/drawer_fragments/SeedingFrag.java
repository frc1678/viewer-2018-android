package com.example.evan.androidviewertemplates.drawer_fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.firebase_classes.Competition;
import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertemplates.team_ranking.SeedingAdapter;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.team_ranking.TeamRankingsAdapter;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Teo on 8/30/18.
 */

public class SeedingFrag extends Fragment {
    Context context;
    ListView listview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        if (!Constants.unseededTeams.contains(1678)) {
            sortSeedingList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myLayout = inflater.inflate(R.layout.seeding_cell, null);
        listview = (ListView) myLayout.findViewById(R.id.listview);
        initializeAdapter();
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                if (StarManager.starredTeams.contains(Integer.parseInt(Constants.seedingTeams.get(position).toString()))) {
                StarManager.removeStarredTeam(Integer.parseInt(Constants.seedingTeams.get(position).toString()));
                    initializeAdapter(); animation(view);
                } else {
                StarManager.addStarredTeam(Integer.parseInt(Constants.seedingTeams.get(position).toString()));
                    initializeAdapter(); animation(view);

                }
                return true;
            }
            });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer teamNumberClicked = Integer.parseInt(Constants.seedingTeams.get(position).toString());
                Intent teamDetailsViewIntent = getTeamDetailsActivityIntent();
                teamDetailsViewIntent.putExtra("teamNumber", teamNumberClicked);
                teamDetailsViewIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(teamDetailsViewIntent);
            }
        });

                return myLayout;
    }

    public void sortSeedingList() {
        List<Object> teams = new ArrayList<>();
        teams.addAll(FirebaseLists.teamsList.getKeys());

        for (int i = 0; i < teams.size(); i++) {
            Constants.unseededTeams.add(Integer.parseInt(teams.get(i).toString()));
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < Constants.unseededTeams.size(); i++) {
            Integer team = Constants.unseededTeams.get(i);
            Team teamTeam = FirebaseLists.teamsList.getFirebaseObjectByKey(team.toString());
            String teamRankString = (Utils.fieldIsNotNull(teamTeam, "calculatedData.actualSeed") ? Utils.roundDataPoint(Utils.getObjectField(teamTeam, "calculatedData.actualSeed"), 2, "???") : "???");
            Log.e("teamarankstring",teamRankString);
            map.put(Integer.parseInt(teamRankString)-1, team);

        }
        for (int i = 0; i < Constants.unseededTeams.size(); i++) {
            Integer team = map.get(i);
            Constants.seedingTeams.add(team);
        }
    }
//

    public static Map<Integer, Integer> sortByValue(Map<Integer, Integer> teams) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(teams.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static <Integer, String> Integer getKeyByValue(Map<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    public Intent getTeamDetailsActivityIntent() {
        return new Intent(getActivity(), TeamDetailsActivity.class);
    }
    public void initializeAdapter() {
        SeedingAdapter adapter = new SeedingAdapter(context, Constants.seedingTeams);
        listview.setAdapter(adapter);
    }
    public void animation(View view) {
        Animation animation = new AlphaAnimation(0, 0);
        animation.setDuration(0);
        view.startAnimation(animation);
    }
}
