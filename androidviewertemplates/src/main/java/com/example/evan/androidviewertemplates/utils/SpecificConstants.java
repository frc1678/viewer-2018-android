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

    public static final String ORIGINAL_ROOT_FIREBASE_PATH = "https://scouting-2018-houston.firebaseio.com/";

    public static String ROOT_FIREBASE_PATH = ORIGINAL_ROOT_FIREBASE_PATH;
    public static String MATCHES_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Matches";
    public static String TEAMS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Teams";
    public static String TEAM_IN_MATCH_DATAS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "TeamInMatchDatas";

    static {
        Map<String, String> initialKeysToTitlesMap = new HashMap<String, String>() {
            {
                //TeamDetailsSectionAdapter
                //Auto
                put("calculatedData.autoRunPercentage", "Auto Run Percent Success");
                put("calculatedData.avgCubesPlacedInScaleAuto", "Avg Cubes in Scale Auto");
                put("calculatedData.avgScaleCycleTimeAuto", "Avg Scale Cycle Time Auto");
                put("calculatedData.avgAllianceSwitchCubesAuto", "Avg Cubes in Switch Auto");
                put("calculatedData.allianceSwitchSuccessPercentageAuto", "Switch Success % Auto");
                put("calculatedData.scaleSuccessPercentageAuto", "Scale Success % Auto");
                put("calculatedData.avgAllianceSwitchCubesAuto", "Avg Cubes In Alliance Switch Auto");
                put("calculatedData.avgAllianceSwitchCycleTimeAuto", "Avg Cycle Time in Alliance Switch Auto");
                put("calculatedData.percentSuccessOppositeSwitchSideAuto", "Opp. Switch Side Success %");
                put("calculatedData.canScoreBothSwitchSidesAuto", "Can score on opp. alliance switch side");

                //Teleop
                put("calculatedData.avgAllVaultTime", "Avg Vault Time");
                put("calculatedData.avgCubesSpilledTele", "Avg Cubes Spilled Tele");
                put("calculatedData.avgNumCubesFumbledTele", "Avg Cubes Fumbled Tele");
                put("calculatedData.avgCubesPlacedInScaleTele", "Avg Cubes in Scale Tele");
                put("calculatedData.avgOpponentSwitchCubesTele", "Avg Cubes in OP Switch Tele");
                put("calculatedData.avgOpponentSwitchCycleTimeTele"," Avg Cycle Time in OP Switch Tele");
                put("calculatedData.avgAllianceSwitchCubesTele", "Avg Cubes in Alliance Switch Tele");
                put("calculatedData.avgAllianceSwitchCycleTimeTele", "Avg Cycle Time in Alliance Switch Tele");
                put("calculatedData.avgNumExchangeInputTele", "Avg Cubes in Exchange Tele");
                put("calculatedData.avgNumGroundPortalIntakeTele", "Avg Ground Portal Intake Tele");
                put("calculatedData.avgNumHumanPortalIntakeTele", "Avg Human Portal Intake Tele");
                put("calculatedData.allianceSwitchSuccessPercentageTele", "Switch Success % Tele");
                put("calculatedData.scaleSuccessPercentageTele", "Scale Success % Tele");
                put("calculatedData.maxScaleCubes", "Max Scale Cubes");
                put("calculatedData.avgScaleCubesBy100s", "Avg Scale Cubes by 100s");
                put("calculatedData.avgScaleCubesBy110s", "Avg Scale Cubes by 110s");
                put("calculatedData.avgScaleCycleTimeTele","Avg Scale Cycle Time Tele");
                put("calculatedData.avgTotalCubesPlaced", "Avg Total Cubes Placed");
                put("calculatedData.avgExchangeCycleTime", "Avg Exchange Cycle Time");


                //FaceTheBoss
                put("calculatedData.avgClimbTime", "Avg Climb Time");
                put("calculatedData.predictedClimb", "Predicted Climb %");
                put("calculatedData.soloClimbPercentage", "Solo Climb %");
                put("calculatedData.assistedClimbPercentage", "Assisted Climb %");
                put("calculatedData.activeLiftClimbPercentage", "Active Lift Climb %");
                put("calculatedData.activeNoClimbLiftClimbPercentage", "Active No Climb Lift %");
                put("calculatedData.activeAssistClimbPercentage", "Active Assist Climb %");
                put("calculatedData.climbPercentage", "Climb %");


                //Matches
                put("matches", "Matches");
                put("VIEWER.matchesUntilNextMatchForTeam", "Matches Until Next Match");
                put("calculatedData.numMatchesPlayed", "Num. Matches Played");
                put("lfm", "Last Four Matches");

                //Status
                put("calculatedData.incapacitatedPercentage", "Incapacitated");
                put("calculatedData.disabledPercentage", "Disabled");

                //HighLevel
                put("calculatedData.avgDrivingAbility", "Avg Driving Ability");


                //SuperData
                put("calculatedData.avgSpeed", "Speed");
                put("calculatedData.avgAgility", "Agility");
                put("calculatedData.avgDefense", "Defense");
                put("calculatedData.totalNumGoodDecisions", "Good Decisions");
                put("calculatedData.totalNumBadDecisions", "Bad Decisions");
                put("totalSuperNotes", "Super Notes");

                //PitData
                put("pitDriveTrain", "Drive Train");
                put("pitCanCheesecake", "Can Use Cheesecake");
                put("pitClimberType", "Type of Climb");
                put("pitSEALsNotes", "SEAL Notes");
                put("pitAvailableWeight", "Available Weight");
                put("pitProgrammingLanguage", "Programming Language");
                put("pitRampTime", "Ramp Time");
                put("pitDriveTimeOutcome", "Drive Time Outcome");
                put("pitHasCamera", "Has a Camera");
                put("pitRobotDimensions", "Robot Dimensions");
                put("pitCanDoPIDOnDriveTrain", "Can Do PID On Drive Train");
                put("pitHasEncodersOnBothSides", "Has Encoders On Both Sides");
                put("pitHasGyro", "Has Gyro");

                //TeamInMatchDetailsAdapter
                //Auto
                put("didMakeAutoRun", "Did Make Auto Run");
                put("numCubesFumbledAuto", "Number Cubes Fumbled Auto");
                put("calculatedData.numAllianceSwitchSuccessAuto", "Num. Alliance Switch Cubes Auto");
                put("calculatedData.allianceSwitchCycleTimeAuto", "Switch Cycle Time for Alliance Auto");
                put("calculatedData.numScaleSuccessAuto", "Num. Auto Scale Success");
                put("calculatedData.scaleCycleTimeAuto","Scale Cycle Time Auto");
                put("teamNumber", "Number");
                put("matchNumber", "Match Number");
                put("calculatedData.canScoreOppositeSwitchAuto", "Can score on opp. alliance switch side");

                //Tele
                put("calculatedData.numAllianceSwitchSuccessTele", "Num. Alli. Switch Success Tele");
                put("calculatedData.allianceSwitchCycleTimeTele", "Switch Cycle Time for Alliance Tele");
                put("calculatedData.numOpponentSwitchSuccessTele", "Num. Opp. Switch Success Tele");
                put("calculatedData.opponentSwitchCycleTimeTele", "Switch Cycle Time for OP Tele");
                put("calculatedData.numScaleSuccessTele", "Scale Success Tele");
                put("calculatedData.scaleCycleTimeTele","Scale Cycle Time Tele");
                put("numExchangeInput", "Num Cubes Placed in Exchange Tele");
                put("calculatedData.exchangeCycleTime","Exchange Cycle Time");
                put("numGroundIntakeTele", "Num Ground Intake Tele");
                put("numHumanPortalIntakeTele", "Num Human Portal Intake Tele");
                put("calculatedData.totalCubesPlaced", "Total Cubes Placed");

                //FaceTheBoss
                put("climb", "Climb");
                put("didPark", "Did Park");

                //SuperData
                put("rankSpeed", "Speed");
                put("rankAgility", "Agility");
                put("rankDefense", "Defense");
                put("superNotes", "Super Notes");
                put("numGoodDecisions", "Num. Good Decisions");
                put("numBadDecisions", "Num. Bad Decisions");
                put("fullcomp", "Full Comp. Data");

                //LastFourMatches
                //Auto
                put("calculatedData.lfmAvgClimbTime", "Avg. Climb Time");
                put("calculatedData.lfmAutoRunPercentage", "Run Percentage");

                //Teleop
                put("calculatedData.lfmMaxExchangeCubes", "Max Exchange Cubes");
                put("calculatedData.lfmMaxScaleCubes", "Max Scale Cubes");
                put("calculatedData.lfmAvgCubesInExchangeTele", "Avg Cubes in Exchange");
                put("calculatedData.lfmAvgNumCubesFumbledTele", "Avg Cubes Fumbled");
                put("calculatedData.lfmAvgCubesPlacedInScaleTele", "Avg Cubes Placed in Scale");
                put("calculatedData.lfmAvgAllianceSwitchCubesTele", "Avg Switch Cubes");
                put("calculatedData.lfmAvgNumExchangeInputTele", "Avg Exchange Input");
                put("calculatedData.lfmAvgScaleCubesBy100s", "Avg Scale Cubes by 100s");
                put("calculatedData.lfmAvgScaleCubesBy110s", "Avg Scale Cubes by 110s");
                put("calculatedData.lfmAvgTotalCubesPlaced", "Avg Total Cubes Placed");
                put("calculatedData.lfmAvgOpponentSwitchCubesTele", "Avg Opp. Switch Cubes");

                //FaceTheBoss
                put("calculatedData.lfmSoloClimbPercentage", "Solo Climb %");
                put("calculatedData.lfmAssistedClimbPercentage", "Assisted Climb %");
                put("calculatedData.lfmActiveLiftClimbPercentage", "Active Lift Climb %");
                put("calculatedData.lfmActiveNoClimbLiftClimbPercentage", "Active No Lift Climb %");
                put("calculatedData.lfmActiveAssistClimbPercentage", "Active Assist Climb %");
                put("calculatedData.lfmAvgClimbTime", "Avg Climb Time %");

                //Status
                put("calculatedData.lfmIncapacitatedPercentage", "Incap. %");
                put("calculatedData.lfmDisabledPercentage", "Disabled %");

                //HighLevel
                put("calculatedData.lfmAvgDrivingAbility", "Avg Driving Ability");


//                put("EXAMPLE_DATA_POINT", "EXAMPLE_DATA_TITLE");
            }
        };
        Map<String, String> initialDatasToGraphMap = new HashMap<String, String>() {
            {
                put("calculatedData.avgOpponentSwitchCubesTele", "calculatedData.numOpponentSwitchSuccessTele");
                put("calculatedData.avgAllVaultTime", "calculatedData.avgVaultTime");
                put("calculatedData.avgCubesSpilledTele", "numSpilledCubesTele");
                put("calculatedData.avgCubesPlacedInScaleAuto", "calculatedData.numScaleSuccessAuto");
                put("calculatedData.avgAllianceSwitchCubesAuto", "calculatedData.numAllianceSwitchSuccessAuto");
                put("calculatedData.avgNumCubesFumbledTele", "numCubesFumbledTele");
                put("calculatedData.avgCubesPlacedInScaleTele", "calculatedData.numScaleSuccessTele");
                put("calculatedData.avgAllianceSwitchCubesTele", "calculatedData.numAllianceSwitchSuccessTele");
                put("calculatedData.avgNumExchangeInputTele", "numExchangeInput");
                put("calculatedData.avgNumGroundIntakeTele", "numGroundIntakeTele");
                put("calculatedData.avgNumGroundPortalIntakeTele", "numGroundPortalIntakeTele");
                put("calculatedData.avgNumHumanPortalIntakeTele", "numHumanPortalIntakeTele");
                put("calculatedData.climb", "calculatedData.climbTime");
                put("calculatedData.avgClimbTime", "calculatedData.climbTime");
                put("calculatedData.avgNumGoodDecisions", "numGoodDecisions");
                put("calculatedData.avgNumBadDecisions", "numBadDecisions");
                put("calculatedData.autoRunPercentage", "didMakeAutoRun");
                put("calculatedData.avgScaleCubesBy100s", "calculatedData.numCubesScaleAt100s");
                put("calculatedData.avgScaleCubesBy110s", "calculatedData.numCubesScaleAt110s");
                put("calculatedData.avgTotalCubesPlaced", "calculatedData.totalCubesPlaced");
                put("calculatedData.avgAllianceSwitchCycleTimeAuto", "Avg Cycle Time in Alliance Switch Auto");
                put("calculatedData.avgAllianceSwitchCycleTimeTele", "Avg Cycle Time in Alliance Switch Tele");
                put("calculatedData.avgOpponentSwitchCycleTimeTele"," Avg Cycle Time in OP Switch Tele");
                put("calculatedData.avgScaleCycleTimeAuto", "Avg Scale Cycle Time Auto");
                put("calculatedData.avgScaleCycleTimeTele", "Avg Scale Cycle Time Tele");
                put("calculatedData.avgExchangeCycleTime", "Avg Exchange Cycle Time");



                //LFM Graphing:
                put("calculatedData.lfmAvgNumExchangeInputTele", "calculatedData.numExchangeInputTele");
                put("calculatedData.lfmAutoRunPercentage","didMakeAutoRun");
                put("calculatedData.lfmAvgTotalCubesPlaced", "calculatedData.totalCubesPlaced");
                put("calculatedData.lfmAvgClimbTime", "calculatedData.climbtime");
                put("calculatedData.lfmAvgNumCubesFumbledTele", "calculatedData.numCubesFumbledTele");
                put("calculatedData.lfmAvgCubesPlacedInScaleTele", "calculatedData.numScaleSuccessTele");
                put("calculatedData.lfmAvgAllianceSwitchCubesTele", "calculatedData.numAllianceSwitchSuccessTele");
                put("calculatedData.lfmAvgClimbTime", "calculatedData.climbTime");
                put("calculatedData.lfmIncapacitatedPercentage", "calculatedData.didGetIncapacitated");
                put("calculatedData.lfmDisabledPercentage", "calculatedData.didGetDisabled");
                put("calculatedData.lfmAvgScaleCubesBy100s", "calculatedData.lfmnumCubesScaleAt100s");
                put("calculatedData.lfmAvgScaleCubesBy110s", "calculatedData.lfmnumCubesScaleAt110s");
                put("calculatedData.lfmAvgOpponentSwitchCubesTele", "calculatedData.numOpponentSwitchSuccessTele");

            }
        };
        //replace all 'DEFENSE's with the correct defenses
        KEYS_TO_TITLES = initialKeysToTitlesMap;
        DATA_TO_GRAPH = initialDatasToGraphMap;
    }
}
