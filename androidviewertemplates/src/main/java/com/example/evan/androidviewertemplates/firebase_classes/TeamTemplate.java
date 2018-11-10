
package com.example.evan.androidviewertemplates.firebase_classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Teo on 1/10/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamTemplate extends com.example.evan.androidviewertools.firebase_classes.Team {
    public ArrayList<Boolean> pitDriveTimeOutcome;
    public ArrayList<Float> pitAutoRunTimes;
    public ArrayList<String> totalSuperNotes;
    public Boolean pitCanDoPIDOnDriveTrain;
    public Boolean pitHasEncodersOnBothSides;
    public Boolean canScoreOppositeSwitchAuto;
    public Boolean pitHasGyro;

    public Boolean pitCanCheesecake;
    public Boolean pitDidDemonstrateCheesecakePotential;
    public Boolean pitHasCamera;
    public CalculatedTeamData calculatedData;
    public Float avgNumCubesPlacedAuto;
    public Float avgNumCubesPlacedTele;
    public Float avgPitRampTime;
    public Float didThreeExchangeInputPercentage;
    public Float pitMaxHeight;
    public Integer firstPicklistPosition;
    public Integer numMatchesPlayed;
    public Integer picklistPosition;
    public Integer pitAutoRunTime;
    public Integer pitAvailableWeight;
    public Integer secondPicklistPosition;
    public Map<String, String> pitAllImageUrls; //Todo Delete later.
    public Map<String, String> imageKeys; //todo Delete later.
    public Object pitAllImageURLs;
    public Object pitImageKeys;
    public Object avgPitDriveTime;
    public Object pitDriveOutcome;
    public Object pitDriveTime;
    public Object pitDriveTimes;
    public Object pitRampTime;
    public Object pitRampTimeOutcome;
    public String SEALSNotes;
    public String pitClimberType;
    public String pitDriveTest;
    public String pitDriveTrain;
    public String pitProgrammingLanguage;
    public String pitSEALsNotes;
    public String pitSelectedImage;
    public String pitSelectedImageName;
    public String pitWheelDiameter;
    public Float pitRobotLength;
    public String pitRobotDimensions; //Todo Delete Later
    public Float pitRobotWidth;
}
