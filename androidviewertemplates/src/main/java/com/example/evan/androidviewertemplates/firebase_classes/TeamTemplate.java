package com.example.evan.androidviewertemplates.firebase_classes;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teo on 1/10/2018.
 */
public class TeamTemplate extends com.example.evan.androidviewertools.firebase_classes.Team {
    public CalculatedTeamData calculatedData;
    public Integer numMatchesPlayed;
    public String pitSelectedImageName;
    public String pitSelectedImage;
    public Map <String,String> pitAllImageURLs;
    public Map <String,String> pitAllImageUrls; //Todo Delete later.

    public Integer pitAvailableWeight;
    public String pitDriveTrain;
    public Map<String,String> pitImageKeys;
    public Map<String,String> imageKeys; //todo Delete later.

    public Boolean pitCanCheesecake;
    public Boolean pitDidDemonstrateCheesecakePotential;
    public String pitSEALsNotes;
    public String SEALSNotes;
    public String pitProgrammingLanguage;
    public String pitClimberType;
    public Float pitMaxHeight;
    public Float pitAutoRunTime;
    public ArrayList<String> totalSuperNotes;

    public Integer firstPicklistPosition;
    public Integer secondPicklistPosition;
}

