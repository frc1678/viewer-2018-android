package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.utils.GlobalV;

/**
 * Created by Teo on 4/6/2018.
 */

public class SettingsFragment extends Fragment {

    public Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myLayout = inflater.inflate(R.layout.settingslayout, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myLayout = inflater.inflate(R.layout.settingslayout, null);

        final EditText highlightTeamAmount = (EditText) myLayout.findViewById(R.id.highlightTeamAmountEditText);
        Button saveButton = (Button) myLayout.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveButtonOnClick(highlightTeamAmount);
            }
        });

        return myLayout;

    }

    public void saveButtonOnClick(EditText highlightTeamAmount) {
        if (highlightTeamAmount.getText() != null) {
            Integer teamAmount = Integer.valueOf(highlightTeamAmount.getText().toString());
            GlobalV.highlightTeamAmountGlobal = teamAmount;
            Log.e("GlobalTeamAmount", GlobalV.highlightTeamAmountGlobal.toString());
        }
    }

}
