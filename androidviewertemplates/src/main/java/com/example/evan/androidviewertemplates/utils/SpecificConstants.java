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
    public static final String[] DRAWER_TITLES = {"Recent Matches", "Upcoming Matches", "Our Schedule", "Starred Matches", "Schedule", "Seeding", "Predicted Seeding", "First Pick", "Second Pick", "Super Data", "Last Four Matches", "Live Picklist"};

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
                    put ("calculatedData.autoRunPercentage", "Auto Run Percent Success");
                    put ("calculatedData.avgCubesPlacedInScaleAuto", "Avg Cubes in Scale Auto");
                    put ("calculatedData.avgAllianceSwitchCubesAuto","Avg Cubes in Switch Auto");
                    put ("calculatedData.canScoreBothSwitchSidesAuto","Can Score Both Switches Auto");
                    put ("autoRunPercentage","Did Make Auto Run");
                    put ("calculatedData.allianceSwitchFailPercentageAuto","Switch Fail Percentage Auto");
                    put ("calculatedData.scaleFailPercentageAuto","Scale Fail Percentage Auto");
                    put ("calculatedData.avgAllianceSwitchCubesAuto","Avg Cubes In Alliance Switch Auto");
                    put ("calculatedData.percentSuccessOppositeSwitchSideAuto","Opp. Switch Side Success %");

                    //Teleop
                    put ("calculatedData.avgCubesSpilledTele","Avg Cubes Spilled Tele");
                    put ("calculatedData.avgNumCubesFumbledTele","Avg Cubes Fumbled Tele");
                    put ("calculatedData.avgCubesPlacedInScaleTele","Avg Cubes in Scale Tele");
                    put ("calculatedData.avgOpponentSwitchCubesTele","Avg Cubes in OP Switch Tele");
                    put ("calculatedData.avgAllianceSwitchCubesTele","Avg Cubes in Alliance Switch Tele");
                    put ("calculatedData.avgNumExchangeInputTele","Avg Cubes in Exchange Tele");
                    put ("calculatedData.avgNumGroundPortalIntakeTele","Avg Ground Portal Intake Tele");
                    put ("calculatedData.avgNumHumanPortalIntakeTele","Avg Human Portal Intake Tele");
                    put ("calculatedData.allianceSwitchFailPercentageTele","Percentage Switch Fails Tele");
                    put ("calculatedData.scaleFailPercentageTele","Percentage Scale Fail Tele");
                    put ("calculatedData.maxScaleCubes","Max Scale Cubes");
                    put ("calculatedData.totalCubesPlaced", "Total Cubes Placed");

                    //FaceTheBoss
                    put ("calculatedData.avgClimbTime","Avg Climb Time");
                    put ("calculatedData.predictedClimb","Predicted Climb Percentage");
                    put ("calculatedData.soloClimbPercentage", "Solo Climb %");
                    put ("calculatedData.assistedClimbPercentage", "Assisted Climb %");
                    put ("calculatedData.activeLiftClimbPercentage", "Active Lift Climb %");
                    put ("calculatedData.activeNoClimbLiftClimbPercentage", "Active No Climb Lift %");
                    put ("calculatedData.activeAssistClimbPercentage", "Active Assist Climb %");


                    //Matches
                    put ("matches","Matches");
                    put ("VIEWER.matchesUntilNextMatchForTeam","Matches Until Next Match");
                    put ("calculatedData.numMatchesPlayed","Num. Matches Played");

                    //Status
                    put ("calculatedData.incapacitatedPercentage","Incapacitated");
                    put ("calculatedData.disabledPercentage","Disabled");

                    //HighLevel
                    put ("calculatedData.avgDrivingAbility","Avg Driving Ability");


                    //SuperData
                    put ("calculatedData.avgSpeed","Speed");
                    put ("calculatedData.avgAgility","Agility");
                    put ("calculatedData.avgDefense","Defense");
                    put ("calculatedData.totalNumGoodDecisions","Good Decisions");
                    put ("calculatedData.totalNumBadDecisions","Bad Decisions");
                    put ("totalSuperNotes","Super Notes");

                    //PitData
                    put ("pitDriveTrain","Drive Train");
                    put ("pitCanCheesecake","Can Use Cheesecake");
                    put ("pitClimberType","Type of Climb");
                    put ("pitSEALsNotes","SEAL Notes");
                    put ("pitAvailableWeight","Available Weight");
                    put ("pitProgrammingLanguage","Programming Language");
                    put ("pitRampTime","Ramp Time");
                    put ("pitDriveTimeOutcome","Drive Time Outcome");
                    put ("pitHasCamera","Has a Camera");

                //TeamInMatchDetailsAdapter
                    //Auto
                    put ("didMakeAutoRun","Did Make Auto Run");
                    put ("calculatedData.canScoreOppositeSwitchAuto","Score Opposite Switch");
                    put ("numCubesFumbledAuto","Number Cubes Fumbled Auto");
                    put ("calculatedData.numAllianceSwitchSuccessAuto","Num. Alliance Switch Cubes Auto");
                    put ("calculatedData.numScaleSuccessAuto","Num. Auto Scale Success");

                    put ("teamNumber","Number");
                    put ("matchNumber","Match Number");

                    //Tele
                    put ("calculatedData.numAllianceSwitchSuccessTele","Num Cubes Placed in Alliance Switch Tele");
                    put ("calculatedData.numOpponentSwitchSuccessTele","Num Cubes Placed in Opponent Switch Tele");
                    put ("calculatedData.numScaleSuccessTele","Scale Success Tele");
                    put ("numExchangeInput","Num Cubes Placed in Exchange Tele");
                    put ("numGroundIntakeTele","Num Ground Intake Tele");
                    put ("numHumanPortalIntakeTele","Num Human Portal Intake Tele");



                    //FaceTheBoss
                    put ("climb","Climb");
                    put ("didPark","Did Park");

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
                put ("calculatedData.avgCubesPlacedInScaleAuto","calculatedData.numScaleSuccessAuto");
                put ("calculatedData.avgAllianceSwitchCubesAuto","calculatedData.numAllianceSwitchSuccessAuto");
                put ("calculatedData.avgNumCubesFumbledTele","numCubesFumbledTele");
                put ("calculatedData.avgCubesPlacedInScaleTele","calculatedData.numScaleSuccessTele");
                put ("calculatedData.avgOpponentSwitchCubesTele","calculatedData.numOpponentSwitchSuccessTele");
                put ("calculatedData.avgAllianceSwitchCubesTele","calculatedData.numAllianceSwitchSuccessTele");
                put ("calculatedData.avgNumExchangeInputTele","numExchangeInput");
                put ("calculatedData.avgNumGroundIntakeTele","numGroundIntakeTele");
                put ("calculatedData.avgNumGroundPortalIntakeTele","numGroundPortalIntakeTele");
                put ("calculatedData.avgNumHumanPortalIntakeTele","numHumanPortalIntakeTele");
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
