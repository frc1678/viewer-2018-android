package com.example.evan.androidviewertools.graphing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evan.androidviewertools.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;


public abstract class GraphFragment extends Fragment {
    BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        barChart = (BarChart)view.findViewById(R.id.chart);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.setDescription("");

        barChart.setPinchZoom(false);

        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setDrawAxisLine(false);
        xAxis.setSpaceBetweenLabels(2);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(13f);

        YAxis left = barChart.getAxisLeft();
        left.setDrawLabels(false);
        left.setStartAtZero(false);
        left.setSpaceTop(25f);
        left.setSpaceBottom(25f);
        left.setDrawAxisLine(false);
        left.setDrawGridLines(false);
        left.setDrawZeroLine(true);
        left.setZeroLineColor(Color.DKGRAY);
        left.setZeroLineWidth(1.0f);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setOnChartValueSelectedListener(getOnChartValueSelectedListener());

        BarDataSet dataSet = new BarDataSet(listToDataPoints(getValues()), "");

        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BarData data = new BarData(getLabels(), dataSets);
        data.setValueTextSize(15f);
        barChart.setData(data);
        barChart.invalidate();
    }

    public abstract List<String> getLabels();
    public abstract List<Float> getValues();
    public abstract OnChartValueSelectedListener getOnChartValueSelectedListener();

    public List<BarEntry> listToDataPoints(List<Float> valuesList)  {
        List<BarEntry> dataPoints = new ArrayList<>();
        for (int i = 0; i < valuesList.size(); i++) {
            if (getValues().get(i) != null) {
                dataPoints.add(new BarEntry(getValues().get(i), i));
            }
        }

        return dataPoints;
    }
}
