package com.example.evan.androidviewertools.utils;

import android.graphics.Color;
import android.util.Log;


import com.example.evan.androidviewertools.LowpassFilterRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String RAW_TEAMS_UPDATED_ACTION = "org.citruscircuits.scout_viewer_2016_android.teamsupdated";
    public static final String TEAMS_UPDATED_ACTION = LowpassFilterRunnable.getFilteredAction(RAW_TEAMS_UPDATED_ACTION);
    public static final String RAW_MATCHES_UPDATED_ACTION = "org.citruscircuits.scout_viewer_2016_android.matchesupdated";
    public static final String MATCHES_UPDATED_ACTION = LowpassFilterRunnable.getFilteredAction(RAW_MATCHES_UPDATED_ACTION);
    public static final String RAW_TEAM_IN_MATCH_DATAS_UPDATED_ACTION = "org.citruscircuits.scout_viewer_2016_android.teaminmatchdatasupdated";
    public static final String TEAM_IN_MATCH_DATAS_UPDATED_ACTION = LowpassFilterRunnable.getFilteredAction(RAW_TEAM_IN_MATCH_DATAS_UPDATED_ACTION);
    public static final String NEW_TEAM_PHOTO_ACTION = "org.citruscircuits.scout_viewer_2016_android.newteamphoto";
    public static final String NEW_MATCH_PLAYED_ACTION = "org.citruscircuits.scout_viewer_2016_android.newmatchplayed";
    public static final String STARS_MODIFIED_ACTION = "org.citruscircuits.scout_viewer_2016_android.starsmodified";
    public static final String[] MATCH_SCOPES = {"Team", "Match"};
    public static final String[] TEAM_SCOPES = {"Team"};
    public static String rankFilterName = "";
    public static final int STAR_COLOR = Color.argb(255, 255, 255, 204);
    public static final int TEAM_NUMBER = 1678;
    /////Ranking Filters////////
    public static boolean sortByTeamNumber = false;
    public static boolean sortByRank = false;
    public static Integer matchNumber;
    ////////////
    public static Integer extraValueOne;
    public static Map<Integer, String> picklistMap = new HashMap<>();
    public static Map<Integer, String> rankedTeamsListByActualSeed = new HashMap<>();
    public static ArrayList<Integer> highlightedTeams = new ArrayList<>();

    public static ArrayList<Integer> unseededTeams = new ArrayList<>();
    public static ArrayList<Integer> mteams = new ArrayList<>();
    public static ArrayList<Integer> seedingTeams = new ArrayList<>();
    public static ArrayList<Integer> onOurAllianceList = new ArrayList<>();
    public static ArrayList<Integer> highlightedMatches = new ArrayList<>();
    public static ArrayList<Integer> onOpponentAllianceList = new ArrayList<>();
    public static ArrayList<Integer> alreadySelectedOnPicklist = new ArrayList<>();
    public static boolean alreadyEnteredPasswordInCurrentSession;
    public static int teamsFromPicklist;
    public static int tempTeamNumber;
    public static int teamRankStringCounter = 1;
    public static boolean sortByFirstPick = false;
    public static boolean sortBySecondPick = false;
    public static boolean lastFourMatches = false;
    public static boolean highlightTeamSchedule = false;
    public static boolean sortByLfm = false;
    public static boolean isInSeedingFragment = false;
    public static final Map<String, String> FIREBASE_KEYS = new HashMap<String, String>() {{
        put("https://1678-dev2-2016.firebaseio.com/", "hL8fStivTbHUXM8A0KXBYPg2cMsl80EcD7vgwJ1u");
        put("https://1678-dev-2016.firebaseio.com/","j1r2wo3RUPMeUZosxwvVSFEFVcrXuuMAGjk6uPOc");
        put("https://1678-dev3-2016.firebaseio.com/", "AEduO6VFlZKD4v10eW81u9j3ZNopr5h2R32SPpeq");
        put("https://1678-scouting-2016.firebaseio.com/", "qVIARBnAD93iykeZSGG8mWOwGegminXUUGF2q0ee");
        put("https://1678-strat-dev-2016.firebaseio.com/", "IMXOxXD3FjOOUoMGJlkAK5pAtn89mGIWAEnaKJhP");
    }};
    //yup, you read that right, those are all of the firebase passwords hard coded in one place
    //lol^

    public static final ArrayList<String> viableLFM = new ArrayList<>(Arrays.asList(
            "calculatedData.lfmAvgNumRobotsLifted",
            "calculatedData.lfmAvgNumAlliancePlatformIntakeAuto",
            "calculatedData.lfmAvgNumAlliancePlatformIntakeTele",
            "calculatedData.lfmAvgNumOpponentPlatformIntakeTele",
            "calculatedData.lfmAvgNumCubesFumbledAuto",
            "calculatedData.lfmAvgNumCubesFumbledTele",
            "calculatedData.lfmAvgNumElevatedPyramidIntakeAuto",
            "calculatedData.lfmAvgNumElevatedPyramidIntakeTele",
            "calculatedData.lfmAvgNumGroundPyramidIntakeAuto",
            "calculatedData.lfmAvgNumGroundPyramidIntakeTele",
            "calculatedData.lfmAvgNumGroundIntakeTele",
            "calculatedData.lfmAvgNumGroundPortalIntakeTele",
            "calculatedData.lfmAvgNumHumanPortalIntakeTele",
            "calculatedData.lfmAvgNumExchangeInputTele",
            "calculatedData.lfmAvgNumReturnIntakeTele",
            "calculatedData.lfmAvgNumGoodDecisions",
            "calculatedData.lfmAvgNumBadDecisions",
            "calculatedData.lfmAvgCubesSpilledAuto",
            "calculatedData.lfmAvgCubesSpilledTele",
            "calculatedData.lfmAvgCubesPlacedInScaleAuto",
            "calculatedData.lfmAvgCubesPlacedInScaleTele",
            "calculatedData.lfmAvgAllianceSwitchCubesAuto",
            "calculatedData.lfmAvgAllianceSwitchCubesTele",
            "calculatedData.lfmAvgOpponentSwitchCubesTele",
            "calculatedData.lfmAvgScaleTimeAuto",
            "calculatedData.lfmAvgScaleTimeTele",
            "calculatedData.lfmAvgAllianceSwitchTimeAuto",
            "calculatedData.lfmAvgAllianceSwitchTimeTele",
            "calculatedData.lfmAvgOpponentSwitchTimeTele",
            "calculatedData.lfmAvgSpeed",
            "calculatedData.lfmAvgDefense",
            "calculatedData.lfmAvgAgility",
            "calculatedData.lfmAvgDrivingAbility"

    ));
}












