package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by evan on 6/18/16.
 */
public class TeamInMatchData extends com.example.evan.androidviewertools.firebase_classes.TeamInMatchData {
    public CalculatedTeamInMatchData calculatedData;

    //public Integer matchNumber;
    public Object scoutName;

    public String superNotes;
    public Boolean didCrossAutoZone;


    public ArrayList<Map<String,Object>> allianceSwitchAttemptAuto;
    public ArrayList<Map<String,Object>> allianceSwitchAttemptTele;

    public ArrayList<Map<String, Map<String, Object>>> climb;

    public Boolean didGetDisabled;
    public Boolean didGetIncapacitated;
    public Boolean didMakeAutoRun;
    public Boolean didPark;
    public Integer numBadDecisions;
    public Integer numGoodDecisions;
    public Integer rankGoodDecision; //todo Delete later.
    public Integer numHumanPortalIntakeTele;

    public Integer rankBadDecision; //todo Delete Later.

    public ArrayList <Integer> numAlliancePlatformIntakeAuto;
    public ArrayList <Integer> numAlliancePlatformIntakeTele;

    public Integer numCubesFumbledAuto;
    public Integer numCubesFumbledTele;
    public Integer numExchangeInput;
    public Integer numGoodDecisisions;
    public Integer numGroundIntakeTele;


    public Integer numGroundPortalIntakeTele;
    public Integer numSpilledCubesAuto;

    public Integer numGroundPyramidIntakeAuto;
    public Integer numGroundPyramidIntakeTele;
    public Integer numElevatedPyramidIntakeTele;
    public Integer numElevatedPyramidIntakeAuto;
    public Integer numGearsFumbledTele;
    public Integer numGroundGearIntakesTele;

    public ArrayList<Boolean> opponentPlatformIntakeAuto;
    public ArrayList<Boolean> alliancePlatformIntakeAuto;
    public ArrayList<Boolean> opponentPlatformIntakeTele;
    public ArrayList<Boolean> alliancePlatformIntakeTele;
    public ArrayList <Integer> numOpponentPlatformIntakeAuto; //todo Delete later.
    public ArrayList <Integer> numOpponentPlatformIntakeTele; //todo Delete Later.

    public Integer numReturnIntake;

    public Integer numSpilledCubesTele;
    public Integer rankAgility;
    public Integer rankDefense;
    public Integer rankSpeed;

    public Object startingPosition; //todo Change Value


    public ArrayList<Map <String,Object>> opponentSwitchAttemptTele;
    public ArrayList<Map <String, Object>> scaleAttemptAuto;
    public ArrayList<Map <String, Object>> scaleAttemptTele;
    public Integer numGoodDecision;
    public Integer numBadDecision;
    public Integer numSpeed;
    public Integer numAgility;
    public Integer numDefense;

}
