package com.example.evan.androidviewertemplates.drawer_fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.team_details.FirstPicklistAdapter;
import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertools.utils.Constants;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Teo on 4/6/2018.
 */

public class OfflinePicklistFragment extends Fragment {

    public Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myLayout = inflater.inflate(R.layout.offlinepicklist, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myLayout = inflater.inflate(R.layout.offlinepicklist, null);
        final ListView listView = (ListView) myLayout.findViewById(R.id.listview);
        final Button picklistEditButton = (Button) myLayout.findViewById(R.id.picklistEditButton);
        final com.example.evan.androidviewertemplates.team_details.FirstPicklistAdapter adapter = new FirstPicklistAdapter(context, sortByValue(Constants.offlinePicklist));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                TextView teamNumberTextView = (TextView)view.findViewById(R.id.teamNumber);
                final Integer teamString = Integer.parseInt(teamNumberTextView.getText().toString());
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.picklistdialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button upButton = (Button) dialog.findViewById(R.id.upButton);
                Button downButton = (Button) dialog.findViewById(R.id.downButton);
                upButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //upButton onClick
                        upButtonClick(Constants.picklistMap, teamString);

                    }
                });
                downButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //downButton onClick
                        downButtonClick(Constants.picklistMap, teamString);

                    }
                });
                adapter.notifyDataSetChanged();
                dialog.show();
            }


        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Integer teamNumberClicked = Integer.parseInt(Constants.picklistMap.get(position));
                Intent teamDetailsViewIntent = getTeamDetailsActivityIntent();
                teamDetailsViewIntent.putExtra("teamNumber", teamNumberClicked);
                teamDetailsViewIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(teamDetailsViewIntent);
                return true;
            }

        });

        return myLayout;
    }

    public Intent getTeamDetailsActivityIntent(){
        return new Intent(getActivity(), TeamDetailsActivity.class);
    }

    private Map<Integer, String> sortByValue(Map<Integer, String> teams) {
        List<Map.Entry<Integer, String>> list = new LinkedList<Map.Entry<Integer, String>>(teams.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Map<Integer, String> sortedMap = new LinkedHashMap<Integer, String>();
        for (Map.Entry<Integer, String> entry : list) {
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

    public void upButtonClick(Map<Integer,String> map , Integer teamString) {
        Integer myTeam = getKeyByValue(map, teamString.toString());
        if(myTeam!=0) {
            Integer otherTeam = myTeam - 1;
            Map<Integer, String> onClickMap = sortByValue(map);
            String extraValue;
            extraValue = onClickMap.get(myTeam);
            String team = onClickMap.get(otherTeam);
            Constants.picklistMap.put(myTeam, onClickMap.get(otherTeam));
            Constants.picklistMap.put(otherTeam, extraValue);
            if (myTeam > 0) {
                //dref.child("picklist").child(myTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(myTeam)));
                Constants.offlinePicklist.get(myTeam).replaceAll(Constants.offlinePicklist.get(myTeam),Constants.offlinePicklist.get(otherTeam));
                //dref.child("picklist").child(otherTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(otherTeam)));
                Constants.offlinePicklist.get(otherTeam).replaceAll(Constants.offlinePicklist.get(otherTeam),Constants.offlinePicklist.get(myTeam));

            }
        } else {
            Toast.makeText(getActivity(), "Nice try.",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void downButtonClick(Map<Integer,String> map, Integer teamString) {
        Integer myTeam = getKeyByValue(map, teamString.toString());
        Integer otherTeam = myTeam + 1;
        Map<Integer, String> onClickMap = sortByValue(map);
        String extraValue;
        extraValue = onClickMap.get(otherTeam);
        String team = onClickMap.get(myTeam);
        Constants.picklistMap.put(otherTeam, onClickMap.get(myTeam));
        Constants.picklistMap.put(myTeam, extraValue);
        if (myTeam < 65) {

            //dref.child("picklist").child(myTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(myTeam)));
            Constants.offlinePicklist.get(myTeam).replaceAll(Constants.offlinePicklist.get(myTeam),Constants.offlinePicklist.get(otherTeam));
            //dref.child("picklist").child(otherTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(otherTeam)));
            Constants.offlinePicklist.get(otherTeam).replaceAll(Constants.offlinePicklist.get(otherTeam),Constants.offlinePicklist.get(myTeam));


        } else {
            Toast.makeText(getActivity(), "Nice try.",
                    Toast.LENGTH_LONG).show();
        }
    }
}

