package com.example.evan.androidviewertools;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.evan.androidviewertools.services.StarManager;
import com.firebase.client.Firebase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ViewerApplicationTemplate extends Application {
    //make sure your application file extends this one

    public static SharedPreferences sharedPreferences = null;
    public static Context appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();


        appContext = getApplicationContext();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }






    public void restoreFromSharedPreferences() {
        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        String jsonImportantMatchesAsString = sharedPreferences.getString("importantMatches", "[]");
        String jsonStarredTeamsAsString = sharedPreferences.getString("starredTeams", "[]");
        String jsonMatchesAddedByTeamAsString = sharedPreferences.getString("matchesAddedByTeam", "{}");

        try {
            StarManager.importantMatches = jsonArrayToIntegerList(new JSONArray(jsonImportantMatchesAsString));
            StarManager.starredTeams = jsonArrayToIntegerList(new JSONArray(jsonStarredTeamsAsString));
        } catch(JSONException jsone) {
            Log.e("test", "ERROR: " + jsone.getMessage());
        }

        try {
            JSONObject jsonMatchesAddedByTeam = new JSONObject(jsonMatchesAddedByTeamAsString);
            StarManager.matchesAddedByTeam = new HashMap<>();
            for (int i = 0; i < jsonMatchesAddedByTeam.length(); i++) {
                String key = (String)jsonMatchesAddedByTeam.names().get(i);
                Integer teamNumber = Integer.parseInt(key);
                List<Integer> matchesAdded = jsonArrayToIntegerList(jsonMatchesAddedByTeam.getJSONArray(key));
                StarManager.matchesAddedByTeam.put(teamNumber, matchesAdded);
            }
        } catch (JSONException jsone) {
            Log.e("test", "JSON ERROR: " + jsone.getMessage());
        }
    }

    public List<Integer> jsonArrayToIntegerList(JSONArray jsonArray) {
        List<Integer> list = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add((Integer)jsonArray.get(i));
            }
        } catch (JSONException jsone) {
            Log.e("test", "JSON ERROR: " + jsone.getMessage());
        }

        return list;
    }
}
