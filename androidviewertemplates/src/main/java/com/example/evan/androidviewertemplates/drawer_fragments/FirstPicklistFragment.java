package com.example.evan.androidviewertemplates.drawer_fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.team_details.FirstPicklistAdapter;
import com.example.evan.androidviewertemplates.team_details.TeamDetailsActivity;
import com.example.evan.androidviewertools.utils.AsteriskPasswordTransformationMethod;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.firebase.FirebaseList;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Teo on 2/1/18.
 */
public class FirstPicklistFragment extends Fragment {
    public static Boolean picklistValue = false;
    public static DatabaseReference dref;
    FirebaseDatabase dataBase;
    Context context;
    public static String picklistPassword = "";
    public static Map<Integer, String> teams = new HashMap<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myLayout = inflater.inflate(R.layout.firstpicklist, null);
        final ListView listView = myLayout.findViewById(R.id.listview);
        dataBase = FirebaseDatabase.getInstance();
        dref = dataBase.getReference();
        dref.child("PicklistPassword").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                picklistPassword = dataSnapshot.getValue().toString();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        dref.child("picklist").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final String teamNumber = dataSnapshot.getValue().toString();
                final Integer teamPicklistPosition = Integer.parseInt(dataSnapshot.getKey());

                putIntoPicklistMaps(teamNumber, teamPicklistPosition);

                if (checkTeamsListSize(Constants.picklistMap)) {

                    final Dialog passwordDialog = new Dialog(context);
                    passwordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    passwordDialog.setContentView(R.layout.passworddialog);
                    passwordDialog.show();

                    final Button passwordButton = passwordDialog.findViewById(R.id.passwordButton);
                    final EditText passwordEditText = (EditText) passwordDialog.findViewById(R.id.passwordEditText);
                    passwordEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());

    passwordButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final String passwordText = passwordEditText.getText().toString();
        passwordDialog.dismiss();
         Log.e("password",passwordText.toString());
         Log.e("passwordtwo",picklistPassword);
         if (checkPassword(passwordText)) {
              FirstPicklistAdapter adapter = new FirstPicklistAdapter(context, sortByValue(Constants.picklistMap));
              listView.setAdapter(adapter);
              FirstPicklistFragment.picklistValue = true;

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
    TextView teamNumberTextView = (TextView) view.findViewById(R.id.teamNumber);
    final Integer teamString = Integer.parseInt(teamNumberTextView.getText().toString());
    Constants.tempTeamNumber = teamString;
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
             Integer myTeam = getKeyByValue(Constants.picklistMap, teamString.toString());
             upButtonClick(myTeam);
             listView.smoothScrollToPosition(myTeam);
            }
         });
    downButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //downButton onClick
            Integer myTeam = getKeyByValue(Constants.picklistMap, teamString.toString());
            downButtonClick(myTeam);
            listView.smoothScrollToPosition(myTeam);
            }
         });
    dialog.show();
        }
    });
    adapter.notifyDataSetChanged();
    } else {
         Toast.makeText(getActivity(), "hacking = bad",
              Toast.LENGTH_LONG).show();
         passwordEditText.getText().clear();
            }
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
        }
    }
    public Intent getTeamDetailsActivityIntent() {
         return new Intent(getActivity(), TeamDetailsActivity.class);
    }
    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
         final String teamNumber = dataSnapshot.getValue().toString();
         Integer teamPicklistPosition = Integer.parseInt(dataSnapshot.getKey());
         putIntoPicklistMaps(teamNumber, teamPicklistPosition);
         FirstPicklistAdapter adapter = new FirstPicklistAdapter(context, sortByValue(Constants.picklistMap));
        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(adapter);
        listView.onRestoreInstanceState(state);
        final Integer teamString = Integer.parseInt(String.valueOf(Constants.tempTeamNumber));
        Integer myTeam = getKeyByValue(Constants.picklistMap, teamString.toString());
        listView.smoothScrollToPosition(myTeam);
         adapter.notifyDataSetChanged();
        }
    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

    @Override
    public void onCancelled(DatabaseError databaseError) {

        }
    });
    return myLayout;
    }

    public static Map<Integer, String> sortByValue(Map<Integer, String> teams) {
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Constants.picklistMap.clear();
    }

    public static void putIntoPicklistMaps(String teamNumber, Integer teamPicklistPosition) {
        teams.put(teamPicklistPosition, teamNumber);
        Constants.picklistMap.put(teamPicklistPosition, teamNumber);
    }
    public static boolean checkTeamsListSize(Map<Integer,String> map) {
        if (map.size() >= FirebaseLists.teamsList.getKeys().size()) {
            return true;
        }
        return false;
    }
    public static boolean checkPassword(String password) {
        if (password.equals(picklistPassword)) {
            return true;
        }
        return false;
    }
    public static void upButtonClick(Integer myTeam) {
        if (myTeam != 0) {
            Integer otherTeam = myTeam - 1;
            Map<Integer, String> onClickMap = sortByValue(Constants.picklistMap);
            String extraValue = onClickMap.get(myTeam);
            Constants.picklistMap.put(myTeam, onClickMap.get(otherTeam));
            Constants.picklistMap.put(otherTeam, extraValue);
            if (myTeam > 0) {
                dref.child("picklist").child(myTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(myTeam)));
                dref.child("picklist").child(otherTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(otherTeam)));
            }
        }
    }
    public static void downButtonClick(Integer myTeam) {
            Integer otherTeam = myTeam + 1;
            Map<Integer, String> onClickMap = sortByValue(Constants.picklistMap);
            String extraValue = onClickMap.get(otherTeam);
            Constants.picklistMap.put(otherTeam, onClickMap.get(myTeam));
            Constants.picklistMap.put(myTeam, extraValue);
            if (myTeam < 65) {
                dref.child("picklist").child(myTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(myTeam)));
                dref.child("picklist").child(otherTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(otherTeam)));
            }
        }
    }

