package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by evan on 6/18/16.
 */
public class TeamInMatchData extends com.example.evan.androidviewertools.firebase_classes.TeamInMatchData {
    public ArrayList <Integer> numAlliancePlatformIntakeAuto;
    public ArrayList <Integer> numAlliancePlatformIntakeTele;
    public ArrayList <Integer> numOpponentPlatformIntakeAuto; //todo Delete later.
    public ArrayList <Integer> numOpponentPlatformIntakeTele; //todo Delete Later.
    public ArrayList<Boolean> alliancePlatformIntakeAuto;
    public ArrayList<Boolean> alliancePlatformIntakeTele;
    public ArrayList<Boolean> opponentPlatformIntakeAuto;
    public ArrayList<Boolean> opponentPlatformIntakeTele;
    public ArrayList<Map <String, Object>> scaleAttemptAuto;
    public ArrayList<Map <String, Object>> scaleAttemptTele;
    public ArrayList<Map <String,Object>> opponentSwitchAttemptTele;
    public ArrayList<Map<String, Map<String, Object>>> climb;
    public ArrayList<Map<String,Object>> allianceSwitchAttemptAuto;
    public ArrayList<Map<String,Object>> allianceSwitchAttemptTele;
    public Boolean didCrossAutoZone;
    public Boolean didGetDisabled;
    public Boolean didGetIncapacitated;
    public Boolean didMakeAutoRun;
    public Boolean didPark;
    public CalculatedTeamInMatchData calculatedData;
    public Integer numAgility;
    public Integer numBadDecision;
    public Integer numBadDecisions;
    public Integer numCubesFumbledAuto;
    public Integer numCubesFumbledTele;
    public Integer numDefense;
    public Integer numElevatedPyramidIntakeAuto;
    public Integer numElevatedPyramidIntakeTele;
    public Integer numExchangeInput;
    public Integer numGearsFumbledTele;
    public Integer numGoodDecision;
    public Integer numGoodDecisions;
    public Integer numGoodDecisisions;
    public Integer numGroundGearIntakesTele;
    public Integer numGroundIntakeTele;
    public Integer numGroundPortalIntakeTele;
    public Integer numGroundPyramidIntakeAuto;
    public Integer numGroundPyramidIntakeTele;
    public Integer numHumanPortalIntakeTele;
    public Integer numReturnIntake;
    public Integer numSpeed;
    public Integer numSpilledCubesAuto;
    public Integer numSpilledCubesTele;
    public Integer rankAgility;
    public Integer rankBadDecision; //todo Delete Later.
    public Integer rankDefense;
    public Integer rankGoodDecision; //todo Delete later.
    public Integer rankSpeed;
    public Object scoutName;
    public Object startingPosition; //todo Change Value
    public Object test; //todo Delete Later
    public String superNotes;
}