package com.example.evan.androidviewertools.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;


import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.R;
import com.example.evan.androidviewertools.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class MultitypeRankingsSectionAdapter extends RankingsSectionAdapter {
    //this class is for teams and teaminmatchdatas

    public Context context;

    public MultitypeRankingsSectionAdapter(Context context) {
        super(context);
        this.context = context;
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                notifyDataSetChanged();
            }
        }, new IntentFilter(getUpdatedAction()));
    }


    @Override
    public Object getRowItem(int section, int row) {
        return getFieldsToDisplay()[section][row];
    }

    @Override
    public int numberOfSections() {
        return getSectionTitles().length;
    }

    @Override
    public int numberOfRows(int section) {
        if (section > -1) {
            return getFieldsToDisplay()[section].length;
        } else {
            return 0;
        }
    }

    @Override
    public boolean hasSectionHeaderView(int section) {
        return true;
    }

    @Override
    public Object getSectionHeaderItem(int section) {
        return getSectionTitles()[section];
    }

    @Override
    public String getRankTextOfRowInSection(int section, int row) {
        String fieldName = (String)getRowItem(section, row);
        Object object = getObject();
        if (fieldName.startsWith("VIEWER.")) {
            Intent intent = new Intent();
            fieldName = Utils.getViewerObjectFieldRank(fieldName.replaceFirst("VIEWER.", ""), intent, getViewerDataPointsClass());
        }
        Integer rank = Utils.getRankOfObject(object, getObjectList(), fieldName, false);
        if (rank == null) {
            return "?";
        }
        return (++rank).toString();
    }

    @Override
    public String getNameOfRowInSection(int section, int row) {
        return getKeysToTitles().get(getRowItem(section, row));
    }

    @Override
    public String getValueOfRowInSection(int section, int row) {
        String fieldKey = (String)getRowItem(section, row);
        Object object = getObject();
        if (fieldKey.contains("VIEWER.")) {
            Intent intent = new Intent();
            try {
                return Utils.getViewerObjectField(object, fieldKey.replaceAll("VIEWER.", ""), intent, getViewerDataPointsClass()).toString();
            } catch (NullPointerException npe) {
                return "???";
            }
        }
        if (new ArrayList<>(Arrays.asList(getPercentageFields())).contains(fieldKey)) {
            //Log.e(fieldKey, Utils.getObjectField(getObject(), fieldKey).toString());
            Log.e("fieldKey", fieldKey);
            return Utils.dataPointToPercentage((Float)Utils.getObjectField(getObject(), fieldKey), 1);
        }
        return Utils.getDisplayValue(object, fieldKey);
    }

    @Override
    public boolean isUnranked(int section, int row) {
        return (Arrays.asList(getUnrankedFields()).contains(getRowItem(section, row)));
    }

    public boolean isLongText(int section, int row) {
        return (Arrays.asList(getLongTextFields()).contains(getRowItem(section, row)));
    }

    public boolean isFurtherInformation(int section, int row) {
        return (Arrays.asList(getFurtherInformationFields()).contains(getRowItem(section, row)));
    }

    public boolean isClickable(int section, int row) {
        return !(Arrays.asList(getNotClickableFields()).contains(getRowItem(section, row)));
    }

    public boolean respondsNormallyToClick(int section, int row) {
        return !(Arrays.asList(getNonDefaultClickResponseFields()).contains(getRowItem(section, row)));
    }

    @Override
    public View getOtherTypeOfView(int section, int row) {
        View cell = null;
        if (isLongText(section, row)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.long_text_cell, null, false);
            TextView titleTextView = (TextView) cell.findViewById(R.id.teamNumberTextView);
            titleTextView.setText(getNameOfRowInSection(section, row));
            TextView longTextView = (TextView) cell.findViewById(R.id.longTextView);
            longTextView.setText(getValueOfRowInSection(section, row));
        } else if (isFurtherInformation(section, row)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.team_details_further_info_cell, null, false);
            TextView titleTextView = (TextView) cell.findViewById(R.id.teamNumberTextView);
            titleTextView.setText(getNameOfRowInSection(section, row));
        }

        return cell;
    }

    @Override
    public void onRowItemClick(AdapterView<?> parent, View view, int section, int row, long id) {
        if(isClickable(section, row)) {
            if (respondsNormallyToClick(section, row)) {
                Intent intent;
                if (Arrays.asList(getRankInsteadOfGraphFields()).contains(getRowItem(section, row))) {
                    intent = getRankActivityIntent();
                } else {
                    intent = getGraphActivityIntent();
                }
                intent.putExtra("team", ((Team)getObject()).number)
                        .putExtra("field", (String) getRowItem(section, row))
                        .putExtra("displayAsPercentage", Arrays.asList(getPercentageFields()).contains(getRowItem(section, row)));
                context.startActivity(intent);
            } else {
                handleNonDefaultClick(section, row);
            }
        }
    }

    @Override
    public boolean isRowEnabled(int section, int row) {
        return isClickable(section, row);
    }

    public abstract String[][] getFieldsToDisplay();
    public abstract String[] getSectionTitles();
    public abstract String[] getUnrankedFields();
    public abstract String[] getLongTextFields();
    public abstract String[] getPercentageFields();
    public abstract String[] getFurtherInformationFields();
    public abstract String[] getNotClickableFields();
    public abstract String[] getNonDefaultClickResponseFields();
    public abstract String[] getRankInsteadOfGraphFields();
    public abstract void handleNonDefaultClick(int section, int row);
    public abstract String getUpdatedAction();
    public abstract Object getObject();
    public abstract List<Object> getObjectList();
    public abstract Intent getRankActivityIntent();
    public abstract Intent getGraphActivityIntent();
    public abstract Map<String, String> getKeysToTitles();
    public abstract Class<?> getViewerDataPointsClass();
}
