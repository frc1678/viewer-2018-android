package com.example.evan.androidviewertemplates.utils;

import com.example.evan.androidviewertools.utils.Constants;

import java.util.Map;

public class SpecificConstants extends Constants {
    //todo
    public static Map<String, String> KEYS_TO_TITLES;
    public static Map<String, String> DATA_TO_GRAPH;
    //todo
    public static final String[] DRAWER_TITLES = {"Recent Matches", "Upcoming Matches", "Our Schedule", "Starred Matches", "Schedule", "Super Data"};
    public static final String ORIGINAL_ROOT_FIREBASE_PATH = "https://1678-scouting-2016.firebaseio.com/";
    public static String ROOT_FIREBASE_PATH = ORIGINAL_ROOT_FIREBASE_PATH;
    public static String MATCHES_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Matches";
    public static String TEAMS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Teams";
    public static String TEAM_IN_MATCH_DATAS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "TeamInMatchDatas";
}
