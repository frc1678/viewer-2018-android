package com.example.evan.androidviewertools.search_view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.BaseAdapter;

import com.example.evan.androidviewertools.utils.Constants;

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
            }
        }

        Collections.sort(filteredValues, filterComparator);
    }

    public abstract String getBroadcastAction();

    public abstract boolean filter(T value, String scope);

    public abstract List<T> getFirebaseList();

    public void cleanup() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }
}
