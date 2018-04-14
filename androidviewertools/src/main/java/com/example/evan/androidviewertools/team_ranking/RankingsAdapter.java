package com.example.evan.androidviewertools.team_ranking;

import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.ObjectFieldComparator;
import com.example.evan.androidviewertools.R;
import com.example.evan.androidviewertools.search_view.SearchableFirebaseListAdapter;
import com.example.evan.androidviewertools.utils.Utils;

import java.util.List;

public abstract class RankingsAdapter<T> extends SearchableFirebaseListAdapter<T> {
    private String rankFieldName;
    private String valueFieldName;

    public RankingsAdapter(Context context, String rankFieldName, String valueFieldName, boolean isNotReversed) {
        super(context, new ObjectFieldComparator(rankFieldName, isNotReversed));
        this.rankFieldName = rankFieldName;
        this.valueFieldName = valueFieldName;


    }

    @Override
    public int getCount() {
        return filteredValues.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.ranking_list_cell, parent, false);
        }

        T value = (T)getItem(position);

        if (isImportant(getRankCellText(value))) {
            rowView.setBackgroundColor(Constants.STAR_COLOR);
        } else {
            rowView.setBackgroundColor(Color.TRANSPARENT);
        }

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.rankingTextView);
        rankingTextView.setText(getRankText(value));

        Log.e("FILTER " + (String) Utils.getObjectField(value, "name"), getRankText(value));

        TextView teamNumberTextView = (TextView)rowView.findViewById(R.id.teamNumberTextView);
        if (searchString.length() > 0) {
            teamNumberTextView.setText(Utils.highlightTextInString(getRankCellText(value), searchString));
        } else {
            teamNumberTextView.setText(getRankCellText(value));
        }

        TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
        if (!displayValueAsPercentage()) {
            valueTextView.setText(Utils.roundDataPoint(getRankCellDataPoint(value), 2, "???"));
        } else {
            valueTextView.setText(Utils.dataPointToPercentage((Float)getRankCellDataPoint(value), 1));
        }

        rowView.setOnLongClickListener(new StarLongClickListener());
        rowView.setOnClickListener(new RankClickListener());

        checkIfInSeedingFragment(rowView, value);

        return rowView;
    }

    public Object getRankCellDataPoint(T value) {
        return Utils.getObjectField(value, valueFieldName);
    }

    public void checkIfInSeedingFragment(View rowView, T value){
        if(Constants.isInSeedingFragment) {
            TextView predictedRankTextView = (TextView) rowView.findViewById(R.id.predictedSeedTextView);
            predictedRankTextView.setText(getPredictedRankText(value));
            predictedRankTextView.setAlpha(1f);
            TextView predictedRPTextView = (TextView) rowView.findViewById(R.id.predictedNumRP);
            predictedRPTextView.setText(Utils.roundDataPoint(Utils.getObjectField(value, "calculatedData.predictedNumRPs"), 2, "???"));
            predictedRPTextView.setAlpha(1f);
        }
    }

    public abstract String getRankCellText(T value);
    public abstract boolean isImportant(String valueTitle);
    public abstract void makeImportant(String valueTitle);
    public abstract void makeNoLongerImportant(String valueTitle);
    public abstract void respondToClick(String valueTitle);
    public Boolean displayValueAsPercentage() {return false;}

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
            TextView rankingCellTitleTextView = (TextView)v.findViewById(R.id.teamNumberTextView);
            String valueTitle = rankingCellTitleTextView.getText().toString();
            if (isImportant(valueTitle)) {
                makeNoLongerImportant(valueTitle);
            } else {
                makeImportant(valueTitle);
            }
            notifyDataSetChanged();
            return true;
        }
    }

    private class RankClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            TextView rankingCellTitleTextView = (TextView)v.findViewById(R.id.teamNumberTextView);

            respondToClick(rankingCellTitleTextView.getText().toString());
        }
    }

    public String getRankText(T value) {
        Integer rank =  Utils.getRankOfObject(value, getOtherValuesForRanking(), rankFieldName);
        if (rank != null) {
            return rank + 1 + "";
        } else {
            return "?";
        }
    }

    public String getPredictedRankText(T value){
        Integer rank =  Utils.getRankOfObject(value, getOtherValuesForRanking(), "calculatedData.predictedSeed");
        if (rank != null) {
            return rank + 1 + "";
        } else {
            return "?";
        }
    }

    public abstract List<Object> getOtherValuesForRanking();
}
