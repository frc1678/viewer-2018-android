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
    public static final String ORIGINAL_ROOT_FIREBASE_PATH = "https://scouting-2018-9023a.firebaseio.com/";
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
                    put ("calculatedData.canScoreBothSwitchSidesAuto","Can Score Both Switches");
                    put ("didMakeAutoRun","Auto Run");
                    put ("calculatedData.switchFailPercentageAuto","Switch Fail Percentage");
                    put("calculatedData.scaleFailPercentageAuto","Scale Fail Percentage");

                    //Teleop
                    put ("calculatedData.avgNumCubesFumbledTele","Avg Cubes Fumbled");
                    put ("calculatedData.avgCubesPlacedInScaleTele","Avg Cubes put in Scale");
                    put ("calculatedData.avgOpponentSwitchCubesTele","Avg Cubes in Blue Switch");
                    put ("calculatedData.avgAllianceSwitchCubesTele","Avg Cubes in Red Switch");
                    put ("calculatedData.avgNumExchangeInputTele","Avg Cubes put in Exchange");
                    put ("calculatedData.avgNumGroundIntakeTele","Avg Ground Intake");
                    put ("calculatedData.avgNumPortalIntakeTele","Avg Portal Intake");
                    put ("calculatedData.switchFailPercentageTele","Percentage Switch Fails");
                    put ("calculatedData.scaleFailPercentageTele","Percentage Scale Fails");

                    //FaceTheBoss
                    put ("calculatedData.climb","Avg Climb Time");
                    put ("climbPercentage","Percentage of Climbing");

                    //Matches
                    put ("currentMatchNum","Matches");
                    put ("VIEWER.matchesUntilNextMatchForTeam","Matches Until Next Match");
                    put ("numMatchesPlayed","Num. Matches Played");

                    //Status
                    put ("calculatedData.incapacitatedPercentage","Incapacitated");
                    put ("calculatedData.disabledPercentage","Disabled");

                    //HighLevel
                    put ("calculatedData.avgDrivingAbility","Avg Driving Ability");

                    //SuperData
                    put ("rankSpeed","Speed");
                    put ("rankAgility","Agility");
                    put ("rankDefense","Defense");
                    put ("calculatedData.totalNumGoodDecisions","Total Num of Good Decisions");
                    put ("calculatedData.totalNumBadDecisions","Total Num of Bad Decisions");
                    put ("superNotes","Super Notes");

                    //PitData
                    put ("pitDriveTrain","Drive Train");
                    put ("pitDidDemonstrateCheesecakePotential","Can Use Cheesecake");
                    put ("pitClimberType","Type of Climb");
                    put ("pitSEALsNotes","SEAL Notes");
                    put ("pitAvailableWeight","Available Weight");
                    put ("pitProgrammingLanguage","Programming Language");

                //TeamInMatchDetailsAdapter
                    //Auto
                    put ("didMakeAutoRun","Did Make Auto Run");
                    put ("didCrossAutoZone","Can Cross Auto Zone");
                    put ("numCubesFumbledAuto","Number Cubes Fumbled");
                    put ("calculatedData.numAllianceSwitchCubeSuccessAuto","Num. Alliance Switch Cubes");
                    put ("caluculatedData.numScaleSuccessAuto","Num Auto Scale Success");

                    //Information
                    put ("number","number");
                    put ("matchNumber","Match Number");

                    //Tele
                    put ("calculatedData.numAllianceSwitchSuccessTele","Num Cubes Placed in Alliance Switch");
                    put ("calculatedData.numOpponentSwitchSuccessTele","Num Cubes Placed in Opponent Switch");
                    put ("calculatedData.numScaleSuccessTele","Scale Success");
                    put ("numExchangeInput","Num Cubes Placed in Exchange");
                    put ("numGroundIntakeTele","Num Ground Intake");
                    put ("numHumanPortalIntakeTele","Num Human Intake");

                    //FaceTheBoss
                    put ("climb","Climb");
                    put ("didPark","Did Park");

                    /*need help with instances*/

                    //SuperData
                    put ("rankSpeed","Ranked Speed");
                    put ("rankAgility","Ranked Agility");
                    put ("rankDefense","Ranked Defense");
                    put ("superNotes","Super Notes");
                    put ("numGoodDecisions","Num of Good Decisions");
                    put ("numBadDecisions","Num of Bad Decisions");


//                put("EXAMPLE_DATA_POINT", "EXAMPLE_DATA_TITLE");
            }
        };
        Map<String, String> initialDatasToGraphMap = new HashMap<String, String> () {
            {
                put ("calculatedData.avgNumAlliancePlatformIntakeAuto", "numAlliancePlatformIntakeAuto");
                put ("calculatedData.avgCubesPlacedInScaleAuto","calculatedData.numScaleSuccessAuto");
                put ("calculatedData.avgCubesPlacedInSwitchAuto","calculatedData.numAllianceSwitchSuccessAuto");
                put ("calculatedData.avgCubesFumbledTele","numCubesFumbledTele");
                put ("calculatedData.avgCubesPlacedInScaleTele","calculatedData.numScaleSuccessTele");
                put ("calculatedData.avgOpponentSwitchCubesTele","calculatedData.numOpponentSwitchSuccessTele");
                put ("calculatedData.avgAllianceSwitchCubesTele","calculatedData.numAllianceSwitchSuccessTele");
                put ("calculatedData.avgNumExchangeInputTele","numExchangeInput");
                put ("calculatedData.avgNumGroundIntakeTele","numGroundIntakeTele");
                put ("calculatedData.avgNumGroundPortalIntake","numGroundPortalIntakeTele");
                put ("calculatedData.avgNumHumanPortalIntake","numHumanPortalIntakeTele");
                put ("calculatedData.climb","calculatedData.climbTime");
                //put ("calculatedData.avgDrivingAbility","drivingAbility");
                put ("calculatedData.avgNumGoodDecisions","numGoodDecisions");
                put ("calculatedData.avgNumBadDecisions","numBadDecisions");
            }
        };
        //replace all 'DEFENSE's with the correct defenses
        KEYS_TO_TITLES = initialKeysToTitlesMap;
        DATA_TO_GRAPH = initialDatasToGraphMap;
    }
}
