package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by evan on 6/18/16.
 */
public class TeamInMatchData extends com.example.evan.androidviewertools.firebase_classes.TeamInMatchData {
    public CalculatedTeamInMatchData calculatedData;
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


    public Map <String,Integer> numPortalIntakeTele;
    public Map <String,Integer> numPyramidIntakeAuto;
    public Map <String,Integer> numPyramidIntakeTele;

    public ArrayList <Integer> numRedPlatformIntakeAuto;
    public ArrayList <Integer> numRedPlatformIntakeTele;

    public Integer numReturnintake;
    public Integer numSpilledCubesAuto;
    public Integer numSpilledCubesTele;
    public Integer rankAgility;
    public Integer rankDefense;
    public Integer rankSpeed;
    public Integer rankStacking;

    /*check*/
    public String startingPosition;
    /*finish check*/

    public Map <String,Object> redSwitchAttempAuto;
    public Map <String, Object> redSwitchAttemptTele;
    public Map <String, Object> scaleAttemptAuto;
    public Map <String, Object> scaleAttemptTele;

}
