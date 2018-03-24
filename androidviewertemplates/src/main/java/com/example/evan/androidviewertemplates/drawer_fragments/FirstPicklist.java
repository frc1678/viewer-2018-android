package com.example.evan.androidviewertemplates.drawer_fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertemplates.team_details.FirstPicklistAdapter;
import com.example.evan.androidviewertools.utils.Constants;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by Teo on 2/1/18.
 */
public class FirstPicklist extends Fragment {


    //Creating the DatabaseReference, dref, which we'll use whenever we need to send/receive from Firebase.
    DatabaseReference dref;

    //Creating the FirebaseDatabase, dataBase, which we'll 'mix' with dref later one.
    FirebaseDatabase dataBase;

    //Creating the context (getActivity())
    Context context;

    //Creates the <Integer,String> map "teams".
    public Map<Integer, String> teams = new HashMap<>();

    //onCreate method, which we won't use much as this class is a Fragment, so we use onViewCreate instead!
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        context = getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View myLayout = inflater.inflate(R.layout.firstpicklist, null);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LayoutInflater fakeInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View myLayout = fakeInflater.inflate(R.layout.firstpicklist, null);

        final ListView listView = (ListView) myLayout.findViewById(R.id.listview);

        dataBase = FirebaseDatabase.getInstance();

        dref = dataBase.getReference();

        dref.child("picklist").addChildEventListener(new ChildEventListener() {

            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String teamNumber = dataSnapshot.getValue().toString();

                Integer teamPicklistPosition = Integer.parseInt(dataSnapshot.getKey());

                teams.put(teamPicklistPosition, teamNumber);

                Constants.picklistMap.put(teamPicklistPosition, teamNumber.toString());

                String key = new String();

                saveMap(context, key, Constants.picklistMap);


                if (Constants.picklistMap.size() >= 65) {


                    FirstPicklistAdapter adapter = new FirstPicklistAdapter(context, sortByValue(Constants.picklistMap));

                    listView.setAdapter(adapter);


/*
                    final Dialog passwordDialog = new Dialog(context);

                    passwordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    passwordDialog.setContentView(R.layout.passworddialog);

                    passwordDialog.setCancelable(false);

                    passwordDialog.setCanceledOnTouchOutside(false);

                    passwordDialog.show();

                    Boolean value = false;

                        final Button passwordButton = passwordDialog.findViewById(R.id.passwordButton);

                        passwordButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                EditText passwordEditText = (EditText) passwordDialog.findViewById(R.id.passwordEditText);
                                if (passwordEditText.getText().toString().equals("ahh")) {
                                    Log.e("passwordEditText:",passwordEditText.getText().toString());
                                    Log.e("Password is correct","Password inputed: "+passwordEditText.getText().toString());
                                    passwordDialog.dismiss();
                                } else {
                                    Log.e("Password is incorrect","Password inputed: "+passwordEditText.getText().toString());

                                    Toast.makeText(getActivity(), "hacking = bad",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                    });
                    final Button passwordButton = passwordDialog.findViewById(R.id.passwordButton);

                    passwordButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            EditText passwordEditText = (EditText) passwordDialog.findViewById(R.id.passwordEditText);
                            if (passwordEditText.getText().toString().equals("password")) {
                                Log.e("passwordEditText:", passwordEditText.getText().toString());
                                Log.e("Password is correct", "Password inputed: " + passwordEditText.getText().toString());
                                passwordDialog.dismiss();
                            } else {
                                Log.e("Password is incorrect", "Password inputed: " + passwordEditText.getText().toString());

                                Toast.makeText(getActivity(), "hacking = bad",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });*/

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override

                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                            Constants.counter = -1;

                            Log.e("Counter", String.valueOf(Constants.counter));
                            Log.e("onItemClickListener", "a listview cell was clicked!~!~");

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

                                    Constants.counter = Constants.counter + 1;

                                 /*   if (Constants.counter > 0); {
                                        Toast.makeText(getActivity(), "Pressing the up button could cause incorrect movements! Please click out of dialog.",
                                                Toast.LENGTH_LONG).show();

                                    }*/

                                    Log.e("Counter", String.valueOf(Constants.counter));

                                    Integer myTeam = position - Constants.counter; //7 -159

                                    Integer otherTeam = position - (Constants.counter + 1); //6 - 1678


                                    Map<Integer, String> onClickMap = sortByValue(Constants.picklistMap);

                                    String extraValue;

                                    Log.e("myposition", myTeam.toString());

                                    Log.e("otherposition", otherTeam.toString());

                                    extraValue = onClickMap.get(myTeam); //159

                                    String team = onClickMap.get(otherTeam);

                                    Constants.picklistMap.put(myTeam, onClickMap.get(otherTeam));

                                    Constants.picklistMap.put(otherTeam, extraValue); //6

                                    Log.e("Position: ", Constants.picklistMap.get(position));

                                    Log.e("Position - 1: ", Constants.picklistMap.get(position - 1));

                                    Log.e("myTeam", myTeam.toString());

                                    if (myTeam > 0) {

                                        dref.child("picklist").child(myTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(myTeam)));
                                        dref.child("picklist").child(otherTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(otherTeam)));

                                    } else {

                                        Toast.makeText(getActivity(), "Nice try.",
                                                Toast.LENGTH_LONG).show();

                                    }

                                }

                            });

                            downButton.setOnClickListener(new View.OnClickListener() {

                                @Override

                                public void onClick(View v) {

                                    //downButton onClick


                                    Constants.counter = Constants.counter + 1;

                                  /*  if (Constants.counter > 0); {
                                        Toast.makeText(getActivity(), "Pressing the up button could cause incorrect movements! Please click out of dialog.",
                                                Toast.LENGTH_LONG).show();

                                    }*/


                                    Log.e("Counter", String.valueOf(Constants.counter));

                                    Integer myTeam = position + Constants.counter; //

                                    Integer otherTeam = position + (Constants.counter + 1); //

                                    Map<Integer, String> onClickMap = sortByValue(Constants.picklistMap);

                                    String extraValue;

                                    extraValue = onClickMap.get(otherTeam);

                                    String team = onClickMap.get(myTeam);

                                    Constants.picklistMap.put(otherTeam, onClickMap.get(myTeam));

                                    Constants.picklistMap.put(myTeam, extraValue);


                                    Log.e("myTeam", myTeam.toString());

                                    if (myTeam < 65) {

                                        dref.child("picklist").child(myTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(myTeam)));
                                        dref.child("picklist").child(otherTeam.toString()).setValue(Integer.parseInt(Constants.picklistMap.get(otherTeam)));


                                    } else {

                                        Toast.makeText(getActivity(), "Nice try.",
                                                Toast.LENGTH_LONG).show();

                                    }

                                }

                            });

                            dialog.show();
                        }

                    });

                    adapter.notifyDataSetChanged();

                }


            }

            @Override

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                FirstPicklistAdapter adapter = new FirstPicklistAdapter(context, sortByValue(Constants.picklistMap));

                listView.setAdapter(adapter);

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

    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        Constants.picklistMap.clear();

    }


    public static void saveMap(Context context, String key, Map<Integer, String> inputMap) {

        SharedPreferences pSharedPref = context.getSharedPreferences("MyPREF", Context.MODE_PRIVATE);

        if (pSharedPref != null) {

            Gson gson = new Gson();

            String hashMapString = gson.toJson(inputMap);


            //save in shared prefs

            pSharedPref.edit().putString(key, hashMapString).apply();

        }

    }


}







