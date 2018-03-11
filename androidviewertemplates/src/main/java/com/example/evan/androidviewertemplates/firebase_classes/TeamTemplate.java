package com.example.evan.androidviewertemplates.firebase_classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teo on 1/10/2018.
 */
public class TeamTemplate extends com.example.evan.androidviewertools.firebase_classes.Team {
    public CalculatedTeamData calculatedData;
    public String pitSelectedImageName;
    public String pitSelectedImage;
    public Map <String,String> pitAllImageUrls; //Todo Delete later.
    public Integer picklistPosition;
    public Integer firstPicklistPosition;
    public Integer secondPicklistPosition;


    public Integer pitAvailableWeight;
    public String pitDriveTrain;

    public Map<String,String> imageKeys; //todo Delete later.

    public Boolean pitCanCheesecake;
    public Boolean pitDidDemonstrateCheesecakePotential;
    public String pitSEALsNotes;
    public String SEALSNotes;
    public String pitProgrammingLanguage;
    public String pitClimberType;
    public String pitWheelDiameter;
    public Float pitMaxHeight;
    public ArrayList<Float> pitAutoRunTimes;
    public Integer pitAutoRunTime;
    public ArrayList<String> totalSuperNotes;
    public Boolean canScoreOppositeSwitchAuto;
    public Object pitRampTimeOutcome;
    //public ArrayList<Boolean> pitRampTimeOutcome;
    public Object pitDriveOutcome;
    public Float avgNumCubesPlacedAuto;
    public Float avgNumCubesPlacedTele;
    //public Map<String,Boolean> pitDriveOutcome;
    public Object pitRampTime;
    public Object pitDriveTime;
    public Integer numMatchesPlayed;



    //public ArrayList<Float> pitRampTime;
    //public ArrayList<Float> pitDriveTime;
    public Float didThreeExchangeInputPercentage;
    public Object avgPitDriveTime;
    public Float avgPitRampTime;
    //public ArrayList<Object> pitRampTimes;
    public Object pitDriveTimes;
    public String pitDriveTest;
    public ArrayList<Boolean>  pitDriveTimeOutcome;
    public Boolean pitHasCamera;
    public ArrayList<String>   pitImageKeys;
    public ArrayList<String>   pitAllImageURLs;

}

