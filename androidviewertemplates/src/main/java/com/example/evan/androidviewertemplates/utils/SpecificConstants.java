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
    public static final String[] DRAWER_TITLES = {"Recent Matches", "Upcoming Matches", "Our Schedule", "Starred Matches", "Schedule", "Seeding", "Predicted Seeding", "First Pick", "Super Data"};
    public static final String ORIGINAL_ROOT_FIREBASE_PATH = "https://1678-scouting-2016.firebaseio.com/";
    public static String ROOT_FIREBASE_PATH = ORIGINAL_ROOT_FIREBASE_PATH;
    public static String MATCHES_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Matches";
    public static String TEAMS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Teams";
    public static String TEAM_IN_MATCH_DATAS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "TeamInMatchDatas";

    static {
        Map<String, String> initialKeysToTitlesMap = new HashMap<String, String>() {
            {
                //TeamDetailsSectionAdapter
                    //Auto
                    put ("calculatedData.avgCubesPlacedInScaleAuto", "Avg Cubes in Scale");
                    put ("calculatedData.avgCubesPlacedInSwitchAuto","Avg Cubes in Switch");
                    put ("canScoreBothSwitchSidesAuto","Can Score Both Sides of Switch");
                    put("didMakeAtoRun","Make Auto Run");
                    put ("calculatedData.switchFailPercentageAuto","Percentage Switch Fails");
                    put("calculatedData.scaleFailPercentageAuto","Percentage Scale Fails");

                    //Teleop
                    put ("calculatedData.avgCubesFumbledTele","Avg Cubes Fumbled");
                    put ("calculatedData.avgCubesPlacedInScaleTele","Avg Cubes put in Scale");
                    put ("calculatedData.avgOpponentSwitchCubesTele","Avg Cubes in Blue Switch");
                    put ("calculatedData.avgAllianceSwitchCubesTele","Avg Cubes in Red Switch");
                    put ("calculatedData.avgNumExchangeInputTele","Avg Cubes put in Exchange");
                    put ("calculatedData.avgNumGroundIntakeTele","Avg Ground Intake");
                    put ("calculatedData.avgNumPortalIntake","Avg Portal Intake");
                    put ("calculatedData.switchFailPercentageTele","Percentage Switch Fails");
                    put ("calculatedData.scaleFailPercentageTele","Percentage Scale Fails");

                    //FaceTheBoss
                    put ("calculatedData.climb","Avg Climb Time");
                    put ("climbPercentage","Percentage of Climbing");

                    //Matches
                    put ("matches","Matches");
                    put ("VIEWER.matchesUntilNextMatchForTeam","Matches Until Next Match");
                    put ("numMatchesPlayed","Number of Matches Played");

                    //Status
                    put ("calculatedData.incapacitatedPercentage","Incapacitated");
                    put ("calculatedData.disabledPercentage","Disabled");

                    //HighLevel
                    put ("rank","Rank");
                        //check rank
                    put ("calculatedData.avgDrivingAbility","Driving Ability");

                    //SuperData
                    put ("rankSpeed","Speed");
                    put ("rankAgility","Agility");
                    put ("rankDefense","Defense");
                    put ("calculatedData.avgNumGoodDecisions","Avg Num of Good Decisions");
                    put ("calculatedData.avgNumBadDecisions","Avg Num of Bad Decisions");

                    //PitData
                    put ("pitDriveTrain","Drive Train");
                    put ("pitDidDemonstrateCheesecake","Can Use Cheesecake");
                    put ("pitClimberType","Type of Climb");
                    put ("pitSEALsNotes","SEAL Notes");
                    put ("pitAvailableWeight","Available Weight");
                    put ("pitProgrammingLanguage","Programming Language");

                //TeamInMatchDetailsAdapter
                    //Auto
                    put ("didCrossAutoLine","Did Cross Auto Line");
                    put ("didCrossAutoZone","Can Cross Auto Zone");
                    put ("numCubesFumbledAuto","Number Cubes Fumbled");
                    put ("numCubesPlacedScale","Number Cubes Placed in Scale");
                    put ("numCubesPlacedSwitch","Number Cubes Placed in Scale");

                    //Information
                    put ("number","number");
                    put ("matchNumber","Match Number");

                    //Tele
                    put ("numCubesFumbled","Number of Cubes Failed");
                    put ("numCubesAllianceSwitch","Num Cubes Placed in Alliance Switch");
                    put ("numCubesOpponentSwitch","Num Cubes Placed in Opponent Switch");
                    put ("numCubesScale","Numb Cubes Placed in Scale");
                    put ("numCubesInExchange","Num Cubes Placed in Exchange");
                    put ("numGroundIntake","Num Ground Intake");
                    put ("numHumanIntake","Num Human Intake");

                    //FaceTheBoss
                    /*need help with instances*/

                    //SuperData
                    put ("rankSpeed","Ranked Speed");
                    put ("rankAgility","Ranked Agility");
                    put ("rankDefense","Ranked Defense");
                    put ("superNotes","Super Notes");

                    /*no 'numGoodDecisions' or 'numBadDecisions' */
                    put ("NumGoodDecisions","Num of Good Decisions");
                    put ("NumBadDecisions","Num of Bad Decisions");


//                put("EXAMPLE_DATA_POINT", "EXAMPLE_DATA_TITLE");
            }
        };
        Map<String, String> initialDatasToGraphMap = new HashMap<String, String> () {
            {
                put ("calculatedData.avgNumAlliancePlatformIntakeAuto", "numAlliancePlatformIntakeAuto");
            put ("calculatedData.avgCubesPlacedInScaleAuto","");
            put ("calculatedData.AvgCubesPlacedInSwitchAuto","");
                put ("calculatedData.avgCubesFumbledTele","numCubesFumbledTele");
            put ("calculatedData.avgCubesPlacedInScaleTele","");
            put ("calculatedData.avgOpponentSwitchCubesTele","");
            put ("calculatedData.avgAllianceSwitchCubesTele","");
            put ("calculatedData.avgNumExchangeInputTele","");
                put ("calculatedData.avgNumGroundIntakeTele","numGroundIntakeTele");
                put ("calculatedData.avgNumGroundPortalIntake","numGroundPortalIntakeTele");
                put ("calculatedData.avgNumHumanPortalIntake","numHumanPortalIntakeTele");
            put ("calculatedData.climb","climb");
                put ("calculatedData.avgDrivingAbility","drivingAbility");

                /*Do we have numBad/GoodDecisions*/
                put ("calculatedData.avgNumGoodDecisions","numGoodDecisions");
                put ("calculatedData.avgNumBadDecisions","numBadDecisions");
            }
        };
        //replace all 'DEFENSE's with the correct defenses
        KEYS_TO_TITLES = initialKeysToTitlesMap;
        DATA_TO_GRAPH = initialDatasToGraphMap;
    }
}
