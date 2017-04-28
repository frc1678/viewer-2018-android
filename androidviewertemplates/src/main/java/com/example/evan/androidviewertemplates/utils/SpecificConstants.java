package com.example.evan.androidviewertemplates.utils;

import com.example.evan.androidviewertools.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class SpecificConstants extends Constants {
    //todo
    public static Map<String, String> KEYS_TO_TITLES;
    public static Map<String, String> DATA_TO_GRAPH;
    //todo
    public static final String[] DRAWER_TITLES = {"Recent Matches", "Upcoming Matches", "Our Schedule", "Starred Matches", "Schedule", "Seeding", "Predicted Seeding", "First Pick", "All Rotors Rank", "Super Data", "Last Four Matches"};
    public static final String ORIGINAL_ROOT_FIREBASE_PATH = "https://1678-scouting-2016.firebaseio.com/";
    public static String ROOT_FIREBASE_PATH = ORIGINAL_ROOT_FIREBASE_PATH;
    public static String MATCHES_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Matches";
    public static String TEAMS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Teams";
    public static String TEAM_IN_MATCH_DATAS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "TeamInMatchDatas";

    static {
        Map<String, String> initialKeysToTitlesMap = new HashMap<String, String>() {
            {
                //MATCH DETAILS ACTIVITY
                //this is a change for newSeasonTemplate
                put("calculatedData.actualSeed", "Seed");
                put("calculatedData.disfunctionalPercentage", "Disfunctional");
                put("calculatedData.avgGearGroundIntakesTele", "Avg. Gears G.P.");
                //TEAM DETAILS SECTION ADAPTER
                    //MATCHES
                put("matches", "Matches");
                put("VIEWER.matchesUntilNextMatchForTeam", "Matches Until Next Match");
                    //STATUS
                put("calculatedData.disabledPercentage", "Disabled");
                put("calculatedData.incapacitatedPercentage", "Incapacitated");
                    //AUTO
                put("calculatedData.avgGearsPlacedAuto", "Avg Gears Auto");
                put("calculatedData.avgHighShotsAuto", "Avg High Shots");
                put("calculatedData.avgLowShotsAuto", "Avg Low Shots");
                put("calculatedData.baselineReachedPercentage", "Baseline Reached Percentage");
                put("calculatedData.avgGearLoaderIntakesAuto", "Avg Gears Loader Intakes");

                    //TELEOP
                put("calculatedData.avgGearsPlacedTele", "Avg Gears Tele");
                put("calculatedData.avgHighShotsTele", "Avg High Shots");
                put("calculatedData.avgLowShotsTele", "Avg Low Shots");
                put("calculatedData.avgKeyShotTime", "Avg Key Shot Time(Sec.)");
                put("calculatedData.avgGearLoaderIntakesTele", "Avg Gears Loader Intakes");
                put("calculatedData.gearAbility", "Gear Ability");
                    //LIFTOFF
                put("calculatedData.liftoffPercentage", "Liftoff Percentage");
                    //HIGH LEVEL
                put("calculatedData.firstPickAbility", "First Pick Ability");
                put("calculatedData.overallSecondPickAbility", "Second Pick Ability");
                put("calculatedData.avgLiftoffTime", "Avg. Liftoff Time");
                put("calculatedData.thirdPickAbility", "Third Pick Ability");
                put("calculatedData.allRotorsAbility", "4 Rotors Ability");
                put("calculatedData.avgDrivingAbility", "Driving Ability");
                    //SUPER DATA
                put("superNotes", "Super Notes");
                put("calculatedData.avgAgility", "Agility");
                put("calculatedData.avgSpeed", "Speed");
                put("calculatedData.avgBallControl", "Ball Control");
                put("calculatedData.avgGearControl", "Gear Control");
                put("calculatedData.avgDefense", "Defense");
                    //PIT DATA
                put("pitSelectedImageURL", "Selected Image Url");
                put("pitNotes", "Notes");
                put("pitProgrammingLanguage", "Programming Language");
                put("pitAvailableWeight", "Available Weight");
                put("pitOrganization", "Pit Organization");
                put("pitDidDemonstrateCheesecakePotential", "Cheesecake");
                put("pitDriveTrain", "Drive Train");
                //TEAM IN MATCH DETAILS SECTION ADAPTER
                    //Information
                put("teamNumber", "TeamTemplate Number");
                put("matchNumber", "Match Number");
                    //Auto
                put("calculatedData.numGearsPlacedAuto", "Gears Placed");
                put("numHoppersOpenedAuto", "Num. Hoppers Opened");
                put("numGearsFumbledAuto", "Gears Fumbled");
                put("numGearsEjectedAuto", "Gears Ejected");
                put("didReachBaselineAuto", "Reach Baseline?");
                    //Tele
                put("calculatedData.numGearsPlacedTele", "Gears Placed");
                put("numHoppersOpenedTele", "Num. Hoppers Opened");
                put("numGearGroundIntakesTele", "Gear Ground Intakes");
                put("numGearLoaderIntakesTele", "Gear Loader Intakes");
                put("numGearsFumbledTele", "Gears Fumbled");
                put("numGearsEjectedTele", "Gears Ejected");
                    //Liftoff
                put("didLiftoff", "Liftoff?");
                    //Super
                put("rankSpeed", "Speed");
                put("rankAgility", "Agility");
                put("rankGearControl", "Gear Control");
                put("rankBallControl", "Ball Control");
                put("rankDefense", "Defense");
                //Last Four Matches
                put("calculatedData.lfmDisabledPercentage", "Disabled");
                put("calculatedData.lfmIncapacitatedPercentage", "Incapacitated");
                put("calculatedData.lfmAvgGearsPlacedAuto", "Avg. Gears Placed");
                put("calculatedData.lfmAvgHighShotsAuto", "Avg. High Shots");
                put("calculatedData.lfmAvgLowShotsAuto", "Avg. Low Shots");
                put("calculatedData.lfmAvgGearsPlacedTele", "Avg. Gears Placed");
                put("calculatedData.lfmAvgGearLoaderIntakesTele", "Avg. Gear Loader Intakes");
                put("calculatedData.lfmAvgHighShotsTele", "Avg. High Shots");
                put("calculatedData.lfmAvgLowShotsTele", "Avg. Low Shots");
                put("calculatedData.lfmAvgKeyShotTime", "Avg. Key Shot Time");
                put("calculatedData.lfmAvgLiftoffTime", "Avg. Liftoff Time");
                put("calculatedData.lfmLiftoffPercentage", "Avg. Liftoff Percentage");
                put("calculatedData.firstPickAbility", "First Pick");
                put("calculatedData.lfmAvgAgility", "Agility");
                put("calculatedData.lfmAvgSpeed", "Speed");
                put("calculatedData.lfmAvgBallControl", "Ball Control");
                put("calculatedData.lfmAvgGearControl", "Gear Control");
                put("calculatedData.lfmAvgDefense", "Defense");
            }
        };
        Map<String, String> initialDatasToGraphMap = new HashMap<String, String> () {
            {
                put("calculatedData.avgGearsPlacedAuto", "calculatedData.numGearsPlacedAuto");
                put("calculatedData.avgHighShotsAuto", "calculatedData.numHighShotsAuto");
                put("calculatedData.avgLowShotsAuto", "calculatedData.numLowShotsAuto");
                put("calculatedData.avgGearsPlacedTele", "calculatedData.numGearsPlacedTele");
                put("calculatedData.avgGearLoaderIntakesTele", "numGearLoaderIntakesTele");
                put("calculatedData.avgHighShotsTele", "calculatedData.numHighShotsTele");
                put("calculatedData.avgLowShotsTele", "calculatedData.numLowShotsTele");
                put("calculatedData.avgKeyShotTime", "calculatedData.avgKeyShotTime");
                put("calculatedData.liftoffPercentage", "didLiftoff");
                put("calculatedData.disabledPercentage", "didStartDisabled");
                put("calculatedData.incapacitatedPercentage", "didBecomeIncapacitated");
                put("calculatedData.baselineReachedPercentage", "didReachBaselineAuto");
                put("calculatedData.avgAgility", "rankAgility");
                put("calculatedData.avgSpeed", "rankSpeed");
                put("calculatedData.avgBallControl", "rankBallControl");
                put("calculatedData.avgGearControl", "rankGearControl");
                put("calculatedData.avgDefense", "rankDefense");
                put("calculatedData.gearAbility", "calculatedData.gearAbility");
                put("calculatedData.avgLiftoffTime", "liftoffTime");

                //Last Four Matches
                put("calculatedData.lfmDisabledPercentage", "didStartDisabled");
                put("calculatedData.lfmIncapacitatedPercentage", "didBecomeIncapacitated");
                put("calculatedData.lfmAvgGearsPlacedAuto", "calculatedData.numGearsPlacedAuto");
                put("calculatedData.lfmAvgHighShotsAuto", "calculatedData.numHighShotsAuto");
                put("calculatedData.lfmAvgLowShotsAuto", "calculatedData.numLowShotsAuto");
                put("calculatedData.lfmAvgGearsPlacedTele", "calculatedData.numGearsPlacedTele");
                put("calculatedData.lfmAvgGearLoaderIntakesTele", "numGearLoaderIntakesTele");
                put("calculatedData.lfmAvgHighShotsTele", "calculatedData.numHighShotsTele");
                put("calculatedData.lfmAvgLowShotsTele", "calculatedData.numLowShotsTele");
                put("calculatedData.lfmAvgKeyShotTime", "calculatedData.avgKeyShotTime");
                put("calculatedData.lfmAvgLiftoffTime", "liftoffTime");
                put("calculatedData.lfmLiftoffPercentage", "didLiftoff");
                put("calculatedData.lfmAvgAgility", "rankAgility");
                put("calculatedData.lfmAvgSpeed", "rankSpeed");
                put("calculatedData.lfmAvgBallControl", "rankBallControl");
                put("calculatedData.lfmAvgGearControl", "rankGearControl");
                put("calculatedData.lfmAvgDefense", "rankDefense");
            }
        };
        //replace all 'DEFENSE's with the correct defenses
        KEYS_TO_TITLES = initialKeysToTitlesMap;
        DATA_TO_GRAPH = initialDatasToGraphMap;
    }
}
