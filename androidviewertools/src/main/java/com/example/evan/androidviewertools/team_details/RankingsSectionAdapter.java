package com.example.evan.androidviewertools.team_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.headerlistview.SectionAdapter;
import com.example.evan.androidviewertools.R;

public abstract class RankingsSectionAdapter extends SectionAdapter {

    public Context context;

    public RankingsSectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getRowView(int section, int row, View convertView, ViewGroup parent) {
        View rowView = null;

        if (isOtherTypeOfView(section, row)) {
            rowView = getOtherTypeOfView(section, row);
        } else {
            if (isUnranked(section, row)) {
                if (rowView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    rowView = inflater.inflate(R.layout.unranked_cell, parent, false);
                    rowView.setId(View.generateViewId());
                }
            } else {
                if (rowView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    rowView = inflater.inflate(R.layout.ranking_cell, parent, false);
                    rowView.setId(View.generateViewId());
                }

                TextView rankingTextView = (TextView) rowView.findViewById(R.id.rankingTextView);
                rankingTextView.setText(getRankTextOfRowInSection(section, row));
            }

            TextView teamNumberTextView = (TextView) rowView.findViewById(R.id.teamNumberTextView);
            teamNumberTextView.setText(getNameOfRowInSection(section, row));

            TextView valueTextView = (TextView) rowView.findViewById(R.id.valueTextView);
            valueTextView.setText(getValueOfRowInSection(section, row).toString());
        }

        rowView.setPadding(50, rowView.getPaddingTop(), 50, rowView.getPaddingBottom());

        return rowView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_separator, parent, false);
        }

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.separatorTextView);
        rankingTextView.setText((String)getSectionHeaderItem(section));

        return rowView;
    }

    public abstract String getRankTextOfRowInSection(int section, int row);
    public abstract String getNameOfRowInSection(int section, int row);
    public abstract String getValueOfRowInSection(int section, int row);
    public abstract boolean isUnranked(int section, int row);
    public abstract boolean isOtherTypeOfView(int section, int row);
    public abstract View getOtherTypeOfView(int section, int row);
}
