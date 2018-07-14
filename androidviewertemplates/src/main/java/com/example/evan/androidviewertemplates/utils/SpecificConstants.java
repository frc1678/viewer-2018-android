package com.example.evan.androidviewertemplates.utils;

import android.util.Log;

import com.example.evan.androidviewertools.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class SpecificConstants extends Constants {
    //todo
    public static Map<String, String> KEYS_TO_TITLES;
    public static Map<String, String> DATA_TO_GRAPH;
    //todo
    public static final String[] DRAWER_TITLES = {"Recent Matches", "Upcoming Matches", "Our Schedule", "Starred Matches", "Schedule", "Seeding", "Predicted Seeding", "First Pick", "Second Pick", "Super Data", "Live Picklist", "Function"};

    public static final String ORIGINAL_ROOT_FIREBASE_PATH = "https://scouting-2018-temp.firebaseio.com/";

    public static String ROOT_FIREBASE_PATH = ORIGINAL_ROOT_FIREBASE_PATH;
    public static String MATCHES_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Matches";
    public static String TEAMS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Teams";
    public static String TEAM_IN_MATCH_DATAS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "TeamInMatchDatas";

    static {
        Map<String, String> initialKeysToTitlesMap = new HashMap<String, String>() {
            {
                //Don't forget to comment titles of sections (such as LastFourMatches or Tele)
//                put("EXAMPLE_DATA_POINT", "EXAMPLE_DATA_TITLE");
            }
        };
        Map<String, String> initialDatasToGraphMap = new HashMap<String, String>() {
            {
                put("calculatedData.avgOpponentSwitchCubesTele", "calculatedData.numOpponentSwitchSuccessTele");
                //Don't forget to comment titles of sections (such as LastFourMatches)
//                  put("EXAMPLE_DATA_POINT_TO_GRAPH", "EXAMPLE_RAW_DATA_pOINT_FOR_GRAPH_DATA_POINT");
            }
        };
        //replace all 'DEFENSE's with the correct defenses
        KEYS_TO_TITLES = initialKeysToTitlesMap;
        DATA_TO_GRAPH = initialDatasToGraphMap;
    }
}
