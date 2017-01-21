package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by evan on 6/18/16.
 */
public class TeamInMatchData extends com.example.evan.androidviewertools.firebase_classes.TeamInMatchData {
    public Integer teamNumber;
    public Integer matchNumber;
    public CalculatedTeamInMatchData calculatedData;

    public String scoutName;
    public Boolean didLiftoff;
    public Boolean didStartDisabled;
    public Boolean didBecomeIncapacitated;
    public Integer rankSpeed;
    public Integer rankAgility;
    public Integer rankGearControl;
    public Integer rankBallControl;
    public Integer rankDefense;

    //AUTO
    public Map<String, Integer> gearsPlacedByLiftAuto;
    public Integer numHoppersOpenedAuto;
    public Integer numGearsFumbledAuto;
    public Integer numGearsEjectedAuto;
    public Boolean didReachBaselineAuto;
    public Boolean didPotentiallyConflictingAuto;
    public ArrayList<Map<String, Object>> highShotTimesForBoilerAuto;
    public ArrayList<Map<String, Object>> lowShotTimesForBoilerAuto;

    //TELE
    public Map<String, Integer> gearsPlacedByLiftTele;
    public Integer numHoppersOpenedTele;
    public Integer numGearGroundIntakesTele;
    public Integer numGearLoaderIntakesTele;
    public Integer numGearsFumbledTele;
    public Integer numGearsEjectedTele;
    public ArrayList<Map<String, Object>> highShotTimesForBoilerTele;
    public ArrayList<Map<String, Object>> lowShotTimesForBoilerTele;
}
