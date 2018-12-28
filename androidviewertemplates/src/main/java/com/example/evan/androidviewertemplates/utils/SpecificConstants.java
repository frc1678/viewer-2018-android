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
    public static final String[] DRAWER_TITLES = {"Schedule", "Upcoming Matches", "Recent Matches", "Starred Matches", "Our Schedule", "Seeding", "First Pick", "Second Pick", "Super Data", "Live Picklist", "Function"};

    public static final String ORIGINAL_ROOT_FIREBASE_PATH = "https://scouting-chezy-2018.firebaseio.com/";

    public static String ROOT_FIREBASE_PATH = ORIGINAL_ROOT_FIREBASE_PATH;
    public static String MATCHES_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Matches";
    public static String TEAMS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Teams";
    public static String TEAM_IN_MATCH_DATAS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "TeamInMatchDatas";

    static {
        Map<String, String> initialKeysToTitlesMap = new HashMap<String, String>() {
            {
//                put("EXAMPLE_DATA_POINT", "EXAMPLE_DATA_TITLE");
//                Look at past years' SpecificConstants for further formatting
            }
        };
        Map<String, String> initialDatasToGraphMap = new HashMap<String, String>() {
            {
//              put("DATA_POINT_TO_BE_GRAPHED","DATA_POINT_NEEDED_TO_GRAPH_PRIOR_DATA_POINT");


            }
        };
        KEYS_TO_TITLES = initialKeysToTitlesMap;
        DATA_TO_GRAPH = initialDatasToGraphMap;
    }
}
