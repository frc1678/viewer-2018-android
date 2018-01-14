package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by evan on 6/18/16.
 */
public class TeamInMatchData extends com.example.evan.androidviewertools.firebase_classes.TeamInMatchData {
    public CalculatedTeamInMatchData calculatedData;
    public Integer teamNumber;
    public Integer matchNumber;
    public String scoutName;
    public String superNotes;

    public ArrayList<Map<String,Object>> blueSwitchAttemptAuto;
    public ArrayList<Map<String,Object>> blueSwitchAttemptTele;

    public Map<String, Map<String, Object>> climb;

    public Boolean didGetDisabled;
    public Boolean didGetIncapacitated;
    public Boolean didMakeAutoRun;
    public Boolean didPark;
    public Integer numBadDecisions;

    public ArrayList <Integer> numBluePlatformIntakeAuto;
    public ArrayList <Integer> numBluePlatformIntakeTele;

    public Integer numCubesFumbledAuto;
    public Integer numCubesFumbledTele;
    public Integer numExchangeInput;
    public Integer numGoodDecisisions;
    public Integer numGroundIntakeTele;
    public Integer numGroundPortalIntakeTele;
    public Integer HumanPortalIntakeTele;

    public Integer numGroundPyramidIntakeAuto;
    public Integer numGroundPyramidIntakeTele;
    public Integer numElevatedPyramidIntakeAuto;
    public Integer numElevatedPyramidIntakeTele;

    public ArrayList <Integer> numRedPlatformIntakeAuto;
    public ArrayList <Integer> numRedPlatformIntakeTele;

    public Integer numReturnIntake;
    public Integer numSpilledCubesAuto;
    public Integer numSpilledCubesTele;
    public Integer rankAgility;
    public Integer rankDefense;
    public Integer rankSpeed;
    public String startingPosition;
    public Map <String,Object> opponentSwitchAttemptTele;
    public Map <String, Object> scaleAttemptAuto;
    public Map <String, Object> scaleAttemptTele;

}
