package com.example.evan.androidviewertemplates.drawer_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teo on 3/14/2018.
 */

public class PicklistCell extends RelativeLayout {
    private String dataName;
    private int max;
    private int min;
    Context context;
    String teamNumber;
    Integer teamPicklistPosition;
    String teamNumberValue;
    String teamPositionValue;
    String positionTextView;
    Button downButton;
    Button upButton;
    String string;
    Map<String, Integer> map = new HashMap<>();

    public PicklistCell(Context context, String teamNumber, Integer teamPicklistPosition) {
        super(context);

        this.teamNumber = teamNumber;
        this.teamPicklistPosition = teamPicklistPosition;
        this.context = context;
        teamNumberValue = Constants.picklistMap.get(teamNumber);
        //teamPositionValue = Constants.picklistMap.get();


        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.firstpicklistcelllayout, this, true);

        TextView position = (TextView) findViewById(R.id.rankNumber);


        TextView number = (TextView) findViewById(R.id.teamNumber);
        number.setText(teamNumber);
        position.setText(teamPicklistPosition.toString());
    }


    public static Map<String,Integer> loadMap(String key, Context context){
        Map<String,Integer> outputMap = new HashMap<String,Integer>();
        SharedPreferences pSharedPref = context.getSharedPreferences("MyPREF", Context.MODE_PRIVATE);
        try{
            //get from shared prefs
            String storedHashMapString = pSharedPref.getString(key, (new JSONObject()).toString());
            java.lang.reflect.Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
            Gson gson = new Gson();

            return  gson.fromJson(storedHashMapString, type);

        }catch(Exception e){
            e.printStackTrace();
        }
        return outputMap;
    }

}