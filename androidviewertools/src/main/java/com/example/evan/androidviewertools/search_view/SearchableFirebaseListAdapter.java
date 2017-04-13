package com.example.evan.androidviewertools.search_view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.BaseAdapter;

import com.example.evan.androidviewertools.ViewerActivity;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public abstract class SearchableFirebaseListAdapter<T> extends BaseAdapter {
    public String searchString;
    public String selectedScope;
    public List<T> filteredValues = new ArrayList<>();
    Comparator<T> filterComparator;
    public Context context;
    public boolean sortByNumber;
    private BroadcastReceiver broadcastReceiver;

    public SearchableFirebaseListAdapter(Context context, Comparator<T> filterComparator) {
        this.filterComparator = filterComparator;
        this.context = context;
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                searchWithTextInScope(searchString, selectedScope);
                notifyDataSetChanged();
            }
        };
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, new IntentFilter(getBroadcastAction()));

        BroadcastReceiver starReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                notifyDataSetChanged();
            }
        };
        LocalBroadcastManager.getInstance(context).registerReceiver(starReceiver, new IntentFilter(Constants.STARS_MODIFIED_ACTION));
    }

    public void searchWithTextInScope(String searchString, String scope) {
        this.searchString = searchString;
        this.selectedScope = scope;
        filterValues();
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        filterValues();

        super.notifyDataSetChanged();
    }

    public void filterValues() {
        filteredValues.clear();
        for (T value : getFirebaseList()) {
            if(filter(value, selectedScope)) {
                filteredValues.add(value);
                //Log.e("filteredValues", filteredValues.toString());
            }
        }

        if(Constants.sortByTeamNumber) {
            sortByTeamNumber();

        }else if(Constants.sortByRank){
            sortByTeamRank();

        }else if(Constants.sortByFirstPick){
            Log.e("sortByFirst", "True");
            sortByFirstPick();
        }else if(Constants.sortBySecondPick){
            Log.e("sortBySecond", "True");
            sortBySecondPick();
        }
        else{
            sortByTeamRank();
        }


    }
    public void sortByTeamNumber(){
        Collections.sort(filteredValues, new Comparator<T>() {
            public int compare(T obj1, T obj2) {
                // ## Ascending order
                Integer teamNumberOne = (Integer) Utils.getObjectField(obj1, "number");
                Integer teamNumberTwo = (Integer) Utils.getObjectField(obj2, "number");
                return (teamNumberOne).compareTo(teamNumberTwo);// To compare string values
                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
            }
        });
    }
    public void sortByTeamRank(){
        Collections.sort(filteredValues, filterComparator);
        Constants.sortByRank = false;
    }
    public void sortByFirstPick(){
        Collections.sort(filteredValues, new Comparator<T>() {
            public int compare(T obj1, T obj2) {
                Log.e("sorting by", "first pick");
                // ## Ascending order
                Float teamNumberOne = (Float) Utils.getObjectField(obj1, "calculatedData.firstPickAbility");
                Log.e("firstPickTeamOne", teamNumberOne + "");
                Float teamNumberTwo = (Float) Utils.getObjectField(obj2, "calculatedData.firstPickAbility");
                return (teamNumberTwo).compareTo(teamNumberOne);// To compare string values
                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
            }
        });
    }
    public void sortBySecondPick(){
        Collections.sort(filteredValues, new Comparator<T>() {
            public int compare(T obj1, T obj2) {
                // ## Ascending order
                Float teamNumberOne = (Float) Utils.getObjectField(obj1, "calculatedData.overallSecondPickAbility");
                Float teamNumberTwo = (Float) Utils.getObjectField(obj2, "calculatedData.overallSecondPickAbility");
                return (teamNumberTwo).compareTo(teamNumberOne);// To compare string values
                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
            }
        });
    }

    public abstract String getBroadcastAction();

    public abstract boolean filter(T value, String scope);

    public abstract List<T> getFirebaseList();

    public void cleanup() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }
}
